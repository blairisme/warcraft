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
import com.evilbird.engine.common.audio.sound.SoundCatalog;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.engine.object.AnimatedObjectStyle;
import com.evilbird.warcraft.object.projectile.animations.CannonballProjectileAnimation;
import com.evilbird.warcraft.object.projectile.animations.CatapultProjectileAnimation;
import com.evilbird.warcraft.object.projectile.animations.DirectionalProjectileAnimation;
import com.evilbird.warcraft.object.projectile.animations.LargeProjectileAnimation;
import com.evilbird.warcraft.object.projectile.animations.RotationalProjectileAnimation;
import com.evilbird.warcraft.object.projectile.animations.SpellProjectileAnimation;

import static com.evilbird.warcraft.object.projectile.ExplosivePattern.Point;
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
    private SoundCatalog sounds;

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

    public ExplosiveProjectile buildExplosive() {
        ExplosiveProjectile projectile = new ExplosiveProjectile(getSkin());
        projectile.setAnimation(Idle);
        projectile.setTouchable(Touchable.disabled);
        projectile.setExplosiveCount(1);
        projectile.setExplosiveInterval(0);
        projectile.setExplosiveRadius(1);
        projectile.setExplosivePattern(Point);
        return projectile;
    }

    protected Skin getSkin() {
        Skin skin = new Skin();
        skin.add("default", getViewableStyle());
        return skin;
    }

    protected AnimatedObjectStyle getViewableStyle() {
        AnimationCatalog animations = getAnimations();
        SoundCatalog sounds = getSounds();

        AnimatedObjectStyle style = new AnimatedObjectStyle();
        style.animations = animations.get();
        style.sounds = sounds.get();
        return style;
    }

    protected AnimationCatalog getAnimations() {
        if (animations == null) {
            animations = newAnimations();
        }
        return animations;
    }

    protected AnimationCatalog newAnimations() {
        switch (type) {
            case Arrow: return new DirectionalProjectileAnimation(assets.getBaseTexture(type));
            case Axe: return new RotationalProjectileAnimation(assets.getBaseTexture(Axe));
            case Bolt: return new LargeProjectileAnimation(assets.getBaseTexture(Bolt));
            case Cannonball: return new RotationalProjectileAnimation(assets.getBaseTexture(Cannonball));
            case DaemonFire: return new RotationalProjectileAnimation(assets.getBaseTexture(DaemonFire));
            case Fireball: return new DirectionalProjectileAnimation(assets.getBaseTexture(Fireball));
            case FlamingCannonball: return new CannonballProjectileAnimation(assets.getBaseTexture(FlamingCannonball));
            case FlamingRock: return new CatapultProjectileAnimation(assets.getBaseTexture(FlamingRock));
            case GryphonHammer: return new RotationalProjectileAnimation(assets.getBaseTexture(GryphonHammer));
            case Lightning: return new SpellProjectileAnimation(assets.getBaseTexture(Lightning));
            case Torpedo: return new LargeProjectileAnimation(assets.getBaseTexture(Torpedo));
            case TouchOfDeath: return new SpellProjectileAnimation(assets.getBaseTexture(TouchOfDeath));
            default: throw new UnsupportedOperationException();
        }
    }

    protected SoundCatalog getSounds() {
        if (sounds == null) {
            sounds = new ProjectileSounds(assets, type);
        }
        return sounds;
    }
}
