/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.gatherer.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.common.value.UpgradeValue;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;
import com.evilbird.warcraft.object.unit.combatant.gatherer.GathererFactory;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.data.upgrade.UpgradeSeries.OilProduction;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.common.value.FixedValue.Zero;
import static com.evilbird.warcraft.object.unit.UnitType.TrollTanker;

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
 *
 * @author Blair Butterworth
 */
public class TrollTankerFactory extends GathererFactory
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
        Gatherer result = builder.build();
        setAttackAttributes(result);
        setGatheringAttributes(result);
        setIdentityAttributes(result);
        setMovementAttributes(result);
        return result;
    }

    private void setAttackAttributes(Gatherer result) {
        result.setArmour(0);
        result.setAttackSpeed(0);
        result.setBasicDamage(0);
        result.setPiercingDamage(0);
        result.setHealth(90);
        result.setHealthMaximum(90);
    }

    private void setGatheringAttributes(Gatherer result) {
        result.setGoldGatherSpeed(0);
        result.setGoldCapacity(Zero);
        result.setWoodGatherSpeed(0);
        result.setWoodCapacity(Zero);
        result.setOilGatherSpeed(5);
        result.setOilCapacity(new UpgradeValue(OilProduction, 100, 110, 125));
    }

    private void setIdentityAttributes(Gatherer result) {
        result.setIdentifier(objectIdentifier("TrollTanker", result));
        result.setType(TrollTanker);
    }

    private void setMovementAttributes(Gatherer result) {
        result.setMovementSpeed(8 * 10);
        result.setSight(tiles(4));
    }
}