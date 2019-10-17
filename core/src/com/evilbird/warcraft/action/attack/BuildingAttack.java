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
import com.evilbird.engine.action.framework.EmptyAction;
import com.evilbird.warcraft.action.death.DeathAction;
import com.evilbird.warcraft.item.common.state.OffensiveObject;
import com.evilbird.warcraft.item.common.state.PerishableObject;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;

/**
 * An {@link Action} that causes a given {@link OffensiveObject
 * offensive building} to attack a {@link PerishableObject}. The building will
 * not be moved at any point of the attack.
 *
 * @author Blair Butterworth
 */
public class BuildingAttack extends AttackSequence
{
    @Inject
    public BuildingAttack(AttackEvents events, ProjectileAttack attack, DeathAction death) {
        super(events, new EmptyAction(), attack, death);
    }

    @Override
    protected boolean moveAttacker(float time, OffensiveObject attacker, PerishableObject target) {
        return ActionComplete;
    }
}
