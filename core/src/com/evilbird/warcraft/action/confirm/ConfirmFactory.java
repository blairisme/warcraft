/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.confirm;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.framework.Action;
import com.evilbird.engine.action.utilities.InjectedPool;
import com.evilbird.warcraft.action.ActionProvider;

import javax.inject.Inject;

public class ConfirmFactory implements ActionProvider
{
    private InjectedPool<ConfirmItem> itemPool;
    private InjectedPool<ConfirmLocation> locationPool;

    @Inject
    public ConfirmFactory(
        InjectedPool<ConfirmItem> itemPool,
        InjectedPool<ConfirmLocation> locationPool)
    {
        this.itemPool = itemPool;
        this.locationPool = locationPool;
    }

    @Override
    public Action get(ActionIdentifier action) {
        switch ((ConfirmActions)action) {
            case ConfirmLocation: return locationPool.obtain();
            case ConfirmTarget: return itemPool.obtain();
            default: throw new UnsupportedOperationException();
        }
    }
}
