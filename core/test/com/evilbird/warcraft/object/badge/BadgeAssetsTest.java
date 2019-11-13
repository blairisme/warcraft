/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.badge;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.test.testcase.AssetBundleTestCase;
import org.junit.Assert;
import org.junit.Test;

/**
 * Instances of this unit test validate logic in the {@link BadgeAssets}
 * class.
 *
 * @author Blair Butterworth
 */
public class BadgeAssetsTest extends AssetBundleTestCase<BadgeAssets>
{
    @Override
    protected BadgeAssets getAssetBundle(AssetManager assets) {
        return new BadgeAssets(assets);
    }

    @Test
    public void getIconTest() {
        bundle.load();
        assets.finishLoading();

        for (BadgeType type: BadgeType.values()) {
            Drawable icon = bundle.getIcon(type);
            Assert.assertNotNull(icon);
        }
    }
}