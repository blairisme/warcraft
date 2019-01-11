package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.common.CreateAction;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemOperations;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.identifier.SiteActionType;

import javax.inject.Inject;

/**
 * Instances of this class create a new building site.
 *
 * @author Blair Butterworth
 */
public class AddBuildingSite implements ActionProvider
{
    private ItemFactory itemFactory;

    @Inject
    public AddBuildingSite(ItemFactory itemFactory)
    {
        this.itemFactory = itemFactory;
    }

    @Override
    public Action get(ActionIdentifier actionType, Item item, Item target, UserInput input)
    {
        SiteActionType action = (SiteActionType)actionType;
        NamedIdentifier identifier = new NamedIdentifier();
        Vector2 location = ItemOperations.getScreenCenter(item.getRoot());
        return new CreateAction(item.getParent(), action.getBuildSiteType(), itemFactory, identifier, location, true);
    }
}
