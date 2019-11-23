/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.conjured.whirlwind;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.audio.sound.SoundCatalog;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.object.unit.conjured.ConjuredAnimations;
import com.evilbird.warcraft.object.unit.conjured.ConjuredAreaSounds;
import com.evilbird.warcraft.object.unit.conjured.ConjuredAssets;
import com.evilbird.warcraft.object.unit.conjured.ConjuredBuilder;
import com.evilbird.warcraft.object.unit.conjured.ConjuredObject;

/**
 * Creates a new Whirlwind game object whose visual and audible
 * presentation is defined by the given {@link ConjuredAssets}.
 *
 * @author Blair Butterworth
 */
public class WhirlwindBuilder extends ConjuredBuilder
{
    private static final GridPoint2 SIZE = new GridPoint2(56, 56);

    private WarcraftPreferences preferences;

    public WhirlwindBuilder(ConjuredAssets assets, WarcraftPreferences preferences) {
        super(assets);
        this.preferences = preferences;
    }

    @Override
    protected ConjuredObject newObject(Skin skin) {
        return new Whirlwind(skin, preferences);
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
