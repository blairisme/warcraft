/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.object;

/**
 * Implementors of this interface denote consumers of {@link GameObjectReference
 * GameObjectReferences}, and provide a method that receives a container from
 * which to lookup object references.
 *
 * @author Blair Butterworth
 */
public interface GameObjectReferencer
{
    /**
     * Sets the {@link GameObjectComposite container} at the root of the
     * {@code GameObject} hierarchy which is used to look up game references.
     */
    void setContainer(GameObjectComposite container);
}
