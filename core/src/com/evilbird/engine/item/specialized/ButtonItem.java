/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.specialized;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.evilbird.engine.item.ItemBasic;
import com.evilbird.engine.item.interop.ButtonDecorator;

/**
 * Instances of this class represent a button user interface control.
 *
 * @author Blair Butterworth
 */
public class ButtonItem extends ItemBasic
{
    private transient Button control;

    public ButtonItem(ButtonStyle style) {
        super();
        setStyle(style);
    }

    public void setStyle(ButtonStyle style) {
        control.setStyle(style);
    }

    @Override
    protected Actor newDelegate() {
        control = new ButtonDecorator(this);
        return control;
    }
}
