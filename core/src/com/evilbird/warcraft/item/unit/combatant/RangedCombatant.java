/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.warcraft.item.projectile.ProjectileType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Instances of this class represent a combatant specialization that can attack
 * other items at distance.
 *
 * @author Blair Butterworth
 */
public class RangedCombatant extends Combatant
{
    private ProjectileType projectileType;

    public RangedCombatant(Skin skin) {
        super(skin);
    }

    public ProjectileType getProjectileType() {
        return projectileType;
    }

    public void setProjectileType(ProjectileType projectileType) {
        this.projectileType = projectileType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        RangedCombatant that = (RangedCombatant)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(projectileType, that.projectileType)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(projectileType)
            .toHashCode();
    }
}
