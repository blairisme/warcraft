/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.projectile;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.item.specialized.Viewable;
import com.evilbird.warcraft.item.common.state.MovableObject;
import com.evilbird.warcraft.item.common.state.MovementCapability;

/**
 * Represents an object that can be propelled towards an enemy to cause damage.
 *
 * @author Blair Butterworth
 */
public class Projectile extends Viewable implements MovableObject
{
    private static final transient int PROJECTILE_SPEED = 500;

    public Projectile(Skin skin) {
        super(skin);
    }

    @Override
    public int getMovementSpeed() {
        return PROJECTILE_SPEED;
    }

    @Override
    public MovementCapability getMovementCapability() {
        return MovementCapability.Air;
    }
}
