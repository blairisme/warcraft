package com.evilbird.warcraft.item.unit.human;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.inject.AssetObjectProvider;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.common.AnimationBuilder;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class PeasantProvider implements AssetObjectProvider<Item>
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
        assets.load("data/sounds/human/unit/peasant/selected_1.mp3", Sound.class);
        assets.load("data/sounds/human/unit/peasant/complete.mp3", Sound.class);
        assets.load("data/sounds/human/unit/peasant/acknowledge_1.mp3", Sound.class);
        assets.load("data/sounds/human/unit/peasant/construct.mp3", Sound.class);

        assets.load("data/textures/human/perennial/peasant.png", Texture.class);
        assets.load("data/textures/neutral/perennial/decompose.png", Texture.class);
        assets.load("data/textures/neutral/perennial/icons.png", Texture.class);
    }

    @Override
    public Item get()
    {
        Unit result = new Unit();
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
        result.setGold(0f);
        result.setOil(0f);
        result.setWood(0f);
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

    private Map<Identifier, Sound> getSounds()
    {
        Sound selected = assets.get("data/sounds/human/unit/peasant/selected_1.mp3", Sound.class);
        Sound complete = assets.get("data/sounds/human/unit/peasant/complete.mp3", Sound.class);
        Sound acknowledge = assets.get("data/sounds/human/unit/peasant/acknowledge_1.mp3", Sound.class);
        Sound construct = assets.get("data/sounds/human/unit/peasant/construct.mp3", Sound.class);

        Map<Identifier, Sound> sounds = new HashMap<Identifier, Sound>();
        sounds.put(new Identifier("Selected"), selected);
        sounds.put(new Identifier("Complete"), complete);
        sounds.put(new Identifier("Acknowledge"), acknowledge);
        sounds.put(new Identifier("Construct"), construct);

        return sounds;
    }
}
