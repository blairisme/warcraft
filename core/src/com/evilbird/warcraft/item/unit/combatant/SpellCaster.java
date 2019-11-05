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
import com.evilbird.warcraft.item.common.spell.Spell;
import com.evilbird.warcraft.item.effect.Effect;
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
    private float castingProgress;
    private Spell castingSpell;

    /**
     * Constructs a new instance of this class given a {@link Skin} describing
     * its visual and auditory presentation.
     *
     * @param skin a {@link Skin} instance containing, amongst others, a
     *             {@link ViewableStyle}.
     */
    public SpellCaster(Skin skin) {
        super(skin);
        mana = 0;
        manaMaximum = 0;
        castingProgress = 1;
        castingSpell = null;
    }

    /**
     * Returns the progress the spell caster has made casting the current
     * spell, specified as a percentage from 0 to 1.
     */
    public float getCastingProgress() {
        return castingProgress;
    }

    /**
     * Returns the spell the spell caster is currently performing.
     */
    public Spell getCastingSpell() {
        return castingSpell;
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
     * Returns the effect shown for the last spell cast by the spell caster.
     */
    public Effect getSpellEffect() {
        return (Effect)getAssociatedItem();
    }

    /**
     * Determines whether or not the spell caster is currently casting a spell.
     */
    public boolean isCasting() {
        return castingProgress != 1;
    }

    /**
     * Sets the progress the spell caster has made casting the current
     * spell, specified as a percentage from 0 to 1.
     */
    public void setCastingProgress(float castingProgress) {
        this.castingProgress = castingProgress;
    }

    /**
     * Sets the spell the spell caster is currently performing.
     */
    public void setCastingSpell(Spell castingSpell) {
        this.castingSpell = castingSpell;
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

    /**
     * Sets the effect shown for the last spell cast by the spell caster.
     */
    public void setSpellEffect(Effect spell) {
        setAssociatedItem(spell);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        SpellCaster that = (SpellCaster)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(castingProgress, that.castingProgress)
            .append(castingSpell, that.castingSpell)
            .append(mana, that.mana)
            .append(manaMaximum, that.manaMaximum)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(castingProgress)
            .append(castingSpell)
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
