/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.upgrade;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static com.evilbird.warcraft.item.common.upgrade.UpgradeSeries.None;

/**
 * Represents a value that can be improved by an {@link Upgrade}.
 *
 * @author Blair Butterworth
 */
public class UpgradableValue
{
    public static final UpgradableValue Zero = new UpgradableValue(None, 0);

    private float basic;
    private float improved;
    private float advanced;
    private UpgradeSeries series;

    /**
     * Constructs a new instance of this class given a single value to be used
     * for all upgrade ranks. This is equivalent to a value not being
     * upgradable.
     */
    public UpgradableValue(UpgradeSeries series, float value) {
        this(series, value, value, value);
    }

    /**
     * Constructs a new instance of this class given the values that map to
     * ranks of the upgrade series.
     */
    public UpgradableValue(UpgradeSeries series, float none, float improved, float advanced) {
        this.series = series;
        this.basic = none;
        this.improved = improved;
        this.advanced = advanced;
    }

    public UpgradeSeries getSeries() {
        return series;
    }

    public float getValue(UpgradeRank rank) {
        switch (rank) {
            case None: return basic;
            case Improved: return improved;
            case Advanced: return advanced;
            default: throw new UnsupportedOperationException();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        UpgradableValue that = (UpgradableValue)obj;
        return new EqualsBuilder()
            .append(basic, that.basic)
            .append(improved, that.improved)
            .append(advanced, that.advanced)
            .append(series, that.series)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(basic)
            .append(improved)
            .append(advanced)
            .append(series)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("basic", basic)
            .append("improved", improved)
            .append("advanced", advanced)
            .toString();
    }
}
