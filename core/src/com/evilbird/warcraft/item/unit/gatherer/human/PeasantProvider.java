/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.gatherer.human;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.audio.SilentSoundEffect;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.animated.AnimationIdentifier;
import com.evilbird.engine.item.specialized.animated.SoundIdentifier;
import com.evilbird.warcraft.item.common.animation.AnimationCollections;
import com.evilbird.warcraft.item.common.texture.TextureUtils;
import com.evilbird.warcraft.item.layer.LayerType;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

import static com.evilbird.engine.common.assets.AssetUtilities.loadSoundSet;
import static com.evilbird.engine.common.audio.SoundType.MP3;
import static com.evilbird.warcraft.item.common.sound.SoundUtils.newSoundEffect;

public class PeasantProvider implements AssetProvider<Item>
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
    public PeasantProvider(Device device) {
        this.assets = device.getAssetStorage().getAssets();
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
        Gatherer result = new Gatherer();
        result.setAvailableAnimations(getAnimations());
        result.setAnimation(UnitAnimation.Idle);
        result.setAvailableSounds(getSounds());
        result.setDefence(0);
        result.setDamageMinimum(1);
        result.setDamageMaximum(5);
        result.setHealth(30f);
        result.setHealthMaximum(30f);
        result.setIcon(getIcon());
        result.setLevel(1);
        result.setMovementSpeed(64f);
        result.setMovementCapability(LayerType.Map);
        result.setRange(1f);
        result.setSpeed(10f);
        result.setSight(5 * 32);
        result.setName("Peasant");
        result.setType(UnitType.Peasant);
        result.setSize(32, 32);
        return result;
    }

    private Map<AnimationIdentifier, DirectionalAnimation> getAnimations() {
        Texture general = assets.get(BASE, Texture.class);
        Texture moveGold = assets.get(MOVE_GOLD, Texture.class);
        Texture moveWood = assets.get(MOVE_WOOD, Texture.class);
        Texture decompose = assets.get(DECOMPOSE, Texture.class);
        return AnimationCollections.gatherAnimations(general, decompose, moveGold, moveWood);
    }

    private Drawable getIcon() {
        return TextureUtils.getDrawable(assets, ICONS, 0, 0, 46, 38);
    }

    private Map<SoundIdentifier, SoundEffect> getSounds() {
        Map<SoundIdentifier, SoundEffect> sounds = new HashMap<>();
        sounds.put(UnitSound.Selected, newSoundEffect(assets, SELECTED, MP3, 4));
        sounds.put(UnitSound.Acknowledge, newSoundEffect(assets, ACKNOWLEDGE, MP3, 4));
        sounds.put(UnitSound.Construct, newSoundEffect(assets, CONSTRUCT));
        sounds.put(UnitSound.Complete, newSoundEffect(assets, COMPLETE));
        sounds.put(UnitSound.Attack, newSoundEffect(assets, ATTACK));
        sounds.put(UnitSound.Ready, newSoundEffect(assets, READY));
        sounds.put(UnitSound.GatherWood, newSoundEffect(assets, CHOPPING, MP3, 4));
        sounds.put(UnitSound.GatherGold, new SilentSoundEffect());
        sounds.put(UnitSound.DepositGold, new SilentSoundEffect());
        sounds.put(UnitSound.DepositWood, new SilentSoundEffect());
        return sounds;
    }
}
