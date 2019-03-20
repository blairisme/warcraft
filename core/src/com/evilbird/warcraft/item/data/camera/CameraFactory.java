/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.data.camera;

import com.evilbird.engine.common.inject.AssetProvider;

import javax.inject.Inject;

public class CameraFactory implements AssetProvider<Camera>
{
    @Inject
    public CameraFactory() {
    }

    @Override
    public void load() {
    }

    @Override
    public Camera get() {
        return new Camera();
    }
}
