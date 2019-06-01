/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.test.data.display;

import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.evilbird.engine.common.graphics.Fonts;

public class TestSkin
{
    private TestSkin() {
    }

    public static Skin newTestSkin() {
        TextButtonStyle buttonStyle = new TextButtonStyle();
        buttonStyle.font = Fonts.ARIAL;

        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = Fonts.ARIAL;

        ProgressBarStyle progressBarStyle = new ProgressBarStyle();

        Skin skin = new Skin();
        skin.add("default", buttonStyle, TextButtonStyle.class);
        skin.add("default", labelStyle, LabelStyle.class);
        skin.add("font-large", labelStyle, LabelStyle.class);
        skin.add("progress-outro", labelStyle, LabelStyle.class);
        skin.add("progress-outro", progressBarStyle, ProgressBarStyle.class);

        return skin;
    }
}
