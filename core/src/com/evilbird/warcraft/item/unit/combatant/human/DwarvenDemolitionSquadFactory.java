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
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.combatant.CombatantAssets;
import com.evilbird.warcraft.item.unit.combatant.CombatantBuilder;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.common.movement.MovementCapability.Land;
import static com.evilbird.warcraft.item.unit.UnitType.DwarvenDemolitionSquad;

/**
 * Instances of this factory create Dwarven Demolition units, Human single use
 * siege {@link Combatant Combatants}.
 *
 * @author Blair Butterworth
 */
public class DwarvenDemolitionSquadFactory implements GameFactory<Combatant>
{
    private static final GridPoint2 SIZE = new GridPoint2(32, 32);

    private CombatantAssets assets;
    private CombatantBuilder builder;

    @Inject
    public DwarvenDemolitionSquadFactory(Device device) {
        this(device.getAssetStorage());
    }

    public DwarvenDemolitionSquadFactory(AssetManager manager) {
        this.assets = new CombatantAssets(manager, DwarvenDemolitionSquad, SIZE);
        this.builder = new CombatantBuilder(assets);
    }

    @Override
    public void load(Identifier context) {
        assets.load();
    }

    @Override
    public void unload(Identifier context) {
    }

    @Override
    public Combatant get(Identifier type) {
        Combatant result = builder.newMeleeCombatant();
        result.setAttackSpeed(1);
        result.setDefence(0);
        result.setDamageMinimum(1);
        result.setDamageMaximum(6);
        result.setHealth(40);
        result.setHealthMaximum(40);
        result.setIdentifier(objectIdentifier("DwarvenDemolitionSquad", result));
        result.setLevel(1);
        result.setName("Dwarven Demolition Squad");
        result.setMovementSpeed(8 * 11);
        result.setMovementCapability(Land);
        result.setRange(tiles(1));
        result.setSight(tiles(4));
        result.setType(DwarvenDemolitionSquad);
        return result;
    }
}