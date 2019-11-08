/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import javax.inject.Inject;
import javax.inject.Provider;

import static com.evilbird.engine.common.function.Predicates.nonNull;
import static com.evilbird.engine.device.UserInputType.PressDown;
import static com.evilbird.engine.device.UserInputType.PressDrag;
import static com.evilbird.engine.device.UserInputType.PressUp;
import static com.evilbird.warcraft.action.selection.SelectActions.SelectBoxBegin;
import static com.evilbird.warcraft.action.selection.SelectActions.SelectBoxEnd;
import static com.evilbird.warcraft.action.selection.SelectActions.SelectBoxResize;
import static com.evilbird.warcraft.action.selection.SelectActions.SelectInvert;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionApplicability.Target;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionDisplacement.Addition;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionDisplacement.Singleton;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isSelectable;
import static com.evilbird.warcraft.item.ui.display.control.status.selection.SelectionButtonType.UnselectButton;

/**
 * Defines user interactions that result in game object selection.
 *
 * @author Blair Butterworth
 */
public class SelectInteractions extends InteractionContainer
{
    /**
     * Constructs a new instance of this class given a {@link InteractionDefinition}
     * factory, used by {@link #addAction} to add {@link Interaction Interactions}
     * for actions.
     */
    @Inject
    public SelectInteractions(Provider<InteractionDefinition> factory) {
        super(factory);
        selectInvert();
        selectArea();
        deselectButton();
    }

    private void selectInvert() {
        addAction(SelectInvert)
            .whenTarget(isSelectable())
            .appliedTo(Target)
            .appliedAs(Addition);
    }

    private void selectArea() {
        addAction(SelectBoxBegin)
            .forInput(PressDown)
            .whenTarget(nonNull())
            .appliedTo(Target)
            .appliedAs(Addition);

        addAction(SelectBoxResize)
            .forInput(PressDrag)
            .whenTarget(nonNull())
            .appliedTo(Target)
            .appliedAs(Addition);

        addAction(SelectBoxEnd)
            .forInput(PressUp)
            .whenTarget(nonNull())
            .appliedTo(Target)
            .appliedAs(Addition);
    }

    private void deselectButton() {
        addAction(SelectInvert)
            .whenTarget(UnselectButton)
            .appliedTo(targetParentItem(), selectedItem())
            .appliedAs(Singleton);
    }
}
