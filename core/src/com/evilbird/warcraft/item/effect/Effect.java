/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.effect;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.item.specialized.Viewable;
import com.evilbird.engine.item.specialized.ViewableStyle;

/**
 * A game object that represents an effect, a visual entity that conveys the
 * consequences of an action undertaken by a game object or the user. For
 * example, an explosion resulting from a siege unit attacking a building.
 *
 * @author Blair Butterworth
 */
public class Effect extends Viewable
{
    /**
     * Constructs a new instance of this class given a {@link Skin} containing
     * a {@link ViewableStyle}, specifying the visual and auditory presentation
     * of the effect.
     *
     * @param skin a {@code Skin} instance.
     */
    public Effect(Skin skin) {
        super(skin);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        super.draw(batch, alpha);
    }
}
