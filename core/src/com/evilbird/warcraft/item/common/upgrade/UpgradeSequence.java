/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.upgrade;

import com.google.gson.annotations.JsonAdapter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Set;

import static com.evilbird.warcraft.item.common.upgrade.UpgradeSeries.None;

/**
 * Represents a value that can be improved by an {@link Upgrade}.
 *
 * @author Blair Butterworth
 */
@JsonAdapter(UpgradeValueSerializer.class)
public class UpgradeSequence implements UpgradeValue<Integer>
{
    public static final UpgradeSequence Zero = new UpgradeSequence(None, 0);

    protected int basic;
    protected int improved;
    protected int advanced;
    protected UpgradeSeries series;

    /**
     * Constructs a new instance of this class given a single value to be used
     * for all upgrade ranks. This is equivalent to a value not being
     * upgradable.
     */
    public UpgradeSequence(UpgradeSeries series, int value) {
        this(series, value, value, value);
    }

    /**
     * Constructs a new instance of this class given the values that map to
     * ranks of the upgrade series.
     */
    public UpgradeSequence(UpgradeSeries series, int none, int improved) {
        this(series, none, improved, improved);
    }

    /**
     * Constructs a new instance of this class given the values that map to
     * ranks of the upgrade series.
     */
    public UpgradeSequence(UpgradeSeries series, int none, int improved, int advanced) {
        this.series = series;
        this.basic = none;
        this.improved = improved;
        this.advanced = advanced;
    }

    public UpgradeSeries getSeries() {
        return series;
    }

    @Override
    public Set<Upgrade> getUpgrades() {
       return series.getUpgrades();
    }

    @Override
    public Integer getBaseValue() {
        return basic;
    }

    public int getValue(UpgradeRank rank) {
        switch (rank) {
            case None: return basic;
            case Improved: return improved;
            case Advanced: return advanced;
            default: throw new UnsupportedOperationException();
        }
    }

    @Override
    public Integer getValue(Set<Upgrade> researched) {
        UpgradeRank highestRank = UpgradeRank.None;
        for (Upgrade upgrade: researched) {
            UpgradeRank rank = upgrade.getRank();
            if (rank.ordinal() > highestRank.ordinal()) {
                highestRank = rank;
            }
        }
        return getValue(highestRank);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        UpgradeSequence that = (UpgradeSequence)obj;
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
