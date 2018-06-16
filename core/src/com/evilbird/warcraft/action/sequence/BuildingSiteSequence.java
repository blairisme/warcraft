package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.common.CreateAction;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.*;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.action.type.BuildAction;
import com.evilbird.warcraft.action.type.BuildSiteAction;
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
    public Action get(ActionIdentifier actionType, Item item, Item target, UserInput input)
    {
        BuildSiteAction action = (BuildSiteAction)actionType;
        NamedIdentifier identifier = new NamedIdentifier();
        Vector2 location = ItemOperations.getScreenCenter(item.getRoot());
        return new CreateAction(item.getParent(), action.getBuildSiteType(), itemFactory, identifier, location, true);
    }
}
