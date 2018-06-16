package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.common.CreateAction;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.action.framework.duration.TimeDuration;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.action.common.ProgressAction;
import com.evilbird.warcraft.action.common.ResourceTransferAction;
import com.evilbird.warcraft.action.type.TrainAction;
import com.evilbird.warcraft.item.common.capability.ResourceContainer;
import com.evilbird.warcraft.item.common.capability.ResourceIdentifier;
import com.evilbird.warcraft.item.data.DataType;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import static com.evilbird.engine.item.ItemOperations.findAncestorByType;

/**
 * Instances of this action sequence create a new unit, decrementing the
 * players resources and adding delay before the new unit can be used.
 *
 * @author Blair Butterworth
 */
public class TrainSequence implements ActionProvider
{
    private ItemFactory itemFactory;

    @Inject
    public TrainSequence(ItemFactory itemFactory) {
        this.itemFactory = itemFactory;
    }

    @Override
    public Action get(ActionIdentifier actionType, Item building, Item target, UserInput input) {
        TrainAction action = (TrainAction)actionType;
        Action purchase = decrementResources(action, building);
        Action train = trainUnit(action, building);
        Action create = createUnit(action, building);
        return new SequenceAction(purchase, train, create);
    }

    private Action decrementResources(TrainAction action, Item building) {
        Item player = findAncestorByType(building, DataType.Player);
        List<Action> resourceTransfers = new ArrayList<>();

        for (Entry<ResourceIdentifier, Float> cost: action.getResourceRequirements().entrySet()) {
            resourceTransfers.add(new ResourceTransferAction((ResourceContainer)player, cost.getKey(), cost.getValue() * -1));
        }
        return new ParallelAction(resourceTransfers);
    }

    private Action trainUnit(TrainAction action, Item building) {
        return new ProgressAction((Building)building, new TimeDuration(action.getTrainTime()));
    }

    private Action createUnit(TrainAction action, Item building) {
        ItemComposite player = building.getParent();
        Identifier identifier = new NamedIdentifier();
        Vector2 location = building.getPosition();
        Vector2 position = new Vector2(location.x - 32f, location.y); //TODO - use width of created item
        return new CreateAction(player, action.getUnitType(), itemFactory, identifier, position, false);
    }
}
