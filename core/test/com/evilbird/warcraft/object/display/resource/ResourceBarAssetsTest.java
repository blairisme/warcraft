/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.resource;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.test.testcase.AssetBundleTestCase;
import com.evilbird.warcraft.object.display.views.resource.ResourceBarAssets;
import com.evilbird.warcraft.state.WarcraftContext;

import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftSeason.Winter;

/**
 * Instances of this unit test validate logic in the {@link ResourceBarAssets}
 * class.
 *
 * @author Blair Butterworth
 */
public class ResourceBarAssetsTest extends AssetBundleTestCase<ResourceBarAssets>
{
    @Override
    protected ResourceBarAssets getAssetBundle(AssetManager assets) {
        return new ResourceBarAssets(assets, new WarcraftContext(Human, Winter));
    }
}