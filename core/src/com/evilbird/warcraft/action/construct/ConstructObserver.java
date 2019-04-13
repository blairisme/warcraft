/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.building.Building;

public interface ConstructObserver
{
    void onConstructionStarted(Item builder, Building building);

    void onConstructionCompleted(Item builder, Building building);

    void onConstructionCancelled(Item builder, Building building);
}
