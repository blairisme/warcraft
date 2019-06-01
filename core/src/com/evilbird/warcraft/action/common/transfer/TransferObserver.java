/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.transfer;

import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceType;

public interface TransferObserver
{
    void onTransfer(ResourceContainer recipient, ResourceType resource, float value, float delta);
}
