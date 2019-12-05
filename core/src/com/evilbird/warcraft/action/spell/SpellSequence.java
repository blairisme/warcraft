/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.StateTransitionAction;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.utility.GameObjectOperations;
import com.evilbird.warcraft.action.move.MoveAction;
import com.evilbird.warcraft.object.common.spell.Spell;
import com.evilbird.warcraft.object.unit.combatant.spellcaster.SpellCaster;

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
        return nextAction(caster, target);
    }

    private Action nextAction(GameObject caster, GameObject target) {
        if (!inCastingRange(caster, target)) {
            return moveAction;
        }
        if (isCastingSpell(caster)) {
            return spellAction;
        }
        return null;
    }

    private boolean inCastingRange(GameObject caster, GameObject target) {
        Spell spell = spellAction.getSpell();
        return GameObjectOperations.isNear(caster, spell.getCastRange(), target);
    }

    private boolean isCastingSpell(GameObject caster) {
        SpellCaster spellCaster = (SpellCaster)caster;
        return spellCaster.getCastingSpell() != null;
    }
}
