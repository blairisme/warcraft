package com.evilbird.warcraft.item.layer;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.specialized.Layer;

import javax.inject.Inject;

public class Map extends Layer
{
    @Inject
    public Map()
    {
        setType(new Identifier("Map"));
    }
}
