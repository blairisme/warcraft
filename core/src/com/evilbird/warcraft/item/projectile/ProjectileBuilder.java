/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.projectile;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Pool;
import com.evilbird.engine.common.graphics.Animation;
import com.evilbird.engine.common.graphics.AnimationSchema;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.specialized.AnimatedItemStyle;
import com.evilbird.warcraft.item.common.animation.AnimationLayouts;
import com.evilbird.warcraft.item.common.animation.AnimationSetBuilder;

import java.util.Collections;
import java.util.Map;

import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;

/**
 * Creates a new {@link Projectile} instance whose visual and audible
 * presentation is defined by the given {@link ProjectileAssets}.
 *
 * @author Blair Butterworth
 */
public class ProjectileBuilder
{
    private Pool<Projectile> pool;
    private ProjectileAssets assets;

    public ProjectileBuilder(ProjectileAssets assets, Pool<Projectile> pool) {
        this.assets = assets;
        this.pool = pool;
    }

    public Projectile build(ProjectileType type) {
        Projectile projectile = pool.obtain();
        projectile.setType(type);
        projectile.setSkin(getSkin(type));
        projectile.setSize(getSize(type));
        projectile.setAnimation(Idle);
        projectile.setTouchable(Touchable.disabled);
        return projectile;
    }

    private Skin getSkin(ProjectileType type) {
        Skin skin = new Skin();
        skin.add("default", getStyle(type));
        return skin;
    }

    private AnimatedItemStyle getStyle(ProjectileType type) {
        AnimatedItemStyle style = new AnimatedItemStyle();
        style.sounds = Collections.emptyMap();
        style.animations = getAnimation(type);
        return style;
    }

    private Map<Identifier, Animation> getAnimation(ProjectileType type) {
        AnimationSetBuilder builder = new AnimationSetBuilder();
        builder.set(Idle, getSchema(type), assets.getTexture(type));
        return builder.build();
    }

    private AnimationSchema getSchema(ProjectileType type) {
        switch (type) {
            case Arrow: return AnimationLayouts.projectileStaticSchema();
            case Axe:
            case Cannon: return AnimationLayouts.projectileAnimatedSchema();
            default: throw new UnsupportedOperationException();
        }
    }

    private Vector2 getSize(ProjectileType type) {
        switch (type) {
            case Arrow: return new Vector2(40, 40);
            case Axe:
            case Cannon: return new Vector2(32, 32);
            default: throw new UnsupportedOperationException();
        }
    }
}
