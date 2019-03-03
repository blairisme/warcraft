/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.main;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.menu.Menu;
import com.evilbird.warcraft.menu.common.controls.StyledButton;
import com.evilbird.warcraft.menu.common.events.SelectListener;
import com.evilbird.warcraft.menu.common.events.SelectListenerAdapter;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;

public class MainMenu extends Menu
{
    private Table table;
    private Collection<StyledButton> buttons;

    @Inject
    public MainMenu() {
        table = createTable();
        buttons = new ArrayList<>();
    }

    public void insertButton(String text) {
        StyledButton button = createButton(text);
        insertButton(table, button);
        buttons.add(button);
    }

    public void insertButton(String text, SelectListener action) {
        StyledButton button = createButton(text, action);
        insertButton(table, button);
        buttons.add(button);
    }

    public void setBackground(Drawable background) {
        table.setBackground(background);
    }

//    public void setButtonTextures(Drawable enabled, Drawable selected, Drawable disabled) {
//        for (StyledButton button: buttons) {
//            button.setEnabledTexture(enabled);
//            button.setDisabledTexture(disabled);
//            button.setSelectedTexture(selected);
//        }
//    }

    public void setButtonEnabled(Drawable drawable) {
        for (StyledButton button: buttons) {
            button.setEnabledTexture(drawable);
        }
    }

    public void setButtonDisabled(Drawable drawable) {
        for (StyledButton button: buttons) {
            button.setDisabledTexture(drawable);
        }
    }

    public void setButtonSelected(Drawable drawable) {
        for (StyledButton button: buttons) {
            button.setSelectedTexture(drawable);
        }
    }

    public void setButtonFont(BitmapFont font){
        for (StyledButton button: buttons) {
            button.setFont(font);
        }
    }

    public void setButtonSound(Sound sound) {
        for (StyledButton button: buttons) {
            button.setClickSound(sound);
        }
    }

    private Table createTable() {
        Table table = new Table();
        table.setFillParent(true);
        table.center();
        table.padTop(150f);

        Stage stage = getStage();
        stage.addActor(table);

        return table;
    }

    private StyledButton createButton(String text) {
        return createButton(text, null);
    }

    private StyledButton createButton(String text, SelectListener action) {
        StyledButton button = new StyledButton(text);
        button.setDisabled(true);

        if (action != null) {
            button.setDisabled(false);
            button.addListener(new SelectListenerAdapter(action));
        }
        return button;
    }

    private void insertButton(Table table, TextButton button) {
        Cell cell = table.add(button);
        cell.width(Value.percentWidth(0.3f, table));
        cell.height(Value.percentHeight(0.04f, table));
        cell.padBottom(Value.percentHeight(0.01f, table));
        cell.fill();
        table.row();
    }
}
