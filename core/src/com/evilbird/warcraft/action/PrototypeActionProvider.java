package com.evilbird.warcraft.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.CreateAction;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemIdentifier;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.item.world.building.BuildingType;

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
        if (Objects.equals(target.getType(), new Identifier("CreateBarracksButton"))){
            return get(item, BuildingType.BarracksPrototype);
        }
        throw new UnsupportedOperationException();
    }

    public Action get(Item item, ItemIdentifier prototype)
    {
        ItemRoot itemRoot = item.getRoot();
        Vector2 screenCenter = new Vector2(512, 384);
        Vector2 location = itemRoot.unproject(screenCenter);
        Action createPrototype = newCreateAction(itemRoot, prototype, new Identifier(), location);
        return createPrototype;
    }

    private Action newCreateAction(ItemRoot root, ItemIdentifier type, Identifier id, Vector2 position)
    {
        return new CreateAction(root, type, itemFactory, id, position);
    }
}
