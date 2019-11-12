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
import com.evilbird.engine.object.AnimatedObjectStyle;
import com.evilbird.warcraft.item.common.capability.OffensiveCapability;
import com.evilbird.warcraft.item.common.capability.RangedOffensiveObject;
import com.evilbird.warcraft.item.projectile.Projectile;
import com.evilbird.warcraft.item.projectile.ProjectileType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static com.evilbird.warcraft.item.common.capability.OffensiveCapability.Air;
import static com.evilbird.warcraft.item.projectile.ProjectileType.Arrow;

/**
 * Represents a combatant specialization that can attack other items at
 * distance.
 *
 * @author Blair Butterworth
 */
public class RangedCombatant extends Combatant implements RangedOffensiveObject
{
    private int attackRange;
    private ProjectileType projectileType;

    /**
     * Constructs a new instance of this class given a {@link Skin} describing
     * its visual and auditory presentation.
     *
     * @param skin  a {@link Skin} instance containing, amongst others, a
     *              {@link AnimatedObjectStyle}.
     */
    public RangedCombatant(Skin skin) {
        super(skin);
        this.attackRange = 0;
        this.projectileType = Arrow;
    }

    /**
     * Returns the attack capability of the {@code RangedCombatant}.
     */
    @Override
    public OffensiveCapability getAttackCapability() {
        return Air;
    }

    /**
     * Returns the distance that the {@code RangedCombatant} can reach with its
     * attacks.
     */
    @Override
    public int getAttackRange() {
        return attackRange;
    }

    /**
     * Returns the objects current projectile, if any.
     */
    @Override
    public Projectile getProjectile() {
        return (Projectile)getAssociatedItem();
    }

    /**
     * Returns the type of projectile used when the ranged offensive unit
     * attacks.
     */
    @Override
    public ProjectileType getProjectileType() {
        return projectileType;
    }

    /**
     * Sets the distance that the {@code RangedCombatant} can reach with its
     * attacks.
     */
    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    /**
     * Sets the type of projectile used when the {@code RangedCombatant}
     * attacks.
     */
    public void setProjectileType(ProjectileType projectileType) {
        this.projectileType = projectileType;
    }

    /**
     * Sets the projectile to be used by the {@code RangedCombatant}.
     */
    @Override
    public void setProjectile(Projectile projectile) {
        setAssociatedItem(projectile);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        RangedCombatant that = (RangedCombatant)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(attackRange, this.attackRange)
            .append(projectileType, that.projectileType)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(attackRange)
            .append(projectileType)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("identifier", getIdentifier())
            .toString();
    }
}
