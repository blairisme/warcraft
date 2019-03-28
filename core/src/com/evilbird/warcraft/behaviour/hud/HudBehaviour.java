/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.hud;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.state.State;
import com.evilbird.warcraft.action.common.resource.ResourceTransferEvent;
import com.evilbird.warcraft.action.select.SelectEvent;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.hud.HudControl;
import com.evilbird.warcraft.item.hud.control.actions.ActionPane;
import com.evilbird.warcraft.item.hud.control.status.StatusPane;
import com.evilbird.warcraft.item.hud.resource.ResourcePane;
import com.evilbird.warcraft.item.unit.resource.ResourceType;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static com.evilbird.engine.item.utility.ItemPredicates.itemWithId;
import static com.evilbird.engine.item.utility.ItemPredicates.selectedItem;
import static com.evilbird.warcraft.item.common.query.UnitOperations.getHumanPlayer;

public class HudBehaviour implements Behaviour
{
    private EventQueue events;
    private Player player;
    private ResourcePane resourcePane;
    private ActionPane actionPane;
    private StatusPane statusPane;

    @Inject
    public HudBehaviour(EventQueue events) {
        this.events = events;
    }

    @Override
    public Identifier getIdentifier() {
        return null; //TODO
    }

    @Override
    public void update(State state, List<UserInput> inputs) {
        ItemRoot world = state.getWorld();
        ItemRoot hud = state.getHud();

        if (initialized(world, hud)) {
            updateResources();
            updateSelection();
        }
        else {
            initializeResources();
            initializeSelection(world);
        }
    }

    private boolean initialized(ItemRoot world, ItemRoot hud) {
        if (player == null) {
            player = getHumanPlayer(world);
            resourcePane = (ResourcePane)hud.find(itemWithId(HudControl.ResourcePane));
            actionPane = (ActionPane)hud.find(itemWithId(HudControl.ActionPane));
            statusPane = (StatusPane)hud.find(itemWithId(HudControl.StatePane));
            return false;
        }
        return true;
    }

    private void updateResources() {
        for (ResourceTransferEvent event: events.getEvents(ResourceTransferEvent.class)) {
            if (Objects.equals(event.getSubject(), player)) {
                ResourceType resource = (ResourceType)event.getResource();

                if (resource == ResourceType.Gold) {
                    resourcePane.setGold(event.getValue());
                }
                else if (resource == ResourceType.Oil) {
                    resourcePane.setOil(event.getValue());
                }
                else if (resource == ResourceType.Wood) {
                    resourcePane.setWood(event.getValue());
                }
            }
        }
    }

    private void updateSelection() {
        for (SelectEvent event: events.getEvents(SelectEvent.class)) {
            Item subject = event.getSubject();
            boolean selected = event.getSelected();

            actionPane.updateSelection(subject, selected);
            statusPane.updateSelection(subject, selected);
        }
    }

    private void initializeResources() {
        resourcePane.setGold(player.getResource(ResourceType.Gold));
        resourcePane.setOil(player.getResource(ResourceType.Oil));
        resourcePane.setWood(player.getResource(ResourceType.Wood));
    }

    private void initializeSelection(ItemRoot world) {
        Collection<Item> selection = world.findAll(selectedItem());
        actionPane.updateSelection(selection);
        statusPane.updateSelection(selection);
    }
}
