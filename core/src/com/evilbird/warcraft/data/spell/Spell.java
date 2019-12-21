/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.data.spell;

import static java.lang.Float.MAX_VALUE;

/**
 * Defines spells available to spell casting game objects.
 *
 * @author Blair Butterworth
 */
@SuppressWarnings("checkstyle:MethodParamPad")
public enum Spell
{
    /*                 | Cost      | Time      | Range     | Duration | Value (Unit)                 */
    Blizzard            (200,       1f,         256f,       60f,        50f     /* Damage Per Second */),
    Bloodlust           (50,        1f,         128f,       30f,        2f      /* Buff Scale Factor */),
    DeathAndDecay       (200,       1f,         256f,       60f,        0_0f    /* Damage Per Second */),
    DeathCoil           (100,       1f,         128f,       0f,         4f      /* Health Per Mana */),
    Exorcism            (4,         1f,         128f,       0f,         0_0f    /* Damage Per Second */),
    EyeOfKilrogg        (70,        1f,         0f,         60f,        0f      /* No Effect */),
    Fireball            (100,       1f,         256f,       0f,         50f     /* Damage Per Second */),
    FlameShield         (80,        1f,         256f,       30f,        30f     /* Damage Per Second */),
    Haste               (50,        1f,         256f,       30f,        2f      /* Buff Scale Factor */),
    Heal                (6,         1f,         128f,       0f,         4f      /* Health Per Mana */),
    HolyVision          (70,        1f,         MAX_VALUE,  60f,        160f),  /* Reveal Diameter */
    Invisibility        (200,       1f,         256f,       75f,        0f      /* No Effect */),
    Lightning           (0,         1f,         2,          0f,         9f      /* Damage Per Second */),
    Polymorph           (200,       1f,         256f,       0f,         0f      /* No Effect */),
    RaiseDead           (200,       1f,         128f,       0f,         2f      /* Number of Skeletons */),
    Runes               (200,       1f,         128f,       75f,        50f     /* Damage Per Second */),
    Slow                (50,        1f,         256f,       30f,        0.5f    /* Buff Scale Factor */),
    TouchOfDarkness     (0,         1f,         96f,        0f,         9f      /* Damage Per Second */),
    UnholyArmour        (80,        1f,         256f,       30f,        2f      /* Buff Scale Factor */),
    Whirlwind           (200,       1f,         256f,       60f,        50f     /* Damage Per Second */);

    private int castCost;
    private float castTime;
    private float castRange;
    private float effectDuration;
    private float effectValue;

    Spell(int mana, float time, float range, float duration, float value) {
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
    public float getEffectValue() {
        return effectValue;
    }
}
