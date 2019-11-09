/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.effect;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.assets.AssetBundle;

/**
 * Provides access to the assets that are required to display a effect game
 * objects.
 *
 * @author Blair Butterworth
 */
public class EffectAssets extends AssetBundle
{
    public EffectAssets(AssetManager assetManager) {
        super(assetManager);
        registerConfirmationTextures();
        registerEnvironmentalTextures();
        registerExplosionTextures();
        registerSpellTextures();
    }

    private void registerConfirmationTextures() {
        register("data/textures/common/ui/green_cross.png");
        register("data/textures/common/ui/red_cross.png");
    }

    private void registerEnvironmentalTextures() {
        register("data/textures/common/environmental/fire.png");
        register("data/textures/common/environmental/flame.png");
        register("data/textures/common/environmental/rune.png");
    }

    private void registerExplosionTextures() {
        register("data/textures/common/explosion/ballista_explosion.png");
        register("data/textures/common/explosion/cannon_explosion.png");
        register("data/textures/common/explosion/tower_explosion.png");
        register("data/textures/common/explosion/explosion.png");
    }

    private void registerSpellTextures() {
        register("data/textures/common/spell/exorcism.png");
        register("data/textures/common/spell/flame_shield.png");
        register("data/textures/common/spell/heal.png");
        register("data/textures/common/spell/spell.png");
    }

    public Texture getGreenCross() {
        return getTexture("green_cross.png");
    }

    public Texture getRedCross() {
        return getTexture("red_cross.png");
    }

    public Texture getBallistaExplosion() {
        return getTexture("ballista_explosion.png");
    }

    public Texture getCannonExplosion() {
        return getTexture("cannon_explosion.png");
    }

    public Texture getTowerExplosion() {
        return getTexture("tower_explosion.png");
    }

    public Texture getExplosion() {
        return getTexture("explosion.png");
    }

    public Texture getFlame() {
        return getTexture("flame.png");
    }

    public Texture getFire() {
        return getTexture("fire.png");
    }

    public Texture getRune() {
        return getTexture("rune.png");
    }

    public Texture getExorcism() {
        return getTexture("exorcism.png");
    }

    public Texture getFlameShield() {
        return getTexture("flame_shield.png");
    }

    public Texture getHeal() {
        return getTexture("heal.png");
    }

    public Texture getSpell() {
        return getTexture("spell.png");
    }
}
