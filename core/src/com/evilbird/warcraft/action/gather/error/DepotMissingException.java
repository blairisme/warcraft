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
 * Represent an action that is raised when a gathering unit is able to locate a
 * depot into which the resources they're carrying can be deposited.
 *
 * @author Blair Butterworth
 */
public class DepotMissingException extends ActionException
{
    public DepotMissingException(ResourceType resource) {
        super("Unable to locate depot for " + resource);
    }
}
