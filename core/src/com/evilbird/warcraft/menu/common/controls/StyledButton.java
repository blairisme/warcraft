/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.common.controls;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.evilbird.engine.common.graphics.Fonts;

//TODO: Move into core / common
public class StyledButton extends TextButton implements Disposable
{
    private Sound clickSound;

    public StyledButton(String text) {
        super(text, newDefaultFont());
        addListener(clickListener());
    }

    @Override
    public void dispose() {
        if (clickSound != null) {
            clickSound.dispose();
        }
    }

    public void setFont(BitmapFont font) {
        TextButtonStyle style = getStyle();
        style.font = font;
        setStyle(style);
    }

    public void setEnabledTexture(Drawable enabled) {
        TextButtonStyle style = getStyle();
        style.up = enabled;
        style.over = enabled;
        style.checked = enabled;
        style.checkedOver = enabled;
        setStyle(style);
    }

    public void setDisabledTexture(Drawable disabled) {
        TextButtonStyle style = getStyle();
        style.disabled = disabled;
        setStyle(style);
    }

    public void setSelectedTexture(Drawable selected) {
        TextButtonStyle style = getStyle();
        style.down = selected;
        setStyle(style);
    }

    public void setClickSound(Sound sound) {
        clickSound = sound;
    }

    private static TextButtonStyle newDefaultFont() {
        TextButtonStyle style = new TextButtonStyle();
        style.font = Fonts.ARIAL;
        return style;
    }

    private ChangeListener clickListener() {
        return new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (clickSound != null) {
                    clickSound.play();
                }
            }
        };
    }
}
