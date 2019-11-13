/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.conjured.fireball;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.object.AnimatedObjectStyle;
import com.evilbird.warcraft.object.unit.conjured.ConjuredObject;

/**
 * A game object that represents a fireball, a spell cast by human mage that
 * emanates from the caster and moves toward a user selected target, damaging
 * all in its path.
 *
 * @author Blair Butterworth
 */
public class Fireball extends ConjuredObject
{
    private Vector2 destination;

    /**
     * Constructs a new instance of this class given a {@link Skin} containing
     * a {@link AnimatedObjectStyle}, specifying the visual and auditory presentation
     * of the fireball object.
     */
    public Fireball(Skin skin) {
        super(skin);
    }
}
