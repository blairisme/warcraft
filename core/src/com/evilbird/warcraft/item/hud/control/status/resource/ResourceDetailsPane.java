/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.status.resource;

import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.item.control.BorderPane;
import com.evilbird.warcraft.item.unit.resource.Resource;

import javax.inject.Inject;

/**
 * Instances of this user interface show details about selectable
 * {@link Resource Resources}.
 *
 * @author Blair Butterworth
 */
public class ResourceDetailsPane extends BorderPane
{
    @Inject
    public ResourceDetailsPane() {
    }

    public void setResource(Resource resource) {
        if (Objects.equals(new NamedIdentifier("Gold"), resource.getType())) {
            showGoldMineDetails(resource);
        } else if (Objects.equals(new NamedIdentifier("Oil"), resource.getType())) {
            showOilPatchDetails(resource);
        }
    }

    private void showGoldMineDetails(Resource resource) {
        GoldMineDetailsPane detailsPane = new GoldMineDetailsPane();
        detailsPane.setResource(resource);
        setCenter(detailsPane);
    }

    private void showOilPatchDetails(Resource resource) {
        OilPatchDetailsPane detailsPane = new OilPatchDetailsPane();
        detailsPane.setResource(resource);
        setCenter(detailsPane);
    }
}
