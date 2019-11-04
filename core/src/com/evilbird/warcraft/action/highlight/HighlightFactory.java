/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.highlight;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.warcraft.action.ActionProvider;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * A factory that create actions related to highlighting units.
 *
 * @author Blair Butterworth
 */
public class HighlightFactory implements ActionProvider
{
    private InjectedPool<HighlightCancel> cancelPool;
    private InjectedPool<HighlightEnemyCombatant> enemyCombatantPool;
    private InjectedPool<HighlightPlayerBuilding> playerBuildingPool;
    private InjectedPool<HighlightPlayerCombatant> playerCombatantPool;

    @Inject
    public HighlightFactory(
        InjectedPool<HighlightCancel> cancelPool,
        InjectedPool<HighlightEnemyCombatant> enemyCombatantPool,
        InjectedPool<HighlightPlayerBuilding> playerBuildingPool,
        InjectedPool<HighlightPlayerCombatant> playerCombatantPool)
    {
        this.cancelPool = cancelPool;
        this.enemyCombatantPool = enemyCombatantPool;
        this.playerBuildingPool = playerBuildingPool;
        this.playerCombatantPool = playerCombatantPool;
    }

    @Override
    public Action get(ActionIdentifier action) {
        Validate.isInstanceOf(HighlightActions.class, action);
        HighlightActions highlightAction = (HighlightActions)action;

        switch (highlightAction) {
            case HighlightCancel: return cancelPool.obtain();
            case HighlightEnemyCombatant: return enemyCombatantPool.obtain();
            case HighlightPlayerBuilding: return playerBuildingPool.obtain();
            case HighlightPlayerCombatant: return playerCombatantPool.obtain();
            default: throw new UnsupportedOperationException();
        }
    }
}
