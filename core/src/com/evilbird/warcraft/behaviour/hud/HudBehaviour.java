package com.evilbird.warcraft.behaviour.hud;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.item.ItemUtils;
import com.evilbird.engine.utility.Identifier;

import java.util.Collection;
import java.util.List;

public class HudBehaviour implements Behaviour
{
    private ActionFactory actionFactory;

   // @Inject
    public HudBehaviour(ActionFactory actionFactory)
    {
        this.actionFactory = actionFactory;
    }

    @Override
    public void update(ItemGroup world, ItemGroup hud, List<UserInput> inputs)
    {
        updateResourceBar(world, hud, inputs);
    }

    private void updateResourceBar(ItemGroup world, ItemGroup hud, List<UserInput> inputs)
    {
        Item player = world.getItem(new Identifier("Player1")); //TODO: Obtain Id + Cache
        Float gold = (Float)player.getProperty(new Identifier("Gold"));
        Float wood = (Float)player.getProperty(new Identifier("Wood"));

        Item resourceBar = hud.getItem(new Identifier("ResourcePanel")); //TODO: Cache
        resourceBar.setProperty(new Identifier("Gold"), gold); //TODO: Frequency too high. Only when changed.
        resourceBar.setProperty(new Identifier("Wood"), wood); //TODO: Frequency too high. Only when changed.

        Collection<Item> selection = ItemUtils.findAll(world.getItems(), new Identifier("Selected"), true); //TODO: Frequency too high. Only when changed.

        Item actionPanel = hud.getItem(new Identifier("ActionPanel")); //TODO: Cache
//        actionPanel.setProperty(new Identifier("Selection"), selection); //TODO: Frequency too high. Only when changed.

        Item selectionPanel = hud.getItem(new Identifier("SelectionPanel")); //TODO: Cache
  //      selectionPanel.setProperty(new Identifier("Selection"), selection); //TODO: Frequency too high. Only when changed.



/*


        for (UserInput input: inputs)
        {
            if (input.getType() == UserInputType.Action)
            {
                Actor actor = getTarget(hud, input);
                if (actor instanceof Item)
                {
                    Item item = (Item)actor;
                    ActionContext context = (ActionContext)item.getProperty(new Identifier("Action"));
                    if (context != null)
                    {
                        Action action = actionFactory.newAction(context);
                        context.getTarget().addAction(action);
                    }
                }
            }
        }
        */
    }

    private Actor getTarget(ItemGroup stage, UserInput userInput)
    {
        Vector2 inputPosition = userInput.getPosition();
        Vector2 worldPosition = stage.screenToStageCoordinates(inputPosition);
        return  stage.hit(worldPosition.x, worldPosition.y, false);

    }
}
