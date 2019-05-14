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
import com.evilbird.warcraft.action.attack.AttackEvent;
import com.evilbird.warcraft.action.attack.AttackStatus;
import com.evilbird.warcraft.action.common.resource.ResourceTransferEvent;
import com.evilbird.warcraft.action.construct.ConstructEvent;
import com.evilbird.warcraft.action.construct.ConstructStatus;
import com.evilbird.warcraft.action.gather.GatherEvent;
import com.evilbird.warcraft.action.gather.GatherStatus;
import com.evilbird.warcraft.action.placeholder.PlaceholderEvent;
import com.evilbird.warcraft.action.placeholder.PlaceholderStatus;
import com.evilbird.warcraft.action.select.SelectEvent;
import com.evilbird.warcraft.action.train.TrainEvent;
import com.evilbird.warcraft.action.train.TrainStatus;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.data.player.PlayerStatistic;
import com.evilbird.warcraft.item.hud.HudControl;
import com.evilbird.warcraft.item.hud.control.actions.ActionPane;
import com.evilbird.warcraft.item.hud.control.status.StatusPane;
import com.evilbird.warcraft.item.hud.resource.ResourcePane;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.building.Building;
import com.evilbird.warcraft.item.unit.resource.Resource;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

import static com.evilbird.engine.item.utility.ItemPredicates.itemWithId;
import static com.evilbird.warcraft.item.common.query.UnitOperations.getHumanPlayer;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isSelected;
import static com.evilbird.warcraft.item.data.player.PlayerScore.getScoreValue;
import static com.evilbird.warcraft.item.data.player.PlayerStatistic.Score;

/**
 * Instances of this behaviour apply the user interface based on game state
 * changes. Specifically it provides the heads up display with the currently
 * selected items and player resources. It also updates player statistics, the
 * number of units and buildings created and killed and the number of resources
 * gathered.
 *
 * @author Blair Butterworth
 */
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
            updatePlaceholderRecipients();
            updateTrainingRecipients();
            updateAttackRecipients();
            updateGatherRecipients();
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
        for (ResourceType resourceType: ResourceType.values()) {
            float resourceValue = player.getResource(resourceType);
            actionPane.setResource(resourceType, resourceValue);
            resourcePane.setResourceText(resourceType, resourceValue);
        }
    }

    private void initializeSelection(ItemRoot world) {
        Collection<Item> selection = world.findAll(isSelected());
        actionPane.setSelected(selection);
        statusPane.setSelected(selection);
    }

    private void updateSelectionRecipients() {
        for (SelectEvent event: events.getEvents(SelectEvent.class)) {
            actionPane.setSelected(event.getSubject(), event.getSelected());
            statusPane.setSelected(event.getSubject(), event.getSelected());
        }
    }

    private void updateResourceRecipients() {
        for (ResourceTransferEvent event: events.getEvents(ResourceTransferEvent.class)) {
            if (event.getSubject() == player) {
                actionPane.setResource(event.getResource(), event.getValue());
                resourcePane.setResourceText(event.getResource(), event.getValue());
            }
        }
    }

    private void updateConstructionRecipients() {
        for (ConstructEvent event: events.getEvents(ConstructEvent.class)) {
            actionPane.setConstructing(event.getBuilding(), event.isConstructing());
            statusPane.setConstructing(event.getBuilding(), event.isConstructing());

            if (event.getStatus() == ConstructStatus.Complete) {
                Player player = UnitOperations.getPlayer(event.getSubject());
                player.incrementStatistic(PlayerStatistic.Buildings, 1);
            }
        }
    }

    private void updatePlaceholderRecipients() {
        for (PlaceholderEvent event: events.getEvents(PlaceholderEvent.class)) {
            actionPane.setPlaceholder(event.getBuilder(), event.getStatus() == PlaceholderStatus.Added);
        }
    }

    private void updateTrainingRecipients() {
        for (TrainEvent event: events.getEvents(TrainEvent.class)) {
            actionPane.setProducing(event.getBuilding(), event.isTraining());
            statusPane.setProducing(event.getBuilding(), event.isTraining());

            if (event.getStatus() == TrainStatus.Complete) {
                Player player = UnitOperations.getPlayer(event.getSubject());
                player.incrementStatistic(PlayerStatistic.Units, 1);
            }
        }
    }

    private void updateAttackRecipients() {
        for (AttackEvent event: events.getEvents(AttackEvent.class)) {
            if (event.getStatus() == AttackStatus.Complete) {
                PlayerStatistic statistic = getAttackStat(event.getTarget());
                Player player = UnitOperations.getPlayer(event.getSubject());
                player.incrementStatistic(statistic, 1);
                player.incrementStatistic(Score, getScoreValue(event.getTarget()));
            }
        }
    }

    private PlayerStatistic getAttackStat(Item target) {
        if (target instanceof Building || target instanceof Resource) {
            return PlayerStatistic.Razed;
        }
        if (target instanceof Unit) {
            return PlayerStatistic.Units;
        }
        throw new UnsupportedOperationException();
    }

    private void updateGatherRecipients() {
        for (GatherEvent event: events.getEvents(GatherEvent.class)) {
            if (event.getStatus() == GatherStatus.DepositStarted) {
                PlayerStatistic statistic = getResourceStat(event.getType());
                Player player = UnitOperations.getPlayer(event.getSubject());
                player.incrementStatistic(statistic, event.getValue());
            }
        }
    }

    private PlayerStatistic getResourceStat(ResourceType type) {
        switch (type) {
            case Gold: return PlayerStatistic.Gold;
            case Oil: return PlayerStatistic.Oil;
            case Wood: return PlayerStatistic.Wood;
            default: throw new UnsupportedOperationException();
        }
    }
}
