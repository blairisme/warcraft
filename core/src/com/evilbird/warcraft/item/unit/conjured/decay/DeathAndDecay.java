/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.conjured.decay;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.item.specialized.ViewableStyle;
import com.evilbird.warcraft.item.unit.conjured.ConjuredObject;

/**
 * A game object that represents the death and decay area of effect game object.
 *
 * @author Blair Butterworth
 */
public class DeathAndDecay extends ConjuredObject
{
    /**
     * Constructs a new instance of this class given a {@link Skin} containing
     * a {@link ViewableStyle}, specifying the visual and auditory presentation
     * of the Death and Decay object.
     */
    public DeathAndDecay(Skin skin) {
        super(skin);
    }


}
