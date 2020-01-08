/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.menu;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Handles input events generated while a {@link Menu} is shown.
 *
 * @author Blair Butterworth
 */
class MenuInput extends InputListener
{
    private Menu menu;

    public MenuInput(Menu menu) {
        this.menu = menu;
    }

    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        if (keycode == Input.Keys.BACK) {
            menu.back();
            return true;
        }
        return false;
    }
}
