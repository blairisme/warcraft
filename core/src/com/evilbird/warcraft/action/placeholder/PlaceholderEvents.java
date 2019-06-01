/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.placeholder;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.LambdaAction;

/**
 * Helper class for generating placeholder events.
 *
 * @author Blair Butterworth
 */
public class PlaceholderEvents
{
    private PlaceholderEvents() {
    }

    public static Action placeholderAdded(PlaceholderObserver observer) {
        return new LambdaAction(observer::onPlaceholderAdded);
    }

    public static Action placeholderRemoved(PlaceholderObserver observer) {
        return new LambdaAction(observer::onPlaceholderRemoved);
    }
}
