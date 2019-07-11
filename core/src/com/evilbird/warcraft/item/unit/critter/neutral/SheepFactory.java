/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.critter.neutral;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.item.unit.critter.Critter;
import com.evilbird.warcraft.item.unit.critter.CritterAssets;
import com.evilbird.warcraft.item.unit.critter.CritterBuilder;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.common.movement.MovementCapability.Land;
import static com.evilbird.warcraft.item.unit.UnitType.Sheep;

/**
 * Represents a sheep, an animal that roams around summer game worlds.
 *
 * @author Blair Butterworth
 */
public class SheepFactory implements GameFactory<Critter>
{
    private CritterAssets assets;
    private CritterBuilder builder;

    @Inject
    public SheepFactory(Device device) {
        this(device.getAssetStorage());
    }

    public SheepFactory(AssetManager manager) {
        this.assets = new CritterAssets(manager, Sheep);
        this.builder = new CritterBuilder(assets);
    }

    @Override
    public void load(Identifier context) {
        assets.load();
    }

    @Override
    public void unload(Identifier context) {
    }

    @Override
    public Critter get(Identifier type) {
        Critter result = builder.build();
        result.setDefence(0);
        result.setHealth(5);
        result.setHealthMaximum(5);
        result.setIdentifier(objectIdentifier("Sheep", result));
        result.setName("Sheep");
        result.setMovementSpeed(8 * 10);
        result.setMovementCapability(Land);
        result.setSight(tiles(2));
        result.setType(Sheep);
        return result;
    }
}