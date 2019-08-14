/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
