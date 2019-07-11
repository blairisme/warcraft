/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.critter;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.audio.SoundUtils;
import com.evilbird.engine.common.graphics.Colours;
import com.evilbird.engine.common.graphics.TextureUtils;
import com.evilbird.warcraft.item.unit.UnitType;

/**
 * Defines the assets that are required to display a {@link Critter}, as well
 * as any sound effects used by it.
 *
 * @author Blair Butterworth
 */
public class CritterAssets
{
    private AssetManager assets;
    private CritterAssetManifest manifest;

    public CritterAssets(AssetManager assets, UnitType unitType) {
        this.assets = assets;
        this.manifest = new CritterAssetManifest(unitType);
    }

    public Texture getBaseTexture() {
        String path = manifest.getBaseTexturePath();
        return assets.get(path, Texture.class);
    }

    public Texture getDecomposeTexture() {
        String path = manifest.getDecomposeTexturePath();
        return assets.get(path, Texture.class);
    }

    public Texture getSelectionTexture() {
        return TextureUtils.getRectangle(32, 32, Colours.FOREST_GREEN);
    }

    public SoundEffect getDieSound() {
        return SoundUtils.newSoundEffect(assets, manifest.getDieSoundEffectPath());
    }

    public SoundEffect getSelectedSound() {
        return SoundUtils.newSoundEffect(assets, manifest.getSelectedSoundEffectPath());
    }

    public void load() {
        assets.load(manifest.getBaseTexturePath(), Texture.class);
        assets.load(manifest.getDecomposeTexturePath(), Texture.class);
        assets.load(manifest.getSelectedSoundEffectPath(), Sound.class);
        assets.load(manifest.getDieSoundEffectPath(), Sound.class);
    }
}
