/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.produce;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.warcraft.action.ActionProvider;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link ProduceUnit} and {@link ProduceUnitCancel}
 * actions.
 *
 * @author Blair Butterworth
 */
public class ProduceFactory implements ActionProvider
{
    private InjectedPool<ProduceUnit> produceUnitPool;
    private InjectedPool<ProduceUpgrade> produceUpgradePool;
    private InjectedPool<ProduceUnitCancel> cancelUnitPool;
    private InjectedPool<ProduceUpgradeCancel> cancelUpgradePool;

    @Inject
    public ProduceFactory(
        InjectedPool<ProduceUnit> produceUnitPool,
        InjectedPool<ProduceUpgrade> produceUpgradePool,
        InjectedPool<ProduceUnitCancel> cancelUnitPool,
        InjectedPool<ProduceUpgradeCancel> cancelUpgradePool)
    {
        this.produceUnitPool = produceUnitPool;
        this.produceUpgradePool = produceUpgradePool;
        this.cancelUnitPool = cancelUnitPool;
        this.cancelUpgradePool = cancelUpgradePool;
    }

    @Override
    public Action get(ActionIdentifier action) {
        Validate.isInstanceOf(ProduceActions.class, action);
        ProduceActions trainAction = (ProduceActions)action;

        switch (trainAction) {
            case TrainFootman:
            case TrainPeasant: return getAction(produceUnitPool, trainAction);
            case TrainFootmanCancel:
            case TrainPeasantCancel: return getAction(cancelUnitPool, trainAction);
            case UpgradeArrowDamage: return getAction(produceUpgradePool, trainAction);
            case UpgradeArrowDamageCancel: return getAction(cancelUpgradePool, trainAction);
            default: throw new UnsupportedOperationException();
        }
    }

    private <T extends Action> T getAction(InjectedPool<T> pool, ActionIdentifier identifier) {
        T result = pool.obtain();
        result.setIdentifier(identifier);
        return result;
    }
}
