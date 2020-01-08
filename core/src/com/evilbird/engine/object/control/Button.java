/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
