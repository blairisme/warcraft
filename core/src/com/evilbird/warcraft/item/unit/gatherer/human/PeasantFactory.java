/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.gatherer.human;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.audio.SilentSoundEffect;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.graphics.Animation;
import com.evilbird.engine.common.graphics.Colours;
import com.evilbird.engine.common.graphics.TextureUtils;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.AnimatedItemStyle;
import com.evilbird.warcraft.item.common.animation.AnimationSets;
import com.evilbird.warcraft.item.layer.LayerType;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.UnitStyle;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

import static com.evilbird.engine.common.assets.AssetUtilities.loadSoundSet;
import static com.evilbird.engine.common.audio.SoundUtils.newSoundEffect;
import static com.evilbird.engine.common.file.FileType.MP3;

/**
 * Instances of this factory create Peasants, the land based gathering unit
 * available to the human faction.
 *
 * @author Blair Butterworth
 */
public class PeasantFactory implements AssetProvider<Item>
{
    private static final String BASE = "data/textures/human/perennial/peasant.png";
    private static final String ICONS = "data/textures/neutral/perennial/icons.png";
    private static final String DECOMPOSE = "data/textures/neutral/perennial/decompose.png";
    private static final String MOVE_GOLD = "data/textures/human/perennial/peasant_move_gold.png";
    private static final String MOVE_WOOD = "data/textures/human/perennial/peasant_move_wood.png";

    private static final String CHOPPING = "data/sounds/neutral/unit/chopping/";
    private static final String SELECTED = "data/sounds/human/unit/peasant/selected/";
    private static final String ACKNOWLEDGE = "data/sounds/human/unit/peasant/acknowledge/";
    private static final String ATTACK = "data/sounds/human/unit/peasant/attack/1.mp3";
    private static final String COMPLETE = "data/sounds/human/unit/peasant/complete/1.mp3";
    private static final String CONSTRUCT = "data/sounds/human/unit/peasant/construct/1.mp3";
    private static final String READY = "data/sounds/human/unit/peasant/ready/1.mp3";

    private AssetManager assets;

    @Inject
    public PeasantFactory(Device device) {
        this.assets = device.getAssetStorage();
    }

    @Override
    public void load() {
        loadTextures();
        loadSounds();
    }

    private void loadTextures() {
        assets.load(BASE, Texture.class);
        assets.load(ICONS, Texture.class);
        assets.load(DECOMPOSE, Texture.class);
        assets.load(MOVE_GOLD, Texture.class);
        assets.load(MOVE_WOOD, Texture.class);
    }

    private void loadSounds() {
        assets.load(ATTACK, Sound.class);
        assets.load(COMPLETE, Sound.class);
        assets.load(CONSTRUCT, Sound.class);
        assets.load(READY, Sound.class);

        loadSoundSet(assets, SELECTED, MP3, 4);
        loadSoundSet(assets, ACKNOWLEDGE, MP3, 4);
        loadSoundSet(assets, CHOPPING, MP3, 4);
    }

    @Override
    public Item get() {
        Gatherer result = new Gatherer(getSkin());
        result.setAnimation(UnitAnimation.Idle);
        result.setDefence(0);
        result.setDamageMinimum(1);
        result.setDamageMaximum(5);
        result.setHealth(30f);
        result.setHealthMaximum(30f);
        result.setLevel(1);
        result.setMovementSpeed(64f);
        result.setMovementCapability(LayerType.Map);
        result.setRange(1f);
        result.setSight(5 * 32);
        result.setName("Peasant");
        result.setType(UnitType.Peasant);
        result.setSize(32, 32);
        return result;
    }

    private Skin getSkin() {
        Skin skin = new Skin();
        skin.add("default", getAnimationStyle(), AnimatedItemStyle.class);
        skin.add("default", getUnitStyle(), UnitStyle.class);
        return skin;
    }

    private AnimatedItemStyle getAnimationStyle() {
        AnimatedItemStyle animatedItemStyle = new AnimatedItemStyle();
        animatedItemStyle.animations = getAnimations();
        animatedItemStyle.sounds = getSounds();
        return animatedItemStyle;
    }

    private Map<Identifier, Animation> getAnimations() {
        Texture general = assets.get(BASE, Texture.class);
        Texture moveGold = assets.get(MOVE_GOLD, Texture.class);
        Texture moveWood = assets.get(MOVE_WOOD, Texture.class);
        Texture decompose = assets.get(DECOMPOSE, Texture.class);
        return AnimationSets.gatherAnimations(general, decompose, moveGold, moveWood);
    }

    private Map<Identifier, SoundEffect> getSounds() {
        Map<Identifier, SoundEffect> sounds = new HashMap<>();
        sounds.put(UnitSound.Selected, newSoundEffect(assets, SELECTED, MP3, 4));
        sounds.put(UnitSound.Acknowledge, newSoundEffect(assets, ACKNOWLEDGE, MP3, 4));
        sounds.put(UnitSound.Build, newSoundEffect(assets, CONSTRUCT));
        sounds.put(UnitSound.Complete, newSoundEffect(assets, COMPLETE));
        sounds.put(UnitSound.Attack, newSoundEffect(assets, ATTACK));
        sounds.put(UnitSound.Ready, newSoundEffect(assets, READY));
        sounds.put(UnitSound.ChopWood, newSoundEffect(assets, CHOPPING, MP3, 4));
        sounds.put(UnitSound.MineGold, new SilentSoundEffect());
        sounds.put(UnitSound.DepositGold, new SilentSoundEffect());
        sounds.put(UnitSound.DepositWood, new SilentSoundEffect());
        return sounds;
    }

    private UnitStyle getUnitStyle() {
        UnitStyle unitStyle = new UnitStyle();
        unitStyle.icon = TextureUtils.getDrawable(assets, ICONS, 0, 0, 46, 38);
        unitStyle.selection = TextureUtils.getRectangle(32, 32, Colours.FOREST_GREEN);
        return unitStyle;
    }
}
