/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.warcraft.action.ActionProvider;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * A factory that creates spell actions.
 *
 * @author Blair Butterworth
 */
public class SpellFactory implements ActionProvider
{
    private InjectedPool<ExorcismSpell> exorcismPool;
    private InjectedPool<HealSpell> healPool;
    private InjectedPool<HolyVisionSpell> holyVisionPool;

    @Inject
    public SpellFactory(
        InjectedPool<ExorcismSpell> exorcismPool,
        InjectedPool<HealSpell> healPool,
        InjectedPool<HolyVisionSpell> holyVisionPool)
    {
        this.exorcismPool = exorcismPool;
        this.healPool = healPool;
        this.holyVisionPool = holyVisionPool;
    }

    @Override
    public Action get(ActionIdentifier action) {
        Validate.isInstanceOf(SpellActions.class, action);
        switch ((SpellActions)action) {
            case ExorcismSpell: getAction(exorcismPool, action);
            case HealSpell: getAction(healPool, action);
            case HolyVisionSpell: getAction(holyVisionPool, action);
            default: throw new UnsupportedOperationException();
        }
    }

    private <T extends Action> T getAction(InjectedPool<T> pool, ActionIdentifier identifier) {
        T result = pool.obtain();
        result.setIdentifier(identifier);
        return result;
    }
}
