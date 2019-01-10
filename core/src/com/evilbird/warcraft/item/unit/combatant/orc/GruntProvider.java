package com.evilbird.warcraft.item.unit.combatant.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.animated.AnimationIdentifier;
import com.evilbird.warcraft.action.identifier.CommonAction;
import com.evilbird.warcraft.item.common.animation.AnimationCollections;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.inject.Inject;

public class GruntProvider implements AssetProvider<Item>
{
    private AssetManager assets;

    @Inject
    public GruntProvider(Device device)
    {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load()
    {
        assets.load("data/textures/orc/perennial/grunt.png", Texture.class);
        assets.load("data/textures/neutral/perennial/decompose.png", Texture.class);
    }

    @Override
    public Item get()
    {
        Combatant result = new Combatant();
        result.setActions(getActions());
        result.setAvailableAnimations(getAnimations());
        result.setAnimation(UnitAnimation.Idle);
        result.setArmour(2f);
        result.setDamageMinimum(2f);
        result.setDamageMaximum(9f);
        result.setHealth(100f);
        result.setHealthMaximum(100f);
        result.setIcon(getIcon());
        result.setLevel(1);
        result.setName("Grunt");
        result.setRange(1f);
        result.setSelected(false);
        result.setSelectable(true);
        result.setTouchable(Touchable.enabled);
        result.setSpeed(10f);
        result.setSight(4f);
        result.setType(UnitType.Grunt);
        result.setSize(72, 72);
        return result;
    }

    private Collection<ActionIdentifier> getActions()
    {
        Collection<ActionIdentifier> actions = new ArrayList<>();
        actions.add(CommonAction.Move);
        actions.add(CommonAction.Stop);
        actions.add(CommonAction.Attack);
        return actions;
    }

    private Map<AnimationIdentifier, DirectionalAnimation> getAnimations()
    {
        Texture general = assets.get("data/textures/orc/perennial/grunt.png", Texture.class);
        Texture decompose = assets.get("data/textures/neutral/perennial/decompose.png", Texture.class);
        return AnimationCollections.combatantAnimations(general, decompose);
    }

    private Drawable getIcon()
    {
        Texture iconTexture = assets.get("data/textures/neutral/perennial/icons.png", Texture.class);
        TextureRegion iconRegion = new TextureRegion(iconTexture, 138, 0, 46, 38);
        return new TextureRegionDrawable(iconRegion);
    }
}
