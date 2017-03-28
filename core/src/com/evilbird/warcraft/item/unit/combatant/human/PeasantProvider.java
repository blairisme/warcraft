package com.evilbird.warcraft.item.unit.combatant.human;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.evilbird.engine.common.audio.SilentSoundEffect;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.AnimationIdentifier;
import com.evilbird.engine.item.specialized.SoundIdentifier;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.common.AnimationBuilderOld;
import com.evilbird.warcraft.common.SoundUtils;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class PeasantProvider implements AssetProvider<Item>
{
    private AssetManager assets;

    @Inject
    public PeasantProvider(Device device)
    {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load()
    {
        assets.load("data/sounds/human/unit/peasant/selected_1.wav", Sound.class);
        assets.load("data/sounds/human/unit/peasant/complete.mp3", Sound.class);
        assets.load("data/sounds/human/unit/peasant/acknowledge_1.mp3", Sound.class);
        assets.load("data/sounds/human/unit/peasant/construct.mp3", Sound.class);

        assets.load("data/sounds/neutral/chopping/1.wav", Sound.class);
        assets.load("data/sounds/neutral/chopping/2.wav", Sound.class);
        assets.load("data/sounds/neutral/chopping/3.wav", Sound.class);
        assets.load("data/sounds/neutral/chopping/4.wav", Sound.class);

        assets.load("data/textures/human/perennial/peasant.png", Texture.class);
        assets.load("data/textures/neutral/perennial/decompose.png", Texture.class);
        assets.load("data/textures/neutral/perennial/icons.png", Texture.class);
    }

    @Override
    public Item get()
    {
        Combatant result = new Combatant();
        result.setActions(getActions());
        result.setAvailableAnimations(getAnimations());
        result.setAnimation(UnitAnimation.Idle);
        result.setAvailableSounds(getSounds());
        result.setArmour(0f);
        result.setDamageMinimum(1f);
        result.setDamageMaximum(5f);
        result.setHealth(30f);
        result.setHealthMaximum(30f);
        result.setIcon(getIcon());
        result.setLevel(1);
        result.setRange(1f);
        result.setSpeed(10f);
        result.setSight(4f);
        result.setName("Peasant");
        result.setType(new NamedIdentifier("Peasant"));
        result.setSize(32, 32);
        return result;
    }

    private EnumSet<ActionType> getActions()
    {
        EnumSet<ActionType> actions = EnumSet.noneOf(ActionType.class);
        actions.add(ActionType.Move);
        actions.add(ActionType.Stop);
        actions.add(ActionType.Attack);
        actions.add(ActionType.Repair);
        actions.add(ActionType.GatherGold);
        actions.add(ActionType.BuildFarm);
        actions.add(ActionType.BuildBarracks);
        actions.add(ActionType.BuildTownHall);
        return actions;
    }

    private Map<AnimationIdentifier, DirectionalAnimation> getAnimations()
    {
        Texture texture = assets.get("data/textures/human/perennial/peasant.png", Texture.class);
        Texture decomposeTexture = assets.get("data/textures/neutral/perennial/decompose.png", Texture.class);

        Map<AnimationIdentifier, DirectionalAnimation> animations = AnimationBuilderOld.getAnimationSet(texture, decomposeTexture);
        animations.put(UnitAnimation.GatherGold, animations.get(UnitAnimation.Hidden));
        animations.put(UnitAnimation.GatherWood, animations.get(UnitAnimation.Attack));
        animations.put(UnitAnimation.DepositGold, animations.get(UnitAnimation.Hidden));
        animations.put(UnitAnimation.DepositWood, animations.get(UnitAnimation.Hidden));
        animations.put(UnitAnimation.Build, animations.get(UnitAnimation.Hidden));
        return animations;
    }

    private Drawable getIcon()
    {
        Texture iconTexture = assets.get("data/textures/neutral/perennial/icons.png", Texture.class);
        TextureRegion iconRegion = new TextureRegion(iconTexture, 0, 0, 46, 38);
        return new TextureRegionDrawable(iconRegion);
    }

    private Map<SoundIdentifier, SoundEffect> getSounds()
    {
        SoundEffect silent = new SilentSoundEffect();
        SoundEffect selected = SoundUtils.newSoundEffect(assets, "data/sounds/human/unit/peasant/selected_1.wav");
        SoundEffect complete = SoundUtils.newSoundEffect(assets, "data/sounds/human/unit/peasant/complete.mp3");
        SoundEffect acknowledge = SoundUtils.newSoundEffect(assets, "data/sounds/human/unit/peasant/acknowledge_1.mp3");
        SoundEffect construct = SoundUtils.newSoundEffect(assets, "data/sounds/human/unit/peasant/construct.mp3");
        SoundEffect gatherWood = SoundUtils.newSoundEffect(assets, "data/sounds/neutral/chopping/", ".wav", 1, 4);

        Map<SoundIdentifier, SoundEffect> sounds = new HashMap<SoundIdentifier, SoundEffect>();
        sounds.put(UnitSound.Selected, selected);
        sounds.put(UnitSound.Complete, complete);
        sounds.put(UnitSound.Acknowledge, acknowledge);
        sounds.put(UnitSound.Construct, construct);
        sounds.put(UnitSound.GatherGold, silent);
        sounds.put(UnitSound.GatherWood, gatherWood);
        sounds.put(UnitSound.DepositGold, silent);
        sounds.put(UnitSound.DepositWood, silent);

        return sounds;
    }


}
