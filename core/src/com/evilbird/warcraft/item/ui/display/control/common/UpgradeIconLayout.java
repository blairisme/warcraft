/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *  
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.common;

import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.IdentifierPair;
import com.evilbird.warcraft.common.WarcraftFaction;
import com.evilbird.warcraft.item.common.upgrade.Upgrade;
import com.evilbird.warcraft.item.unit.UnitAttack;

import java.util.HashMap;
import java.util.Map;

import static com.evilbird.warcraft.item.common.upgrade.Upgrade.MeleeDamage1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.MeleeDamage2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.MeleeDefence1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.MeleeDefence2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.NavalDamage1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.NavalDamage2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.NavalDefence1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.NavalDefence2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.RangedAccuracy1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.RangedDamage1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.RangedDamage2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.RangedSight1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.RangedWeapon1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.SiegeDamage1;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.Unknown;

/**
 * Defines the layout of an icon file: a texture containing a set of icons in a
 * predefined order.
 *
 * @author Blair Butterworth
 */
public class UpgradeIconLayout
{
    private final GridPoint2 size;
    private final Map<Identifier, GridPoint2> icons;

    public UpgradeIconLayout() {
        size = new GridPoint2(46, 38);
        icons = layout(
            Unknown, Unknown, Unknown, Unknown, Unknown,
            Unknown, Unknown, Unknown, Unknown, Unknown,
            Unknown, Unknown, Unknown, Unknown, Unknown,
            Unknown, Unknown, Unknown, Unknown, Unknown,
            Unknown, Unknown, Unknown, Unknown, Unknown,
            Unknown, Unknown, Unknown, Unknown, Unknown,
            Unknown, Unknown, Unknown, Unknown, Unknown,
            Unknown, Unknown, Unknown, Unknown, Unknown,
            Unknown, Unknown, Unknown, Unknown, Unknown,
            Unknown, Unknown, Unknown, Unknown, Unknown,
            Unknown, Unknown, Unknown, Unknown, Unknown,
            Unknown, Unknown, Unknown, Unknown, Unknown,
            Unknown, Unknown, Unknown, Unknown, Unknown,
            Unknown, Unknown, Unknown, Unknown, Unknown,
            Unknown, Unknown, Unknown, Unknown, Unknown,
            Unknown, Unknown, Unknown, Unknown, Unknown,
            Unknown, Unknown, Unknown, Unknown, Unknown,
            Unknown, Unknown, Unknown, Unknown, Unknown,
            Unknown, Unknown, Unknown, Unknown, Unknown,
            Unknown, Unknown, Unknown, Unknown, Unknown,
            Unknown, Unknown, Unknown, Unknown, Unknown,
            Unknown, Unknown, Unknown, Unknown, Unknown,
            Unknown, Unknown, Unknown, Unknown, Unknown,
            Unknown,            Unknown,            h(MeleeDamage1),    h(MeleeDamage2),    Unknown,
            o(MeleeDamage1),    o(MeleeDamage2),    Unknown,            Unknown,            Unknown,
            h(RangedDamage1),   h(RangedDamage2),   Unknown,            o(RangedDamage1),   o(RangedDamage2),
            Unknown,            Unknown,            h(RangedWeapon1),   h(RangedSight1),    h(RangedAccuracy1),
            o(RangedWeapon1),   o(RangedSight1),    o(RangedAccuracy1), Unknown,            o(SiegeDamage1),
            Unknown,            h(SiegeDamage1),    Unknown,            Unknown,            Unknown,
            h(NavalDamage1),    h(NavalDamage2),    Unknown,            o(NavalDamage1),    o(NavalDamage2),
            Unknown,            o(NavalDefence1),   o(NavalDefence2),   Unknown,            h(NavalDefence1),
            h(NavalDefence2),   Unknown,            Unknown,            Unknown,            Unknown,
            Unknown,            Unknown,            Unknown,            Unknown,            Unknown,
            h(MeleeDefence1),   h(MeleeDefence2),   Unknown,            o(MeleeDefence1),   o(MeleeDefence2)
        );
    }

    /**
     * Returns the size of the icon with the given {@link Upgrade}
     * within an icon texture file.
     */
    public GridPoint2 getSize(Upgrade type) {
        return size;
    }

    /**
     * Returns the size of the icon with the given {@link Upgrade}
     * within an icon texture file. If an icon specialization exists for the
     * given faction and attack type then this will be returned.
     */
    public GridPoint2 getSize(Upgrade type, WarcraftFaction faction, UnitAttack attack) {
        return size;
    }

    /**
     * Returns the location of the icon with the given {@link Upgrade}
     * within an icon texture file.
     */
    public GridPoint2 getLocation(Upgrade type) {
        return icons.get(type);
    }

    /**
     * Returns the location of the icon with the given {@link Upgrade}
     * within an icon texture file. If an icon specialization exists for the
     * given faction and attack type then this will be returned.
     */
    public GridPoint2 getLocation(Upgrade upgrade, WarcraftFaction faction, UnitAttack attack) {
        Identifier combinedSpecialization = combination(faction, combination(attack, upgrade));
        if (icons.containsKey(combinedSpecialization)) {
            return icons.get(combinedSpecialization);
        }
        Identifier attackSpecialization = combination(attack, upgrade);
        if (icons.containsKey(attackSpecialization)) {
            return icons.get(attackSpecialization);
        }
        Identifier factionSpecialization = combination(faction, upgrade);
        if (icons.containsKey(factionSpecialization)) {
            return icons.get(factionSpecialization);
        }
        return icons.get(upgrade);
    }

    private Map<Identifier, GridPoint2> layout(Identifier ... types) {
        Map<Identifier, GridPoint2> result = new HashMap<>();
        for (int index = 0; index < types.length; ++index) {
            int column = (index % 5) * size.x;
            int row = (index / 5) * size.y;
            result.put(types[index], new GridPoint2(column, row));
        }
        return result;
    }

    private Identifier h(Identifier id) {
        return combination(WarcraftFaction.Human, id);
    }

    private Identifier o(Identifier id) {
        return combination(WarcraftFaction.Orc, id);
    }

    private Identifier combination(UnitAttack attack, Identifier id) {
        return new IdentifierPair(attack, id);
    }

    private Identifier combination(WarcraftFaction faction, Identifier id) {
        return new IdentifierPair(faction, id);
    }
}
