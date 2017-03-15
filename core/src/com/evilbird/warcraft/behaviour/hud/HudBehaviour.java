package com.evilbird.warcraft.behaviour.hud;

import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.hud.action.ActionPane;
import com.evilbird.warcraft.item.hud.resource.ResourcePane;
import com.evilbird.warcraft.item.hud.state.StatePane;
import com.evilbird.warcraft.item.unit.ResourceType;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import static com.evilbird.engine.item.ItemPredicates.itemWithId;
import static com.evilbird.engine.item.ItemPredicates.selectedItem;

//TODO: Resource update frequency too high. Only when changed.
//TODO: Selection update Frequency too high. Only when selection changed.
//TODO: Use player.isConsoleUser to find player
//TODO: Use statically defined hud control identifiers
public class HudBehaviour implements Behaviour
{
    private Player player;
    private ResourcePane resourcePane;
    private ActionPane actionPane;
    private StatePane statePane;
    private ActionFactory actionFactory;

    @Inject
    public HudBehaviour(ActionFactory actionFactory)
    {
        this.actionFactory = actionFactory;
    }

    @Override
    public void update(ItemRoot world, ItemRoot hud, List<UserInput> inputs)
    {
        updateResourceBar(world, hud);
        updateSelection(world, hud);
    }

    private void updateResourceBar(ItemRoot world, ItemRoot hud)
    {
        Player player = getConsolePlayer(world);
        ResourcePane resourcePane = getResourcePane(hud);

        resourcePane.setGold(player.getResource(ResourceType.Gold));
        resourcePane.setOil(player.getResource(ResourceType.Oil));
        resourcePane.setWood(player.getResource(ResourceType.Wood));
    }

    private void updateSelection(ItemRoot world, ItemRoot hud)
    {
        ActionPane actionPane = getActionPane(hud);
        StatePane statePane = getStatePane(hud);

        Collection<Item> selection = world.findAll(selectedItem());
        actionPane.setSelection(selection);
        statePane.setSelection(selection);
    }

    private Player getConsolePlayer(ItemRoot world)
    {
        if (player == null){
            player = (Player)world.find(itemWithId(new Identifier("Player1")));
        }
        return player;
    }

    private ResourcePane getResourcePane(ItemRoot hud)
    {
        if (resourcePane == null){
            resourcePane = (ResourcePane)hud.find(itemWithId(new Identifier("ResourcePane")));
        }
        return resourcePane;
    }

    private ActionPane getActionPane(ItemRoot hud)
    {
        if (actionPane == null){
            actionPane = (ActionPane) hud.find(itemWithId(new Identifier("ActionPane")));
        }
        return actionPane;
    }

    private StatePane getStatePane(ItemRoot hud)
    {
        if (statePane == null){
            statePane = (StatePane)hud.find(itemWithId(new Identifier("StatePane")));
        }
        return statePane;
    }
}
