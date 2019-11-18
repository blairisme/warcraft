/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.DelegateAction;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;
import com.evilbird.warcraft.object.common.capability.PerishableObject;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitType;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * An {@link Action} that causes a given {@link OffensiveObject} to attack a
 * {@link PerishableObject}, after first moving within attack range, if
 * applicable.
 *
 * @author Blair Butterworth
 */
public class AttackAction extends DelegateAction
{
    private BuildingAttack buildingAttack;
    private DemolitionAttack demoAttack;
    private MeleeAttack meleeAttack;
    private RangedAttack rangedAttack;

    @Inject
    public AttackAction(
        BuildingAttack buildingAttack,
        DemolitionAttack demoAttack,
        MeleeAttack meleeAttack,
        RangedAttack rangedAttack)
    {
        this.buildingAttack = buildingAttack;
        this.demoAttack = demoAttack;
        this.meleeAttack = meleeAttack;
        this.rangedAttack = rangedAttack;
    }

    @Override
    public void setSubject(GameObject gameObject) {
        Validate.isInstanceOf(Unit.class, gameObject);
        delegate = getAttackAction((Unit) gameObject);
        super.setSubject(gameObject);
    }

    private Action getAttackAction(Unit unit) {
        UnitType type = (UnitType)unit.getType();

        if (type.isBuilding()) {
            return buildingAttack;
        }
        if (type.isDemoTeam()) {
            return demoAttack;
        }
        if (type.isMelee()) {
            return meleeAttack;
        }
        return rangedAttack;
    }
}
