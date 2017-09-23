package com.evilbird.warcraft.item.common.capability;

/**
 * Created by blair on 15/09/2017.
 */
public interface Zoomable
{
    public float getZoom();

    public float getOriginalZoom();

    public void setZoom(float zoom);

    public void setOriginalZoom(float originalZoom);
}
