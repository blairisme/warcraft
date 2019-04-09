/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.action.common.AnimateAction;
import com.evilbird.engine.action.framework.DelegateAction;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.UnitAnimation;

import javax.inject.Inject;

/**
 * Instances of this class stop a given {@link Item} from gathering, retaining
 * resources from the partially completed gathering process.
 *
 * @author Blair Butterworth
 */
public class GatherCancel extends DelegateAction
{
    @Inject
    public GatherCancel(){
        delegate = new AnimateAction(UnitAnimation.Idle);
    }
}
