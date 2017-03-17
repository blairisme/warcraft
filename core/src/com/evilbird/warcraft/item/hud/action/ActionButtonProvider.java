package com.evilbird.warcraft.item.hud.action;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.action.ActionType;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import static com.evilbird.warcraft.common.TextureUtils.getDrawable;

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

    @Inject
    public ActionButtonProvider(Device device)
    {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load()
    {
        assets.load("data/textures/neutral/perennial/action.png", Texture.class);
        assets.load("data/textures/neutral/perennial/icons.png", Texture.class);
    }

    @Override
    public ActionButton get()
    {
        ActionButton result = new ActionButton();
        result.setBackground(getBackground());
        result.setActionIcons(getActionIcons());
        return result;
    }

    private Drawable getBackground()
    {
        if (background == null){
            background = getDrawable(assets, "data/textures/neutral/perennial/action.png");
        }
        return background;
    }

    //TODO: Improve implementation
    private Map<ActionIdentifier, Drawable> getActionIcons()
    {
        if (icons == null){
            icons = new HashMap<ActionIdentifier, Drawable>();
            icons.put(ActionType.Move, getDrawable(assets, "data/textures/neutral/perennial/icons.png", 138, 608, 46, 38));
            icons.put(ActionType.Stop, getDrawable(assets, "data/textures/neutral/perennial/icons.png", 46, 1254, 46, 38));
            icons.put(ActionType.Attack, getDrawable(assets, "data/textures/neutral/perennial/icons.png", 46, 874, 46, 38));

            icons.put(ActionType.Repair, getDrawable(assets, "data/textures/neutral/perennial/icons.png", 0, 646, 46, 38)); //TO
            icons.put(ActionType.GatherGold, getDrawable(assets, "data/textures/neutral/perennial/icons.png", 46, 646, 46, 38)); //TO
            icons.put(ActionType.GatherWood, getDrawable(assets, "data/textures/neutral/perennial/icons.png", 46, 646, 46, 38)); //TO

            icons.put(ActionType.BuildBarracks, getDrawable(assets, "data/textures/neutral/perennial/icons.png", 92, 304, 46, 38));
            icons.put(ActionType.BuildFarm, getDrawable(assets, "data/textures/neutral/perennial/icons.png", 138, 266, 46, 38));
            icons.put(ActionType.BuildTownHall, getDrawable(assets, "data/textures/neutral/perennial/icons.png", 0, 304, 46, 38)); //TO
        }
        return icons;
    }
}

