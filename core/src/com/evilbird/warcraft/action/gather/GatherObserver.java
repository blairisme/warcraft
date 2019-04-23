/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;

/**
 * Implementors of this interface provide methods that are called when a
 * {@link Gatherer}
 * construction action is started, completes or is cancelled.
 *
 * @author Blair Butterworth
 */
public interface GatherObserver
{
    void onObtainStarted(Gatherer gatherer, Item source, ResourceType type);

    void onObtainComplete(Gatherer gatherer, Item source, ResourceType type, float value);

    void onDepositStarted(Gatherer gatherer, Item destination, ResourceType type, float value);

    void onDepositComplete(Gatherer gatherer, Item destination, ResourceType type);

    void onGatherCancelled(Gatherer gatherer);
}
