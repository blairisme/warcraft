/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.projectile;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.evilbird.test.testcase.AssetBundleTestCase;
import org.junit.Assert;
import org.junit.Test;

/**
 * Instances of this unit test validate logic in the {@link ProjectileAssets}
 * class.
 *
 * @author Blair Butterworth
 */
public class ProjectileAssetsTest extends AssetBundleTestCase<ProjectileAssets>
{
    @Override
    protected ProjectileAssets getAssetBundle(AssetManager assets) {
        return new ProjectileAssets(assets);
    }

    @Test
    public void getTest() {
        bundle.load();
        assets.finishLoading();

        for (ProjectileType type: ProjectileType.values()) {
            Assert.assertNotNull(bundle.getTexture(type));
        }
    }

    @Test (expected = GdxRuntimeException.class)
    public void getWithoutLoadingTest() {
        bundle.getTexture(ProjectileType.Arrow);
    }
}