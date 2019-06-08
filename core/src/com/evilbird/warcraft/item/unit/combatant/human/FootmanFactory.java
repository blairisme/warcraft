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
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.combatant.CombatantAssets;
import com.evilbird.warcraft.item.unit.combatant.CombatantBuilder;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.TILE_WIDTH;
import static com.evilbird.warcraft.item.common.movement.MovementCapability.Land;

/**
 * Instances of this factory create footman, human entry level melee
 * {@link Combatant Combatants}.
 *
 * @author Blair Butterworth
 */
public class FootmanFactory implements AssetProvider<Item>
{
    private CombatantAssets assets;
    private CombatantBuilder builder;

    @Inject
    public FootmanFactory(Device device) {
        this(device.getAssetStorage());
    }

    public FootmanFactory(AssetManager manager) {
        this.assets = new CombatantAssets(manager, "human", "footman", "melee");
        this.assets.setIcon(92, 0);
        this.assets.setSize(32, 32);
        this.builder = new CombatantBuilder(assets);
    }

    @Override
    public void load() {
        assets.load();
    }

    @Override
    public Item get() {
        Combatant result = builder.build();
        result.setDefence(4);
        result.setDamageMinimum(4);
        result.setDamageMaximum(18);
        result.setHealth(60);
        result.setHealthMaximum(60);
        result.setIdentifier(objectIdentifier("Footman", result));
        result.setLevel(1);
        result.setName("Footman");
        result.setMovementSpeed(80);
        result.setMovementCapability(Land);
        result.setRange(TILE_WIDTH + 5);
        result.setSight(TILE_WIDTH * 4);
        result.setType(UnitType.Footman);
        return result;
    }
}
