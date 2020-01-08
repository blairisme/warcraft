/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.state;

import com.badlogic.gdx.files.FileHandle;
import org.junit.Assert;
import org.junit.Test;

/**
 * Instances of this unit test validate logic in the {@link WarcraftSave}
 * class.
 *
 * @author Blair Butterworth
 */
public class WarcraftSaveTest
{
    @Test
    public void getFileNameTest() {
        WarcraftSave save = new WarcraftSave("foo");
        Assert.assertEquals("foo.json", save.getFileName());
    }

    @Test
    public void constructWithExtensionTest() {
        WarcraftSave save = new WarcraftSave("foo.json");
        Assert.assertEquals("foo.json", save.getFileName());
    }

    @Test
    public void constructWithHandleTest() {
        FileHandle handle = new FileHandle("data/test/foo.json");
        WarcraftSave save = new WarcraftSave(handle);
        Assert.assertEquals("foo.json", save.getFileName());
    }
}