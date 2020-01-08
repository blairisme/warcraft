/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.spell;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.StateTransitionAction;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.utility.GameObjectOperations;
import com.evilbird.warcraft.action.move.MoveAction;
import com.evilbird.warcraft.data.spell.Spell;

/**
 * An {@link Action} implementation that moves a spell caster in range of its
 * target before casting a spell.
 *
 * @author Blair Butterworth
 */
public class SpellSequence extends StateTransitionAction
{
    private SpellAction spellAction;
    private MoveAction moveAction;

    public SpellSequence(SpellAction spellAction, MoveAction moveAction) {
        super(spellAction, moveAction);
        this.spellAction = spellAction;
        this.moveAction = moveAction;
    }

    @Override
    protected Action nextAction(Action previous) {
        GameObject caster = getSubject();
        GameObject target = getTarget();
        return nextAction(previous, caster, target);
    }

    private Action nextAction(Action previous, GameObject caster, GameObject target) {
        if (!inCastingRange(caster, target)) {
            return moveAction;
        }
        if (previous != spellAction) {
            return spellAction;
        }
        return null;
    }

    private boolean inCastingRange(GameObject caster, GameObject target) {
        Spell spell = spellAction.getSpell();
        return GameObjectOperations.isNear(caster, spell.getCastRange(), target);
    }
}
