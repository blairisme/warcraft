/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.data.player;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.warcraft.common.WarcraftFaction;
import com.evilbird.warcraft.common.WarcraftNation;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.common.upgrade.Upgrade;
import com.evilbird.warcraft.item.common.upgrade.UpgradeRank;
import com.evilbird.warcraft.item.common.upgrade.UpgradeSeries;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.inject.Inject;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Instances of this class represent a player, either real or artificial.
 * Player items own other units as well as storing accumulated resources.
 *
 * @author Blair Butterworth
 */
public class Player extends ItemGroup implements ResourceContainer
{
    private int level;
    private WarcraftNation nation;
    private WarcraftFaction faction;
    private Map<String, Double> statistics;
    private Map<String, Double> resources;
    private Map<String, Boolean> upgrades;

    @Inject
    public Player() {
        this.resources = new LinkedHashMap<>();
        this.statistics = new LinkedHashMap<>();
        this.upgrades = new LinkedHashMap<>();
    }

    public boolean isArtificial() {
        return getType() == PlayerType.Artificial;
    }

    public boolean isCorporeal() {
        return getType() == PlayerType.Corporeal;
    }

    public boolean isNeutral() {
        return getType() == PlayerType.Neutral;
    }

    public WarcraftNation getNation() {
        return nation;
    }

    public WarcraftFaction getFaction() {
        return faction;
    }

    public int getLevel() {
        return level;
    }

    public float getResource(ResourceType type) {
        return resources.getOrDefault(type.name(), 0d).floatValue();
    }

    public int getStatistic(PlayerStatistic statistic) {
        return statistics.getOrDefault(statistic.name(), 0d).intValue();
    }

    public UpgradeRank getUpgradeRank(UpgradeSeries series) {
        for (Upgrade upgrade: series.getUpgrades()) {
            if (hasUpgrade(upgrade)) {
                return upgrade.getRank();
            }
        }
        return UpgradeRank.None;
    }

    public boolean hasUpgrade(Upgrade upgrade) {
        return upgrades.getOrDefault(upgrade.name(), Boolean.FALSE);
    }

    public void setNation(WarcraftNation nation) {
        this.nation = nation;
    }

    public void setFaction(WarcraftFaction faction) {
        this.faction = faction;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setResource(ResourceType type, float value) {
        resources.put(type.name(), (double)value);
    }

    public void setStatistic(PlayerStatistic statistic, float value) {
        statistics.put(statistic.name(), (double)value);
    }

    public void setUpgrade(Upgrade upgrade, boolean value) {
        upgrades.put(upgrade.name(), value);
    }

    public void decrementStatistic(PlayerStatistic type, float value) {
        float current = getStatistic(type);
        float updated = Math.max(current - value, 0);
        setStatistic(type, updated);
    }

    public void incrementStatistic(PlayerStatistic type, float value) {
        float current = getStatistic(type);
        float updated = current + value;
        setStatistic(type, updated);
    }

    @Override
    public void setSize(Vector2 size) {
    }

    @Override
    public void setSize(float width, float height) {
    }

    @Override
    public void setPosition(Vector2 position) {
    }

    @Override
    public void setPosition(float x, float y) {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        Player player = (Player)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(nation, player.nation)
            .append(faction, player.faction)
            .append(level, player.level)
            .append(resources, player.resources)
            .append(statistics, player.statistics)
            .append(upgrades, player.upgrades)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(nation)
            .append(faction)
            .append(level)
            .append(resources)
            .append(statistics)
            .append(upgrades)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("identifier", getIdentifier())
            .append("type", getType())
            .append("resources", resources)
            .toString();
    }
}
