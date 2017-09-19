package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.CreateAction;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.ItemType;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.item.hud.building.BuildSiteType;

import javax.inject.Inject;

import static com.badlogic.gdx.Gdx.graphics;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class BuildingSiteSequence implements ActionProvider
{
    private ItemFactory itemFactory;

    @Inject
    public BuildingSiteSequence(ItemFactory itemFactory)
    {
        this.itemFactory = itemFactory;
    }

    @Override
    public Action get(ActionType action, Item item, Item target, UserInput input)
    {
        ItemType type = getBuildingSiteType(action);
        NamedIdentifier identifier = new NamedIdentifier();
        Vector2 location = getScreenCenter(item.getRoot());
        return new CreateAction(item.getParent(), type, itemFactory, identifier, location, true);
    }

    private BuildSiteType getBuildingSiteType(ActionType actionType)
    {
        switch (actionType){
            case BuildBarracksSite: return BuildSiteType.BarracksBuildSite;
            case BuildFarmSite: return BuildSiteType.FarmBuildSite;
            case BuildTownHallSite: return BuildSiteType.TownHallBuildSite;
            default: throw new UnsupportedOperationException();
        }
    }

    private Vector2 getScreenCenter(ItemRoot root)
    {
        float x = graphics.getWidth() * 0.5f;
        float y = graphics.getHeight() * 0.5f;
        Vector2 screenCenter = new Vector2(x, y);
        return root.unproject(screenCenter);
    }
}
