/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.train;

import com.evilbird.warcraft.item.unit.building.Building;

/**
 * Implementors of this interface provide methods that are called when the
 * training action is started, completes or is cancelled.
 *
 * @author Blair Butterworth
 */
public interface TrainObserver
{
    void onTrainStarted(Building building);

    void onTrainCompleted(Building building);

    void onTrainCancelled(Building building);
}
