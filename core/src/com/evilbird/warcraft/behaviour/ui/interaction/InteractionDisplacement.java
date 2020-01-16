/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

/**
 * Defines options for specifying whether any actions resulting from an
 * {@link InteractionDefinition} should replace existing actions or supplement
 * them.
 *
 * @author Blair Butterworth
 */
public enum InteractionDisplacement
{
    Addition,
    Replacement,
    Singleton,
    Standalone
}
