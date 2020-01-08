/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.warcraft.action.selector.SelectorActions;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.display.components.actions.ActionButtonType;
import com.evilbird.warcraft.object.selector.SelectorType;
import com.evilbird.warcraft.object.unit.UnitType;

import javax.inject.Inject;
import javax.inject.Provider;

import static com.evilbird.engine.common.function.Predicates.nonNull;
import static com.evilbird.engine.device.UserInputType.Drag;
import static com.evilbird.engine.device.UserInputType.PressDown;
import static com.evilbird.engine.device.UserInputType.PressDrag;
import static com.evilbird.engine.device.UserInputType.PressUp;
import static com.evilbird.warcraft.action.selector.SelectorActions.HideAreaSelector;
import static com.evilbird.warcraft.action.selector.SelectorActions.HideSelector;
import static com.evilbird.warcraft.action.selector.SelectorActions.MoveSelector;
import static com.evilbird.warcraft.action.selector.SelectorActions.ResizeAreaSelector;
import static com.evilbird.warcraft.action.selector.SelectorActions.ShowAreaSelector;
import static com.evilbird.warcraft.action.selector.SelectorActions.ShowBlizzardSelector;
import static com.evilbird.warcraft.action.selector.SelectorActions.ShowDeathAndDecaySelector;
import static com.evilbird.warcraft.action.selector.SelectorActions.ShowDemolitionSelector;
import static com.evilbird.warcraft.action.selector.SelectorActions.ShowFireballSelector;
import static com.evilbird.warcraft.action.selector.SelectorActions.ShowHolyVisionSelector;
import static com.evilbird.warcraft.action.selector.SelectorActions.ShowRuneTrapSelector;
import static com.evilbird.warcraft.action.selector.SelectorActions.ShowWhirlwindSelector;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionApplicability.Selected;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionApplicability.Target;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionDisplacement.Addition;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.BlizzardButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.CancelButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.DeathAndDecayButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.DetonateButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.FireballButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.HolyVisionButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.RunesButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.WhirlwindButton;

/**
 * Defines user interactions that result in selectors.
 *
 * @author Blair Butterworth
 */
public class SelectorInteractions extends InteractionContainer
{
    /**
     * Constructs a new instance of this class given a {@link InteractionDefinition}
     * factory, used by {@link #addAction} to add {@link Interaction Interactions}
     * for actions.
     */
    @Inject
    public SelectorInteractions(Provider<InteractionDefinition> factory) {
        super(factory);
        showSelectors();
        hideSelectors();
        moveSelectors();
        resizeSelectors();
    }

    private void showSelectors() {
        showAreaSelector();
        showBuildingSelectors();
        showTargetSelectors();
    }

    private void showAreaSelector() {
        addAction(ShowAreaSelector)
            .forInput(PressDown)
            .whenTarget(nonNull())
            .appliedTo(Target)
            .appliedAs(Addition);
    }

    private void showBuildingSelectors() {
        for (ActionButtonType button: ActionButtonType.values()) {
            if (button.isBuildButton()) {
                UnitType building = button.getBuildProduct();
                SelectorType selector = SelectorType.forBuilding(building);
                SelectorActions action = SelectorActions.forSelector(selector);
                showSelector(button, action);
            }
        }
    }

    private void showTargetSelectors() {
        showSelector(BlizzardButton, ShowBlizzardSelector);
        showSelector(DeathAndDecayButton, ShowDeathAndDecaySelector);
        showSelector(DetonateButton, ShowDemolitionSelector);
        showSelector(FireballButton, ShowFireballSelector);
        showSelector(HolyVisionButton, ShowHolyVisionSelector);
        showSelector(RunesButton, ShowRuneTrapSelector);
        showSelector(WhirlwindButton, ShowWhirlwindSelector);
    }

    private void showSelector(ActionButtonType button, ActionIdentifier action) {
        addAction(action)
            .whenTarget(button)
            .whenSelected(nonNull())
            .appliedTo(Selected)
            .appliedAs(Addition);
    }

    private void hideSelectors() {
        addAction(HideAreaSelector)
            .forInput(PressUp)
            .whenTarget(nonNull())
            .appliedTo(Target)
            .appliedAs(Addition);

        addAction(HideSelector)
            .whenTarget(CancelButton)
            .whenSelected(UnitOperations::hasSelector)
            .appliedTo(Selected)
            .appliedAs(Addition);
    }

    private void moveSelectors() {
        addAction(MoveSelector)
            .forInput(Drag)
            .whenTarget(UnitOperations::isSelector)
            .appliedTo(Target);
    }

    private void resizeSelectors() {
        addAction(ResizeAreaSelector)
            .forInput(PressDrag)
            .whenTarget(nonNull())
            .appliedTo(Target)
            .appliedAs(Addition);
    }
}
