package com.evilbird.warcraft.item.hud.actionpane;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.action.identifier.BuildActionType;
import com.evilbird.warcraft.action.identifier.CommonActionType;
import com.evilbird.warcraft.action.identifier.GatherActionType;
import com.evilbird.warcraft.action.identifier.TrainActionType;

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
            icons.put(CommonActionType.Move, getDrawable(assets, "data/textures/neutral/perennial/icons.png", 138, 608, 46, 38));
            icons.put(CommonActionType.Attack, getDrawable(assets, "data/textures/neutral/perennial/icons.png", 46, 874, 46, 38));
            icons.put(CommonActionType.Stop, getDrawable(assets, "data/textures/neutral/perennial/icons.png", 46, 1254, 46, 38));
            icons.put(CommonActionType.Cancel, getDrawable(assets, "data/textures/neutral/perennial/icons.png", 46, 684, 46, 38));
            icons.put(CommonActionType.Repair, getDrawable(assets, "data/textures/neutral/perennial/icons.png", 0, 646, 46, 38));

            icons.put(GatherActionType.GatherGold, getDrawable(assets, "data/textures/neutral/perennial/icons.png", 46, 646, 46, 38));
            icons.put(GatherActionType.GatherWood, getDrawable(assets, "data/textures/neutral/perennial/icons.png", 46, 646, 46, 38));

            icons.put(BuildActionType.BuildBarracks, getDrawable(assets, "data/textures/neutral/perennial/icons.png", 92, 304, 46, 38));
            icons.put(BuildActionType.BuildFarm, getDrawable(assets, "data/textures/neutral/perennial/icons.png", 138, 266, 46, 38));
            icons.put(BuildActionType.BuildTownHall, getDrawable(assets, "data/textures/neutral/perennial/icons.png", 0, 304, 46, 38));

            icons.put(TrainActionType.TrainPeasant, getDrawable(assets, "data/textures/neutral/perennial/icons.png", 0, 0, 46, 38));
            icons.put(TrainActionType.TrainFootman, getDrawable(assets, "data/textures/neutral/perennial/icons.png", 92, 0, 46, 38));
        }
        return icons.get(identifier);
    }

    private Drawable getDisabledIcon(ActionIdentifier identifier) {
        if (disabledIcons == null){
            disabledIcons = new HashMap<ActionIdentifier, Drawable>();
            disabledIcons.put(CommonActionType.Move, getDrawable(assets, "data/textures/neutral/perennial/icons_disabled.png", 138, 608, 46, 38));
            disabledIcons.put(CommonActionType.Attack, getDrawable(assets, "data/textures/neutral/perennial/icons_disabled.png", 46, 874, 46, 38));
            disabledIcons.put(CommonActionType.Stop, getDrawable(assets, "data/textures/neutral/perennial/icons_disabled.png", 46, 1254, 46, 38));
            disabledIcons.put(CommonActionType.Cancel, getDrawable(assets, "data/textures/neutral/perennial/icons_disabled.png", 46, 684, 46, 38));
            disabledIcons.put(CommonActionType.Repair, getDrawable(assets, "data/textures/neutral/perennial/icons_disabled.png", 0, 646, 46, 38));

            disabledIcons.put(GatherActionType.GatherGold, getDrawable(assets, "data/textures/neutral/perennial/icons_disabled.png", 46, 646, 46, 38));
            disabledIcons.put(GatherActionType.GatherWood, getDrawable(assets, "data/textures/neutral/perennial/icons_disabled.png", 46, 646, 46, 38));

            disabledIcons.put(BuildActionType.BuildBarracks, getDrawable(assets, "data/textures/neutral/perennial/icons_disabled.png", 92, 304, 46, 38));
            disabledIcons.put(BuildActionType.BuildFarm, getDrawable(assets, "data/textures/neutral/perennial/icons_disabled.png", 138, 266, 46, 38));
            disabledIcons.put(BuildActionType.BuildTownHall, getDrawable(assets, "data/textures/neutral/perennial/icons_disabled.png", 0, 304, 46, 38));

            disabledIcons.put(TrainActionType.TrainPeasant, getDrawable(assets, "data/textures/neutral/perennial/icons_disabled.png", 0, 0, 46, 38));
            disabledIcons.put(TrainActionType.TrainFootman, getDrawable(assets, "data/textures/neutral/perennial/icons_disabled.png", 92, 0, 46, 38));
        }
        return disabledIcons.get(identifier);
    }
}

