/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.attack;

import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.warcraft.action.spell.SpellAction;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.common.spell.Spell;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.effect.EffectType;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.conjured.ConjuredObject;

import javax.inject.Inject;

/**
 * A spell that surrounds a target with a flame shield, degrading the health of
 * any game objects adjacent to it. Flame shield is temporary effect that wears
 * off after a period of time.
 *
 * @author Blair Butterworth
 */
public class FlameShieldSpell extends SpellAction
{
    private FlameShieldCancel cancelAction;

    @Inject
    public FlameShieldSpell(ItemFactory factory, FlameShieldCancel cancelAction) {
        super(Spell.FlameShield, EffectType.Spell, factory);
        this.cancelAction = cancelAction;
    }

    @Override
    protected void initialize() {
        super.initialize();
        addEffect();
    }

    private void addEffect() {
        Item caster = getItem();
        Item selector = getTarget();

        ConjuredObject flameShield = (ConjuredObject)factory.get(UnitType.FlameShield);
        flameShield.setPosition(selector.getPosition(Alignment.Center), Alignment.Center);

        Player player = UnitOperations.getPlayer(caster);
        player.addItem(flameShield);

        cancelAction.setItem(caster);
        cancelAction.setTarget(flameShield);
        flameShield.addAction(cancelAction, spell.getEffectDuration());
    }
}
