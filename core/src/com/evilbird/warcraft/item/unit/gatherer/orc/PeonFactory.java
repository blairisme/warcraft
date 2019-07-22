/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.gatherer.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;
import com.evilbird.warcraft.item.unit.gatherer.GathererFactoryBase;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.common.movement.MovementCapability.Land;
import static com.evilbird.warcraft.item.unit.UnitType.Peon;

/**
 * <p>
 *   Instances of this factory create Peons, the land based gathering unit
 *   available to the Orc faction.
 * </p>
 * <p>
 *   The label of Peon denotes the lowest station amongst those in the Orcish
 *   Horde. Inferior in all skills of import, these dogs are relegated to
 *   menial tasks such as harvesting lumber and mining gold. Their labor is
 *   also required for the construction and maintenance of buildings necessary
 *   to support the vast undertakings of the Horde. Downtrodden, the Orc Peons
 *   slave thanklessly to please their overseers.
 * </p>
 *
 * @author Blair Butterworth
 */
public class PeonFactory extends GathererFactoryBase
{
    @Inject
    public PeonFactory(Device device) {
        this(device.getAssetStorage());
    }

    public PeonFactory(AssetManager manager) {
        super(manager, Peon);
    }

    @Override
    public Gatherer get(Identifier type) {
        Gatherer result = builder.newLandGatherer();
        result.setDefence(0);
        result.setDamageMinimum(1);
        result.setDamageMaximum(5);
        result.setHealth(30f);
        result.setHealthMaximum(30f);
        result.setIdentifier(objectIdentifier("Peon", result));
        result.setLevel(1);
        result.setMovementSpeed(8 * 10);
        result.setMovementCapability(Land);
        result.setName("Peon");
        result.setRange(tiles(1));
        result.setSight(tiles(4));
        result.setType(Peon);
        return result;
    }
}
