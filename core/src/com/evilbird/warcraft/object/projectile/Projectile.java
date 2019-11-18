/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.projectile;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.object.AnimatedObject;
import com.evilbird.warcraft.object.common.capability.MovableObject;
import com.evilbird.warcraft.object.common.capability.MovementCapability;
import com.evilbird.warcraft.object.effect.EffectType;

/**
 * Represents an object that can be propelled towards an enemy to cause damage.
 *
 * @author Blair Butterworth
 */
public class Projectile extends AnimatedObject implements MovableObject
{
    private static final transient int MOVEMENT_SPEED = 500;
    private static final transient MovementCapability MOVEMENT_CAPABILITY = MovementCapability.Air;

    private transient EffectType explosionEffect;

    public Projectile(Skin skin) {
        super(skin);
    }

    public boolean isExplodingProjectile() {
        return explosionEffect != null;
    }

    public EffectType getExplosionEffect() {
        return explosionEffect;
    }

    @Override
    public int getMovementSpeed() {
        return MOVEMENT_SPEED;
    }

    @Override
    public MovementCapability getMovementCapability() {
        return MOVEMENT_CAPABILITY;
    }

    public void setExplosionEffect(EffectType explosionEffect) {
        this.explosionEffect = explosionEffect;
    }
}
