/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.action.framework.StateTransitionAction;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.utility.ItemOperations;
import com.evilbird.warcraft.action.common.associate.AssociateAction;
import com.evilbird.warcraft.action.common.create.CreateAction;
import com.evilbird.warcraft.action.common.remove.RemoveOccluding;
import com.evilbird.warcraft.action.common.remove.RemoveTarget;
import com.evilbird.warcraft.action.common.transfer.TransferAction;
import com.evilbird.warcraft.action.move.MoveToItemAction;
import com.evilbird.warcraft.action.select.DeselectAction;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.common.resource.ResourceQuantity;
import com.evilbird.warcraft.item.common.resource.ResourceUtils;
import com.evilbird.warcraft.item.unit.UnitCosts;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;
import java.util.Collection;

import static com.evilbird.engine.action.common.ActionRecipient.Player;
import static com.evilbird.engine.action.common.ActionRecipient.Target;
import static com.evilbird.warcraft.item.unit.UnitAnimation.BuildingSite;

/**
 * Instances of this class construct a building.
 *
 * @author Blair Butterworth
 */
public class ConstructSequence extends StateTransitionAction
{
    private Action reposition;
    private Action remove;
    private Action initialize;
    private Action build;

    @Inject
    public ConstructSequence(
        AssociateAction associate,
        ConstructAction construct,
        CreateAction create,
        DeselectAction deselect,
        MoveToItemAction move,
        RemoveOccluding removeCorpses,
        RemoveTarget removePlaceholder,
        TransferAction purchase,
        TransferAction transfer)
    {
        reposition = add(new ParallelAction(deselect, move));
        remove = add(new ParallelAction(purchase, removePlaceholder, removeCorpses));
        initialize = add(new SequenceAction(remove, create, associate));
        build = add(new SequenceAction(construct, transfer));

        create.setType(this::getBuildingType);
        create.setRecipient(this::setTarget);
        create.setProperties(this::setBuildingProperties);

        purchase.setParties(Player, null);
        purchase.setAmount(this::getBuildingCost);

        transfer.setParties(Target, Player);
        transfer.setAmount(this::getBuildingResources);
    }

    @Override
    protected Action nextAction(Action previous) {
        return nextAction(getItem(), getTarget());
    }

    private Action nextAction(Item builder, Item building) {
        if (!ItemOperations.isNear(builder, builder.getWidth(), building)) {
            return reposition;
        }
        if (UnitOperations.isBuildingPlaceholder(building)) {
            return initialize;
        }
        if (UnitOperations.isConstructing(building)) {
            return build;
        }
        return null;
    }

    private Collection<ResourceQuantity> getBuildingCost() {
        return UnitCosts.cost(getBuildingType());
    }

    private Collection<ResourceQuantity> getBuildingResources() {
        return ResourceUtils.getResources((Building)getTarget());
    }

    private UnitType getBuildingType() {
        ConstructActions constructAction = (ConstructActions)getIdentifier();
        return constructAction.getProduct();
    }

    private void setBuildingProperties(Item item) {
        Item target = getTarget();
        Building building = (Building)item;
        building.setConstructionProgress(0);
        building.setAnimation(BuildingSite);
        building.setPosition(target.getPosition());
        building.setVisible(true);
    }
}
