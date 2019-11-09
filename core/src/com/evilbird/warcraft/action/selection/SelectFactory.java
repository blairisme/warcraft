/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.selection;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.warcraft.action.ActionProvider;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * Instances of this factory create actions that alter item selection.
 *
 * @author Blair Butterworth
 */
public class SelectFactory implements ActionProvider
{
    private InjectedPool<SelectArea> selectAreaPool;
    private InjectedPool<SelectInvert> selectInvertPool;

    @Inject
    public SelectFactory(
        InjectedPool<SelectArea> selectAreaPool,
        InjectedPool<SelectInvert> selectInvertPool)
    {
        this.selectAreaPool = selectAreaPool;
        this.selectInvertPool = selectInvertPool;
    }

    @Override
    public Action get(ActionIdentifier identifier) {
        Validate.isInstanceOf(SelectActions.class, identifier);
        switch ((SelectActions)identifier) {
            case SelectBoxBegin:
            case SelectBoxResize:
            case SelectBoxEnd: return getAction(selectAreaPool, identifier);
            case SelectInvert: return getAction(selectInvertPool, identifier);
            default: throw new UnsupportedOperationException();
        }
    }

    private <T extends Action> T getAction(InjectedPool<T> pool, ActionIdentifier identifier) {
        T result = pool.obtain();
        result.setIdentifier(identifier);
        return result;
    }
}