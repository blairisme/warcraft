/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.warcraft.action.produce.ProduceUnitActions;
import com.evilbird.warcraft.action.produce.ProduceUpgradeActions;
import com.evilbird.warcraft.data.upgrade.Upgrade;
import com.evilbird.warcraft.object.display.components.actions.ActionButtonType;
import com.evilbird.warcraft.object.unit.UnitType;

import javax.inject.Inject;
import javax.inject.Provider;

import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionApplicability.Selected;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.isBuilding;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.CancelButton;

/**
 * Defines user interactions that result in the creation of new game
 * objects or upgrades.
 *
 * @author Blair Butterworth
 */
public class ProduceInteractions extends InteractionContainer
{
    /**
     * Constructs a new instance of this class given a {@link InteractionDefinition}
     * factory, used by {@link #addAction} to add {@link Interaction Interactions}
     * for actions.
     */
    @Inject
    public ProduceInteractions(Provider<InteractionDefinition> factory) {
        super(factory);
        produceUnits();
        produceUpgrades();
    }

    private void produceUnits() {
        for (ActionButtonType button: ActionButtonType.values()) {
            if (button.isTrainButton()) {
                UnitType product = button.getTrainProduct();
                ProduceUnitActions action = ProduceUnitActions.forProduct(product);
                ProduceUnitActions cancel = ProduceUnitActions.forProductCancel(product);
                produce(button, action, cancel);
            }
        }
    }

    private void produceUpgrades() {
        for (ActionButtonType button: ActionButtonType.values()) {
            if (button.isUpgradeButton()) {
                Upgrade upgrade = button.getUpgradeProduct();
                ProduceUpgradeActions action = ProduceUpgradeActions.forProduct(upgrade);
                ProduceUpgradeActions cancel = ProduceUpgradeActions.forProductCancel(upgrade);
                produce(button, action, cancel);
            }
        }
    }

    private void produce(
        ActionButtonType button,
        ActionIdentifier action,
        ActionIdentifier cancel)
    {
        addAction(action)
            .whenSelected(isBuilding())
            .whenTarget(button)
            .appliedTo(Selected);

        addAction(cancel)
            .whenSelected(isBuilding())
            .whenTarget(CancelButton)
            .withAction(action)
            .appliedTo(Selected);
    }
}
