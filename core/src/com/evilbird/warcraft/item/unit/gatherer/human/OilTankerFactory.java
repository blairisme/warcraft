/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.gatherer.human;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;
import com.evilbird.warcraft.item.unit.gatherer.GathererAssets;
import com.evilbird.warcraft.item.unit.gatherer.GathererBuilder;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.common.movement.MovementCapability.Water;
import static com.evilbird.warcraft.item.unit.UnitType.OilTanker;

/**
 * <p>
 * Instances of this factory create Oil Tankers, an ocean based oil gathering
 * {@link Unit} available to the Human faction.
 * </p>
 * <p>
 * As the only ships which do not require oil be built, Oil Tankers make
 * possible the construction of the Alliance fleet. They are manned by hard
 * working, dependable mariners who search for the rich oil deposits which lie
 * beneath the waves. The crew of every Tanker is skilled in building Oil
 * Platforms and ferrying the oil back to a Shipyard or Oil Refinery where it
 * may be processed and put to use.
 * </p>
 *
 * @author Blair Butterworth
 */
public class OilTankerFactory implements GameFactory<Gatherer>
{
    private static final GridPoint2 SIZE = new GridPoint2(72, 72);

    private GathererAssets assets;
    private GathererBuilder builder;

    @Inject
    public OilTankerFactory(Device device) {
        this(device.getAssetStorage());
    }

    public OilTankerFactory(AssetManager manager) {
        this.assets = new GathererAssets(manager, OilTanker, SIZE);
        this.builder = new GathererBuilder(assets);
    }

    @Override
    public void load(Identifier context) {
        assets.load();
    }

    @Override
    public void unload(Identifier context) {
    }

    @Override
    public Gatherer get(Identifier type) {
        Gatherer result = builder.newSeaGatherer();
        result.setDefence(0);
        result.setDamageMinimum(0);
        result.setDamageMaximum(0);
        result.setHealth(90);
        result.setHealthMaximum(90);
        result.setIdentifier(objectIdentifier("OilTanker", result));
        result.setLevel(1);
        result.setMovementSpeed(8 * 10);
        result.setMovementCapability(Water);
        result.setName("OilTanker");
        result.setRange(tiles(1));
        result.setSight(tiles(4));
        result.setType(OilTanker);
        return result;
    }
}