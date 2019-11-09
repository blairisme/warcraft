/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.spell;

import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;

/**
 * Defines spells available to spell casting game objects.
 *
 * @author Blair Butterworth
 */
public enum Spell
{
    Blizzard,
    Bloodlust,
    DeathAndDecay,
    DeathCoil,
    Exorcism,
    EyeOfKilrogg,
    FlameShield,
    Haste,
    Heal,
    HolyVision,
    Invisibility,
    Polymorph,
    RaiseDead,
    Runes,
    Slow,
    UnholyArmour,
    Whirlwind;

    public int getManaCost() {
        switch (this) {
            case Blizzard: return 0;
            case Exorcism: return 4;
            case Heal: return 6;
            case HolyVision: return 70;
            default: throw new UnsupportedOperationException();
        }
    }

    public int getValue() {
        switch (this) {
            case Blizzard: return 0;
            case Bloodlust: return 2;
            case Exorcism: return 20;
            case Heal: return 40;
            case HolyVision: return tiles(5);
            default: throw new UnsupportedOperationException();
        }
    }

    public float getCastTime() {
        switch (this) {
            case Bloodlust:
            case Exorcism:
            case Heal: return 1f;
            case HolyVision: return 5f;
            case Blizzard: return 10f;
            default: throw new UnsupportedOperationException();
        }
    }

    public float getEffectDuration() {
        return 20;
    }
}
