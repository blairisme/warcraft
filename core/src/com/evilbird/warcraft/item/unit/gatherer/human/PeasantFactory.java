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
        result.setIdentifier(objectIdentifier("Peasant", result));
        result.setType(Peasant);
    }

    private void setMovementAttributes(Gatherer result) {
        result.setMovementSpeed(8 * 10);
        result.setMovementCapability(Land);
        result.setSight(tiles(4));
    }
}
