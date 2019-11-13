/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.conjured.decay;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.audio.sound.SoundCatalog;
import com.evilbird.engine.common.graphics.AnimationCatalog;
import com.evilbird.warcraft.object.unit.conjured.ConjuredAnimations;
import com.evilbird.warcraft.object.unit.conjured.ConjuredAssets;
import com.evilbird.warcraft.object.unit.conjured.ConjuredBuilder;
import com.evilbird.warcraft.object.unit.conjured.ConjuredObject;
import com.evilbird.warcraft.object.unit.conjured.ConjuredSounds;

/**
 * Creates a new Death and Decay game object whose visual and audible
 * presentation is defined by the given {@link ConjuredAssets}.
 *
 * @author Blair Butterworth
 */
public class DeathAndDecayBuilder extends ConjuredBuilder
{
    private static final GridPoint2 SIZE = new GridPoint2(32, 32);

    public DeathAndDecayBuilder(ConjuredAssets assets) {
        super(assets);
    }

    @Override
    protected ConjuredObject newObject(Skin skin) {
        return new DeathAndDecay(skin);
    }

    @Override
    protected AnimationCatalog newAnimations(ConjuredAssets assets) {
        return new ConjuredAnimations(assets.getDeathAndDecay(), SIZE);
    }

    @Override
    protected SoundCatalog newSounds(ConjuredAssets assets) {
        return new ConjuredSounds(assets);
    }
}