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
import com.evilbird.engine.object.AnimatedObjectStyle;
import com.evilbird.warcraft.object.effect.EffectType;

/**
 * A {@link Projectile} specialization that produces one or many explosions
 * when the projectile strikes its target.
 *
 * @author Blair Butterworth
 */
public class ExplosiveProjectile extends Projectile
{
    private transient int count;
    private transient int radius;
    private transient float interval;
    private transient EffectType effect;
    private transient ExplosivePattern pattern;

    /**
     * Constructs a new instance of this class given a {@link Skin} describing
     * its visual and auditory presentation.
     *
     * @param skin  a {@link Skin} instance containing, amongst others, a
     *              {@link AnimatedObjectStyle}.
     */
    public ExplosiveProjectile(Skin skin) {
        super(skin);
    }

    /**
     * Returns the number of explosions that will be produces when the
     * projectile strikes a target.
     */
    public int getExplosiveCount() {
        return count;
    }

    /**
     * Returns the {@link EffectType effect} used to display an explosion that
     * will be produced when the projectile strikes a target.
     */
    public EffectType getExplosiveEffect() {
        return effect;
    }

    /**
     * Returns the interval between explosions that will be produced when the
     * projectile strikes a target. An interval is only relevant if the
     * explosive count is more than one.
     */
    public float getExplosiveInterval() {
        return interval;
    }

    /**
     * Returns the {@link ExplosivePattern pattern} that any explosions
     * produced when the projectile strikes a target will take. A pattern is
     * only relevant if the explosive count is more than one.
     */
    public ExplosivePattern getExplosivePattern() {
        return pattern;
    }

    /**
     * Returns the destructive reach of the explosion that will be produced
     * when the projectile strikes a target, specified in pixels.
     */
    public int getExplosiveRadius() {
        return radius;
    }

    /**
     * Sets the number of explosions that will be produces when the projectile
     * strikes a target.
     */
    public void setExplosiveCount(int count) {
        this.count = count;
    }

    /**
     * Sets the {@link EffectType effect} used to display an explosion that
     * will be produced when the projectile strikes a target.
     */
    public void setExplosiveEffect(EffectType effect) {
        this.effect = effect;
    }

    /**
     * Sets interval between explosions that will be produced when the
     * projectile strikes a target. An interval is only relevant if the
     * explosive count is more than one.
     */
    public void setExplosiveInterval(float interval) {
        this.interval = interval;
    }

    /**
     * Sets the {@link ExplosivePattern pattern} that any explosions produced
     * when the projectile strikes a target will take. A pattern is only
     * relevant if the explosive count is more than one.
     */
    public void setExplosivePattern(ExplosivePattern pattern) {
        this.pattern = pattern;
    }

    /**
     * Sets the destructive reach of the explosion that will be produced when
     * the projectile strikes a target, specified in pixels.
     */
    public void setExplosiveRadius(int radius) {
        this.radius = radius;
    }
}
