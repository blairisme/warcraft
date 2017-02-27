package com.evilbird.warcraft.item.hud;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.utility.AssetObjectProvider;
import com.evilbird.warcraft.action.Actions;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ActionTileProvider implements AssetObjectProvider<ActionTile>
{
    private AssetManager assets;

    @Inject
    public ActionTileProvider(Device device)
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
    public ActionTile get()
    {
        ActionTile result = new ActionTile();
        result.setBackground(getBackground());
        result.setActionIcons(getActionIcons());
        return result;
    }

    //TODO: Cache
    private Drawable getBackground()
    {
        return getTexture("data/textures/neutral/perennial/action.png");
    }

    //TODO: Cache
    //TODO: Improve implementation
    private Map<ActionIdentifier, Drawable> getActionIcons()
    {
        Map<ActionIdentifier, Drawable> result = new HashMap<ActionIdentifier, Drawable>();
        result.put(Actions.Move, getTexture("data/textures/neutral/perennial/icons.png", 138, 608, 46, 38));
        result.put(Actions.Attack, getTexture("data/textures/neutral/perennial/icons.png", 46, 874, 46, 38));
        result.put(Actions.BuildBarracks, getTexture("data/textures/neutral/perennial/icons.png", 92, 646, 46, 38));
        result.put(Actions.BuildFarm, getTexture("data/textures/neutral/perennial/icons.png", 138, 646, 46, 38));
        return result;
    }

    private Drawable getTexture(String path)
    {
        Texture texture = assets.get(path);
        TextureRegion region = new TextureRegion(texture);
        return new TextureRegionDrawable(region);
    }

    private Drawable getTexture(String path, int x, int y, int width, int height)
    {
        Texture texture = assets.get(path);
        TextureRegion region = new TextureRegion(texture, x, y, width, height);
        return new TextureRegionDrawable(region);
    }
}
