/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.resource;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.graphics.Colours;
import com.evilbird.engine.common.graphics.TextureUtils;
import com.evilbird.warcraft.item.unit.UnitType;

import static com.evilbird.engine.common.assets.AssetUtilities.loadSoundSet;
import static com.evilbird.engine.common.audio.SoundUtils.newSoundEffect;
import static com.evilbird.engine.common.file.FileType.MP3;

/**
 * Provides access to the assets that are required to display a
 * {@link Resource}, as well as any sound effects used by it.
 *
 * @author Blair Butterworth
 */
public class ResourceAssets
{
    private AssetManager assets;
    private ResourceAssetManifest manifest;

    public ResourceAssets(AssetManager assets, UnitType type) {
        this.assets = assets;
        this.manifest = new ResourceAssetManifest(type);
    }

    public Texture getSelectionTexture() {
        return TextureUtils.getRectangle(96, 96, Colours.FOREST_GREEN);
    }

    public Texture getGeneralTexture() {
        return assets.get(manifest.getGeneralTexturePath(), Texture.class);
    }

    public Texture getDestructionTexture() {
        return assets.get(manifest.getDestructionTexturePath(), Texture.class);
    }

    public SoundEffect getDestroyedSound() {
        return newSoundEffect(assets, manifest.getDestroyedSoundEffectPath(), MP3, 3);
    }

    public SoundEffect getSelectedSound() {
        return newSoundEffect(assets, manifest.getSelectedSoundEffectPath());
    }

    public void load() {
        loadTextures();
        loadSounds();
    }

    private void loadTextures() {
        assets.load(manifest.getGeneralTexturePath(), Texture.class);
        assets.load(manifest.getDestructionTexturePath(), Texture.class);
    }

    private void loadSounds() {
        assets.load(manifest.getSelectedSoundEffectPath(), Sound.class);
        loadSoundSet(assets, manifest.getDestroyedSoundEffectPath(), MP3, 3);
    }
}
