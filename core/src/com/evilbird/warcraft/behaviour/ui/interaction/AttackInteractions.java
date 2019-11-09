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

import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.engine.common.function.Predicates.not;
import static com.evilbird.warcraft.action.attack.AttackActions.Attack;
import static com.evilbird.warcraft.action.attack.AttackActions.AttackCancel;
import static com.evilbird.warcraft.action.confirm.ConfirmActions.ConfirmAttack;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionApplicability.Selected;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isAi;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isAttacker;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isControllable;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isDestroyable;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isResource;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.CancelButton;
import static com.evilbird.warcraft.item.layer.LayerType.WallSection;

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
            .whenTarget(isAi().and(isDestroyable()).and(not(isResource())))
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
