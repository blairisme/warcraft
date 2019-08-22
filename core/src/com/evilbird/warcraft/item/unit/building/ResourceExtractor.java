/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.spatial.ItemGraph;
import com.evilbird.engine.item.spatial.ItemNode;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.resource.Resource;

import java.util.Collection;

import static com.evilbird.warcraft.item.common.query.UnitPredicates.isResource;

/**
 * Instances of this class represent a {@link Building} that is used to extract
 * resources from a {@link Resource}. Specifically, this class delegates
 * {@link ResourceContainer} behaviour to an
 * {@link Unit#setAssociatedItem(Item) associated resource}.
 *
 * @author Blair Butterworth
 */
public class ResourceExtractor extends Building
{
    public ResourceExtractor(Skin skin) {
        super(skin);
    }

    @Override
    public float getResource(ResourceType type) {
        Resource resource = getResource();
        if (resource != null) {
            return resource.getResource(type);
        }
        return super.getResource(type);
    }

    @Override
    public void setResource(ResourceType type, float value) {
        Resource resource = getResource();
        if (resource != null) {
            resource.setResource(type, value);
            return;
        }
        super.setResource(type, value);
    }

    private Resource getResource() {
        Item association = getAssociatedItem();
        if (association == null) {
            association = getUnderlyingItem();
            setAssociatedItem(association);
        }
        if (association instanceof Resource) {
            return (Resource)association;
        }
        return null;
    }

    private Item getUnderlyingItem() {
        ItemRoot root = getRoot();
        ItemGraph graph = root.getSpatialGraph();
        ItemNode node = graph.getNode(getPosition(Alignment.Center));
        Collection<Item> occupants = node.getOccupants();
        return CollectionUtils.findFirst(occupants, isResource());
    }
}
