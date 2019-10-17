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
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.warcraft.common.TeamColour;
import com.evilbird.warcraft.common.WarcraftFaction;
import com.evilbird.warcraft.common.WarcraftNation;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.common.upgrade.Upgrade;
import com.evilbird.warcraft.item.common.upgrade.UpgradeRank;
import com.evilbird.warcraft.item.common.upgrade.UpgradeSeries;
import com.evilbird.warcraft.item.unit.Unit;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.inject.Inject;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import static com.evilbird.warcraft.common.TeamColour.None;
import static com.evilbird.warcraft.common.WarcraftNation.Neutral;

/**
 * Instances of this class represent a player, either real or artificial.
 * Player items own other units as well as storing accumulated resources.
 *
 * @author Blair Butterworth
 */
public class Player extends ItemGroup implements ResourceContainer
{
    private int level;
    private int team;
    private boolean capturable;
    private boolean controllable;
    private boolean viewable;
    private TeamColour colour;
    private WarcraftNation nation;
    private WarcraftFaction faction;
    private Map<String, Double> statistics;
    private Map<String, Double> resources;
    private Map<String, Boolean> upgrades;

    @Inject
    public Player() {
        this.level = 1;
        this.team = 0;
        this.colour = None;
        this.nation = Neutral;
        this.capturable = false;
        this.faction = WarcraftFaction.Neutral;
        this.resources = new LinkedHashMap<>();
        this.statistics = new LinkedHashMap<>();
        this.upgrades = new LinkedHashMap<>();
    }

    /**
     * Assigns an {@link Item} to the Player. If the given {@code Item} is a
     * {@link Unit} then the Units team colour will be set to that of the
     * Players colour.
     */
    @Override
    public void addItem(Item item) {
        super.addItem(item);
        if (item instanceof Unit) {
            Unit unit = (Unit)item;
            unit.setTeamColour(colour);
        }
    }

    /**
     * Returns the players {@link WarcraftNation nation}, a distinct group
     * within the players {@link Player#faction}.
     */
    public WarcraftNation getNation() {
        return nation;
    }

    /**
     * Returns the players {@link WarcraftFaction faction}.
     */
    public WarcraftFaction getFaction() {
        return faction;
    }

    /**
     * A value indicating the technological attainment of the Player. This is
     * commonly used to restrict the buildings or units available to the user.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Returns the amount of the given {@link ResourceType resource} owned by
     * the player.
     */
    public float getResource(ResourceType type) {
        return resources.getOrDefault(type.name(), 0d).floatValue();
    }

    /**
     * Returns the value of a {@link PlayerStatistic} kept about Player actions.
     */
    public int getStatistic(PlayerStatistic statistic) {
        return statistics.getOrDefault(statistic.name(), 0d).intValue();
    }

    /**
     * Returns the Players team number. If different players are allies they
     * will share the same team number. Conversely, enemies will have different
     * team numbers.
     */
    public int getTeam() {
        return team;
    }

    public UpgradeRank getUpgradeRank(UpgradeSeries series) {
        List<Upgrade> upgrades = series.getUpgrades();
        ListIterator<Upgrade> iterator = upgrades.listIterator();

        while (iterator.hasPrevious()) {
            Upgrade upgrade = iterator.previous();
            if (hasUpgrade(upgrade)) {
                return upgrade.getRank();
            }
        }
        return UpgradeRank.None;
    }

    /**
     * Returns whether or not the Player has the given {@link Upgrade}.
     */
    public boolean hasUpgrade(Upgrade upgrade) {
        return upgrades.getOrDefault(upgrade.name(), Boolean.FALSE);
    }

    /**
     * Returns whether or not the player is owned by an artificial player,
     * excluding the neutral player.
     */
    public boolean isArtificial() {
        return getType() == PlayerType.Artificial;
    }

    /**
     * Returns whether or not the players children, units and buildings, can be
     * captured by the corporeal player: the user.
     */
    public boolean isCapturable() {
        return capturable;
    }

    /**
     * Returns whether or not the players children, units and buildings, can be
     * controlled by the corporeal player: the user.
     */
    public boolean isControllable() {
        return controllable;
    }

    /**
     * Returns whether or not the player is owned by the user.
     */
    public boolean isCorporeal() {
        return getType() == PlayerType.Corporeal;
    }

    /**
     * Returns whether or not the player is the neutral player, a special
     * player that owns all critters and natural resources.
     */
    public boolean isNeutral() {
        return getType() == PlayerType.Neutral;
    }

    /**
     * Returns whether or not the players children, units and buildings, can be
     * seen by the corporeal player, the user, through the fog of war.
     */
    public boolean isViewable() {
        return viewable;
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

    /**
     * Sets whether or not the players children, units and buildings, can be
     * captured by the corporeal player: the user.
     */
    public void setCapturable(boolean capturable) {
        this.capturable = capturable;
    }

    /**
     * Sets the colour of the players children, unit and buildings, visually
     * denoting those game objects owned by the player.
     */
    public void setColour(TeamColour colour) {
        this.colour = colour;
    }

    /**
     * Sets whether or not the players children, units and buildings, can be
     * controlled by the corporeal player: the user.
     */
    public void setControllable(boolean controllable) {
        this.controllable = controllable;
    }

    /**
     * Sets the players {@link WarcraftNation nation}, a distinct group
     * within the players {@link Player#faction}.
     */
    public void setNation(WarcraftNation nation) {
        this.nation = nation;
    }

    /**
     * Sets the players {@link WarcraftFaction faction}.
     */
    public void setFaction(WarcraftFaction faction) {
        this.faction = faction;
    }

    /**
     * Sets the players level: a value indicating the technological attainment
     * of the Player. This is commonly used to restrict the buildings or units
     * available to the user.
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Sets the amount of the given {@link ResourceType resource} owned by
     * the player.
     */
    public void setResource(ResourceType type, float value) {
        resources.put(type.name(), (double)value);
    }

    /**
     * Sets the value of a {@link PlayerStatistic} kept about Player actions.
     */
    public void setStatistic(PlayerStatistic statistic, float value) {
        statistics.put(statistic.name(), (double)value);
    }

    /**
     * Sets the Players team number. If different players are allies they
     * will share the same team number. Conversely, enemies will have different
     * team numbers.
     */
    public void setTeam(int team) {
        this.team = team;
    }

    /**
     * Sets whether or not the Player has the given {@link Upgrade}.
     */
    public void setUpgrade(Upgrade upgrade, boolean value) {
        upgrades.put(upgrade.name(), value);
    }

    /**
     * Sets whether or not the Player has the given {@link Upgrade}.
     */
    public void setUpgrades(Collection<Upgrade> upgrades, boolean value) {
        upgrades.forEach(upgrade -> setUpgrade(upgrade, value));
    }

    /**
     * Sets whether or not the players children, units and buildings, can be
     * seen by the corporeal player, the user, through the fog of war.
     */
    public void setViewable(boolean viewable) {
        this.viewable = viewable;
    }

    /**
     * Calling this method will have no effect, as players do not have an
     * intrinsic size.
     */
    @Override
    public void setSize(Vector2 size) {
    }

    /**
     * Calling this method will have no effect, as players do not have an
     * intrinsic size.
     */
    @Override
    public void setSize(float width, float height) {
    }

    /**
     * Calling this method will have no effect, as players do not have an
     * intrinsic position.
     */
    @Override
    public void setPosition(Vector2 position) {
    }

    /**
     * Calling this method will have no effect, as players do not have an
     * intrinsic position.
     */
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
            .append(colour, player.colour)
            .append(team, player.team)
            .append(capturable, player.capturable)
            .append(controllable, player.controllable)
            .append(viewable, player.viewable)
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
            .append(colour)
            .append(team)
            .append(capturable)
            .append(controllable)
            .append(viewable)
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
