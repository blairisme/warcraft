/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant.human;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
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
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

import static com.evilbird.engine.common.assets.AssetUtilities.loadSoundSet;
import static com.evilbird.engine.common.audio.SoundUtils.newSoundEffect;
import static com.evilbird.engine.common.file.FileType.MP3;

/**
 * Instances of this factory create footman, human entry level
 * {@link Combatant Combatants}.
 *
 * @author Blair Butterworth
 */
public class FootmanFactory implements AssetProvider<Item>
{
    private static final String BASE = "data/textures/human/perennial/footman.png";
    private static final String ICONS = "data/textures/neutral/perennial/icons.png";
    private static final String DECOMPOSE = "data/textures/neutral/perennial/decompose.png";
    private static final String ACKNOWLEDGE = "data/sounds/human/unit/footman/acknowledge/";
    private static final String SELECTED = "data/sounds/human/unit/footman/selected/";
    private static final String ATTACK = "data/sounds/neutral/unit/attack/melee/";
    private static final String DEAD = "data/sounds/human/unit/common/dead/";

    private AssetManager assets;

    @Inject
    public FootmanFactory(Device device) {
        this(device.getAssetStorage());
    }

    public FootmanFactory(AssetManager assets) {
        this.assets = assets;
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
    }

    private void loadSounds() {
        loadSoundSet(assets, ACKNOWLEDGE, MP3, 4);
        loadSoundSet(assets, SELECTED, MP3, 6);
        loadSoundSet(assets, ATTACK, MP3, 3);
        loadSoundSet(assets, DEAD, MP3, 1);
    }

    @Override
    public Item get() {
        Combatant result = new Combatant(getSkin());
        result.setAnimation(UnitAnimation.Idle);
        result.setDefence(2);
        result.setDamageMinimum(10); //2
        result.setDamageMaximum(19); //9
        result.setHealth(60);
        result.setHealthMaximum(60);
        result.setLevel(1);
        result.setName("Footman");
        result.setMovementSpeed(64); // 10
        result.setMovementCapability(LayerType.Map);
        result.setRange(32 + 5); // 1
        result.setSelected(false);
        result.setSelectable(true);
        result.setTouchable(Touchable.enabled);
        result.setSight(4 * 32); //4
        result.setType(UnitType.Footman);
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
        Texture decompose = assets.get(DECOMPOSE, Texture.class);
        return AnimationSets.combatantAnimations(general, decompose);
    }

    private Map<Identifier, SoundEffect> getSounds() {
        Map<Identifier, SoundEffect> sounds = new HashMap<>();
        sounds.put(UnitSound.Acknowledge, newSoundEffect(assets, ACKNOWLEDGE, MP3, 4));
        sounds.put(UnitSound.Selected, newSoundEffect(assets, SELECTED, MP3, 6));
        sounds.put(UnitSound.Attack, newSoundEffect(assets, ATTACK, MP3, 3));
        sounds.put(UnitSound.Die, newSoundEffect(assets, DEAD, MP3, 1));
        return sounds;
    }

    private UnitStyle getUnitStyle() {
        UnitStyle unitStyle = new UnitStyle();
        unitStyle.icon = TextureUtils.getDrawable(assets, ICONS, 92, 0, 46, 38);
        unitStyle.selection = TextureUtils.getRectangle(32, 32, Colours.FOREST_GREEN);
        return unitStyle;
    }
}
