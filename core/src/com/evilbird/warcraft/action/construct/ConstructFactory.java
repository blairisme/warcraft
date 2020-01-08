/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
