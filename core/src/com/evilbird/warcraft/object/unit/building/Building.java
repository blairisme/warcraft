/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.building;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.common.graphics.renderable.Renderable;
import com.evilbird.warcraft.object.common.resource.ResourceContainer;
import com.evilbird.warcraft.object.common.resource.ResourceType;
import com.evilbird.warcraft.object.common.upgrade.Upgrade;
import com.evilbird.warcraft.object.common.upgrade.UpgradeContainer;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.gatherer.Gatherer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.evilbird.engine.common.graphics.renderable.EmptyRenderable.BlankRenderable;

/**
 * Instances of this class represent a building, a {@link Unit} specialization
 * that provides the user the ability to train {@link Unit Units}.
 *
 * @author Blair Butterworth
 */
public class Building extends Unit implements ResourceContainer, UpgradeContainer
{
    private float producing;
    private float constructing;
    private Collection<Upgrade> upgrades;
    private Map<String, Double> resources;

    private transient BuildingStyle style;
    private transient Renderable damage;
    private transient float lightDamageThreshold;
    private transient float heavyDamageThreshold;

    /**
     * Constructs a new instance of this class given a {@link Skin} containing
     * an {@link BuildingStyle}, specifying the visual and auditory
     * presentation of the new Unit.
     *
     * @param skin a {@code Skin} instance containing a {@code BuildingStyle}.
     *
     * @throws NullPointerException if the given skin is {@code null} or
     *                              doesn't contain a {@code BuildingStyle}.
     */
    public Building(Skin skin) {
        super(skin);
        producing = 1;
        constructing = 1;
        upgrades = Collections.emptyList();
        resources = new LinkedHashMap<>(2);
    }

    /**
     * Removes any {@link Upgrade Upgrades} contained in the building.
     */
    public void clearUpgrades() {
        upgrades.clear();
    }

    /**
     * Returns the {@link Gatherer} that is constructing the building.
     */
    public Gatherer getConstructor() {
        return (Gatherer)getAssociatedItem();
    }

    /**
     * Returns the progress of the buildings construction, specified as a
     * percentage ranging from 0 to 1.
     */
    public float getConstructionProgress() {
        return constructing;
    }

    /**
     * Returns the progress of the buildings production process, specified as a
     * percentage ranging from 0 to 1.
     */
    public float getProductionProgress() {
        return producing;
    }

    /**
     * Returns the value of a resource held in the building.
     */
    public float getResource(ResourceType type) {
        return Maps.getOrDefault(resources, type.name(), 0.0).floatValue();
    }

    /**
     * Returns a {@link Collection} of those {@link Upgrade Upgrades} contained
     * in the building.
     */
    public Collection<Upgrade> getUpgrades() {
        return upgrades;
    }

    /**
     * Returns whether of not the building is currently being constructed.
     */
    public boolean isConstructing() {
        return constructing != 1;
    }

    /**
     * Returns whether of not the building is currently producing something.
     */
    public boolean isProducing() {
        return producing != 1;
    }

    /**
     * Sets the {@link Gatherer} that is constructing the building.
     */
    public void setConstructor(Gatherer gatherer) {
        setAssociatedItem(gatherer);
    }

    /**
     * Sets the progress of the buildings construction, specified as a
     * percentage ranging from 0 to 1.
     */
    public void setConstructionProgress(float constructing) {
        this.constructing = constructing;
    }

    @Override
    public void setHealth(float health) {
        super.setHealth(health);
        setDamageAnimation(health);
    }

    @Override
    public void setHealthMaximum(float healthMaximum) {
        super.setHealthMaximum(healthMaximum);
        setDamageThreshold(healthMaximum);
        setDamageAnimation();
    }

    /**
     * Sets the progress of the buildings production process, specified as a
     * percentage ranging from 0 to 1.
     */
    public void setProductionProgress(float progress) {
        this.producing = progress;
    }

    /**
     * Sets the value of a resource held in the building.
     */
    public void setResource(ResourceType type, float value) {
        this.resources.put(type.name(), (double)value);
    }

    /**
     * Assigns the given {@link Upgrade Upgrades} to the building.
     */
    public void setUpgrade(Upgrade upgrade) {
        this.upgrades = new ArrayList<>();
        this.upgrades.add(upgrade);
    }

    /**
     * Assigns the given {@link Collection} of {@link Upgrade Upgrades} to the
     * building.
     */
    public void setUpgrades(Collection<Upgrade> upgrades) {
        this.upgrades = upgrades;
    }

    @Override
    public void setStyle(String name) {
        super.setStyle(name);
        Skin skin = getSkin();
        setStyle(skin.get(name, BuildingStyle.class));
    }

    public void setStyle(BuildingStyle style) {
        this.style = style;
        setDamageAnimation();
    }

    @Override
    public void draw(Batch batch, float alpha) {
        super.draw(batch, alpha);
        damage.draw(batch, position, size);
    }

    @Override
    public void update(float time) {
        super.update(time);
        damage.update(time);
    }

    private void setDamageAnimation() {
        setDamageAnimation(getHealth());
    }

    private void setDamageAnimation(float health) {
        if (health > 0 && health < heavyDamageThreshold) {
            damage = style.heavyDamage;
        } else if (health >= heavyDamageThreshold && health < lightDamageThreshold) {
            damage = style.lightDamage;
        } else {
            damage = BlankRenderable;
        }
    }

    private void setDamageThreshold(float healthMaximum) {
        lightDamageThreshold = healthMaximum * 0.66f;
        heavyDamageThreshold = healthMaximum * 0.33f;
    }
}
