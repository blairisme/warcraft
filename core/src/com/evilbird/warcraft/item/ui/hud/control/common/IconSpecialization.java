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
import com.evilbird.warcraft.item.unit.UnitFaction;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class IconSpecialization implements Identifier
{
    private Identifier identifier;
    private Identifier specialization;

    public IconSpecialization(Identifier identifier, Identifier faction) {
        this.identifier = identifier;
        this.specialization = faction;
    }

    public static Identifier human(Identifier identifier) {
        return new IconSpecialization(identifier, UnitFaction.Human);
    }

    public static Identifier orc(Identifier identifier) {
        return new IconSpecialization(identifier, UnitFaction.Orc);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        IconSpecialization that = (IconSpecialization)obj;
        return new EqualsBuilder()
            .append(identifier, that.identifier)
            .append(specialization, that.specialization)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(identifier)
            .append(specialization)
            .toHashCode();
    }
}