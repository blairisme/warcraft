/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.aoe;

import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.warcraft.action.spell.SpellAction;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.common.spell.Spell;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.effect.EffectType;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.conjured.ConjuredObject;

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
        EffectType castEffect,
        UnitType aoeType,
        GameObjectFactory factory,
        AoeSpellCancel cancelAction)
    {
        super(spell, castEffect, factory);
        this.aoeType = aoeType;
        this.cancelAction = cancelAction;
    }

    @Override
    protected void initialize() {
        super.initialize();

        GameObject caster = getSubject();
        GameObject selector = getTarget();

        ConjuredObject aoe = (ConjuredObject)factory.get(aoeType);
        aoe.setPosition(selector.getPosition(Alignment.Center), Alignment.Center);

        Player player = UnitOperations.getPlayer(caster);
        player.addObject(aoe);

        cancelAction.setSubject(caster);
        cancelAction.setTarget(aoe);
        aoe.addAction(cancelAction, spell.getEffectDuration());
    }
}
