/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.attack;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.action.attack.AttackActions;
import com.evilbird.engine.behaviour.framework.task.ActionTaskSet;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

import static com.evilbird.engine.object.utility.GameObjectOperations.hasAction;

/**
 * A task that assigns an attack action to the attacker contained in the
 * tasks blackboard, targeting one or many targets.
 *
 * @author Blair Butterworth
 */
public class AttackTask extends ActionTaskSet<AttackData>
{
    @Inject
    public AttackTask(ActionFactory factory) {
        super(factory);
    }

    @Override
    protected Collection<Action> getActions(ActionFactory factory) {
        AttackData data = getObject();
        OffensiveObject attacker = data.getAttacker();
        Collection<GameObject> targets = data.getTargets();
        Collection<Action> actions = new ArrayList<>(targets.size());

        for (GameObject target: targets) {
            if (!hasAction(attacker, actionClassifier(attacker, target))) {
                actions.add(getAction(factory, attacker, target));
            }
        }
        return actions;
    }

    private Action getAction(ActionFactory factory, OffensiveObject attacker, GameObject target) {
        Action action = factory.get(AttackActions.Attack);
        action.setSubject(attacker);
        action.setTarget(target);

        attacker.removeActions();
        attacker.addAction(action);
        return action;
    }

    private Predicate<Action> actionClassifier(OffensiveObject attacker, GameObject target) {
        switch (attacker.getAttackPlurality()) {
            case Individual: return individualAttack(attacker);
            case Multiple: return multipleAttack(target);
            default: throw new UnsupportedOperationException();
        }
    }

    private Predicate<Action> individualAttack(OffensiveObject attacker) {
        Identifier identifier = attacker.getIdentifier();
        return action -> action.getIdentifier() == identifier;
    }

    private Predicate<Action> multipleAttack(GameObject target) {
        Identifier identifier = target.getIdentifier();
        return action -> action.getTarget().getIdentifier() == identifier;
    }
}
