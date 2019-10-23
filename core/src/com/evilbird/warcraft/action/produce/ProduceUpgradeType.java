/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.produce;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.warcraft.action.common.transfer.ResourceTransfer;
import com.evilbird.warcraft.common.WarcraftFaction;
import com.evilbird.warcraft.item.common.production.ProductionCosts;
import com.evilbird.warcraft.item.common.production.ProductionTimes;
import com.evilbird.warcraft.item.common.upgrade.Upgrade;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.Building;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;

import static com.evilbird.engine.item.utility.ItemPredicates.hasType;
import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.item.common.query.UnitOperations.getPlayer;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.MeleeType1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.RangedType1;
import static com.evilbird.warcraft.item.unit.UnitType.ElvenArcher;
import static com.evilbird.warcraft.item.unit.UnitType.ElvenRanger;
import static com.evilbird.warcraft.item.unit.UnitType.Knight;
import static com.evilbird.warcraft.item.unit.UnitType.Ogre;
import static com.evilbird.warcraft.item.unit.UnitType.OgreMage;
import static com.evilbird.warcraft.item.unit.UnitType.Paladin;
import static com.evilbird.warcraft.item.unit.UnitType.TrollAxethrower;
import static com.evilbird.warcraft.item.unit.UnitType.TrollBerserker;

/**
 * Instances of this action sequence research an upgrade that converts one type
 * of unit into another.
 *
 * @author Blair Butterworth
 */
public class ProduceUpgradeType extends ProduceUpgrade
{
    private ItemFactory itemFactory;

    @Inject
    public ProduceUpgradeType(
        ProduceEvents events,
        ResourceTransfer resources,
        ProductionCosts productionCosts,
        ProductionTimes productionTimes,
        ItemFactory itemFactory)
    {
        super(events, resources, productionCosts, productionTimes);
        this.itemFactory = itemFactory;
    }

    @Override
    protected boolean complete() {
        Building building = (Building)getItem();
        UnitType type = (UnitType)building.getType();
        WarcraftFaction faction = type.getFaction();

        Upgrade upgradeType = getProduct();
        Identifier obsoleteType = getObsoleteType(upgradeType, faction);
        Identifier upgradedType = getUpgradedType(upgradeType, faction);

        Combatant upgrade = (Combatant)itemFactory.get(upgradedType);
        Player player = getPlayer(building);

        for (Item obsolete: player.findAll(hasType(obsoleteType))) {
            updateAttributes((Combatant)obsolete, upgrade);
        }
        return super.complete();
    }

    private Identifier getObsoleteType(Upgrade upgrade, WarcraftFaction faction) {
        if (upgrade == MeleeType1) {
            return faction == Human ? Knight : Ogre;
        }
        if (upgrade == RangedType1) {
            return faction == Human ? ElvenArcher : TrollAxethrower;
        }
        throw new UnsupportedOperationException();
    }

    private Identifier getUpgradedType(Upgrade upgrade, WarcraftFaction faction) {
        if (upgrade == MeleeType1) {
            return faction == Human ? Paladin : OgreMage;
        }
        if (upgrade == RangedType1) {
            return faction == Human ? ElvenRanger : TrollBerserker;
        }
        throw new UnsupportedOperationException();
    }

    private void updateAttributes(Combatant obsolete, Combatant upgrade) {
        obsolete.setType(upgrade.getType());
        obsolete.setHealth(upgrade.getHealth());
        obsolete.setHealthMaximum(upgrade.getHealthMaximum());
    }
 }