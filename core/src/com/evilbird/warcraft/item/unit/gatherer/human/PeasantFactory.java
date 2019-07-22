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
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;
import com.evilbird.warcraft.item.unit.gatherer.GathererFactoryBase;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.common.movement.MovementCapability.Land;
import static com.evilbird.warcraft.item.unit.UnitType.Peasant;

/**
 * <p>
 * Instances of this factory create Peasants, the land based gathering unit
 * available to the human faction.
 *</p>
 * <p>
 * Peasants are trained from the hard-working and stouthearted citizens that
 * live in the numerous kingdoms of Lordaeron. By mining gold and harvesting
 * lumber to meet the ever increasing needs of the fighting force which must
 * push back the unrelenting Horde, they are the backbone of the Alliance.
 * Trained not only in the construction and maintenance of the myriad buildings
 * found in every community, but also those necessary to wage war, they take
 * great pride in the invaluable service they provide. Roused by tales of the
 * Orcish atrocities in Azeroth, these Peasants have learned to use both pick
 * and axe for their own defense if threatened.
 *</p>
 *
 * @author Blair Butterworth
 */
public class PeasantFactory extends GathererFactoryBase
{
    @Inject
    public PeasantFactory(Device device) {
        this(device.getAssetStorage());
    }

    public PeasantFactory(AssetManager manager) {
        super(manager, Peasant);
    }

    @Override
    public Gatherer get(Identifier type) {
        Gatherer result = builder.newLandGatherer();
        result.setDefence(0);
        result.setDamageMinimum(1);
        result.setDamageMaximum(5);
        result.setHealth(30);
        result.setHealthMaximum(30);
        result.setIdentifier(objectIdentifier("Peasant", result));
        result.setLevel(1);
        result.setMovementSpeed(8 * 10);
        result.setMovementCapability(Land);
        result.setName("Peasant");
        result.setRange(tiles(1));
        result.setSight(tiles(4));
        result.setType(Peasant);
        return result;
    }
}
