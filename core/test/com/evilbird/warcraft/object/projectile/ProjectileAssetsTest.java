/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
            Assert.assertNotNull(bundle.getBaseTexture(type));
        }
    }

    @Test (expected = GdxRuntimeException.class)
    public void getWithoutLoadingTest() {
        bundle.getBaseTexture(ProjectileType.Arrow);
    }
}