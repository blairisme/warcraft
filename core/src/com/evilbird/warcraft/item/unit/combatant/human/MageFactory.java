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
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.combatant.CombatantFactoryBase;
import com.evilbird.warcraft.item.unit.combatant.SpellCaster;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.item.common.capability.MovementCapability.Land;
import static com.evilbird.warcraft.item.projectile.ProjectileType.Lightning;
import static com.evilbird.warcraft.item.unit.UnitType.Mage;

/**
 * Instances of this factory create Mages, Human spell casting
 * {@link Combatant Combatants}.
 *
 * @author Blair Butterworth
 */
public class MageFactory extends CombatantFactoryBase
{
    @Inject
    public MageFactory(Device device) {
        this(device.getAssetStorage());
    }

    public MageFactory(AssetManager manager) {
        super(manager, Mage);
    }

    @Override
    public Combatant get(Identifier type) {
        SpellCaster result = builder.newSpellCaster();
        result.setAttackSpeed(1.5f);
        result.setAttackRange(tiles(2));
        result.setArmour(0);
        result.setPiercingDamage(2);
        result.setBasicDamage(9);
        result.setHealth(60);
        result.setHealthMaximum(60);
        result.setIdentifier(objectIdentifier("Mage", result));
        result.setMana(100f);
        result.setManaMaximum(255f);
        result.setManaRegeneration(1f);
        result.setMovementSpeed(8 * 10);
        result.setMovementCapability(Land);
        result.setSight(tiles(9));
        result.setType(Mage);
        result.setProjectileType(Lightning);
        return result;
    }
}