/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.hud.control.common;

import com.evilbird.engine.common.lang.Identifier;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * An identifier for a specialized icon: an icon that differs based on a units
 * faction, attack capability or the presence of an upgrade.
 *
 * @author Blair Butterworth
 */
public class IconSpecialization implements Identifier
{
    private Identifier button;
    private Identifier level;
    private Identifier attack;
    private Identifier faction;

    public IconSpecialization(
        Identifier button,
        Identifier faction,
        Identifier attack,
        Identifier level)
    {
        this.button = button;
        this.level = level;
        this.attack = attack;
        this.faction = faction;
    }

    public static IconSpecialization special(
        Identifier button,
        Identifier faction,
        Identifier attack,
        Identifier level)
    {
        return new IconSpecialization(button, faction, attack, level);
    }

    public Identifier getButton() {
        return button;
    }

    public Identifier getLevel() {
        return level;
    }

    public Identifier getAttack() {
        return attack;
    }

    public Identifier getFaction() {
        return faction;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        IconSpecialization that = (IconSpecialization)obj;
        return new EqualsBuilder()
            .append(button, that.button)
            .append(level, that.level)
            .append(attack, that.attack)
            .append(faction, that.faction)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(button)
            .append(level)
            .append(attack)
            .append(faction)
            .toHashCode();
    }
}
