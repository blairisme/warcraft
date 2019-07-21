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
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.test.testcase.GameFactoryTestCase;
import com.evilbird.warcraft.common.WarcraftContext;
import com.evilbird.warcraft.item.unit.UnitType;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static com.evilbird.warcraft.common.WarcraftAssetSet.Swamp;
import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftFaction.Orc;
import static com.evilbird.warcraft.item.unit.UnitType.Sheep;

/**
 * Instances of this unit test validate logic in the {@link SheepFactory} class.
 *
 * @author Blair Butterworth
 */
public class SheepFactoryTest extends GameFactoryTestCase<SheepFactory>
{
    @Override
    protected SheepFactory newFactory(DeviceDisplay display, AssetManager assets) {
        return new SheepFactory(assets);
    }

    @Override
    protected Collection<Identifier> getLoadContexts() {
        return Arrays.asList(new WarcraftContext(Human, Swamp), new WarcraftContext(Orc, Swamp));
    }

    @Override
    protected Collection<Identifier> getValueTypes() {
        return Collections.singleton(UnitType.Sheep);
    }

    @Override
    protected Map<String, Object> getValueProperties() {
        return Maps.of("type", Sheep);
    }
}