/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ai.attack;

import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.utility.ItemOperations;
import com.evilbird.warcraft.action.common.create.CreateEvent;
import com.evilbird.warcraft.action.common.remove.RemoveEvent;
import com.evilbird.warcraft.action.move.MoveEvent;
import com.evilbird.warcraft.item.common.capability.OffensiveCapability;
import com.evilbird.warcraft.item.common.capability.OffensiveObject;
import com.evilbird.warcraft.item.common.capability.PerishableObject;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.unit.UnitType;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.evilbird.engine.common.collection.CollectionUtils.filter;
import static com.evilbird.warcraft.item.common.capability.OffensivePlurality.Individual;
import static com.evilbird.warcraft.item.common.query.UnitOperations.isAnotherTeam;
import static com.evilbird.warcraft.item.common.query.UnitOperations.isNeutral;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

/**
 * Instances of this class are used to obtain attackable targets within range
 * of a given attacker, and vice versa.
 *
 * @author Blair Butterworth
 */
public class AttackController
{
    private Events events;
    private AttackGraph graph;
    private Map<Identifier, List<OffensiveObject>> attackers;
    private Map<Identifier, List<PerishableObject>> targets;

    /**
     * Creates a new instance of this class given an {@link Events} instance,
     * used to monitor pertinent game phenomena, and an {@link AttackGraph}
     * used to determine potential targets.
     */
    public AttackController(Events events, AttackGraph graph) {
        this.events = events;
        this.graph = graph;
        this.targets = new HashMap<>();
        this.attackers = new HashMap<>();
    }

    /**
     * Returns a collection of targets the given attacker can attack at the
     * current time. The targets all belong to an opposing team and are within
     * the attackers capability to attack.
     */
    public Collection<PerishableObject> getTargets(OffensiveObject attacker) {
        List<PerishableObject> potentialTargets = targets.getOrDefault(attacker.getIdentifier(), emptyList());
        potentialTargets.removeIf(UnitOperations::isDead);

        if (attacker.getAttackPlurality() == Individual) {
            return !potentialTargets.isEmpty() ? singletonList(potentialTargets.get(0)) : emptyList();
        }
        return potentialTargets;
    }

    /**
     * Returns a collection of attackers that are within range or the given
     * target and are not currently attacking another target, or have the
     * ability to attack multiple targets at the same time.
     */
    public Collection<OffensiveObject> getAttackers(PerishableObject target) {
        List<OffensiveObject> potentialAttackers = attackers.getOrDefault(target.getIdentifier(), emptyList());
        return CollectionUtils.filter(potentialAttackers, this::isIdleAttacker);
    }

    /**
     * Initializes the attack controller using the given state, which is used
     * to lookup attackers and potential targets.
     */
    public void initialize(ItemRoot state) {
        for (Item attacker: state.findAll(OffensiveObject.class::isInstance)) {
            addAttacker((OffensiveObject)attacker);
        }
        for (Item target: state.findAll(PerishableObject.class::isInstance)) {
            addTarget((PerishableObject) target);
        }
    }

    /**
     * Updates the attack controller, using the events system to maintain an
     * accurate list of attackers and potential targets.
     */
    public void update() {
        evaluateMovedObjects();
        evaluateCreatedObjects();
        evaluateRemovedObjects();
    }

    private void evaluateCreatedObjects() {
        for (CreateEvent event: events.getEvents(CreateEvent.class)) {
            Item subject = event.getSubject();

            if (subject instanceof OffensiveObject) {
                addAttacker((OffensiveObject)subject);
            }
            if (subject instanceof PerishableObject) {
                addTarget((PerishableObject)subject);
            }
        }
    }

    private void evaluateMovedObjects() {
        for (MoveEvent event: events.getEvents(MoveEvent.class)) {
            Item subject = event.getSubject();

            if (subject instanceof OffensiveObject) {
                updateAttacker((OffensiveObject)subject);
            }
            if (subject instanceof PerishableObject) {
                updateTarget((PerishableObject)subject);
            }
        }
    }

    private void evaluateRemovedObjects() {
        for (RemoveEvent event: events.getEvents(RemoveEvent.class)) {
            Item subject = event.getSubject();

            if (subject instanceof OffensiveObject) {
                removeAttacker((OffensiveObject)subject);
            }
            if (subject instanceof PerishableObject) {
                removeTarget((PerishableObject)subject);
            }
        }
    }

    private void addAttacker(OffensiveObject attacker) {
        List<PerishableObject> potentials = graph.getTargets(attacker);
        List<PerishableObject> targets = filter(potentials, target -> isAttackable(attacker, target));
        this.targets.put(attacker.getIdentifier(), targets);
    }

    private void addTarget(PerishableObject target) {
        List<OffensiveObject> potentials = graph.getAttackers(target);
        List<OffensiveObject> attackers = filter(potentials, attacker -> isAttackable(attacker, target));
        this.attackers.put(target.getIdentifier(), attackers);
    }

    private void removeAttacker(OffensiveObject attacker) {
        attackers.remove(attacker.getIdentifier());
    }

    private void removeTarget(PerishableObject target) {
        targets.remove(target.getIdentifier());
    }

    private void updateAttacker(OffensiveObject attacker) {
        removeAttacker(attacker);
        addAttacker(attacker);
    }

    private void updateTarget(PerishableObject target) {
        removeTarget(target);
        addTarget(target);
    }

    private boolean isAttackable(OffensiveObject attacker, PerishableObject target) {
        return isAttackableTarget(attacker, target)
            && isAttackableTeam(attacker, target);
    }

    private boolean isAttackableTarget(OffensiveObject attacker, PerishableObject target) {
        Identifier type = target.getType();
        if (type instanceof UnitType) {
            return isAttackableTarget(attacker.getAttackCapability(), (UnitType)type);
        }
        return false;
    }

    private boolean isAttackableTarget(OffensiveCapability capability, UnitType target) {
        switch (capability) {
            case Air: return true;
            case Water: return target.isNaval();
            case Proximity: return !target.isFlying() && !target.isSubmarine();
            default: return false;
        }
    }

    private boolean isAttackableTeam(OffensiveObject attacker, PerishableObject target) {
        Player attackingPlayer = UnitOperations.getPlayer(attacker);
        Player targetPlayer = UnitOperations.getPlayer(target);
        return isAnotherTeam(attackingPlayer, targetPlayer) && !isNeutral(targetPlayer);
    }

    private boolean isIdleAttacker(OffensiveObject attacker) {
        return attacker.getAttackPlurality() != Individual || ItemOperations.isIdle(attacker);
    }
}
