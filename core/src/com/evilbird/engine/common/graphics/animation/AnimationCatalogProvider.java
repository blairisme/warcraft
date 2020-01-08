/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.graphics.animation;

/**
 * Implementors of this interface provide {@link Animation Animations} for
 * storage in {@link AnimationCatalog AnimationCatalogs}.
 *
 * @author Blair Butterworth
 */
interface AnimationCatalogProvider
{
    Animation getAnimation();
}
