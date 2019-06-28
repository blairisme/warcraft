/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.combatant.CombatantAssets;
import com.evilbird.warcraft.item.unit.combatant.CombatantBuilder;
import com.evilbird.warcraft.item.unit.combatant.RangedCombatant;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.common.movement.MovementCapability.Land;
import static com.evilbird.warcraft.item.projectile.ProjectileType.Axe;
import static com.evilbird.warcraft.item.unit.UnitType.TrollAxethrower;

/**
 * Instances of this factory create Troll Axe Throwers, Orcish entry level
 * ranged {@link Combatant Combatants}.
 *
 * @author Blair Butterworth
 */
public class TrollAxethrowerFactory implements AssetProvider<Item>
{
    private static final GridPoint2 ICON = new GridPoint2(0, 38);
    private static final GridPoint2 SIZE = new GridPoint2(32, 32);

    protected CombatantAssets assets;
    protected CombatantBuilder builder;

    @Inject
    public TrollAxethrowerFactory(Device device) {
        this(device.getAssetStorage());
    }

    public TrollAxethrowerFactory(AssetManager manager) {
        this.assets = new CombatantAssets(manager, TrollAxethrower, ICON, SIZE);
        this.builder = new CombatantBuilder(assets);
    }

    @Override
    public void load() {
        assets.load();
    }

    @Override
    public Item get() {
        RangedCombatant result = builder.newRangedCombatant();
        result.setAttackSpeed(1.5f);
        result.setDefence(0);
        result.setDamageMinimum(3);
        result.setDamageMaximum(9);
        result.setHealth(40);
        result.setHealthMaximum(40);
        result.setIdentifier(objectIdentifier("TrollAxethrower", result));
        result.setLevel(1);
        result.setName("Troll Axe Thrower");
        result.setMovementSpeed(8 * 10);
        result.setMovementCapability(Land);
        result.setRange(tiles(4));
        result.setSight(tiles(5));
        result.setType(TrollAxethrower);
        result.setProjectileType(Axe);
        return result;
    }
}