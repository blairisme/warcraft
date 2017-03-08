package com.evilbird.warcraft.behaviour.hud;

import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.item.hud.action.ActionPane;
import com.evilbird.warcraft.item.hud.resource.ResourcePanel;
import com.evilbird.warcraft.item.hud.state.StatePane;
import com.evilbird.warcraft.item.unit.Unit;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import static com.evilbird.engine.item.ItemPredicates.itemWithId;
import static com.evilbird.engine.item.ItemPredicates.selectedItem;

public class HudBehaviour implements Behaviour
{
    private ActionFactory actionFactory;

    @Inject
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
        Unit player = (Unit)world.find(itemWithId(new Identifier("Player1"))); //TODO: Obtain Id && Cache && Use Player class
        ResourcePanel resourcePane = (ResourcePanel)hud.find(itemWithId(new Identifier("ResourcePane"))); //TODO: Cache

        resourcePane.setGold(player.getGold()); //TODO: Frequency too high. Only when changed.
        resourcePane.setOil(player.getOil()); //TODO: Frequency too high. Only when changed.
        resourcePane.setWood(player.getWood()); //TODO: Frequency too high. Only when changed.

        Collection<Item> selection = world.findAll(selectedItem()); //TODO: Frequency too high. Only when changed.

        ActionPane actionPane = (ActionPane)hud.find(itemWithId(new Identifier("ActionPane"))); //TODO: Cache
        actionPane.setSelection(selection); //TODO: Frequency too high. Only when changed.

        StatePane statePane = (StatePane)hud.find(itemWithId(new Identifier("StatePane"))); //TODO: Cache
        statePane.setSelection(selection); //TODO: Frequency too high. Only when changed.
    }
}
