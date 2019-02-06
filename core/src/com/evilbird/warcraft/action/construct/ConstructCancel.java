/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.action.ActionContext;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.common.AlignAction;
import com.evilbird.engine.action.common.ReplacementAction;
import com.evilbird.engine.action.common.VisibleAction;
import com.evilbird.engine.action.framework.Action;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.attack.DeathAction;
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
 * Instances of this class stop the construction of a building.
 *
 * @author Blair Butterworth
 */
public class ConstructCancel implements ActionProvider
{
    @Inject
    public ConstructCancel() {
    }

    @Override
    public Action get(ActionIdentifier action, ActionContext context) {
        Action stopConstructing = stopConstructing((ConstructActions)action, (Building)context.getItem());
        return new ReplacementAction(context.getItem(), stopConstructing);
    }

    private Action stopConstructing(ConstructActions type, Building building) {
        Action restoreResources = restoreResources(type, building);
        Action restoreBuilder = restoreBuilder(building);
        Action removeBuilding = removeBuilding(building);
        return new ParallelAction(restoreResources, restoreBuilder, removeBuilding);
    }

    private Action restoreResources(ConstructActions type, Building building) {
        ResourceContainer player = (ResourceContainer)findAncestorByType(building, DataType.Player);
        Map<ResourceIdentifier, Float> requirements = type.getResourceRequirements();
        Map<ResourceIdentifier, Float> resources = ResourceUtils.scale(requirements, 1 - building.getProgress());
        return new ResourceTransferAction(player, resources);
    }

    private Action restoreBuilder(Building building) {
        Item builder = building.getBuilder();
        Action reposition = new AlignAction(builder, building, Alignment.BottomLeft);
        Action show = new VisibleAction(builder, true);
        return new ParallelAction(reposition, show);
    }

    private Action removeBuilding(Building building) {
        //Action constructing = new ConstructAction(building, null, false);
        Action destroy = new DeathAction(building);
        return new ParallelAction(/*constructing,*/ destroy);
    }
}
