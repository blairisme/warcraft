package com.evilbird.warcraft.item.layer;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.specialized.Layer;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class Wood extends Layer
{
    @Inject
    public Wood()
    {
        setType(new Identifier("Wood"));
    }


}
