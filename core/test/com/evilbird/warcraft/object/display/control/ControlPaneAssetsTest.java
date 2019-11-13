/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.control;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.test.testcase.AssetBundleTestCase;
import com.evilbird.warcraft.object.display.resource.ResourcePaneAssets;
import com.evilbird.warcraft.state.WarcraftContext;

import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftSeason.Winter;

/**
 * Instances of this unit test validate logic in the {@link ResourcePaneAssets}
 * class.
 *
 * @author Blair Butterworth
 */
public class ControlPaneAssetsTest extends AssetBundleTestCase<com.evilbird.warcraft.object.display.control.ControlPaneAssets>
{
    @Override
    protected com.evilbird.warcraft.object.display.control.ControlPaneAssets getAssetBundle(AssetManager assets) {
        return new ControlPaneAssets(assets, new WarcraftContext(Human, Winter));
    }
}