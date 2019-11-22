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
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.action.attack.AttackEvent;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;
import com.evilbird.warcraft.object.common.capability.PerishableObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.evilbird.engine.common.collection.CollectionUtils.filter;
import static com.evilbird.engine.common.collection.CollectionUtils.findFirst;
import static com.evilbird.warcraft.behaviour.ai.attack.AttackStatus.isValidTarget;
import static com.evilbird.warcraft.object.common.capability.OffensivePlurality.Multiple;
import static java.util.Collections.singletonList;

/**
 * Instances of this class are used to obtain attackable targets within range
 * of a given attacker, and vice versa.
 *
 * @author Blair Butterworth
 */
public class AttackController
{
    private static final float COOLDOWN = 1f;

    private Events events;
    private AttackGraph graph;
    private Map<Identifier, GameTimer> cooldown;

    /**
     * Creates a new instance of this class given an {@link Events} instance,
     * used to monitor pertinent game phenomena, and an {@link AttackGraph}
     * used to determine potential targets.
     */
    public AttackController(Events events, AttackGraph graph) {
        this.events = events;
        this.graph = graph;
        this.cooldown = new HashMap<>();
    }

    /**
     * Returns a collection of targets the given attacker can attack at the
     * current time. The targets will belong to an opposing team and will be
     * within the attackers capability to attack.
     */
    public List<PerishableObject> getTargets(OffensiveObject attacker) {
        Identifier attackerId = attacker.getIdentifier();
        if (cooldown.containsKey(attackerId)) {
            return Collections.emptyList();
        }
        if (attacker.getAttackPlurality() == Multiple) {
            return getMultipleTargets(attacker);
        }
        return getSingleTarget(attacker);
    }

    private List<PerishableObject> getMultipleTargets(OffensiveObject attacker) {
        List<PerishableObject> potentials = graph.getTargets(attacker);
        return filter(potentials, target -> isValidTarget(attacker, target));
    }

    private List<PerishableObject> getSingleTarget(OffensiveObject attacker) {
        List<PerishableObject> potentials = graph.getTargets(attacker);
        PerishableObject result = findFirst(potentials, target -> isValidTarget(attacker, target));
        return result != null ? singletonList(result) : Collections.emptyList();
    }

    /**
     * Returns a collection of attackers that are within range or the given
     * target and are not currently attacking another target, or have the
     * ability to attack multiple targets at the same time.
     */
    public List<OffensiveObject> getAttackers(PerishableObject target) {
        List<OffensiveObject> potentials = graph.getAttackers(target);
        List<OffensiveObject> attackers = filter(potentials, attacker -> isValidTarget(attacker, target));
        return CollectionUtils.filter(attackers, AttackStatus::isValidAttacker);
    }

    /**
     * Adds an attack delay to any attackers whose attack operation has just
     * failed.
     */
    public void update(float time) {
        CollectionUtils.removeIf(cooldown.entrySet(), it -> it.getValue().advance(time));
        for (AttackEvent event: events.getEvents(AttackEvent.class)) {
            if (event.isFailed()) {
                GameObject attacker = event.getSubject();
                cooldown.put(attacker.getIdentifier(), new GameTimer(COOLDOWN));
            }
        }
    }
}
