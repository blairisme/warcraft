/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.conjured.whirlwind;

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
 * Creates a new Whirlwind game object whose visual and audible
 * presentation is defined by the given {@link ConjuredAssets}.
 *
 * @author Blair Butterworth
 */
public class WhirlwindBuilder extends ConjuredBuilder
{
    private static final GridPoint2 SIZE = new GridPoint2(56, 56);

    public WhirlwindBuilder(ConjuredAssets assets) {
        super(assets);
    }

    @Override
    protected ConjuredObject newObject(Skin skin) {
        return new Whirlwind(skin);
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
