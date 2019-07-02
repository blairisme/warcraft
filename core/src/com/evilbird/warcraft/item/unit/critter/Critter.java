/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.critter;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.warcraft.item.unit.MovableUnit;

/**
 * Represents a critter, an animal that inhabits the game world. Critters can
 * move and be attacked, but cannot attack themselves.
 *
 * @author Blair Butterworth
 */
public class Critter extends MovableUnit
{
    public Critter(Skin skin) {
        super(skin);
    }
}
