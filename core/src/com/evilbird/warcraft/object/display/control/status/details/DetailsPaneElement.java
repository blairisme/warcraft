/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.control.status.details;

import com.evilbird.engine.object.GameObject;

public interface DetailsPaneElement extends GameObject
{
    void setItem(GameObject gameObject);
}
