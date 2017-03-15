package com.evilbird.warcraft.item.layer.map;

import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.item.specialized.Layer;

import javax.inject.Inject;

public class Map extends Layer
{
    @Inject
    public Map()
    {
        setType(new NamedIdentifier("Map"));
    }
}
