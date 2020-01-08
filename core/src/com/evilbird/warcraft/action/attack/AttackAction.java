/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.CompositeAction;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;
import com.evilbird.warcraft.object.common.capability.PerishableObject;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitType;

import javax.inject.Inject;

import static com.evilbird.warcraft.object.unit.UnitType.RuneTrap;

/**
 * An {@link Action} that causes a given {@link OffensiveObject} to attack a
 * {@link PerishableObject}, after first moving within attack range, if
 * applicable.
 *
 * @author Blair Butterworth
 */
public class AttackAction extends CompositeAction
{
    private Action delegate;
    private BuildingAttack buildingAttack;
    private ConjuredAttack conjuredAttack;
    private DemolitionSequence demoAttack;
    private MeleeAttack meleeAttack;
    private RangedAttack rangedAttack;

    @Inject
    public AttackAction(
        BuildingAttack buildingAttack,
        ConjuredAttack conjuredAttack,
        DemolitionSequence demoAttack,
        MeleeAttack meleeAttack,
        RangedAttack rangedAttack)
    {
        super(buildingAttack, conjuredAttack, demoAttack, meleeAttack, rangedAttack);
        this.buildingAttack = buildingAttack;
        this.conjuredAttack = conjuredAttack;
        this.demoAttack = demoAttack;
        this.meleeAttack = meleeAttack;
        this.rangedAttack = rangedAttack;
    }

    @Override
    public boolean act(float delta) {
        if (delegate == null) {
            delegate = getAttackAction();
        }
        return delegate.act(delta);
    }

    @Override
    public void reset() {
        super.reset();
        delegate = null;
    }

    private Action getAttackAction() {
        Unit unit = (Unit)getSubject();
        UnitType type = (UnitType)unit.getType();

        if (type.isBuilding()) {
            return buildingAttack;
        }
        if (type.isDemoTeam() || type == RuneTrap) {
            return demoAttack;
        }
        if (type.isConjuredEffect()) {
            return conjuredAttack;
        }
        if (type.isMelee()) {
            return meleeAttack;
        }
        return rangedAttack;
    }
}