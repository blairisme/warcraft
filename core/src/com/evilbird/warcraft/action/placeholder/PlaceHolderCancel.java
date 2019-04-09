/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.placeholder;

import com.evilbird.engine.action.framework.BasicAction;

import javax.inject.Inject;

public class PlaceHolderCancel extends BasicAction
{
    @Inject
    public PlaceHolderCancel() {
        setIdentifier(PlaceholderActions.PlaceholderCancel);
    }

    @Override
    public boolean act(float delta) {
        return false;
    }
}
