/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.resource;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.test.testcase.GameFactoryTestCase;
import com.evilbird.warcraft.common.WarcraftContext;
import com.evilbird.warcraft.item.ui.display.HudControl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static com.evilbird.warcraft.common.WarcraftAssetSet.Summer;
import static com.evilbird.warcraft.common.WarcraftAssetSet.Swamp;
import static com.evilbird.warcraft.common.WarcraftAssetSet.Winter;
import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftFaction.Orc;

/**
 * Instances of this unit test validate logic in the {@link ResourcePaneAssets}
 * class.
 *
 * @author Blair Butterworth
 */
public class ResourcePaneFactoryTest extends GameFactoryTestCase<ResourcePaneFactory>
{
    @Override
    protected ResourcePaneFactory newFactory(DeviceDisplay display, AssetManager assets) {
        return new ResourcePaneFactory(assets);
    }

    @Override
    protected Collection<Identifier> getLoadContexts() {
        return Arrays.asList(
            new WarcraftContext(Human, Summer),
            new WarcraftContext(Human, Swamp),
            new WarcraftContext(Human, Winter),
            new WarcraftContext(Orc, Summer),
            new WarcraftContext(Orc, Swamp),
            new WarcraftContext(Orc, Winter));
    }

    @Override
    protected Collection<Identifier> getValueTypes() {
        return Collections.singleton(HudControl.ResourcePane);
    }

    @Override
    protected Map<String, Object> getValueProperties() {
        return Collections.emptyMap();
    }
}