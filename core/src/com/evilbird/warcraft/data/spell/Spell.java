/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.data.spell;

/**
 * Defines spells available to spell casting game objects.
 *
 * @author Blair Butterworth
 */
public enum Spell
{
    /*               | Cost  | Time  | Range | Duration | Value | */
    Blizzard            (25,    1f,     256f,   60f,    0_0),
    Bloodlust           (50,    1f,     128f,   30f,    0_0),
    DeathAndDecay       (200,   1f,     256f,   0_0f,   0_0),
    DeathCoil           (100,   1f,     128f,   0_0f,   0_0),
    Exorcism            (4,     1f,     128f,   0f,     0_0),
    EyeOfKilrogg        (70,    1f,     0,      60f,    0),
    Fireball            (100,   1f,     256f,   0f,     0_0),
    FlameShield         (80,    1f,     256f,   30f,    2),
    Haste               (50,    1f,     256f,   30f,    0_0),
    Heal                (6,     1f,     128f,   0f,     4),
    HolyVision          (70,    1f,     0,      60f,    160),
    Invisibility        (200,   1f,     256f,   75f,    0),
    Lightning           (0,     1f,     2,      0f,     9),
    Polymorph           (200,   1f,     256f,   0f,     0),
    RaiseDead           (200,   1f,     128f,   0f,     2),
    Runes               (200,   1f,     128f,   75f,    0_0),
    Slow                (50,    1f,     256f,   30f,    0_0),
    TouchOfDarkness     (0,     1f,     96f,    0,      9),
    UnholyArmour        (80,    1f,     256f,   30f,    0_0),
    Whirlwind           (200,   1f,     256f,   60f,    0_0);

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
