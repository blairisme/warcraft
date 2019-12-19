/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.components.status.details.combatant;

import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.warcraft.object.common.value.ModifiedValue;
import com.evilbird.warcraft.object.common.value.Value;
import com.evilbird.warcraft.object.unit.combatant.Combatant;

/**
 * Provides methods that dictate how {@link Combatant} attributes are
 * displayed.
 *
 * @author Blair Butterworth
 */
public class CombatantVisualization
{
    public static final int MOVEMENT_FACTOR = 8;

    private CombatantVisualization() {
    }

    public static int getDamageMin(Combatant combatant) {
        return combatant.getPiercingDamage();
    }

    public static int getDamageMax(Combatant combatant) {
        int basicDamage = combatant.getBasicDamage();
        int pierceDamage = combatant.getPiercingDamage();
        return basicDamage + pierceDamage;
    }

    public static int getDamageUpgrade(Combatant combatant) {
        Value value = combatant.getBasicDamageValue();
        return getModifiedValue(value, combatant);
    }

    public static int getArmour(Combatant combatant) {
        return combatant.getArmour();
    }

    public static int getArmourUpgrade(Combatant combatant) {
        Value value = combatant.getArmourValue();
        return getModifiedValue(value, combatant);
    }

    public static int getSpeed(Combatant combatant) {
        return combatant.getMovementSpeed() / MOVEMENT_FACTOR;
    }

    public static int getRange(Combatant combatant) {
        GameObjectContainer container = combatant.getRoot();
        GameObjectGraph graph = container.getSpatialGraph();
        return combatant.getAttackRange() / graph.getNodeWidth();
    }

    public static int getSight(Combatant combatant) {
        GameObjectContainer container = combatant.getRoot();
        GameObjectGraph graph = container.getSpatialGraph();
        return combatant.getSight() / graph.getNodeWidth();
    }

    public static int getLevel(Combatant combatant) {
        return 1;
    }

    private static int getModifiedValue(Value value, Combatant combatant) {
        if (value instanceof ModifiedValue) {
            ModifiedValue modified = (ModifiedValue)value;
            return (int)(modified.getValue(combatant) - modified.getBaseValue(combatant));
        }
        return 0;
    }
}
