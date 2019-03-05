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
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.evilbird.engine.common.graphics.Fonts;
import com.evilbird.warcraft.menu.common.events.SelectListener;
import com.evilbird.warcraft.menu.common.events.SelectListenerAdapter;

//TODO: Move into core / common
public class StyledButton extends TextButton implements Disposable
{
    private Sound clickSound;

    public StyledButton(String text) {
        super(text, newDefaultFont());
        addListeners();
    }

    public StyledButton(String text, Skin skin) {
        super(text, skin);
        addListeners();
    }

    public StyledButton(String text, SelectListener action) {
        super(text, newDefaultFont());
        addListeners(new SelectListenerAdapter(action));
    }

    public StyledButton(String text, SelectListener action, Skin skin) {
        super(text, skin);
        addListeners(new SelectListenerAdapter(action));
    }

    private void addListeners(EventListener... listeners) {
        addListener(clickListener());
        for (EventListener listener: listeners) {
            addListener(listener);
        }
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

    public void setSkin(Skin skin) {
        setStyle(skin.get(TextButtonStyle.class));
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
