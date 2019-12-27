/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
