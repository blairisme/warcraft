/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.ingame;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.state.StateService;
import com.evilbird.test.testcase.GameFactoryTestCase;
import com.evilbird.warcraft.common.WarcraftContext;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static com.evilbird.warcraft.common.WarcraftAssetSet.Winter;
import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftFaction.Orc;

/**
 * Instances of this unit test validate logic in the {@link IngameMenuFactory}
 * class.
 *
 * @author Blair Butterworth
 */
public class IngameMenuFactoryTest extends GameFactoryTestCase<IngameMenuFactory>
{
    @Override
    protected IngameMenuFactory newFactory(DeviceDisplay display, AssetManager assets) {
        StateService stateService = Mockito.mock(StateService.class);
        return new IngameMenuFactory(display, stateService, assets);
    }

    @Override
    protected Collection<Identifier> getLoadContexts() {
        return Arrays.asList(new WarcraftContext(Human, Winter), new WarcraftContext(Orc, Winter));
    }

    @Override
    protected Collection<Identifier> getValueTypes() {
        return Arrays.asList(IngameMenuType.values());
    }

    @Override
    protected Map<String, Object> getValueProperties() {
        return Collections.emptyMap();
    }
}