/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.effect;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.engine.object.AnimatedObjectStyle;
import com.evilbird.warcraft.object.effect.confirmation.ConfirmationAnimations;
import com.evilbird.warcraft.object.effect.environmental.FireAnimations;
import com.evilbird.warcraft.object.effect.environmental.FlameAnimations;
import com.evilbird.warcraft.object.effect.environmental.RuneAnimations;
import com.evilbird.warcraft.object.effect.explosion.BallistaExplosionAnimations;
import com.evilbird.warcraft.object.effect.explosion.CannonExplosionAnimations;
import com.evilbird.warcraft.object.effect.explosion.ExplosionAnimations;
import com.evilbird.warcraft.object.effect.spell.SmallSpellAnimations;
import com.evilbird.warcraft.object.effect.spell.SpellAnimations;
import com.evilbird.warcraft.object.unit.UnitAnimation;

import java.util.Objects;

/**
 * Constructs new effect game objects.
 *
 * @author Blair Butterworth
 */
public class EffectBuilder
{
    private EffectAssets assets;
    private EffectType type;
    private AnimationCatalog animations;

    /**
     * Creates a new instance of this class given an asset bundle containing
     * the textures that will used to display game objects created by this
     * class and an effect type, specifying the type of effects that will be
     * created by the builder.
     *
     * @param assets    an {@link EffectAssets} instance
     * @param type      the type of effects that will be produced by the
     *                  builder.
     *
     * @throws NullPointerException if either the given asset bundle or type
     *                              is {@code null}.
     */
    public EffectBuilder(EffectAssets assets, EffectType type) {
        Objects.requireNonNull(assets);
        Objects.requireNonNull(type);

        this.assets = assets;
        this.type = type;
    }

    /**
     * Creates a new {@link Effect}.
     */
    public Effect build() {
        Effect result = new Effect(getSkin());
        result.setAnimation(UnitAnimation.Idle);
        result.setTouchable(Touchable.disabled);
        result.setVisible(true);
        result.setSize(32, 32);
        return result;
    }

    private Skin getSkin() {
        Skin skin = new Skin();
        skin.add("default", getAnimationStyle(), AnimatedObjectStyle.class);
        return skin;
    }

    private AnimatedObjectStyle getAnimationStyle() {
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
            case Attack: return new ConfirmationAnimations(assets.getRedCross());
            case Confirm: return new ConfirmationAnimations(assets.getGreenCross());

            case BallistaExplosion: return new BallistaExplosionAnimations(assets.getBallistaExplosion());
            case CannonExplosion: return new CannonExplosionAnimations(assets.getCannonExplosion());
            case TowerExplosion: return new CannonExplosionAnimations(assets.getTowerExplosion());
            case Explosion: return new ExplosionAnimations(assets.getExplosion());

            case Fire: return new FireAnimations(assets.getFire());
            case Flame: return new FlameAnimations(assets.getFlame());

            case Exorcism: return new SpellAnimations(assets.getExorcism());
            case Heal: return new SpellAnimations(assets.getHeal());
            case Rune: return new RuneAnimations(assets.getRune());
            case Spell: return new SmallSpellAnimations(assets.getSpell());

            default: throw new UnsupportedOperationException();
        }
    }
}
