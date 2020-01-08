/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.components.common;

import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.IdentifierPair;
import com.evilbird.warcraft.common.WarcraftFaction;
import com.evilbird.warcraft.data.upgrade.Upgrade;
import com.evilbird.warcraft.object.unit.UnitAttack;

import java.util.HashMap;
import java.util.Map;

import static com.evilbird.warcraft.data.upgrade.Upgrade.BlizzardUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.BloodlustUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.DeathAndDecayUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.ExorcismUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.FlameShieldUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.HasteUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.HealingUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.InvisibilityUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.MeleeDamage1;
import static com.evilbird.warcraft.data.upgrade.Upgrade.MeleeDamage2;
import static com.evilbird.warcraft.data.upgrade.Upgrade.MeleeDefence1;
import static com.evilbird.warcraft.data.upgrade.Upgrade.MeleeDefence2;
import static com.evilbird.warcraft.data.upgrade.Upgrade.MeleeType1;
import static com.evilbird.warcraft.data.upgrade.Upgrade.NavalDamage1;
import static com.evilbird.warcraft.data.upgrade.Upgrade.NavalDamage2;
import static com.evilbird.warcraft.data.upgrade.Upgrade.NavalDefence1;
import static com.evilbird.warcraft.data.upgrade.Upgrade.NavalDefence2;
import static com.evilbird.warcraft.data.upgrade.Upgrade.PolymorphUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.RaiseTheDeadUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.RangedAccuracy1;
import static com.evilbird.warcraft.data.upgrade.Upgrade.RangedDamage1;
import static com.evilbird.warcraft.data.upgrade.Upgrade.RangedDamage2;
import static com.evilbird.warcraft.data.upgrade.Upgrade.RangedSight1;
import static com.evilbird.warcraft.data.upgrade.Upgrade.RangedType1;
import static com.evilbird.warcraft.data.upgrade.Upgrade.RangedWeapon1;
import static com.evilbird.warcraft.data.upgrade.Upgrade.RunesUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.SiegeDamage1;
import static com.evilbird.warcraft.data.upgrade.Upgrade.SiegeDamage2;
import static com.evilbird.warcraft.data.upgrade.Upgrade.SlowUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.UnholyArmourUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.WhirlwindUpgrade;
import static com.evilbird.warcraft.object.display.components.common.IconType.Unknown;

/**
 * Defines the layout of an icon file: a texture containing a set of icons in a
 * predefined order.
 *
 * @author Blair Butterworth
 */
@SuppressWarnings("checkstyle:LineLength")
public class UpgradeIconLayout
{
    private final GridPoint2 size;
    private final Map<Identifier, GridPoint2> icons;

    public UpgradeIconLayout() {
        size = new GridPoint2(46, 38);
        icons = layout(
            Unknown,                Unknown,                Unknown,                Unknown,                Unknown,
            Unknown,                human(RangedType1),     orc(RangedType1),       Unknown,                Unknown,
            human(MeleeType1),      orc(MeleeType1),        Unknown,                Unknown,                Unknown,
            Unknown,                Unknown,                Unknown,                Unknown,                Unknown,
            Unknown,                Unknown,                Unknown,                Unknown,                Unknown,
            Unknown,                Unknown,                Unknown,                Unknown,                Unknown,
            Unknown,                Unknown,                Unknown,                Unknown,                Unknown,
            Unknown,                Unknown,                Unknown,                Unknown,                Unknown,
            Unknown,                Unknown,                Unknown,                Unknown,                Unknown,
            Unknown,                Unknown,                Unknown,                Unknown,                Unknown,
            Unknown,                Unknown,                Unknown,                Unknown,                Unknown,
            Unknown,                Unknown,                Unknown,                Unknown,                Unknown,
            Unknown,                Unknown,                Unknown,                Unknown,                Unknown,
            Unknown,                Unknown,                Unknown,                Unknown,                Unknown,
            Unknown,                Unknown,                Unknown,                Unknown,                Unknown,
            Unknown,                Unknown,                Unknown,                Unknown,                Unknown,
            Unknown,                Unknown,                Unknown,                Unknown,                Unknown,
            Unknown,                Unknown,                Unknown,                Unknown,                Unknown,
            Unknown,                Unknown,                Unknown,                Unknown,                HasteUpgrade,
            InvisibilityUpgrade,    SlowUpgrade,            RunesUpgrade,           UnholyArmourUpgrade,    Unknown,
            FlameShieldUpgrade,     Unknown,                Unknown,                DeathAndDecayUpgrade,   WhirlwindUpgrade,
            BlizzardUpgrade,        Unknown,                HealingUpgrade,         Unknown,                Unknown,
            ExorcismUpgrade,        Unknown,                BloodlustUpgrade,       Unknown,                RaiseTheDeadUpgrade,
            PolymorphUpgrade,       Unknown,                human(MeleeDamage1),    human(MeleeDamage2),    Unknown,
            orc(MeleeDamage1),      orc(MeleeDamage2),      Unknown,                Unknown,                Unknown,
            human(RangedDamage1),   human(RangedDamage2),   Unknown,                orc(RangedDamage1),     orc(RangedDamage2),
            Unknown,                Unknown,                human(RangedWeapon1),   human(RangedSight1),    human(RangedAccuracy1),
            orc(RangedWeapon1),     orc(RangedSight1),      orc(RangedAccuracy1),   orc(SiegeDamage1),      orc(SiegeDamage2),
            human(SiegeDamage1),    human(SiegeDamage2),    Unknown,                Unknown,                Unknown,
            human(NavalDamage1),    human(NavalDamage2),    Unknown,                orc(NavalDamage1),      orc(NavalDamage2),
            Unknown,                orc(NavalDefence1),     orc(NavalDefence2),     Unknown,                human(NavalDefence1),
            human(NavalDefence2),   Unknown,                Unknown,                Unknown,                Unknown,
            Unknown,                Unknown,                Unknown,                Unknown,                Unknown,
            human(MeleeDefence1),   human(MeleeDefence2),   Unknown,                orc(MeleeDefence1),     orc(MeleeDefence2)
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

    private Identifier human(Identifier id) {
        return combination(WarcraftFaction.Human, id);
    }

    private Identifier orc(Identifier id) {
        return combination(WarcraftFaction.Orc, id);
    }

    private Identifier combination(UnitAttack attack, Identifier id) {
        return new IdentifierPair(attack, id);
    }

    private Identifier combination(WarcraftFaction faction, Identifier id) {
        return new IdentifierPair(faction, id);
    }
}
