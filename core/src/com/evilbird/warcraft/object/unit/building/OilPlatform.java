/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.building;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.engine.object.spatial.GameObjectNode;
import com.evilbird.warcraft.object.common.capability.TerrainType;
import com.evilbird.warcraft.object.common.resource.ResourceContainer;
import com.evilbird.warcraft.object.common.resource.ResourceType;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.resource.Resource;

import java.util.Collection;

import static com.evilbird.warcraft.object.common.query.UnitPredicates.isResource;

/**
 * Instances of this class represent a {@link Building} that is used to extract
 * resources from a {@link Resource}. Specifically, this class delegates
 * {@link ResourceContainer} behaviour to an
 * {@link Unit#setAssociatedItem(GameObject) associated resource}.
 *
 * @author Blair Butterworth
 */
public class OilPlatform extends Building
{
    public OilPlatform(Skin skin) {
        super(skin);
    }

    @Override
    public TerrainType getTerrainType() {
        return TerrainType.Water;
    }

    @Override
    public float getResource(ResourceType type) {
        Resource resource = getAssociatedResource();
        if (resource != null) {
            return resource.getResource(type);
        }
        return super.getResource(type);
    }

    @Override
    public void setResource(ResourceType type, float value) {
        Resource resource = getAssociatedResource();
        if (resource != null) {
            resource.setResource(type, value);
            return;
        }
        super.setResource(type, value);
    }

    private Resource getAssociatedResource() {
        GameObject association = getAssociatedItem();
        if (association == null) {
            association = getUnderlyingItem();
            setAssociatedItem(association);
        }
        if (association instanceof Resource) {
            return (Resource)association;
        }
        return null;
    }

    private GameObject getUnderlyingItem() {
        GameObjectContainer root = getRoot();
        GameObjectGraph graph = root.getSpatialGraph();
        GameObjectNode node = graph.getNode(getPosition(Alignment.Center));
        Collection<GameObject> occupants = node.getOccupants();
        return CollectionUtils.findFirst(occupants, isResource());
    }
}
