/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import javax.inject.Inject;
import javax.inject.Provider;

import static com.evilbird.warcraft.action.selection.SelectActions.SelectInvert;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionApplicability.Target;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionDisplacement.Addition;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionDisplacement.Singleton;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.isSelectable;
import static com.evilbird.warcraft.object.display.components.status.selection.SelectionButtonType.UnselectButton;

/**
 * Defines user interactions that result in game object selection.
 *
 * @author Blair Butterworth
 */
public class SelectionInteractions extends InteractionContainer
{
    /**
     * Constructs a new instance of this class given a {@link InteractionDefinition}
     * factory, used by {@link #addAction} to add {@link Interaction Interactions}
     * for actions.
     */
    @Inject
    public SelectionInteractions(Provider<InteractionDefinition> factory) {
        super(factory);
        selectInvert();
        deselectButton();
    }

    private void selectInvert() {
        addAction(SelectInvert)
            .whenTarget(isSelectable())
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
