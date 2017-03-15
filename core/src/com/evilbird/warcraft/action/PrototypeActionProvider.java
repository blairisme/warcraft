package com.evilbird.warcraft.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.CreateAction;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.ItemType;
import com.evilbird.warcraft.item.hud.building.BuildingSiteType;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class PrototypeActionProvider implements ActionProvider
{
    private ItemFactory itemFactory;

    @Inject
    public PrototypeActionProvider(ItemFactory itemFactory)
    {
        this.itemFactory = itemFactory;
    }

    @Override
    public Action get(Item item, Item target, UserInput input)
    {
        if (Objects.equals(target.getType(), new NamedIdentifier("CreateBarracksButton"))){
            return get(item, BuildingSiteType.BarracksBuildingSite);
        }
        throw new UnsupportedOperationException();
    }

    public Action get(Item item, ItemType siteType)
    {
        ItemRoot root = item.getRoot();
        Vector2 screenCenter = new Vector2(512, 384);
        Vector2 location = root.unproject(screenCenter);
        return new CreateAction(root, siteType, itemFactory, new NamedIdentifier(), location, true);
    }
}
