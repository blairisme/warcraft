package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.common.CreateAction;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.action.framework.duration.TimeDuration;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.Reference;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.component.AlignAction;
import com.evilbird.warcraft.action.component.ProducingAction;
import com.evilbird.warcraft.action.component.ProgressAction;
import com.evilbird.warcraft.action.component.ResourceTransferAction;
import com.evilbird.warcraft.action.identifier.TrainActionType;
import com.evilbird.warcraft.item.common.capability.ResourceContainer;
import com.evilbird.warcraft.item.data.DataType;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;

import static com.evilbird.engine.common.function.Suppliers.constantValue;
import static com.evilbird.engine.common.lang.Alignment.BottomLeft;
import static com.evilbird.engine.item.ItemOperations.findAncestorByType;

/**
 * Instances of this action sequence create a new unit, decrementing the
 * players resources and adding delay before the new unit can be used.
 *
 * @author Blair Butterworth
 */
//TODO - Relocate unit using width of created item
public class TrainSequence implements ActionProvider
{
    private ItemFactory itemFactory;

    @Inject
    public TrainSequence(ItemFactory itemFactory) {
        this.itemFactory = itemFactory;
    }

    @Override
    public Action get(ActionIdentifier actionType, Item item, Item target, UserInput input) {
        return train((TrainActionType)actionType, (Building)item);
    }

    private Action train(TrainActionType action, Building building) {
        Action purchase = purchaseUnit(action, building);
        Action train = trainUnit(action, building);
        Action create = createUnit(action, building);
        return new SequenceAction(purchase, train, create);
    }

    private Action purchaseUnit(TrainActionType action, Building building) {
        Item player = findAncestorByType(building, DataType.Player);
        return new ResourceTransferAction((ResourceContainer)player, action.getResourceRequirements());
    }

    private Action trainUnit(TrainActionType action, Building building) {
        ProducingAction before = new ProducingAction(building, true);
        ProgressAction progress = new ProgressAction(building, new TimeDuration(action.getTrainTime()));
        ProducingAction after = new ProducingAction(building, false);
        return new SequenceAction(before, progress, after);
    }

    private Action createUnit(TrainActionType action, Building building) {
        ItemComposite parent = building.getParent();
        Identifier identifier = new NamedIdentifier();
        Reference<Item> reference = new Reference<>(parent, identifier);

        Action create = new CreateAction(parent, action.getUnitType(), itemFactory, identifier, building.getPosition(), false);
        Action reposition = new AlignAction(reference, constantValue(building), BottomLeft);

        return new SequenceAction(create, reposition);
    }
}
