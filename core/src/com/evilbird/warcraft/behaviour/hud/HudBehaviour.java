package com.evilbird.warcraft.behaviour.hud;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.common.function.Predicate;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.control.Invokable;

import java.util.Collection;
import java.util.List;

import static com.evilbird.engine.item.ItemPredicates.itemWithId;
import static com.evilbird.engine.item.ItemPredicates.itemWithProperty;

public class HudBehaviour implements Behaviour
{
    private ActionFactory actionFactory;

   // @Inject
    public HudBehaviour(ActionFactory actionFactory)
    {
        this.actionFactory = actionFactory;
    }

    @Override
    public void update(ItemRoot world, ItemRoot hud, List<UserInput> inputs)
    {
        updateResourceBar(world, hud, inputs);
    }

    private void updateResourceBar(ItemRoot world, ItemRoot hud, List<UserInput> inputs)
    {
        Item player = world.find(itemWithId(new Identifier("Player1"))); //TODO: Obtain Id + Cache
        Float gold = (Float)player.getProperty(new Identifier("Gold"));
        Float wood = (Float)player.getProperty(new Identifier("Wood"));

        Item resourceBar = hud.find(itemWithId(new Identifier("ResourcePane"))); //TODO: Cache
        resourceBar.setProperty(new Identifier("Gold"), gold); //TODO: Frequency too high. Only when changed.
        resourceBar.setProperty(new Identifier("Wood"), wood); //TODO: Frequency too high. Only when changed.

        Collection<Item> selection = world.findAll(selectedItems()); //TODO: Frequency too high. Only when changed.

        Item actionPanel = hud.find(itemWithId(new Identifier("ActionPane"))); //TODO: Cache
        actionPanel.setProperty(new Identifier("Selection"), selection); //TODO: Frequency too high. Only when changed.

        Item selectionPanel = hud.find(itemWithId(new Identifier("StatePane"))); //TODO: Cache
        selectionPanel.setProperty(new Identifier("Selection"), selection); //TODO: Frequency too high. Only when changed.


        for (UserInput input: inputs)
        {
            if (input.getType() == UserInputType.Action)
            {
                Item item = getTarget(hud, input);
                if (item instanceof Invokable)
                {
                    Invokable invokable = (Invokable)item;
                    invokable.invoke(actionFactory);
                }
            }
        }

    }

    private Predicate<Item> selectedItems()
    {
        return itemWithProperty(new Identifier("Selected"), true);
    }

    private Item getTarget(ItemRoot root, UserInput userInput)
    {
        Vector2 inputPosition = userInput.getPosition();
        Vector2 worldPosition = root.unproject(inputPosition);
        return root.hit(worldPosition, false);

    }
}
