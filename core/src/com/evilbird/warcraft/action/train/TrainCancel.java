/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.train;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ActionContext;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.common.ReplacementAction;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.common.ResourceTransferAction;
import com.evilbird.warcraft.item.common.capability.ResourceContainer;
import com.evilbird.warcraft.item.common.capability.ResourceIdentifier;
import com.evilbird.warcraft.item.common.resource.ResourceUtils;
import com.evilbird.warcraft.item.data.DataType;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;
import java.util.Map;

import static com.evilbird.engine.item.ItemOperations.findAncestorByType;

/**
 * Instances of this class stop the training of a unit.
 *
 * @author Blair Butterworth
 */
public class TrainCancel implements ActionProvider
{
    @Inject
    public TrainCancel() {
    }

    @Override
    public Action get(ActionIdentifier action, ActionContext context) {
        Item item = context.getItem();
        Action restoreResources = restoreResources((TrainActions)action, (Building)item);
        return new ReplacementAction(item, restoreResources);
    }

    private Action restoreResources(TrainActions type, Building building) {
        ResourceContainer player = (ResourceContainer)findAncestorByType(building, DataType.Player);
        Map<ResourceIdentifier, Float> requirements = type.getResourceRequirements();
        Map<ResourceIdentifier, Float> resources = ResourceUtils.scale(requirements, 1 - building.getProgress());
        return new ResourceTransferAction(player, resources);
    }
}