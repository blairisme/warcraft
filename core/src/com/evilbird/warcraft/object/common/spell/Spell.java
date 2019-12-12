/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.common.spell;

/**
 * Defines spells available to spell casting game objects.
 *
 * @author Blair Butterworth
 */
public enum Spell
{
    /* Name           Mana time range duration value */
    Blizzard            (25, 1f, 128f, 20f, 5),
    Bloodlust           (50, 1f, 128f, 20f, 2),
    DeathAndDecay       (200, 1f, 128f, 20f, 5),
    DeathCoil           (100, 1f, 128f, 20f, 5),
    Exorcism            (4, 1f, 128f, 20f, 20),
    EyeOfKilrogg        (70, 1f, 128f, 20f, 5),
    Fireball            (100, 1f, 128f, 20f, 5),
    FlameShield         (80, 1f, 128f, 20f, 5),
    Haste               (50, 1f, 128f, 20f, 5),
    Heal                (6, 1f, 128f, 20f, 40),
    HolyVision          (70, 1f, 128f, 20f, 160),
    Invisibility        (200, 1f, 128f, 20f, 5),
    Lightning           (0, 1f, 128f, 20f, 5),
    Polymorph           (200, 1f, 128f, 20f, 5),
    RaiseDead           (200, 1f, 128f, 20f, 5),
    Runes               (200, 1f, 128f, 20f, 5),
    Slow                (50, 1f, 128f, 20f, 5),
    TouchOfDarkness     (0, 1f, 128f, 20f, 5),
    UnholyArmour        (80, 1f, 128f, 20f, 5),
    Whirlwind           (200, 1f, 128f, 20f, 5);

    private int castCost;
    private float castTime;
    private float castRange;
    private float effectDuration;
    private int effectValue;

    Spell(int mana, float time, float range, float duration, int value) {
        this.castCost = mana;
        this.castTime = time;
        this.castRange = range;
        this.effectDuration = duration;
        this.effectValue = value;
    }

    /**
     * Returns the amount of mana required to cast this spell.
     */
    public int getCastCost() {
        return castCost;
    }

    /**
     * Returns the maximum distance away from the spells target that the spell
     * can be cast, specified in world pixels.
     */
    public float getCastRange() {
        return castRange;
    }

    /**
     * Returns the amount of time required to cast the spell, specified in
     * seconds.
     */
    public float getCastTime() {
        return castTime;
    }

    /**
     * Returns the length of time the spells effects last for, specified in
     * seconds.
     */
    public float getEffectDuration() {
        return effectDuration;
    }

    /**
     * Returns a spell specific value indicate the scale of the effect produced
     * by the spell.
     */
    public int getEffectValue() {
        return effectValue;
    }
}
