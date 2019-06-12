/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.projectile;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.graphics.Animation;
import com.evilbird.engine.common.graphics.AnimationSchema;
import com.evilbird.engine.common.inject.IdentifiedAssetProvider;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.AnimatedItemStyle;
import com.evilbird.warcraft.item.common.animation.AnimationSchemas;
import com.evilbird.warcraft.item.common.animation.AnimationSetBuilder;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;
import java.util.Collections;
import java.util.Map;

import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;

/**
 * Creates {@link Projectile} instances of a given {@link ProjectileType type}.
 *
 * @author Blair Butterworth
 */
public class ProjectileFactory implements IdentifiedAssetProvider<Item>
{
    private static final String ARROW = "data/textures/neutral/projectile/arrow.png";
    private static final String AXE = "data/textures/neutral/projectile/axe.png";
    private static final String CANNON = "data/textures/neutral/projectile/cannon.png";

    private AssetManager assets;
    private InjectedPool<Projectile> pool;

    @Inject
    public ProjectileFactory(Device device, InjectedPool<Projectile> pool) {
        this(device.getAssetStorage(), pool);
    }

    public ProjectileFactory(AssetManager assets, InjectedPool<Projectile> pool) {
        this.assets = assets;
        this.pool = pool;
    }

    @Override
    public void load() {
        assets.load(ARROW, Texture.class);
        assets.load(AXE, Texture.class);
        assets.load(CANNON, Texture.class);
    }

    @Override
    public Item get(Identifier identifier) {
        Validate.isInstanceOf(ProjectileType.class, identifier);
        ProjectileType type = (ProjectileType)identifier;

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
        builder.set(Idle, getSchema(type), getTexture(type));
        return builder.build();
    }

    private Texture getTexture(ProjectileType type) {
        switch (type) {
            case Arrow: return assets.get(ARROW, Texture.class);
            case Axe: return assets.get(AXE, Texture.class);
            case Cannon: return assets.get(CANNON, Texture.class);
            default: throw new UnsupportedOperationException();
        }
    }

    private AnimationSchema getSchema(ProjectileType type) {
        switch (type) {
            case Arrow: return AnimationSchemas.projectileStaticSchema();
            case Axe:
            case Cannon: return AnimationSchemas.projectileAnimatedSchema();
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
