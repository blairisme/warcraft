/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
    private transient int range;
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
     * Returns the destructive reach of the explosion(s) produced when the
     * explosive projectile strikes its target(s), specified in world pixels.
     */
    public int getExplosiveRadius() {
        return radius;
    }

    /**
     * Returns the destructive reach of the explosion, specified in world
     * pixels. The use of explosive reach varies depending on the projectiles
     * explosive pattern. Some patterns do not support an explosive range, such
     * as {@link ExplosivePattern#Point}.
     */
    public int getExplosiveRange() {
        return range;
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
     * Sets the destructive reach of the explosion(s) produced when the
     * explosive projectile strikes its target(s), specified in world pixels.
     */
    public void setExplosiveRadius(int radius) {
        this.radius = radius;
    }

    /**
     * Sets the destructive reach of the explosion, specified in world
     * pixels. The use of explosive reach varies depending on the projectiles
     * explosive pattern. Some patterns do not support an explosive range, such
     * as {@link ExplosivePattern#Point}.
     */
    public void setExplosiveRange(int range) {
        this.range = range;
    }


}
