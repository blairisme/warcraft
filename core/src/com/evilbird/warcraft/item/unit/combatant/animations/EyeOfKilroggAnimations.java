/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.graphics.AnimationCatalog;
import com.evilbird.warcraft.item.unit.combatant.CombatantAssets;

import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Move;

/**
 * Defines Eye of Kilrogg animations as laid out in Eye of Kilrogg texture
 * atlas files.
 *
 * @author Blair Butterworth
 */
public class EyeOfKilroggAnimations extends AnimationCatalog
{
    private static final GridPoint2 SIZE = new GridPoint2(32, 32);

    public EyeOfKilroggAnimations(CombatantAssets assets) {
        this(assets.getBaseTexture());
    }

    public EyeOfKilroggAnimations(Texture texture) {
        super(2);

        alias(Move, Idle);
        animation(Idle)
            .withTexture(texture)
            .withSequence(0, 1)
            .withSize(SIZE)
            .withInterval(10f)
            .notLooping();
    }
}
