/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.conjured;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.audio.sound.SoundCatalog;
import com.evilbird.engine.common.graphics.AnimationCatalog;
import com.evilbird.engine.item.specialized.ViewableStyle;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitStyle;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.conjured.animations.ConjuredAnimations;
import com.evilbird.warcraft.item.unit.conjured.sounds.ConjuredSounds;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;

/**
 * Creates a new {@link ConjuredObject} instance whose visual and audible
 * presentation is defined by the given {@link ConjuredAssets}.
 *
 * @author Blair Butterworth
 */
public class ConjuredBuilder
{
    private UnitType type;
    private ConjuredAssets assets;
    private AnimationCatalog animations;
    private SoundCatalog sounds;

    public ConjuredBuilder(ConjuredAssets assets, UnitType type) {
        this.assets = assets;
        this.type = type;
    }

    public ConjuredObject build() {
        ConjuredObject result = new ConjuredObject(getSkin());
        result.setAnimation(UnitAnimation.Idle);
        result.setHealth(1);
        result.setHealthMaximum(1);
        result.setIdentifier(objectIdentifier(type.name(), result));
        result.setSelected(false);
        result.setSelectable(false);
        result.setSight(0);
        result.setSize(32, 32);
        result.setTouchable(Touchable.enabled);
        result.setType(type);
        result.setZIndex(0);
        return result;
    }

    private Skin getSkin() {
        UnitStyle style = getStyle();
        Skin skin = new Skin();
        skin.add("default", style, ViewableStyle.class);
        skin.add("default", style, UnitStyle.class);
        return skin;
    }

    private UnitStyle getStyle() {
        SoundCatalog sounds = getSounds();
        AnimationCatalog animations = getAnimations();

        UnitStyle style = new UnitStyle();
        style.animations = animations.get();
        style.sounds = sounds.get();
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
            case Blizzard: return new ConjuredAnimations(assets.getBlizzard());
            case DeathAndDecay: return new ConjuredAnimations(assets.getDeathAndDecay());
            case RuneTrap: return new ConjuredAnimations(assets.getRune());
            case Whirlwind: return new ConjuredAnimations(assets.getTornado());
            default: throw new UnsupportedOperationException();
        }
    }

    private SoundCatalog getSounds() {
        if (sounds == null) {
            sounds = new ConjuredSounds(assets);
        }
        return sounds;
    }
}
