/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.conjured.rune;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.audio.sound.SoundCatalog;
import com.evilbird.engine.common.graphics.AnimationCatalog;
import com.evilbird.warcraft.item.unit.conjured.ConjuredAnimations;
import com.evilbird.warcraft.item.unit.conjured.ConjuredAssets;
import com.evilbird.warcraft.item.unit.conjured.ConjuredBuilder;
import com.evilbird.warcraft.item.unit.conjured.ConjuredObject;
import com.evilbird.warcraft.item.unit.conjured.ConjuredSounds;

/**
 * Creates a new Rune Trap game object whose visual and audible
 * presentation is defined by the given {@link ConjuredAssets}.
 *
 * @author Blair Butterworth
 */
public class RuneTrapBuilder extends ConjuredBuilder
{
    public RuneTrapBuilder(ConjuredAssets assets) {
        super(assets);
    }

    @Override
    protected ConjuredObject newObject(Skin skin) {
        return new RuneTrap(skin);
    }

    @Override
    protected AnimationCatalog newAnimations(ConjuredAssets assets) {
        return new ConjuredAnimations(assets.getRune());
    }

    @Override
    protected SoundCatalog newSounds(ConjuredAssets assets) {
        return new ConjuredSounds(assets);
    }
}
