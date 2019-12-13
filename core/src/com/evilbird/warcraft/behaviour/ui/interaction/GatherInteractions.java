/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.data.resource.ResourceType;

import javax.inject.Inject;
import javax.inject.Provider;

import static com.evilbird.engine.common.function.Predicates.all;
import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.warcraft.action.confirm.ConfirmActions.ConfirmTarget;
import static com.evilbird.warcraft.action.gather.GatherActions.GatherCancel;
import static com.evilbird.warcraft.action.gather.GatherActions.GatherGold;
import static com.evilbird.warcraft.action.gather.GatherActions.GatherOil;
import static com.evilbird.warcraft.action.gather.GatherActions.GatherWood;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionApplicability.Selected;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.hasResources;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.isControllable;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.isDepotFor;
import static com.evilbird.warcraft.data.resource.ResourceType.Gold;
import static com.evilbird.warcraft.data.resource.ResourceType.Oil;
import static com.evilbird.warcraft.data.resource.ResourceType.Wood;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.CancelButton;
import static com.evilbird.warcraft.object.layer.LayerType.Tree;
import static com.evilbird.warcraft.object.unit.UnitType.GoldMine;
import static com.evilbird.warcraft.object.unit.UnitType.OilPlatform;

/**
 * Defines user interactions that result in game objects gathering resources.
 *
 * @author Blair Butterworth
 */
public class GatherInteractions extends InteractionContainer
{
    /**
     * Constructs a new instance of this class given a {@link InteractionDefinition}
     * factory, used by {@link #addAction} to add {@link Interaction Interactions}
     * for actions.
     */
    @Inject
    public GatherInteractions(Provider<InteractionDefinition> factory) {
        super(factory);
        gather(GatherGold, GoldMine, Gold);
        gather(GatherWood, Tree, Wood);
        gather(GatherOil, OilPlatform, Oil);
    }

    private void gather(ActionIdentifier action, Identifier resource, ResourceType type) {
        gatherByResource(action, resource);
        gatherByDepot(action, type);
        gatherCancel(action);
    }

    private void gatherByResource(ActionIdentifier action, Identifier resource) {
        addAction(action, ConfirmTarget)
            .whenSelected(both(isControllable(), UnitOperations::isGatherer))
            .whenTarget(resource)
            .appliedTo(Selected)
            .appliedAs(confirmedAction());
    }

    private void gatherByDepot(ActionIdentifier action, ResourceType type) {
        addAction(action, ConfirmTarget)
            .whenSelected(all(isControllable(), UnitOperations::isGatherer, hasResources(type)))
            .whenTarget(isDepotFor(type))
            .appliedTo(Selected)
            .appliedAs(confirmedAction());
    }

    private void gatherCancel(ActionIdentifier action) {
        addAction(GatherCancel)
            .whenSelected(both(isControllable(), UnitOperations::isGatherer))
            .whenTarget(CancelButton)
            .withAction(action)
            .appliedTo(Selected);
    }
}
