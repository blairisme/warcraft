package com.evilbird.warcraft.item.layer.map;

import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.item.specialized.layer.Layer;
import com.evilbird.warcraft.item.layer.LayerType;

import javax.inject.Inject;

public class Map extends Layer
{
    @Inject
    public Map() {
        setType(LayerType.Map);
    }
}
