/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.spell.novel;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.warcraft.action.spell.SpellAction;
import com.evilbird.warcraft.data.spell.Spell;
import com.evilbird.warcraft.object.effect.EffectType;
import com.evilbird.warcraft.object.layer.fog.Fog;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.Alignment.Center;
import static com.evilbird.engine.object.utility.GameObjectPredicates.withType;
import static com.evilbird.warcraft.object.layer.LayerType.OpaqueFog;

/**
 * A spell that reveals a portions of the map for a period of time.
 *
 * @author Blair Butterworth
 */
public class HolyVisionSpell extends SpellAction
{
    @Inject
    public HolyVisionSpell(GameObjectFactory factory) {
        super(Spell.HolyVision, EffectType.Spell, factory);
    }

    @Override
    protected void initialize() {
        super.initialize();

        GameObject spellCaster = getSubject();
        GameObjectContainer world = spellCaster.getRoot();

        GameObject target = getTarget();
        Vector2 location = target.getPosition(Center);

        Fog fog = (Fog)world.find(withType(OpaqueFog));
        //fog.revealLocations(location, Vector2.Zero, (int)Spell.HolyVision.getEffectValue());
    }
}
