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
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;
import com.evilbird.warcraft.item.unit.gatherer.GathererFactoryBase;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.common.movement.MovementCapability.Water;
import static com.evilbird.warcraft.item.unit.UnitType.TrollTanker;

/**
 * <p>
 *  Instances of this factory create Troll Tankers, an ocean based oil gathering
 *  {@link Unit} available to the Orc faction.
 * </p>
 * <p>
 *  The Orc Oil Tanker is crudely constructed, as its purpose is for bearing
 *  cargo - not weapons or troops. The Tanker, being little more than a
 *  collection of wood, bone and storage space, is crewed by a mob of Orcs
 *  scarcely more capable than the lowly Peons. Other than being able to pilot
 *  the craft, the crew of the Tanker performs tasks equivalent to those of a
 *  Peon - building Oil Platforms and returning their cargo so that it may be
 *  processed and used as the overseer chooses.
 * </p>
 * @author Blair Butterworth
 */
public class TrollTankerFactory extends GathererFactoryBase
{
    @Inject
    public TrollTankerFactory(Device device) {
        this(device.getAssetStorage());
    }

    public TrollTankerFactory(AssetManager manager) {
        super(manager, TrollTanker);
    }

    @Override
    public Gatherer get(Identifier type) {
        Gatherer result = builder.newSeaGatherer();
        result.setDefence(0);
        result.setDamageMinimum(0);
        result.setDamageMaximum(0);
        result.setHealth(90);
        result.setHealthMaximum(90);
        result.setIdentifier(objectIdentifier("TrollTanker", result));
        result.setLevel(1);
        result.setMovementSpeed(8 * 10);
        result.setMovementCapability(Water);
        result.setName("TrollTanker");
        result.setRange(tiles(1));
        result.setSight(tiles(4));
        result.setType(TrollTanker);
        return result;
    }
}