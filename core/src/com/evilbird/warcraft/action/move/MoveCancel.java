/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.evilbird.engine.action.common.AnimateAction;
import com.evilbird.engine.action.framework.DelegateAction;
import com.evilbird.warcraft.item.unit.UnitAnimation;

import javax.inject.Inject;

public class MoveCancel extends DelegateAction
{
    @Inject
    public MoveCancel() {
        super(new AnimateAction(UnitAnimation.Idle));
        setIdentifier(MoveActions.MoveCancel);
    }
}
