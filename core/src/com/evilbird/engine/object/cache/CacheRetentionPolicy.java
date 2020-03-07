/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.object.cache;

/**
 * Defines options for specifying cache retention policies.
 *
 * @author Blair Butterworth
 */
public enum CacheRetentionPolicy
{
    /**
     * Indicates that cached results should be retained until the object they
     * were obtained from is modified.
     */
    Modify,

    /**
     * Indicates that cached results should be retained until the object they
     * were obtained from is updated.
     */
    Update,

    /**
     * Indicates that cached results should be retained forever.
     */
    Permanent
}
