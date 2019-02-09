/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.select;

import com.evilbird.engine.action.ActionContext;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.framework.Action;
import com.evilbird.engine.action.framework.EmptyAction;
import com.evilbird.engine.action.utilities.InjectedPool;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.ActionProvider;

import javax.inject.Inject;

/**
 * Instances of this class toggle the selection of a given {@link Item}.
 *
 * @author Blair Butterworth
 */
public class SelectFactory implements ActionProvider
{
    private SelectReporter reporter;
    private InjectedPool<SelectToggleAction> pool;

    @Inject
    public SelectFactory(SelectReporter reporter, InjectedPool<SelectToggleAction> pool) {
        this.pool = pool;
        this.reporter = reporter;
    }

    @Override
    public Action get(ActionIdentifier action, ActionContext context) {
        Item item = context.getItem();
        if (item.getSelectable()) {
            SelectToggleAction toggle = pool.obtain();
            toggle.setObserver(reporter);
            return toggle;
        }
        return new EmptyAction();
    }
}