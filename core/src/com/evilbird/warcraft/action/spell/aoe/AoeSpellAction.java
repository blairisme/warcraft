/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.aoe;

import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.warcraft.action.spell.SpellAction;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.common.spell.Spell;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.effect.EffectType;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.conjured.ConjuredObject;

/**
 * A spell that creates a conjured area of effect object.
 *
 * @author Blair Butterworth
 */
public class AoeSpellAction extends SpellAction
{
    private UnitType aoeType;
    private AoeSpellCancel cancelAction;

    public AoeSpellAction(
        Spell spell,
        EffectType effect,
        UnitType aoeType,
        ItemFactory factory,
        AoeSpellCancel cancelAction)
    {
        super(spell, effect, factory);
        this.aoeType = aoeType;
        this.cancelAction = cancelAction;
    }

    @Override
    protected void initialize() {
        super.initialize();
        addAoeEffect();
    }

    protected ConjuredObject addAoeEffect() {
        Item caster = getItem();
        Player player = UnitOperations.getPlayer(caster);

        ConjuredObject aoe = (ConjuredObject)factory.get(aoeType);
        player.addItem(aoe);

        cancelAction.setItem(caster);
        cancelAction.setTarget(aoe);
        aoe.addAction(cancelAction, spell.getEffectDuration());

        return aoe;
    }
}
