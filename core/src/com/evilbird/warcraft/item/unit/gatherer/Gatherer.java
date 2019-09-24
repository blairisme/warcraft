/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.gatherer;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.common.upgrade.UpgradableValue;
import com.evilbird.warcraft.item.common.upgrade.UpgradeRank;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.inject.Inject;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.evilbird.warcraft.item.common.upgrade.UpgradeSeries.GoldProduction;
import static com.evilbird.warcraft.item.common.upgrade.UpgradeSeries.OilProduction;
import static com.evilbird.warcraft.item.common.upgrade.UpgradeSeries.WoodProduction;

/**
 * Instances of this class represent a gatherer: a {@link Combatant}
 * specialization that can both fight and collect resources.
 *
 * @author Blair Butterworth
 */
public class Gatherer extends Combatant implements ResourceContainer
{
    private float progress;
    private float goldGatherSpeed;
    private float oilGatherSpeed;
    private float woodGatherSpeed;
    private UpgradableValue goldCapacity;
    private UpgradableValue oilCapacity;
    private UpgradableValue woodCapacity;
    private Map<String, Double> resources;

    @Inject
    public Gatherer(Skin skin) {
        super(skin);
        resources = new LinkedHashMap<>(2);
        progress = 1;
    }

    public void clearResources() {
        resources.clear();
    }

    public boolean isGathering() {
        return progress != 1;
    }

    public float getGathererProgress() {
        return progress;
    }

    public float getGoldCapacity() {
        return getGoldCapacity(getPlayerUpgrade(GoldProduction));
    }

    public float getGoldCapacity(UpgradeRank rank) {
        return goldCapacity.getValue(rank);
    }

    public float getOilCapacity() {
        return getOilCapacity(getPlayerUpgrade(OilProduction));
    }

    public float getOilCapacity(UpgradeRank rank) {
        return oilCapacity.getValue(rank);
    }

    public float getWoodCapacity() {
        return getWoodCapacity(getPlayerUpgrade(WoodProduction));
    }

    public float getWoodCapacity(UpgradeRank rank) {
        return woodCapacity.getValue(rank);
    }

    public float getGoldGatherSpeed() {
        return goldGatherSpeed;
    }

    public float getOilGatherSpeed() {
        return oilGatherSpeed;
    }

    public float getWoodGatherSpeed() {
        return woodGatherSpeed;
    }

    @Override
    public float getResource(ResourceType type) {
        return resources.getOrDefault(type.name(), 0.0).floatValue();
    }

    public boolean hasResource(ResourceType type) {
        return resources.containsKey(type.name());
    }

    public boolean hasOtherResource(ResourceType type) {
        return !resources.isEmpty() && !resources.containsKey(type.name());
    }

    public void setGathererProgress(float progress) {
        this.progress = progress;
    }

    public void setGoldCapacity(UpgradableValue goldCapacity) {
        this.goldCapacity = goldCapacity;
    }

    public void setGoldGatherSpeed(float goldGatherSpeed) {
        this.goldGatherSpeed = goldGatherSpeed;
    }

    public void setOilCapacity(UpgradableValue oilCapacity) {
        this.oilCapacity = oilCapacity;
    }

    public void setOilGatherSpeed(float oilGatherSpeed) {
        this.oilGatherSpeed = oilGatherSpeed;
    }

    public void setWoodCapacity(UpgradableValue woodCapacity) {
        this.woodCapacity = woodCapacity;
    }

    public void setWoodGatherSpeed(float woodGatherSpeed) {
        this.woodGatherSpeed = woodGatherSpeed;
    }

    @Override
    public void setResource(ResourceType type, float value) {
        resources.put(type.name(), (double)value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper("combatant")
            .append("progress", progress)
            .append("resources", resources)
            .toString();
    }
}
