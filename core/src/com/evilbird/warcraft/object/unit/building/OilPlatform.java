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
import com.evilbird.engine.object.GameObjectReference;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.engine.object.spatial.GameObjectNode;
import com.evilbird.warcraft.data.resource.ResourceContainer;
import com.evilbird.warcraft.data.resource.ResourceType;
import com.evilbird.warcraft.object.common.capability.TerrainType;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitStyle;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;
import com.evilbird.warcraft.object.unit.resource.Resource;

import java.util.Collection;

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
    private GameObjectReference oilPatch;

    /**
     * Constructs a new instance of this class given a {@link Skin} containing
     * an {@link UnitStyle}, specifying the visual and auditory
     * presentation of the new oil platform.
     *
     * @param skin a {@code Skin} instance containing a {@code UnitStyle}.
     *
     * @throws NullPointerException if the given skin is {@code null} or
     *                              doesn't contain a {@code UnitStyle}.
     */
    public OilPlatform(Skin skin) {
        super(skin);
    }

    @Override
    public TerrainType getTerrainType() {
        return TerrainType.Water;
    }

    /**
     * Returns the {@link Gatherer} that is currently obtaining resources from
     * the oil platform, if any.
     */
    public Gatherer getGatherer() {
        return (Gatherer)getAssociatedItem();
    }

    /**
     * Returns the amount of oil type held by the oil patch under the oil
     * platform.
     */
    @Override
    public float getResource(ResourceType type) {
        Resource resource = getOilPatch();
        if (resource != null) {
            return resource.getResource(type);
        }
        return super.getResource(type);
    }

    /**
     * Sets the {@link Gatherer} that is currently obtaining resources from
     * the oil platform, if any.
     */
    public void setGatherer(Gatherer gatherer) {
        setAssociatedItem(gatherer);
    }

    /**
     * Sets the amount of oil type held by the oil patch under the oil
     * platform.
     */
    @Override
    public void setResource(ResourceType type, float value) {
        Resource resource = getOilPatch();
        if (resource != null) {
            resource.setResource(type, value);
            return;
        }
        super.setResource(type, value);
    }

    private Resource getOilPatch() {
        if (oilPatch == null) {
            oilPatch = new GameObjectReference<>(getUnderlyingItem());
        }
        return (Resource)oilPatch.get();
    }

    private Resource getUnderlyingItem() {
        GameObjectContainer root = getRoot();
        GameObjectGraph graph = root.getSpatialGraph();
        GameObjectNode node = graph.getNode(getPosition(Alignment.Center));
        Collection<GameObject> occupants = node.getOccupants();
        return (Resource)CollectionUtils.findFirst(occupants, UnitOperations::isResource);
    }
}
