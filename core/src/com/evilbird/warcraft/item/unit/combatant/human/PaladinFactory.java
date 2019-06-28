/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant.human;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.combatant.CombatantAssets;
import com.evilbird.warcraft.item.unit.combatant.CombatantBuilder;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.common.movement.MovementCapability.Land;
import static com.evilbird.warcraft.item.unit.UnitType.Knight;
import static com.evilbird.warcraft.item.unit.UnitType.Paladin;

/**
 * Instances of this factory create Paladins, knights with additional attack
 * damage and sight.
 *
 * @author Blair Butterworth
 */
public class PaladinFactory implements AssetProvider<Item>
{
    private static final GridPoint2 ICON = new GridPoint2(138, 38);
    private static final GridPoint2 SIZE = new GridPoint2(32, 32);

    private CombatantAssets assets;
    private CombatantBuilder builder;

    @Inject
    public PaladinFactory(Device device) {
        this(device.getAssetStorage());
    }

    public PaladinFactory(AssetManager manager) {
        this.assets = new CombatantAssets(manager, Knight, ICON, SIZE);
        this.builder = new CombatantBuilder(assets);
    }

    @Override
    public void load() {
        assets.load();
    }

    @Override
    public Item get() {
        Combatant result = builder.newMeleeCombatant();
        result.setAttackSpeed(1);
        result.setDefence(4);
        result.setDamageMinimum(8);
        result.setDamageMaximum(12);
        result.setHealth(90);
        result.setHealthMaximum(90);
        result.setIdentifier(objectIdentifier("Paladin", result));
        result.setLevel(1);
        result.setName("Paladin");
        result.setMovementSpeed(8 * 13);
        result.setMovementCapability(Land);
        result.setRange(tiles(1));
        result.setSight(tiles(5));
        result.setType(Paladin);
        return result;
    }
}