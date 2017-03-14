package com.evilbird.warcraft.item.unit.combatant.human;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.evilbird.engine.common.audio.BasicSoundEffect;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.audio.SoundEffectSet;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.common.AnimationBuilder;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import java.util.ArrayList;
import java.util.Collection;
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
        result.setAnimation(new Identifier("Idle"));
        result.setAvailableSounds(getSounds());
        result.setArmour(0f);
        result.setDamageMinimum(1f);
        result.setDamageMaximum(5f);
        result.setHealth(30f);
        result.setHealthMaximum(30f);
        result.setIcon(getIcon());
        result.setLevel(1);
        result.setName("Peasant");
        result.setRange(1f);
        result.setSelected(false);
        result.setSelectable(true);
        result.setTouchable(Touchable.enabled);
        result.setSpeed(10f);
        result.setSight(4f);
        result.setType(new Identifier("Peasant"));
        //result.setGold(0f);
        //result.setOil(0f);
        //result.setWood(0f);
        result.setSize(72, 72);
        return result;
    }

    private EnumSet<ActionType> getActions()
    {
        EnumSet<ActionType> actions = EnumSet.noneOf(ActionType.class);
        actions.add(ActionType.Move);
        actions.add(ActionType.Stop);
        actions.add(ActionType.Attack);
        //actions.add(Actions.Repair);
        //actions.add(Actions.Harvest);
        actions.add(ActionType.CreateFarm);
        actions.add(ActionType.CreateBarracks);
        //actions.add(Actions.BuildTownhall);
        //return with goods
        return actions;
    }

    private Map<Identifier, DirectionalAnimation> getAnimations()
    {
        Texture texture = assets.get("data/textures/human/perennial/peasant.png", Texture.class);
        Texture decomposeTexture = assets.get("data/textures/neutral/perennial/decompose.png", Texture.class);
        Map<Identifier, DirectionalAnimation> animations = AnimationBuilder.getAnimationSet(texture, decomposeTexture);
        animations.put(new Identifier("GatherGold"), animations.get(new Identifier("Hidden")));
        animations.put(new Identifier("GatherWood"), animations.get(new Identifier("Attack")));
        animations.put(new Identifier("DepositGold"), animations.get(new Identifier("Hidden")));
        animations.put(new Identifier("DepositWood"), animations.get(new Identifier("Hidden")));
        animations.put(new Identifier("Build"), animations.get(new Identifier("Hidden")));
        return animations;
    }

    private Drawable getIcon()
    {
        Texture iconTexture = assets.get("data/textures/neutral/perennial/icons.png", Texture.class);
        TextureRegion iconRegion = new TextureRegion(iconTexture, 0, 0, 46, 38);
        return new TextureRegionDrawable(iconRegion);
    }

    private Map<Identifier, SoundEffect> getSounds()
    {
        SoundEffect selected = newSoundEffect("data/sounds/human/unit/peasant/selected_1.wav");
        SoundEffect complete = newSoundEffect("data/sounds/human/unit/peasant/complete.mp3");
        SoundEffect acknowledge = newSoundEffect("data/sounds/human/unit/peasant/acknowledge_1.mp3");
        SoundEffect construct = newSoundEffect("data/sounds/human/unit/peasant/construct.mp3");

        SoundEffect gatherWood = newSoundEffect(
            "data/sounds/neutral/chopping/1.wav",
            "data/sounds/neutral/chopping/2.wav",
            "data/sounds/neutral/chopping/3.wav",
            "data/sounds/neutral/chopping/4.wav");

        Map<Identifier, SoundEffect> sounds = new HashMap<Identifier, SoundEffect>();
        sounds.put(new Identifier("Selected"), selected);
        sounds.put(new Identifier("Complete"), complete);
        sounds.put(new Identifier("Acknowledge"), acknowledge);
        sounds.put(new Identifier("Construct"), construct);
        sounds.put(new Identifier("GatherWood"), gatherWood);

        return sounds;
    }

    private SoundEffect newSoundEffect(String path)
    {
        Sound sound = assets.get(path, Sound.class);
        return new BasicSoundEffect(sound);
    }

    private SoundEffect newSoundEffect(String ... paths)
    {
        Collection<SoundEffect> effects = new ArrayList<SoundEffect>(paths.length);
        for (String path: paths){
            effects.add(newSoundEffect(path));
        }
        return new SoundEffectSet(effects);
    }
}
