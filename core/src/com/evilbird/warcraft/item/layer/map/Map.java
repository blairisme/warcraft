/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.map;

import com.evilbird.engine.item.layer.Layer;
import com.evilbird.warcraft.item.layer.LayerType;

import javax.inject.Inject;

public class Map extends Layer
{
    @Inject
    public Map() {
        setType(LayerType.Map);
    }
}
