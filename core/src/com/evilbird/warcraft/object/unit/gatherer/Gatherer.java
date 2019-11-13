/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.gatherer;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.warcraft.object.common.resource.ResourceContainer;
import com.evilbird.warcraft.object.common.resource.ResourceType;
import com.evilbird.warcraft.object.common.value.FixedValue;
import com.evilbird.warcraft.object.common.value.Value;
import com.evilbird.warcraft.object.common.value.ValueProperty;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.inject.Inject;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.evilbird.warcraft.object.common.value.FixedValue.Zero;

/**
 * Instances of this class represent a gatherer: a {@link Combatant}
 * specialization that can both fight and collect resources.
 *
 * @author Blair Butterworth
 */
public class Gatherer extends Combatant implements ResourceContainer
{
    private float progress;
    private Value goldGatherSpeed;
    private Value oilGatherSpeed;
    private Value woodGatherSpeed;
    private Value goldCapacity;
    private Value oilCapacity;
    private Value woodCapacity;
    private Map<String, Double> resources;

    @Inject
    public Gatherer(Skin skin) {
        super(skin);
        this.resources = new LinkedHashMap<>(2);
        this.progress = 1;
        this.goldGatherSpeed = Zero;
        this.oilGatherSpeed = Zero;
        this.woodGatherSpeed = Zero;
        this.goldCapacity = Zero;
        this.oilCapacity = Zero;
        this.woodCapacity = Zero;
    }

    /**
     * Removes all resources held by the Gatherer.
     */
    public void clearResources() {
        resources.clear();
    }

    /**
     * Returns whether or not the gatherer is currently gathering resources.
     */
    public boolean isGathering() {
        return progress != 1;
    }

    /**
     * Returns the gatherers current progress gathering resources.
     */
    public float getGathererProgress() {
        return progress;
    }

    /**
     * Returns the gatherers maximum gold carrying capacity.
     */
    public float getGoldCapacity() {
        return goldCapacity.getValue(this);
    }

    /**
     * Returns the gatherers maximum oil carrying capacity.
     */
    public float getOilCapacity() {
        return oilCapacity.getValue(this);
    }

    /**
     * Returns the gatherers maximum wood carrying capacity.
     */
    public float getWoodCapacity() {
        return woodCapacity.getValue(this);
    }

    /**
     * Returns the gatherers gold gathering speed.
     */
    public float getGoldGatherSpeed() {
        return goldGatherSpeed.getValue(this);
    }

    /**
     * Returns the gatherers gold gathering speed.
     */
    public Value getGoldGatherSpeedValue() {
        return goldGatherSpeed;
    }

    /**
     * Returns the gatherers gold gathering speed.
     */
    public ValueProperty getGoldGatherSpeedProperty() {
        return new ValueProperty(this::getGoldGatherSpeedValue, this::setGoldGatherSpeed);
    }

    /**
     * Returns the gatherers oil gathering speed.
     */
    public float getOilGatherSpeed() {
        return oilGatherSpeed.getValue(this);
    }

    /**
     * Returns the gatherers oil gathering speed.
     */
    public Value getOilGatherSpeedValue() {
        return oilGatherSpeed;
    }

    /**
     * Returns the gatherers oil gathering speed.
     */
    public ValueProperty getOilGatherSpeedProperty() {
        return new ValueProperty(this::getOilGatherSpeedValue, this::setOilGatherSpeed);
    }

    /**
     * Returns the gatherers wood gathering speed.
     */
    public float getWoodGatherSpeed() {
        return woodGatherSpeed.getValue(this);
    }

    /**
     * Returns the gatherers wood gathering speed.
     */
    public Value getWoodGatherSpeedValue() {
        return woodGatherSpeed;
    }

    /**
     * Returns the gatherers wood gathering speed.
     */
    public ValueProperty getWoodGatherSpeedProperty() {
        return new ValueProperty(this::getWoodGatherSpeedValue, this::setWoodGatherSpeed);
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

    /**
     * Sets the gathers maximum gold carrying capacity.
     */
    public void setGoldCapacity(Value goldCapacity) {
        this.goldCapacity = goldCapacity;
    }

    /**
     * Sets the gathers gold gathering speed, specified in resources per
     * second.
     */
    public void setGoldGatherSpeed(float goldGatherSpeed) {
        this.goldGatherSpeed = new FixedValue(goldGatherSpeed);
    }

    /**
     * Sets the gathers gold gathering speed, specified in resources per
     * second.
     */
    public void setGoldGatherSpeed(Value goldGatherSpeed) {
        this.goldGatherSpeed = goldGatherSpeed;
    }

    /**
     * Sets the gathers maximum oil carrying capacity.
     */
    public void setOilCapacity(Value oilCapacity) {
        this.oilCapacity = oilCapacity;
    }

    /**
     * Sets the gathers oil gathering speed, specified in resources per second.
     */
    public void setOilGatherSpeed(float oilGatherSpeed) {
        this.oilGatherSpeed = new FixedValue(oilGatherSpeed);
    }

    /**
     * Sets the gathers oil gathering speed, specified in resources per
     * second.
     */
    public void setOilGatherSpeed(Value oilGatherSpeed) {
        this.oilGatherSpeed = oilGatherSpeed;
    }

    /**
     * Sets the gathers maximum wood carrying capacity.
     */
    public void setWoodCapacity(Value woodCapacity) {
        this.woodCapacity = woodCapacity;
    }

    /**
     * Sets the gathers wood gathering speed, specified in resources per
     * second.
     */
    public void setWoodGatherSpeed(float woodGatherSpeed) {
        this.woodGatherSpeed = new FixedValue(woodGatherSpeed);
    }

    /**
     * Sets the gathers wood gathering speed, specified in resources per
     * second.
     */
    public void setWoodGatherSpeed(Value woodGatherSpeed) {
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
