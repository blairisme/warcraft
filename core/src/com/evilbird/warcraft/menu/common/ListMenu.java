/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.common;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.evilbird.engine.menu.IndexMenu;

import javax.inject.Inject;

//TODO: Add button click sounds
public class ListMenu extends IndexMenu
{
    private Table buttonTable;
    private TextButtonStyle buttonStyle;

    @Inject
    public ListMenu() {
        buttonTable = createTable();
        buttonStyle = createButtonStyle();
    }

    public void addButton(String text) {
        TextButton button = createButton(text, buttonStyle);
        addButton(buttonTable, button);
    }

    public void addButton(String text, SelectListener action) {
        TextButton button = createButton(text, buttonStyle, action);
        addButton(buttonTable, button);
    }

    public void setBackground(Drawable background) {
        buttonTable.setBackground(background);
    }

    public void setButtonTexture(TextureRegion enabled, TextureRegion selected, TextureRegion disabled) {
        buttonStyle.up = new TextureRegionDrawable(enabled);
        buttonStyle.down = new TextureRegionDrawable(selected);
        buttonStyle.over = new TextureRegionDrawable(enabled);
        buttonStyle.checked = new TextureRegionDrawable(enabled);
        buttonStyle.checkedOver = new TextureRegionDrawable(enabled);
        buttonStyle.disabled = new TextureRegionDrawable(disabled);
    }

    public void setButtonSound(Sound sound) {

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

    private TextButtonStyle createButtonStyle() {
        TextButtonStyle buttonStyle = new TextButtonStyle();
        buttonStyle.font = new BitmapFont();
        return buttonStyle;
    }

    private TextButton createButton(String text, TextButtonStyle style) {
        return createButton(text, style, null);
    }

    private TextButton createButton(String text, TextButtonStyle style, SelectListener action) {
        TextButton button = new TextButton(text, style);
        button.setDisabled(true);

        if (action != null) {
            button.setDisabled(false);
            button.addListener(new SelectListenerAdapter(action));
        }
        return button;
    }

    private void addButton(Table table, TextButton button) {
        Cell cell = table.add(button);
        cell.width(Value.percentWidth(0.3f, table));
        cell.height(Value.percentHeight(0.04f, table));
        cell.padBottom(Value.percentHeight(0.01f, table));
        cell.fill();
        table.row();
    }
}
