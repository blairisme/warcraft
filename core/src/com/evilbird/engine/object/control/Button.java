/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.object.control;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.evilbird.engine.object.BasicGameObject;
import com.evilbird.engine.object.interop.ButtonDecorator;

/**
 * Instances of this class represent a button user interface control.
 *
 * @author Blair Butterworth
 */
public class Button extends BasicGameObject
{
    private transient com.badlogic.gdx.scenes.scene2d.ui.Button control;

    public Button(ButtonStyle style) {
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
