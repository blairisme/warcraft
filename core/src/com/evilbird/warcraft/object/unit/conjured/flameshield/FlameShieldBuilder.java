/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.conjured.flameshield;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.audio.sound.SoundCatalog;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.warcraft.object.unit.conjured.ConjuredAnimations;
import com.evilbird.warcraft.object.unit.conjured.ConjuredAreaSounds;
import com.evilbird.warcraft.object.unit.conjured.ConjuredAssets;
import com.evilbird.warcraft.object.unit.conjured.ConjuredBuilder;
import com.evilbird.warcraft.object.unit.conjured.ConjuredObject;

/**
 * Creates a new Flame Shield game object whose visual and audible
 * presentation is defined by the given {@link ConjuredAssets}.
 *
 * @author Blair Butterworth
 */
public class FlameShieldBuilder extends ConjuredBuilder
{
    private static final GridPoint2 SIZE = new GridPoint2(32, 32);

    public FlameShieldBuilder(ConjuredAssets assets) {
        super(assets);
    }

    @Override
    protected ConjuredObject newObject(Skin skin) {
        return new FlameShield(skin);
    }

    @Override
    protected AnimationCatalog newAnimations(ConjuredAssets assets) {
        return new ConjuredAnimations(assets, SIZE);
    }

    @Override
    protected SoundCatalog newSounds(ConjuredAssets assets) {
        return new ConjuredAreaSounds(assets);
    }
}
