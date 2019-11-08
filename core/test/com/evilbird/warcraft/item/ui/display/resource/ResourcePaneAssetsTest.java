/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.resource;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.test.testcase.AssetBundleTestCase;
import com.evilbird.warcraft.item.display.resource.ResourcePaneAssets;
import com.evilbird.warcraft.state.WarcraftContext;

import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftSeason.Winter;

/**
 * Instances of this unit test validate logic in the {@link com.evilbird.warcraft.item.display.resource.ResourcePaneAssets}
 * class.
 *
 * @author Blair Butterworth
 */
public class ResourcePaneAssetsTest extends AssetBundleTestCase<com.evilbird.warcraft.item.display.resource.ResourcePaneAssets>
{
    @Override
    protected com.evilbird.warcraft.item.display.resource.ResourcePaneAssets getAssetBundle(AssetManager assets) {
        return new ResourcePaneAssets(assets, new WarcraftContext(Human, Winter));
    }
}