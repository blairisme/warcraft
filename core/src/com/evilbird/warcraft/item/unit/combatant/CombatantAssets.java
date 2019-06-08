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
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.graphics.Colours;
import com.evilbird.engine.common.graphics.TextureUtils;

import static com.evilbird.engine.common.assets.AssetUtilities.loadSoundSet;
import static com.evilbird.engine.common.audio.SoundUtils.newSoundEffect;
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
    private String base;
    private String icons;
    private String decompose;
    private String acknowledge;
    private String selected;
    private String attack;
    private String ready;
    private String dead;
    private GridPoint2 icon;
    private GridPoint2 select;

    public CombatantAssets(AssetManager assets, String race, String name, String type) {
        this.assets = assets;
        this.base = "data/textures/" + race + "/perennial/" + name + ".png";
        this.icons = "data/textures/neutral/perennial/icons.png";
        this.decompose = "data/textures/neutral/perennial/decompose.png";
        this.acknowledge = "data/sounds/" + race + "/unit/" + name + "/acknowledge/";
        this.selected = "data/sounds/" + race + "/unit/" + name +"/selected/";
        this.ready = "data/sounds/" + race + "/unit/" + name +"/ready/1.mp3";
        this.attack = "data/sounds/neutral/unit/attack/" + type + "/";
        this.dead = "data/sounds/" + race + "/unit/common/dead/1.mp3";
    }

    public Drawable getIcon() {
        return TextureUtils.getDrawable(assets, icons, icon.x, icon.y, 46, 38);
    }

    public Texture getBaseTexture() {
        return assets.get(base, Texture.class);
    }

    public Texture getDecomposeTexture() {
        return assets.get(decompose, Texture.class);
    }

    public Texture getSelectionTexture() {
        return TextureUtils.getRectangle(select.x, select.y, Colours.FOREST_GREEN);
    }

    public SoundEffect getAcknowledgeSound() {
        return newSoundEffect(assets, acknowledge, MP3, 4);
    }
    
    public SoundEffect getAttackSound() {
        return newSoundEffect(assets, attack, MP3, 3);
    }
    
    public SoundEffect getDieSound() {
        return newSoundEffect(assets, dead);
    }
    
    public SoundEffect getReadySound() {
        return newSoundEffect(assets, ready);
    }

    public SoundEffect getSelectedSound() {
        //return newSoundEffect(assets, selected, MP3, 6);
        return newSoundEffect(assets, selected, MP3, 4);
    }

    public void setIcon(int x, int y) {
        this.icon = new GridPoint2(x, y);
    }

    public void setSize(int x, int y) {
        this.select = new GridPoint2(x, y);
    }

    public void load() {
        loadTextures();
        loadSounds();
    }

    private void loadTextures() {
        assets.load(base, Texture.class);
        assets.load(icons, Texture.class);
        assets.load(decompose, Texture.class);
    }

    private void loadSounds() {
        loadSoundSet(assets, acknowledge, MP3, 4);
        //loadSoundSet(assets, selected, MP3, 6);
        loadSoundSet(assets, selected, MP3, 4);
        loadSoundSet(assets, attack, MP3, 3);
        assets.load(dead, Sound.class);
        assets.load(ready, Sound.class);
    }
}
