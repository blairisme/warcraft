package com.evilbird.warcraft.item.hud.actionpane;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.action.identifier.BuildAction;
import com.evilbird.warcraft.action.identifier.CommonAction;
import com.evilbird.warcraft.action.identifier.GatherAction;
import com.evilbird.warcraft.action.identifier.TrainAction;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import static com.evilbird.warcraft.item.common.texture.TextureUtils.getDrawable;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ActionButtonProvider implements AssetProvider<ActionButton>
{
    private AssetManager assets;
    private Drawable background;
    private Map<ActionIdentifier, Drawable> icons;
    private Map<ActionIdentifier, Drawable> disabledIcons;

    @Inject
    public ActionButtonProvider(Device device) {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load() {
        assets.load("data/textures/neutral/perennial/action.png", Texture.class);
        assets.load("data/textures/neutral/perennial/icons.png", Texture.class);
        assets.load("data/textures/neutral/perennial/icons_disabled.png", Texture.class);
    }

    @Override
    public ActionButton get() {
        ActionButton result = new ActionButton();
        result.setBackground(getBackground());
        return result;
    }

    public ActionButton get(ActionIdentifier identifier) {
        ActionButton result = get();
        result.setPadding(4);
        result.setSize(54, 46);
        result.setId(identifier); //TODO: use something else m
        //result.setType(HudControls.ActionButton);
        result.setType(identifier);
        result.setTouchable(Touchable.enabled);
        result.setImage(getNormalIcon(identifier));
        result.setDisabledImage(getDisabledIcon(identifier));
        return result;
    }

    private Drawable getBackground() {
        if (background == null){
            background = getDrawable(assets, "data/textures/neutral/perennial/action.png");
        }
        return background;
    }

    private Drawable getNormalIcon(ActionIdentifier identifier) {
        if (icons == null){
            icons = new HashMap<ActionIdentifier, Drawable>();
            icons.put(CommonAction.Move, getDrawable(assets, "data/textures/neutral/perennial/icons.png", 138, 608, 46, 38));
            icons.put(CommonAction.Attack, getDrawable(assets, "data/textures/neutral/perennial/icons.png", 46, 874, 46, 38));
            icons.put(CommonAction.Stop, getDrawable(assets, "data/textures/neutral/perennial/icons.png", 46, 1254, 46, 38));
            icons.put(CommonAction.Cancel, getDrawable(assets, "data/textures/neutral/perennial/icons.png", 46, 684, 46, 38));
            icons.put(CommonAction.Repair, getDrawable(assets, "data/textures/neutral/perennial/icons.png", 0, 646, 46, 38));

            icons.put(GatherAction.GatherGold, getDrawable(assets, "data/textures/neutral/perennial/icons.png", 46, 646, 46, 38));
            icons.put(GatherAction.GatherWood, getDrawable(assets, "data/textures/neutral/perennial/icons.png", 46, 646, 46, 38));

            icons.put(BuildAction.BuildBarracks, getDrawable(assets, "data/textures/neutral/perennial/icons.png", 92, 304, 46, 38));
            icons.put(BuildAction.BuildFarm, getDrawable(assets, "data/textures/neutral/perennial/icons.png", 138, 266, 46, 38));
            icons.put(BuildAction.BuildTownHall, getDrawable(assets, "data/textures/neutral/perennial/icons.png", 0, 304, 46, 38));

            icons.put(TrainAction.TrainPeasant, getDrawable(assets, "data/textures/neutral/perennial/icons.png", 0, 0, 46, 38));
            icons.put(TrainAction.TrainFootman, getDrawable(assets, "data/textures/neutral/perennial/icons.png", 92, 0, 46, 38));
        }
        return icons.get(identifier);
    }

    private Drawable getDisabledIcon(ActionIdentifier identifier) {
        if (disabledIcons == null){
            disabledIcons = new HashMap<ActionIdentifier, Drawable>();
            disabledIcons.put(CommonAction.Move, getDrawable(assets, "data/textures/neutral/perennial/icons_disabled.png", 138, 608, 46, 38));
            disabledIcons.put(CommonAction.Attack, getDrawable(assets, "data/textures/neutral/perennial/icons_disabled.png", 46, 874, 46, 38));
            disabledIcons.put(CommonAction.Stop, getDrawable(assets, "data/textures/neutral/perennial/icons_disabled.png", 46, 1254, 46, 38));
            disabledIcons.put(CommonAction.Cancel, getDrawable(assets, "data/textures/neutral/perennial/icons_disabled.png", 46, 684, 46, 38));
            disabledIcons.put(CommonAction.Repair, getDrawable(assets, "data/textures/neutral/perennial/icons_disabled.png", 0, 646, 46, 38));

            disabledIcons.put(GatherAction.GatherGold, getDrawable(assets, "data/textures/neutral/perennial/icons_disabled.png", 46, 646, 46, 38));
            disabledIcons.put(GatherAction.GatherWood, getDrawable(assets, "data/textures/neutral/perennial/icons_disabled.png", 46, 646, 46, 38));

            disabledIcons.put(BuildAction.BuildBarracks, getDrawable(assets, "data/textures/neutral/perennial/icons_disabled.png", 92, 304, 46, 38));
            disabledIcons.put(BuildAction.BuildFarm, getDrawable(assets, "data/textures/neutral/perennial/icons_disabled.png", 138, 266, 46, 38));
            disabledIcons.put(BuildAction.BuildTownHall, getDrawable(assets, "data/textures/neutral/perennial/icons_disabled.png", 0, 304, 46, 38));

            disabledIcons.put(TrainAction.TrainPeasant, getDrawable(assets, "data/textures/neutral/perennial/icons_disabled.png", 0, 0, 46, 38));
            disabledIcons.put(TrainAction.TrainFootman, getDrawable(assets, "data/textures/neutral/perennial/icons_disabled.png", 92, 0, 46, 38));
        }
        return disabledIcons.get(identifier);
    }
}

