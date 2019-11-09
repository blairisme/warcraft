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
            case Blizzard: return 25;
            case Bloodlust: return 50;
            case DeathAndDecay: return 200;
            case DeathCoil: return 6;
            case Exorcism: return 4;
            case EyeOfKilrogg: return 70;
            //case Fireball: : return 100;
            case FlameShield: return 80;
            case Haste: return 50;
            case Heal: return 6;
            case HolyVision: return 70;
            case Invisibility: return 200;
            case Polymorph: return 200;
            case RaiseDead: return 200;
            case Runes: return 200;
            case Slow: return 50;
            case UnholyArmour: return 80;
            case Whirlwind: return 200;
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
            default: return 20;
        }
    }

    public float getCastTime() {
        return 1f;
    }

    public float getEffectDuration() {
        return 20;
    }
}
