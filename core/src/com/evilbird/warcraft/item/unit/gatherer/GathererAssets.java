/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.gatherer;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.graphics.Colours;
import com.evilbird.engine.common.graphics.TextureUtils;
import com.evilbird.warcraft.item.unit.UnitType;

import static com.evilbird.engine.common.assets.AssetUtilities.loadSoundSet;
import static com.evilbird.engine.common.audio.SoundUtils.newSoundEffect;
import static com.evilbird.engine.common.file.FileType.MP3;

/**
 * Provides access to the assets that are required to display a
 * {@link Gatherer}, as well as any sound effects used by it.
 *
 * @author Blair Butterworth
 */
public class GathererAssets
{
    private AssetManager assets;
    private GathererAssetManifest manifest;
    private GridPoint2 icon;
    private GridPoint2 size;

    public GathererAssets(AssetManager assets, UnitType unitType, GridPoint2 icon, GridPoint2 size) {
        this.assets = assets;
        this.manifest = new GathererAssetManifest(unitType);
        this.icon = icon;
        this.size = size;
    }

    public Drawable getIcon() {
        String path = manifest.getIconTexturePath();
        return TextureUtils.getDrawable(assets, path, icon.x, icon.y, 46, 38);
    }

    public Texture getBaseTexture() {
        return assets.get(manifest.getBaseTexturePath(), Texture.class);
    }

    public Texture getDecomposeTexture() {
        return assets.get(manifest.getBaseTexturePath(), Texture.class);
    }

    public Texture getMoveWithGoldTexture() {
        return assets.get(manifest.getMoveWithGoldTexturePath(), Texture.class);
    }

    public Texture getMoveWithWoodTexture() {
        return assets.get(manifest.getMoveWithWoodTexturePath(), Texture.class);
    }

    public Texture getSelectionTexture() {
        return TextureUtils.getRectangle(size.x, size.y, Colours.FOREST_GREEN);
    }

    public SoundEffect getChoppingSound() {
        return newSoundEffect(assets, manifest.getChoppingSoundEffectPath(), MP3, 4);
    }

    public SoundEffect getSelectedSound() {
        return newSoundEffect(assets, manifest.getSelectedSoundEffectPath(), MP3, 4);
    }

    public SoundEffect getAcknowledgeSound() {
        return newSoundEffect(assets, manifest.getAcknowledgeSoundEffectPath(), MP3, 4);
    }

    public SoundEffect getAttackSound() {
        return newSoundEffect(assets, manifest.getAttackSoundEffectPath());
    }

    public SoundEffect getCompleteSound() {
        return newSoundEffect(assets, manifest.getCompleteSoundEffectPath());
    }

    public SoundEffect getConstructSound() {
        return newSoundEffect(assets, manifest.getConstructSoundEffectPath());
    }

    public SoundEffect getReadySound() {
        return newSoundEffect(assets, manifest.getReadySoundEffectPath());
    }

    public SoundEffect getDeadSound() {
        return newSoundEffect(assets, manifest.getDeadSoundEffectPath());
    }

    public GridPoint2 getSize() {
        return size;
    }

    public void load() {
        loadTextures();
        loadSounds();
    }

    private void loadTextures() {
        assets.load(manifest.getBaseTexturePath(), Texture.class);
        assets.load(manifest.getIconTexturePath(), Texture.class);
        assets.load(manifest.getDecomposeTexturePath(), Texture.class);
        assets.load(manifest.getMoveWithGoldTexturePath(), Texture.class);
        assets.load(manifest.getMoveWithWoodTexturePath(), Texture.class);
    }

    private void loadSounds() {
        loadSoundSet(assets, manifest.getChoppingSoundEffectPath(), MP3, 4);
        loadSoundSet(assets, manifest.getSelectedSoundEffectPath(), MP3, 4);
        loadSoundSet(assets, manifest.getAcknowledgeSoundEffectPath(), MP3, 4);

        assets.load(manifest.getAttackSoundEffectPath(), Sound.class);
        assets.load(manifest.getCompleteSoundEffectPath(), Sound.class);
        assets.load(manifest.getConstructSoundEffectPath(), Sound.class);
        assets.load(manifest.getReadySoundEffectPath(), Sound.class);
        assets.load(manifest.getDeadSoundEffectPath(), Sound.class);
    }
}
