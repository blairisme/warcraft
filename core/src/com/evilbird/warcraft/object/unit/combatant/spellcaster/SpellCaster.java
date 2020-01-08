/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.spellcaster;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.object.AnimatedObjectStyle;
import com.evilbird.warcraft.data.spell.Spell;
import com.evilbird.warcraft.object.effect.Effect;
import com.evilbird.warcraft.object.unit.combatant.RangedCombatant;
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
    private float manaRegeneration;
    private float castingProgress;
    private Spell castingSpell;

    /**
     * Constructs a new instance of this class given a {@link Skin} describing
     * its visual and auditory presentation.
     *
     * @param skin a {@link Skin} instance containing, amongst others, a
     *             {@link AnimatedObjectStyle}.
     */
    public SpellCaster(Skin skin) {
        super(skin);
        mana = 0;
        manaMaximum = 0;
        manaRegeneration = 0;
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
     * Returns the rate at which this spell casters mana regeneration rate.
     */
    public float getManaRegeneration() {
        return manaRegeneration;
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
     * Sets the rate at which this spell casters mana regeneration rate.
     */
    public void setManaRegeneration(float manaRegeneration) {
        this.manaRegeneration = manaRegeneration;
    }

    /**
     * Sets the effect shown for the last spell cast by the spell caster.
     */
    public void setSpellEffect(Effect spell) {
        setAssociatedItem(spell);
    }

    @Override
    public void update(float time) {
        super.update(time);
        if (mana < manaMaximum) {
            mana = Math.min(manaMaximum, mana + (manaRegeneration * time));
        }
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
            .append(manaRegeneration, that.manaRegeneration)
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
            .append(manaRegeneration)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("identifier", getIdentifier())
            .toString();
    }
}
