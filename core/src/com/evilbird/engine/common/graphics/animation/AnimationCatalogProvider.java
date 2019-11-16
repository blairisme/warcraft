/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
