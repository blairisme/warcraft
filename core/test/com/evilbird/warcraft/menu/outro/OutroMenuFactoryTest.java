/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.outro;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.I18NBundle;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.test.testcase.GameFactoryTestCase;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.warcraft.common.WarcraftContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static com.evilbird.test.data.device.TestDevices.newTestDevice;
import static com.evilbird.warcraft.common.WarcraftAssetSet.Summer;
import static com.evilbird.warcraft.common.WarcraftAssetSet.Winter;
import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftFaction.Orc;

/**
 * Instances of this unit test validate logic in the {@link OutroMenuFactory}
 * class.
 *
 * @author Blair Butterworth
 */
public class OutroMenuFactoryTest extends GameFactoryTestCase<OutroMenuFactory>
{
    @Override
    protected OutroMenuFactory newFactory(DeviceDisplay display, AssetManager assets) {
        return new OutroMenuFactory(display, assets);
    }

    @Override
    protected Collection<Identifier> getLoadContexts() {
        return Arrays.asList(new WarcraftContext(Human, Winter), new WarcraftContext(Orc, Summer));
    }

    @Override
    protected Collection<Identifier> getValueTypes() {
        return Arrays.asList(OutroMenuType.values());
    }

    @Override
    protected Map<String, Object> getValueProperties() {
        return Collections.emptyMap();
    }
}