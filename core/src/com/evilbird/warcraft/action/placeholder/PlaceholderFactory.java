/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.placeholder;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.warcraft.action.ActionProvider;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.placeholder.PlaceholderActions.PlaceholderCancel;
import static com.evilbird.warcraft.action.placeholder.PlaceholderActions.PlaceholderMove;

/**
 * Instances of this factory create {@link Action Actions} related to
 * placeholders, user interface elements used to prepare a building for
 * construction.
 *
 * @author Blair Butterworth
 */
public class PlaceholderFactory implements ActionProvider
{
    private InjectedPool<com.evilbird.warcraft.action.placeholder.PlaceholderCancel> cancelPool;
    private InjectedPool<PlaceholderCreate> createPool;
    private InjectedPool<PlaceholderMove> movePool;

    @Inject
    public PlaceholderFactory(
        InjectedPool<com.evilbird.warcraft.action.placeholder.PlaceholderCancel> cancelPool,
        InjectedPool<PlaceholderCreate> createPool,
        InjectedPool<PlaceholderMove> movePool)
    {
        this.cancelPool = cancelPool;
        this.createPool = createPool;
        this.movePool = movePool;
    }

    @Override
    public Action get(ActionIdentifier action) {
        Validate.isInstanceOf(PlaceholderActions.class, action);
        PlaceholderActions identifier = (PlaceholderActions)action;

        switch (identifier) {
            case PlaceholderMove: return getAction(movePool, PlaceholderMove);
            case PlaceholderCancel: return getAction(cancelPool, PlaceholderCancel);
            default: return getAction(createPool, identifier);
        }
    }

    private <T extends Action> Action getAction(InjectedPool<T> pool, PlaceholderActions identifier) {
        T action = pool.obtain();
        action.setIdentifier(identifier);
        return action;
    }
}
