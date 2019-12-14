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
import com.evilbird.engine.events.Events;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.state.State;
import com.evilbird.warcraft.action.attack.AttackEvent;
import com.evilbird.warcraft.action.attack.AttackStatus;
import com.evilbird.warcraft.action.common.transfer.TransferEvent;
import com.evilbird.warcraft.action.construct.ConstructEvent;
import com.evilbird.warcraft.action.construct.ConstructStatus;
import com.evilbird.warcraft.action.gather.GatherEvent;
import com.evilbird.warcraft.action.gather.GatherStatus;
import com.evilbird.warcraft.action.produce.ProduceEvent;
import com.evilbird.warcraft.action.produce.ProduceStatus;
import com.evilbird.warcraft.action.selection.SelectEvent;
import com.evilbird.warcraft.action.selector.SelectorEvent;
import com.evilbird.warcraft.action.selector.SelectorStatus;
import com.evilbird.warcraft.data.resource.ResourceType;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.data.player.PlayerStatistic;
import com.evilbird.warcraft.object.display.HudControl;
import com.evilbird.warcraft.object.display.control.actions.ActionPane;
import com.evilbird.warcraft.object.display.control.status.StatusPane;
import com.evilbird.warcraft.object.display.resource.ResourcePane;
import com.evilbird.warcraft.object.layer.wall.WallSection;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.building.Building;
import com.evilbird.warcraft.object.unit.resource.Resource;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

import static com.evilbird.engine.object.utility.GameObjectPredicates.itemWithId;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.isSelected;
import static com.evilbird.warcraft.object.data.player.PlayerScore.getScoreValue;
import static com.evilbird.warcraft.object.data.player.PlayerStatistic.Score;

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
    private Events events;
    private Player player;
    private ActionPane actionPane;
    private StatusPane statusPane;
    private ResourcePane resourcePane;

    @Inject
    public MenuBehaviour(Events events) {
        this.events = events;
    }

    @Override
    public void update(State state, List<UserInput> inputs, float time) {
        GameObjectContainer world = state.getWorld();
        GameObjectContainer hud = state.getHud();

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
            initializePopulation(world);
        }
    }

    private boolean initialized(GameObjectContainer world, GameObjectContainer hud) {
        if (player == null) {
            player = UnitOperations.getCorporealPlayer(world);
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
            actionPane.setPlayerResource(resourceType, resourceValue);
            resourcePane.setPlayerResource(resourceType, resourceValue);
            statusPane.setPlayerResource(resourceType, resourceValue);
        }
    }

    private void initializeSelection(GameObjectContainer world) {
        Collection<GameObject> selection = world.findAll(isSelected());
        actionPane.setSelected(selection);
        statusPane.setSelected(selection);
    }

    private void initializePopulation(GameObjectContainer world) {
        for (GameObject farm: world.findAll(UnitOperations::isFoodProducer)){
            player.incrementStatistic(PlayerStatistic.Population, 5);
        }
    }

    private void updateSelectionRecipients() {
        for (SelectEvent event: events.getEvents(SelectEvent.class)) {
            actionPane.setSelected(event.getSubject(), event.getSelected());
            statusPane.setSelected(event.getSubject(), event.getSelected());
        }
    }

    private void updateResourceRecipients() {
        for (TransferEvent event: events.getEvents(TransferEvent.class)) {
            GameObject subject = event.getSubject();
            ResourceType resource = event.getResource();
            float amount = event.getValue();

            if (subject == player) {
                actionPane.setPlayerResource(resource, amount);
                resourcePane.setPlayerResource(resource, amount);
                statusPane.setPlayerResource(resource, amount);
            } else {
                statusPane.setItemResource(subject, resource, amount);
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

                UnitType buildingType = (UnitType)event.getBuilding().getType();
                if (buildingType.isFoodProducer()) {
                    player.incrementStatistic(PlayerStatistic.Population, 5);
                }
            }
        }
    }

    private void updatePlaceholderRecipients() {
        for (SelectorEvent event: events.getEvents(SelectorEvent.class)) {
            actionPane.setPlaceholder(event.getOwner(), event.getStatus() == SelectorStatus.Added);
        }
    }

    private void updateTrainingRecipients() {
        for (ProduceEvent event: events.getEvents(ProduceEvent.class)) {
            actionPane.setProducing(event.getBuilding(), event.isTraining());
            statusPane.setProducing(event.getBuilding(), event.isTraining());

            if (event.getStatus() == ProduceStatus.Complete) {
                Player player = UnitOperations.getPlayer(event.getSubject());
                player.incrementStatistic(PlayerStatistic.Units, 1);
            }
        }
    }

    private void updateAttackRecipients() {
        for (AttackEvent event: events.getEvents(AttackEvent.class)) {
            if (event.getStatus() == AttackStatus.Complete) {
                Player player = UnitOperations.getPlayer(event.getSubject());
                player.incrementStatistic(getAttackStat(event.getTarget()), 1);
                player.incrementStatistic(Score, getScoreValue(event.getTarget()));

                if (event.getTarget().getType() == UnitType.Farm) {
                    player.decrementStatistic(PlayerStatistic.Population, 5);
                }
            }
        }
    }

    private PlayerStatistic getAttackStat(GameObject target) {
        if (target instanceof Building || target instanceof Resource || target instanceof WallSection) {
            return PlayerStatistic.Razed;
        }
        if (target instanceof Unit) {
            return PlayerStatistic.Kills;
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
