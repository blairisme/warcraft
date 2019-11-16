/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.projectile;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.engine.object.AnimatedObjectStyle;
import com.evilbird.warcraft.object.projectile.animations.CannonballProjectileAnimation;
import com.evilbird.warcraft.object.projectile.animations.CatapultProjectileAnimation;
import com.evilbird.warcraft.object.projectile.animations.DirectionalProjectileAnimation;
import com.evilbird.warcraft.object.projectile.animations.LargeProjectileAnimation;
import com.evilbird.warcraft.object.projectile.animations.RotationalProjectileAnimation;
import com.evilbird.warcraft.object.projectile.animations.SpellProjectileAnimation;

import static com.evilbird.warcraft.object.projectile.ProjectileType.Axe;
import static com.evilbird.warcraft.object.projectile.ProjectileType.Bolt;
import static com.evilbird.warcraft.object.projectile.ProjectileType.Cannonball;
import static com.evilbird.warcraft.object.projectile.ProjectileType.DaemonFire;
import static com.evilbird.warcraft.object.projectile.ProjectileType.Fireball;
import static com.evilbird.warcraft.object.projectile.ProjectileType.FlamingCannonball;
import static com.evilbird.warcraft.object.projectile.ProjectileType.FlamingRock;
import static com.evilbird.warcraft.object.projectile.ProjectileType.GryphonHammer;
import static com.evilbird.warcraft.object.projectile.ProjectileType.Lightning;
import static com.evilbird.warcraft.object.projectile.ProjectileType.Torpedo;
import static com.evilbird.warcraft.object.projectile.ProjectileType.TouchOfDeath;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;

/**
 * Creates a new {@link Projectile} instance whose visual and audible
 * presentation is defined by the given {@link ProjectileAssets}.
 *
 * @author Blair Butterworth
 */
public class ProjectileBuilder
{
    private ProjectileType type;
    private ProjectileAssets assets;
    private AnimationCatalog animations;

    public ProjectileBuilder(ProjectileAssets assets, ProjectileType type) {
        this.assets = assets;
        this.type = type;
    }

    public Projectile build() {
        Projectile projectile = new Projectile(getSkin());
        projectile.setAnimation(Idle);
        projectile.setTouchable(Touchable.disabled);
        return projectile;
    }

    private Skin getSkin() {
        Skin skin = new Skin();
        skin.add("default", getViewableStyle());
        return skin;
    }

    private AnimatedObjectStyle getViewableStyle() {
        AnimationCatalog animations = getAnimations();
        AnimatedObjectStyle style = new AnimatedObjectStyle();
        style.animations = animations.get();
        return style;
    }

    private AnimationCatalog getAnimations() {
        if (animations == null) {
            animations = newAnimations();
        }
        return animations;
    }

    private AnimationCatalog newAnimations() {
        switch (type) {
            case Arrow: return new DirectionalProjectileAnimation(assets.getTexture(type));
            case Axe: return new RotationalProjectileAnimation(assets.getTexture(Axe));
            case Bolt: return new LargeProjectileAnimation(assets.getTexture(Bolt));
            case Cannonball: return new RotationalProjectileAnimation(assets.getTexture(Cannonball));
            case DaemonFire: return new RotationalProjectileAnimation(assets.getTexture(DaemonFire));
            case Fireball: return new DirectionalProjectileAnimation(assets.getTexture(Fireball));
            case FlamingCannonball: return new CannonballProjectileAnimation(assets.getTexture(FlamingCannonball));
            case FlamingRock: return new CatapultProjectileAnimation(assets.getTexture(FlamingRock));
            case GryphonHammer: return new RotationalProjectileAnimation(assets.getTexture(GryphonHammer));
            case Lightning: return new SpellProjectileAnimation(assets.getTexture(Lightning));
            case Torpedo: return new LargeProjectileAnimation(assets.getTexture(Torpedo));
            case TouchOfDeath: return new SpellProjectileAnimation(assets.getTexture(TouchOfDeath));
            default: throw new UnsupportedOperationException();
        }
    }
}
