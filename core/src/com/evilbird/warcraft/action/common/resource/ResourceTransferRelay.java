/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.resource;

import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceIdentifier;

public class ResourceTransferRelay implements ResourceTransferObserver
{
    private ResourceTransferObserver delegate;

    public ResourceTransferRelay() {
        this(null);
    }

    public ResourceTransferRelay(ResourceTransferObserver delegate) {
        this.delegate = delegate;
    }

    public void setDelegate(ResourceTransferObserver delegate) {
        this.delegate = delegate;
    }

    @Override
    public void onTransfer(ResourceContainer recipient, ResourceIdentifier resource, float value) {
        if (delegate != null) {
            delegate.onTransfer(recipient, resource, value);
        }
    }
}
