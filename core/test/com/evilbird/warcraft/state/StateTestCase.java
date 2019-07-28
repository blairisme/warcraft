/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.utils.TestAssetManager;
import com.evilbird.test.utils.TestFileHandleResolver;
import org.junit.Before;

import static org.mockito.Mockito.when;

public class StateTestCase extends GameTestCase
{
    protected LevelLoader levelLoader;
    protected AssetManager assetManager;
    protected TestFileHandleResolver assetResolver;

    @Before
    public void setup() {
        super.setup();
        assetResolver = new TestFileHandleResolver();
        assetManager = TestAssetManager.getTestAssetManager(assetResolver);
        levelLoader = new LevelLoader(itemFactory, assetManager);
        when(device.getAssetStorage()).thenReturn(assetManager);
    }

    protected void loadAssets() {
        assetManager.load(assetResolver.fullPath("/warcraft/asset/texture/barracks.png"), Texture.class);
        assetManager.load(assetResolver.fullPath("/warcraft/asset/texture/farm.png"), Texture.class);
        assetManager.load(assetResolver.fullPath("/warcraft/asset/texture/footman.png"), Texture.class);
        assetManager.load(assetResolver.fullPath("/warcraft/asset/texture/gold_mine.png"), Texture.class);
        assetManager.load(assetResolver.fullPath("/warcraft/asset/texture/grunt.png"), Texture.class);
        assetManager.load(assetResolver.fullPath("/warcraft/asset/texture/peasant.png"), Texture.class);
        assetManager.load(assetResolver.fullPath("/warcraft/asset/texture/terrain.png"), Texture.class);
        assetManager.load(assetResolver.fullPath("/warcraft/asset/texture/townhall.png"), Texture.class);
        assetManager.finishLoading();
    }
}
