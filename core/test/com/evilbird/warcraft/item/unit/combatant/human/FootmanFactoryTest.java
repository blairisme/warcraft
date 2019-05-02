/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant.human;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static com.evilbird.test.data.device.TestAssets.newAssetManagerMock;

/**
 * Instances of this unit test validate logic in the {@link FootmanFactory} class.
 *
 * @author Blair Butterworth
 */
public class FootmanFactoryTest extends GameTestCase
{
    private static final String BASE = "data/textures/human/perennial/footman.png";

    private FootmanFactory factory;
    private AssetManager assets;

    @Before
    public void setup() {
        assets = newAssetManagerMock();
        factory = new FootmanFactory(assets);
    }

    @Test
    public void loadTest() {
        factory.load();
        Mockito.verify(assets).load(BASE, Texture.class);
    }

    @Test
    public void getTest() {
        Combatant footman = (Combatant)factory.get();
        Assert.assertNotNull(footman);
        Assert.assertEquals(UnitType.Footman, footman.getType());
    }
}