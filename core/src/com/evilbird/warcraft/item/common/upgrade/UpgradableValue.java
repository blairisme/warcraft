/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.upgrade;

/**
 * Represents a value that can be improved by an {@link Upgrade}.
 *
 * @author Blair Butterworth
 */
public class UpgradableValue
{
    public static final UpgradableValue Zero = new UpgradableValue(0);

    private float basic;
    private float improved;
    private float advanced;

    /**
     * Constructs a new instance of this class given a single value to be used
     * for all upgrade ranks. This is equivalent to a value not being
     * upgradable.
     */
    public UpgradableValue(float value) {
        this(value, value, value);
    }

    public UpgradableValue(float none, float improved) {
        this(none, improved, improved);
    }

    public UpgradableValue(float none, float improved, float advanced) {
        this.basic = none;
        this.improved = improved;
        this.advanced = advanced;
    }

    public float getValue(UpgradeRank rank) {
        switch (rank) {
            case None: return basic;
            case Improved: return improved;
            case Advanced: return advanced;
            default: throw new UnsupportedOperationException();
        }
    }
}
