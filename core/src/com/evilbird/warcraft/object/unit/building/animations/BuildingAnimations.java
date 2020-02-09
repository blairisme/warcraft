/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.building.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.engine.common.time.Duration;
import com.evilbird.warcraft.object.unit.UnitProduction;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.building.BuildingAssets;

import static com.evilbird.engine.common.time.ChronoUnit.SECONDS;
import static com.evilbird.warcraft.object.unit.UnitAnimation.BuildingSite;
import static com.evilbird.warcraft.object.unit.UnitAnimation.BuildingUpgrade;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Construct;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Death;
import static com.evilbird.warcraft.object.unit.UnitAnimation.HeavyDamage;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.object.unit.UnitAnimation.LightDamage;
import static java.util.Objects.requireNonNull;

/**
 * Defines a catalog of animations as laid out in building texture atlas files.
 * Animations are provided for construction, destruction, pre-construction and
 * post-construction.
 *
 * @author Blair Butterworth
 */
public class BuildingAnimations extends AnimationCatalog
{
    public BuildingAnimations(BuildingAssets assets) {
        this(assets.getBaseTexture(),
            assets.getConstructionTexture(),
            assets.getDestructionTexture(),
            assets.getLightDamageTexture(),
            assets.getHeavyDamageTexture(),
            assets.getSize(),
            assets.getType());
    }

    public BuildingAnimations(
        Texture base,
        Texture construction,
        Texture destruction,
        Texture lightDamage,
        Texture heavyDamage,
        GridPoint2 size,
        UnitType type)
    {
        super(7);

        requireNonNull(base);
        requireNonNull(construction);
        requireNonNull(destruction);
        requireNonNull(size);

        idle(base, size);
        buildingSite(construction, size);
        buildingUpgrade(base, size);
        construction(base, construction, size, type);
        destruction(destruction);
        damage(lightDamage, heavyDamage);
    }

    private void idle(Texture base, GridPoint2 size) {
        animation(Idle)
            .withTexture(base)
            .withSequence(0, 1)
            .withSize(size)
            .withInterval(10f)
            .singleDirection()
            .looping();
    }

    private void buildingSite(Texture construction, GridPoint2 size) {
        animation(BuildingSite)
            .withTexture(construction)
            .withSequence(0, 1)
            .withSize(size)
            .withInterval(10f)
            .singleDirection()
            .looping();
    }

    private void buildingUpgrade(Texture base, GridPoint2 size) {
        animation(BuildingUpgrade)
            .withTexture(base)
            .withSequence(size.y, 1)
            .withSize(size)
            .withInterval(10f)
            .singleDirection()
            .looping();
    }

    private void construction(Texture base, Texture construction, GridPoint2 size, UnitType type) {
        UnitProduction production = type.getProduction();
        Duration duration = production.getDuration();
        float interval = duration.get(SECONDS) / 4f;

        sequence(Construct)
            .element()
                .withTexture(construction)
                .withSequence(0, 2)
                .withSize(size)
                .withInterval(interval)
                .singleDirection()
            .element()
                .withTexture(base)
                .withSequence(0, 2)
                .withSize(size)
                .withInterval(interval)
                .singleDirection()
                .reversed();
    }

    private void destruction(Texture destruction) {
        animation(Death)
            .withTexture(destruction)
            .withSequence(0, 1)
            .withSize(destruction.getWidth(), destruction.getWidth())
            .withInterval(10f)
            .singleDirection()
            .notLooping();
    }

    private void damage(Texture lightDamage, Texture heavyDamage) {
        animation(LightDamage)
            .withTexture(lightDamage)
            .withSequence(0, 6)
            .withSize(lightDamage.getWidth(), lightDamage.getWidth())
            .withInterval(0.06f)
            .singleDirection()
            .looping();

        animation(HeavyDamage)
            .withTexture(heavyDamage)
            .withSequence(0, 10)
            .withSize(heavyDamage.getWidth(), heavyDamage.getWidth())
            .withInterval(0.06f)
            .singleDirection()
            .looping();
    }
}
