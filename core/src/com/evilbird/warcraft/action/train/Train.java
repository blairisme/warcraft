/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.train;

import com.evilbird.engine.action.ActionContext;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.common.AlignAction;
import com.evilbird.engine.action.common.CreateAction;
import com.evilbird.engine.action.framework.Action;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.action.framework.duration.TimeDuration;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.Reference;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.common.ProgressAction;
import com.evilbird.warcraft.action.common.ResourceTransferAction;
import com.evilbird.warcraft.item.common.capability.ResourceContainer;
import com.evilbird.warcraft.item.common.capability.ResourceIdentifier;
import com.evilbird.warcraft.item.common.resource.ResourceUtils;
import com.evilbird.warcraft.item.data.DataType;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;
import java.util.Map;

import static com.evilbird.engine.common.function.Suppliers.constantValue;
import static com.evilbird.engine.common.lang.Alignment.BottomLeft;
import static com.evilbird.engine.item.ItemOperations.findAncestorByType;

/**
 * Instances of this action sequence create a new unit, decrementing the
 * players resources and adding delay before the new unit can be used.
 *
 * @author Blair Butterworth
 */
public class Train implements ActionProvider
{
    private ItemFactory itemFactory;

    @Inject
    public Train(ItemFactory itemFactory) {
        this.itemFactory = itemFactory;
    }

    @Override
    public Action get(ActionIdentifier action, ActionContext context) {
        return train((TrainActions)action, (Building)context.getItem());
    }

    private Action train(TrainActions action, Building building) {
        Action purchase = purchaseUnit(action, building);
        Action train = trainUnit(action, building);
        Action create = createUnit(action, building);
        return new SequenceAction(purchase, train, create);
    }

    private Action purchaseUnit(TrainActions type, Building building) {
        ResourceContainer player = (ResourceContainer)findAncestorByType(building, DataType.Player);
        Map<ResourceIdentifier, Float> requirements = type.getResourceRequirements();
        Map<ResourceIdentifier, Float> resources = ResourceUtils.negate(requirements);
        return new ResourceTransferAction(player, resources);
    }

    private Action trainUnit(TrainActions action, Building building) {
        ProducingAction before = new ProducingAction(building, true);
        ProgressAction progress = new ProgressAction(building, new TimeDuration(action.getTrainTime()));
        ProducingAction after = new ProducingAction(building, false);
        return new SequenceAction(before, progress, after);
    }

    private Action createUnit(TrainActions action, Building building) {
        ItemComposite parent = building.getParent();
        Identifier identifier = new NamedIdentifier();
        Reference<Item> reference = new Reference<>(parent, identifier);

        Action create = new CreateAction(parent, action.getUnitType(), itemFactory, identifier, building.getPosition(), false);
        Action reposition = new AlignAction(reference, constantValue(building), BottomLeft);

        return new SequenceAction(create, reposition);
    }
}
