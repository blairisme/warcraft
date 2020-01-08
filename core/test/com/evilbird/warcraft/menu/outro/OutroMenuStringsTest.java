/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.menu.outro;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.utils.I18NBundle;
import com.evilbird.test.utils.AssetFileHandleResolver;
import com.evilbird.warcraft.common.WarcraftNation;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.data.player.PlayerType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * Instances of this unit test validate logic in the {@link OutroMenuStrings}
 * class.
 *
 * @author Blair Butterworth
 */
public class OutroMenuStringsTest
{
    private static final String OUTRO_BUNDLE = "data/strings/common/menu/outro";
    private static final String NATIONS_BUNDLE = "data/strings/common/menu/nations";

    private OutroMenuStrings strings;

    @Before
    public void setup() {
        FileHandleResolver resolver = new AssetFileHandleResolver();
        AssetManager assets = new AssetManager(resolver);
        assets.load(OUTRO_BUNDLE, I18NBundle.class);
        assets.load(NATIONS_BUNDLE, I18NBundle.class);
        assets.finishLoading();

        I18NBundle detailsBundle = assets.get(OUTRO_BUNDLE, I18NBundle.class);
        I18NBundle namesBundle = assets.get(NATIONS_BUNDLE, I18NBundle.class);
        strings = new OutroMenuStrings(detailsBundle, namesBundle);
    }

    @Test
    public void getTest() throws Exception {
        for (Method method: strings.getClass().getMethods()) {
            if (method.getName().startsWith("get") && method.getReturnType() == String.class) {
                String result = invoke(method);
                Assert.assertNotNull(result);
                Assert.assertFalse(result.isEmpty());
            }
        }
    }

    private String invoke(Method method) throws Exception {
        if (method.getParameterCount() == 0) {
            return (String)method.invoke(strings);
        }
        if (method.getParameterCount() == 1 && method.getParameterTypes()[0] == int.class) {
            return (String)method.invoke(strings, 1);
        }
        if (method.getParameterCount() == 1 && method.getParameterTypes()[0] == Player.class) {
            return (String)method.invoke(strings, newTestPlayer(PlayerType.Corporeal, WarcraftNation.Azeroth));
        }
        if (method.getParameterCount() == 1 && method.getParameterTypes()[0] == OutroMenuType.class) {
            return (String)method.invoke(strings, OutroMenuType.Victory);
        }
        throw new UnsupportedOperationException();
    }

    private Player newTestPlayer(PlayerType type, WarcraftNation nation) {
        Player player = new Player();
        player.setType(type);
        player.setNation(nation);
        return player;
    }

    @Test
    public void getPlayerNameTest() {
        for (PlayerType type: PlayerType.values()) {
            for (WarcraftNation nation : WarcraftNation.values()) {
                Assert.assertNotNull(strings.getPlayerName(newTestPlayer(type, nation)));
            }
        }
    }
}

