/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.evilbird.engine.common.serialization.SerializedConstructor;
import com.evilbird.engine.common.serialization.SerializedType;
import com.evilbird.engine.item.ItemType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Instances of this class uniquely identify a {@link Layer}. LayerIdentifiers
 * include the file path and layer name where layer data can be obtained. For
 * efficiency reasons (to save rereading a persisted layer) the LayerIdentifier
 * may optionally contain an instance of the raw layer data represented by the
 * LayerIdentifier.
 *
 * @author Blair Butterworth
 */
@SerializedType("LayerId")
public class LayerIdentifier implements ItemType
{
    private String file;
    private String name;
    private transient TiledMapTileLayer layer;

    @SerializedConstructor
    private LayerIdentifier() {
    }

    public LayerIdentifier(String file, String name, TiledMapTileLayer layer){
        this.file = file;
        this.name = name;
        this.layer = layer;
    }

    public String getFile() {
        return file;
    }

    public String getName() {
        return name;
    }

    public LayerType getType() {
        return LayerType.valueOf(name);
    }

    public TiledMapTileLayer getLayer() {
        return layer;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;

        LayerIdentifier that = (LayerIdentifier)obj;
        return new EqualsBuilder()
            .append(file, that.file)
            .append(name, that.name)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(file)
            .append(name)
            .toHashCode();
    }
}
