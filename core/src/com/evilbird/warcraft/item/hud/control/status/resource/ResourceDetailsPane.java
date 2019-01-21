package com.evilbird.warcraft.item.hud.control.state.resource;

import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.item.control.BorderPane;
import com.evilbird.warcraft.item.unit.resource.Resource;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ResourceDetailsPane extends BorderPane
{
    @Inject
    public ResourceDetailsPane()
    {
    }

    public void setResource(Resource resource)
    {
        if (Objects.equals(new NamedIdentifier("Gold"), resource.getType())){
            showGoldMineDetails(resource);
        }
        else if (Objects.equals(new NamedIdentifier("Oil"), resource.getType())){
            showOilPatchDetails(resource);
        }
    }

    private void showGoldMineDetails(Resource resource)
    {
        GoldMineDetailsPane detailsPane = new GoldMineDetailsPane();
        detailsPane.setResource(resource);
        setCenter(detailsPane);
    }

    private void showOilPatchDetails(Resource resource)
    {
        OilPatchDetailsPane detailsPane = new OilPatchDetailsPane();
        detailsPane.setResource(resource);
        setCenter(detailsPane);
    }
}
