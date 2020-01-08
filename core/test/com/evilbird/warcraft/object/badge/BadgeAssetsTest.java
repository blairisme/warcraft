/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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