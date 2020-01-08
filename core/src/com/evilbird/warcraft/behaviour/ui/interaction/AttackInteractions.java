/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import com.evilbird.warcraft.object.common.query.UnitOperations;

import javax.inject.Inject;
import javax.inject.Provider;

import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.engine.common.function.Predicates.not;
import static com.evilbird.warcraft.action.attack.AttackActions.Attack;
import static com.evilbird.warcraft.action.attack.AttackActions.AttackCancel;
import static com.evilbird.warcraft.action.confirm.ConfirmActions.ConfirmAttack;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionApplicability.Selected;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.isAi;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.isAttacker;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.isControllable;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.CancelButton;
import static com.evilbird.warcraft.object.layer.LayerType.WallSection;

/**
 * Defines user interactions that result in attacking game objects.
 *
 * @author Blair Butterworth
 */
public class AttackInteractions extends InteractionContainer
{
    /**
     * Constructs a new instance of this class given a {@link InteractionDefinition}
     * factory, used by {@link #addAction} to add {@link Interaction Interactions}
     * for actions.
     */
    @Inject
    public AttackInteractions(Provider<InteractionDefinition> factory) {
        super(factory);
        attack();
        attackWall();
        attackCancel();
    }

    private void attack() {
        addAction(Attack, ConfirmAttack)
            .whenSelected(both(isControllable(), isAttacker()))
            .whenTarget(isAi().and(UnitOperations::isDestroyable).and(not(UnitOperations::isResource)))
            .appliedTo(Selected)
            .appliedAs(confirmedAction());
    }

    private void attackWall() {
        addAction(Attack, ConfirmAttack)
            .whenSelected(both(isControllable(), isAttacker()))
            .whenTarget(WallSection)
            .appliedTo(Selected)
            .appliedAs(confirmedAction());
    }

    private void attackCancel() {
        addAction(AttackCancel)
            .whenSelected(both(isControllable(), isAttacker()))
            .whenTarget(CancelButton)
            .withAction(Attack)
            .appliedTo(Selected);
    }
}
