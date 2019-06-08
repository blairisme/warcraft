/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.assets.AssetUtilities;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.audio.SoundUtils;
import com.evilbird.engine.common.graphics.Colours;
import com.evilbird.engine.common.graphics.TextureUtils;
import com.evilbird.warcraft.item.unit.UnitType;

import java.util.HashMap;
import java.util.Map;

import static com.evilbird.engine.common.file.FileType.MP3;

/**
 * Defines the assets that are required to display a {@link Combatant}, as well
 * as any sound effects used by it.
 *
 * @author Blair Butterworth
 */
public class CombatantAssets
{
    private AssetManager assets;
    private Map<String, Integer> cache;
    private CombatantAssetManifest manifest;
    private GridPoint2 icon;
    private GridPoint2 size;

    public CombatantAssets(AssetManager assets, UnitType unitType, GridPoint2 icon, GridPoint2 size) {
        this.assets = assets;
        this.cache = new HashMap<>();
        this.manifest = new CombatantAssetManifest(unitType);
        this.icon = icon;
        this.size = size;
    }

    public Drawable getIcon() {
        String path = manifest.getIconTexturePath();
        return TextureUtils.getDrawable(assets, path, icon.x, icon.y, 46, 38);
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
        return TextureUtils.getRectangle(size.x, size.y, Colours.FOREST_GREEN);
    }

    public SoundEffect getAcknowledgeSound() {
        return newSoundEffect(manifest.getAcknowledgeSoundEffectPath());
    }
    
    public SoundEffect getAttackSound() {
        return newSoundEffect(manifest.getAttackSoundEffectPath());
    }
    
    public SoundEffect getDieSound() {
        return newSoundEffect(manifest.getDieSoundEffectPath());
    }

    public SoundEffect getReadySound() {
        return newSoundEffect(manifest.getReadySoundEffectPath());
    }

    public SoundEffect getSelectedSound() {
        return newSoundEffect(manifest.getSelectedSoundEffectPath());
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
    }

    private void loadSounds() {
        loadSoundSet(manifest.getAcknowledgeSoundEffectPath());
        loadSoundSet(manifest.getSelectedSoundEffectPath());
        loadSoundSet(manifest.getAttackSoundEffectPath());
        loadSoundSet(manifest.getDieSoundEffectPath());
        loadSoundSet(manifest.getReadySoundEffectPath());
    }

    private void loadSoundSet(String path) {
        AssetUtilities.loadSoundSet(assets, path, MP3, getAssetCount(path));
    }

    private SoundEffect newSoundEffect(String path) {
        return SoundUtils.newSoundEffect(assets, path, MP3, getAssetCount(path));
    }

    private int getAssetCount(String path) {
        Integer result = cache.get(path);
        if (result == null) {
            FileHandleResolver resolver = assets.getFileHandleResolver();
            if (resolver != null) {
                FileHandle directory = resolver.resolve(path);
                result = directory.list().length;
                cache.put(path, result);
            }
        }
        return result != null ? result : 0;
    }
}
