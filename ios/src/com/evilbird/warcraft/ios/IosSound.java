/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.ios;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.backends.iosrobovm.IOSSound;
import com.badlogic.gdx.backends.iosrobovm.objectal.ALSource;
import com.evilbird.engine.audio.sound.AbstractSound;
import com.evilbird.engine.audio.sound.Sound;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A {@link Sound} implementation for use on iOS devices.
 *
 * @author Blair Butterworth
 */
public class IosSound extends AbstractSound
{
    private static final Logger logger = LoggerFactory.getLogger(IosSound.class);

    public IosSound(FileHandleResolver resolver, String path) {
        super(resolver, path);
    }

    @Override
    public boolean isPlaying() {
        ALSource source = getSource();
        if (source != null) {
            return source.getState() == 0;
        }
        return false;
    }

    private ALSource getSource() {
        try {
            IOSSound sound = (IOSSound)delegate;
            return (ALSource)MethodUtils.invokeMethod(sound, true, "getSoundSource", identifier);
        }
        catch (ReflectiveOperationException exception) {
            logger.error("Unable to obtain sound instance", exception);
            return null;
        }
    }
}
