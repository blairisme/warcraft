/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer.fog;

import com.evilbird.warcraft.object.layer.LayerGroupAdapter;

/**
 * Instances of this class serialize and deserialize {@link Fog} objects.
 *
 * @author Blair Butterworth
 */
public class FogAdapter extends LayerGroupAdapter<Fog>
{
    private static final String FOG = "fog";
    private static final String CLOUD = "cloud";

    @Override
    protected String getCellArrayProperty() {
        return FOG;
    }

    @Override
    protected String getValueProperty() {
        return CLOUD;
    }
}
