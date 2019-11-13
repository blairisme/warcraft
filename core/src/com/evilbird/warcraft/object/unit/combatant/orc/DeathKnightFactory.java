/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import com.evilbird.warcraft.object.unit.combatant.CombatantFactoryBase;
import com.evilbird.warcraft.object.unit.combatant.SpellCaster;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.common.capability.MovementCapability.Land;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.projectile.ProjectileType.TouchOfDeath;
import static com.evilbird.warcraft.object.unit.UnitType.DeathKnight;

/**
 * Instances of this factory create Death Knights, Orcish mounted spell casting
 * {@link Combatant Combatants}.
 *
 * @author Blair Butterworth
 */
public class DeathKnightFactory extends CombatantFactoryBase
{
    @Inject
    public DeathKnightFactory(Device device) {
        this(device.getAssetStorage());
    }

    public DeathKnightFactory(AssetManager manager) {
        super(manager, DeathKnight);
    }

    @Override
    public Combatant get(Identifier type) {
        SpellCaster result = builder.newSpellCaster();
        result.setAttackSpeed(1.5f);
        result.setArmour(0);
        result.setPiercingDamage(3);
        result.setBasicDamage(9);
        result.setHealth(40);
        result.setHealthMaximum(40);
        result.setIdentifier(objectIdentifier("DeathKnight", result));
        result.setMana(255f);
        result.setManaMaximum(255f);
        result.setManaRegeneration(1f);
        result.setMovementSpeed(8 * 10);
        result.setMovementCapability(Land);
        result.setAttackRange(tiles(4));
        result.setSight(tiles(5));
        result.setType(DeathKnight);
        result.setProjectileType(TouchOfDeath);
        return result;
    }
}