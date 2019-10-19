/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.warcraft.action.ActionProvider;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link ConstructSequence} and
 * {@link ConstructCancel} actions.
 *
 * @author Blair Butterworth
 */
public class ConstructFactory implements ActionProvider
{
    private InjectedPool<ConstructSequence> constructPool;
    private InjectedPool<ConstructCancel> constructCancelPool;
    private InjectedPool<ConstructUpgrade> upgradePool;
    private InjectedPool<ConstructUpgradeCancel> upgradeCancelPool;

    @Inject
    public ConstructFactory(
        InjectedPool<ConstructSequence> constructPool,
        InjectedPool<ConstructCancel> constructCancelPool,
        InjectedPool<ConstructUpgrade> upgradePool,
        InjectedPool<ConstructUpgradeCancel> upgradeCancelPool)
    {
        this.constructPool = constructPool;
        this.upgradePool = upgradePool;
        this.constructCancelPool = constructCancelPool;
        this.upgradeCancelPool = upgradeCancelPool;
    }

    @Override
    public Action get(ActionIdentifier id) {
        Validate.isInstanceOf(ConstructActions.class, id);
        ConstructActions action = (ConstructActions)id;
        return action.isCancel() ? getConstructCancelAction(action) : getConstructAction(action);
    }

    private Action getConstructAction(ConstructActions action) {
        Action result = action.isUpgrade() ? upgradePool.obtain() : constructPool.obtain();
        result.setIdentifier(action);
        return result;
    }

    private Action getConstructCancelAction(ConstructActions action) {
        Action result = action.isUpgrade() ? upgradeCancelPool.obtain() : constructCancelPool.obtain();
        result.setIdentifier(action);
        return result;
    }
}
