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

import javax.inject.Inject;

/**
 * Instances of this factory create {@link ProduceUnit} and {@link ProduceUnitCancel}
 * actions.
 *
 * @author Blair Butterworth
 */
public class ProduceFactory implements ActionProvider
{
    private InjectedPool<ProduceUnit> unitPool;
    private InjectedPool<ProduceUpgrade> upgradePool;
    private InjectedPool<ProduceUpgradeType> typeUpgradePool;
    private InjectedPool<ProduceUnitCancel> cancelUnitPool;
    private InjectedPool<ProduceUpgradeCancel> cancelUpgradePool;

    @Inject
    public ProduceFactory(
        InjectedPool<ProduceUnit> unitPool,
        InjectedPool<ProduceUpgrade> upgradePool,
        InjectedPool<ProduceUpgradeType> typeUpgradePool,
        InjectedPool<ProduceUnitCancel> cancelUnitPool,
        InjectedPool<ProduceUpgradeCancel> cancelUpgradePool)
    {
        this.unitPool = unitPool;
        this.upgradePool = upgradePool;
        this.typeUpgradePool = typeUpgradePool;
        this.cancelUnitPool = cancelUnitPool;
        this.cancelUpgradePool = cancelUpgradePool;
    }

    @Override
    public Action get(ActionIdentifier action) {
        if (action instanceof ProduceUnitActions) {
            return getUnitAction(action);
        }
        else if (action instanceof ProduceUpgradeActions) {
            return getUpgradeAction(action);
        }
        throw new UnsupportedOperationException();
    }

    private Action getUnitAction(ActionIdentifier action) {
        ProduceUnitActions produceUnit = (ProduceUnitActions)action;

        if (produceUnit.isCancel()) {
            return getAction(cancelUnitPool, produceUnit);
        }
        return getAction(unitPool, produceUnit);
    }

    private Action getUpgradeAction(ActionIdentifier action) {
        ProduceUpgradeActions produceUpgrade = (ProduceUpgradeActions)action;

        if (produceUpgrade.isCancel()) {
            return getAction(cancelUpgradePool, produceUpgrade);
        }
        if (produceUpgrade.isTypeUpgrade()) {
            return getAction(typeUpgradePool, produceUpgrade);
        }
        return getAction(upgradePool, produceUpgrade);
    }

    private <T extends Action> T getAction(InjectedPool<T> pool, ActionIdentifier identifier) {
        T result = pool.obtain();
        result.setIdentifier(identifier);
        return result;
    }
}
