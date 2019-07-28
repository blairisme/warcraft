/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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