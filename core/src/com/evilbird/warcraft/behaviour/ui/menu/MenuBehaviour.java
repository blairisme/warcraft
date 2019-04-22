/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ui.menu;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.state.State;
import com.evilbird.warcraft.action.common.create.CreateEvent;
import com.evilbird.warcraft.action.common.resource.ResourceTransferEvent;
import com.evilbird.warcraft.action.construct.ConstructEvent;
import com.evilbird.warcraft.action.select.SelectEvent;
import com.evilbird.warcraft.action.train.TrainEvent;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.data.player.PlayerStatisticType;
import com.evilbird.warcraft.item.hud.HudControl;
import com.evilbird.warcraft.item.hud.control.actions.ActionPane;
import com.evilbird.warcraft.item.hud.control.status.StatusPane;
import com.evilbird.warcraft.item.hud.resource.ResourcePane;
import com.evilbird.warcraft.item.unit.building.Building;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

import static com.evilbird.engine.item.utility.ItemPredicates.itemWithId;
import static com.evilbird.engine.item.utility.ItemPredicates.selectedItem;
import static com.evilbird.warcraft.item.common.query.UnitOperations.getHumanPlayer;

public class MenuBehaviour implements Behaviour
{
    private EventQueue events;
    private Player player;
    private ActionPane actionPane;
    private StatusPane statusPane;
    private ResourcePane resourcePane;

    @Inject
    public MenuBehaviour(EventQueue events) {
        this.events = events;
    }

    @Override
    public void update(State state, List<UserInput> inputs) {
        ItemRoot world = state.getWorld();
        ItemRoot hud = state.getHud();

        if (initialized(world, hud)) {
            updateResourceRecipients();
            updateSelectionRecipients();
            updateConstructionRecipients();
            updateTrainingRecipients();
            updateCreationRecipients();
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

    private void initializeResources() {
        resourcePane.setGold(player.getResource(ResourceType.Gold));
        resourcePane.setOil(player.getResource(ResourceType.Oil));
        resourcePane.setWood(player.getResource(ResourceType.Wood));
    }

    private void initializeSelection(ItemRoot world) {
        Collection<Item> selection = world.findAll(selectedItem());
        actionPane.setSelected(selection);
        statusPane.setSelected(selection);
    }

    private void updateResourceRecipients() {
        for (ResourceTransferEvent event: events.getEvents(ResourceTransferEvent.class)) {
            if (event.getSubject() == player) {
                resourcePane.setResource(event.getResource(), event.getValue());
            }
        }
    }

    private PlayerStatisticType getResourceStat(ResourceType type) {
        switch (type) {
            case Gold: return PlayerStatisticType.Gold;
            case Oil: return PlayerStatisticType.Oil;
            case Wood: return PlayerStatisticType.Wood;
            default: throw new UnsupportedOperationException();
        }
    }

    private void updateSelectionRecipients() {
        for (SelectEvent event: events.getEvents(SelectEvent.class)) {
            Item subject = event.getSubject();
            boolean selected = event.getSelected();

            actionPane.setSelected(subject, selected);
            statusPane.setSelected(subject, selected);
        }
    }

    private void updateConstructionRecipients() {
        for (ConstructEvent event: events.getEvents(ConstructEvent.class)) {
            Building building = event.getBuilding();
            boolean constructing = event.isConstructing();

            actionPane.setConstructing(building, constructing);
            statusPane.setConstructing(building, constructing);
        }
    }

    private void updateTrainingRecipients() {
        for (TrainEvent event: events.getEvents(TrainEvent.class)) {
            Building building = event.getBuilding();
            boolean training = event.isTraining();

            actionPane.setProducing(building, training);
            statusPane.setProducing(building, training);
        }
    }

    private void updateCreationRecipients() {
        for (CreateEvent event: events.getEvents(CreateEvent.class)) {
            Item subject = event.getSubject();
            Item parent = subject.getParent();
            if (parent == player) {
                if (subject instanceof Building) {
                    player.incrementStatistic(PlayerStatisticType.Buildings, 1);
                } else if (subject instanceof Combatant) {
                    player.incrementStatistic(PlayerStatisticType.Units, 1);
                }
            }
        }
    }

    private void updateRemovalRecipients() {

    }
}
