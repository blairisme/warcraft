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
import com.evilbird.engine.events.Events;

import static com.evilbird.warcraft.action.placeholder.PlaceholderStatus.Added;
import static com.evilbird.warcraft.action.placeholder.PlaceholderStatus.Removed;

/**
 * Helper class for generating placeholder events.
 *
 * @author Blair Butterworth
 */
public class PlaceholderEvents
{
    private PlaceholderEvents() {
    }

    public static Action placeholderAdded(Events events) {
        return new LambdaAction((builder, placeholder) ->
            events.add(new PlaceholderEvent(builder, placeholder, Added)));
    }

    public static Action placeholderRemoved(Events events) {
        return new LambdaAction((builder, placeholder) ->
            events.add(new PlaceholderEvent(builder, placeholder, Removed)));
    }
}
