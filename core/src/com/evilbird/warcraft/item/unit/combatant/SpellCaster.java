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
import com.evilbird.engine.item.specialized.ViewableStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Represents a ranged combatant specialization that can attack other game
 * objects using spells.
 *
 * @author Blair Butterworth
 */
public class SpellCaster extends RangedCombatant
{
    private float mana;
    private float manaMaximum;

    /**
     * Constructs a new instance of this class given a {@link Skin} describing
     * its visual and auditory presentation.
     *
     * @param skin a {@link Skin} instance containing, amongst others, a
     *             {@link ViewableStyle}.
     */
    public SpellCaster(Skin skin) {
        super(skin);
    }

    /**
     * Returns the SpellCasters current mana, the pool of magic consumed when
     * casting spells.
     */
    public float getMana() {
        return mana;
    }

    /**
     * Returns the SpellCasters total mana, the pool of magic consumed when
     * casting spells.
     */
    public float getManaMaximum() {
        return manaMaximum;
    }

    /**
     * Sets the SpellCasters current mana, the pool of magic consumed when
     * casting spells.
     */
    public void setMana(float mana) {
        this.mana = mana;
    }

    /**
     * Sets the SpellCasters total mana, the pool of magic consumed when
     * casting spells.
     */
    public void setManaMaximum(float manaMaximum) {
        this.manaMaximum = manaMaximum;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        SpellCaster that = (SpellCaster)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(mana, this.mana)
            .append(manaMaximum, that.manaMaximum)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(mana)
            .append(manaMaximum)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("identifier", getIdentifier())
            .toString();
    }
}
