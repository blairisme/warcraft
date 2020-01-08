/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.spellcaster.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.warcraft.object.unit.combatant.CombatantAssets;
import com.evilbird.warcraft.object.unit.combatant.melee.MeleeUnitAnimations;

import static com.evilbird.warcraft.object.unit.UnitAnimation.CastSpell;

/**
 * Defines a catalog of spell caster animations as laid out in assault texture
 * atlas files, used for Human Paladins and Orcish Ogre Mages.
 *
 * @author Blair Butterworth
 */
public class AssaultCasterAnimations extends MeleeUnitAnimations
{
    /**
     * Creates a new instance of this class given a {@link CombatantAssets}
     * bundle, containing the {@link CombatantAssets#getBaseTexture() base} and
     * {@link CombatantAssets#getDecomposeTexture()} decomposing} textures
     * required by assault spell caster animations. Animations contained in
     * this catalog will use a default unit size of 72 x 72.
     *
     * @param assets an asset bundle, which must contain a valid base and
     *               decompose texture.
     *
     * @throws NullPointerException if the given asset bundle is {@code null}.
     */
    public AssaultCasterAnimations(CombatantAssets assets) {
        super(assets);
        castSpell(assets.getBaseTexture(), SIZE);
    }

    private void castSpell(Texture base, GridPoint2 size) {
        animation(CastSpell)
            .withTexture(base)
            .withSequence(size.y * 5, 4)
            .withSequence(0, 1)
            .withSize(size)
            .withInterval(0.15f)
            .notLooping();
    }
}
