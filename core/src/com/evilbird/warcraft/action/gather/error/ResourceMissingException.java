/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.gather.error;

import com.evilbird.engine.action.ActionException;
import com.evilbird.warcraft.data.resource.ResourceType;

/**
 * Represents an action that is raised when a gathering unit is able to locate a
 * resource from which to gather resources.
 *
 * @author Blair Butterworth
 */
public class ResourceMissingException extends ActionException
{
    public ResourceMissingException(ResourceType resource) {
        super("Unable to locate resource of type " + resource);
    }
}
