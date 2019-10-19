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
import com.evilbird.warcraft.item.common.upgrade.UpgradeSequence;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;
import com.evilbird.warcraft.item.unit.gatherer.GathererFactoryBase;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.common.state.MovementCapability.Land;
import static com.evilbird.warcraft.item.common.upgrade.UpgradeSequence.Zero;
import static com.evilbird.warcraft.item.common.upgrade.UpgradeSeries.GoldProduction;
import static com.evilbird.warcraft.item.common.upgrade.UpgradeSeries.MeleeDamage;
import static com.evilbird.warcraft.item.common.upgrade.UpgradeSeries.MeleeDefence;
import static com.evilbird.warcraft.item.common.upgrade.UpgradeSeries.WoodProduction;
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
        Gatherer result = builder.build();
        setAttackAttributes(result);
        setGatheringAttributes(result);
        setIdentityAttributes(result);
        setMovementAttributes(result);
        return result;
    }

    private void setAttackAttributes(Gatherer result) {
        result.setAttackSpeed(1);
        result.setArmour(new UpgradeSequence(MeleeDefence, 0, 2, 4));
        result.setBasicDamage(new UpgradeSequence(MeleeDamage, 5, 7, 9));
        result.setPiercingDamage(1);
        result.setHealth(30);
        result.setHealthMaximum(30);
    }

    private void setGatheringAttributes(Gatherer result) {
        result.setGoldGatherSpeed(5);
        result.setGoldCapacity(new UpgradeSequence(GoldProduction, 100, 110, 125));
        result.setWoodGatherSpeed(45);
        result.setWoodCapacity(new UpgradeSequence(WoodProduction, 100, 110, 125));
        result.setOilGatherSpeed(0);
        result.setOilCapacity(Zero);
    }

    private void setIdentityAttributes(Gatherer result) {
        result.setIdentifier(objectIdentifier("Peon", result));
        result.setType(Peon);
    }

    private void setMovementAttributes(Gatherer result) {
        result.setMovementSpeed(8 * 10);
        result.setMovementCapability(Land);
        result.setSight(tiles(4));
    }
}
