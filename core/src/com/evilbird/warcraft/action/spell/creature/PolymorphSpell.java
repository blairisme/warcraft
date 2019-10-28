/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.creature;

import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.warcraft.action.common.exclusion.ItemExclusion;
import com.evilbird.warcraft.action.spell.SpellAction;
import com.evilbird.warcraft.item.common.spell.Spell;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.effect.EffectType;
import com.evilbird.warcraft.item.unit.Unit;

import javax.inject.Inject;

import static com.evilbird.warcraft.item.common.query.UnitOperations.getPlayer;
import static com.evilbird.warcraft.item.unit.UnitType.Sheep;

/**
 * A spell that transforms a given target into a critter, rendering it unable
 * to be controlled for a period of time.
 *
 * @author Blair Butterworth
 */
public class PolymorphSpell extends SpellAction
{
    private ItemExclusion exclusion;

    @Inject
    public PolymorphSpell(ItemFactory factory, ItemExclusion exclusion) {
        super(Spell.Polymorph, EffectType.Spell, factory);
        this.exclusion = exclusion;
    }

    @Override
    protected void initialize() {
        super.initialize();

        Unit target = (Unit)getTarget();
        exclusion.disable(target);

        Item sheep = factory.get(Sheep);
        sheep.setPosition(target.getPosition());

        Item caster = getItem();
        Player player = getPlayer(caster);
        player.addItem(sheep);
    }
}
