/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.maps;

import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.utils.TestFileHandleResolver;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Instances of this unit test validate logic in the {@link TiledMapFile}
 * class.
 *
 * @author Blair Butterworth
 */
public class TiledMapFileTest extends GameTestCase
{
    private TiledMapFile mapFile;

    @Before
    public void setup() {
        TiledMapLoader loader = new TiledMapLoader(new TestFileHandleResolver());
        mapFile = loader.load("/warcraft/state/level.tmx");
    }

    @Test
    public void getMapSizeTest() {
        GridPoint2 expected = new GridPoint2(32, 32);
        assertEquals(expected, mapFile.getMapSize());
    }

    @Test
    public void getTileSizeTest() {
        GridPoint2 expected = new GridPoint2(32, 32);
        assertEquals(expected, mapFile.getTileSize());
    }
}