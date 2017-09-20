package com.evilbird.engine.item;

/**
 * Created by blair on 15/09/2017.
 */

public interface Selectable
{
    public boolean getSelected();

    public boolean getSelectable();

    public void setSelected(boolean selected);

    public void setSelectable(boolean selectable);
}
