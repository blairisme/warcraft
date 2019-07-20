/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;

public class AssetContainer
{
    private AssetManager manager;

    public AssetContainer(AssetManager manager) {
        this.manager = manager;
    }



    public boolean isLoaded(AssetDescriptor assetDescriptor) {
        return manager.isLoaded(assetDescriptor);
    }


}
