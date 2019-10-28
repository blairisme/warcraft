/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.power;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.action.spell.SpellAction;
import com.evilbird.warcraft.item.common.spell.Spell;
import com.evilbird.warcraft.item.effect.EffectType;
import com.evilbird.warcraft.item.layer.fog.Fog;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.Alignment.Center;
import static com.evilbird.engine.item.utility.ItemPredicates.withType;
import static com.evilbird.warcraft.item.layer.LayerType.OpaqueFog;

/**
 * A spell that reveals a portions of the map for a period of time.
 *
 * @author Blair Butterworth
 */
public class HolyVisionSpell extends SpellAction
{
    @Inject
    public HolyVisionSpell(ItemFactory factory) {
        super(Spell.HolyVision, EffectType.Spell, factory);
    }

    @Override
    protected void initialize() {
        super.initialize();

        Item spellCaster = getItem();
        ItemRoot world = spellCaster.getRoot();

        Item target = getTarget();
        Vector2 location = target.getPosition(Center);

        Fog fog = (Fog)world.find(withType(OpaqueFog));
        fog.revealLocations(location, Vector2.Zero, Spell.HolyVision.getValue());
    }
}
