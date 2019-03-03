/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.ingame;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.common.function.Function;
import com.evilbird.engine.menu.Menu;
import com.evilbird.warcraft.menu.common.controls.StyledButton;
import com.evilbird.warcraft.menu.common.controls.StyledLabel;
import com.evilbird.warcraft.menu.common.controls.StyledList;
import com.evilbird.warcraft.menu.common.controls.StyledTextField;
import com.evilbird.warcraft.menu.common.events.SelectListener;
import com.evilbird.warcraft.menu.common.events.SelectListenerAdapter;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class IngameMenu extends Menu
{
    private Table table;
    private Collection<StyledButton> buttons;
    private Collection<StyledList> lists;
    private Collection<StyledTextField> textFields;

    @Inject
    public IngameMenu() {
        table = createTable();
        buttons = new ArrayList<>();
        lists = new ArrayList<>();
        textFields = new ArrayList<>();
    }

    public void insertButton(String text, IngameMenus menu) {
        insertButton(text, () -> showMenuOverlay(menu));
    }

    public void insertButton(String text, SelectListener action) {
        StyledButton button = createButton(text, action);
        insertButton(table, button);
        buttons.add(button);
    }

    public void insertSpacer() {
        insertButton(table, null);
    }

    public void insertTitle(String title) {
        StyledLabel label = createLabel(title);
        insertLabel(table, label);
    }

    public void insertList(Supplier<Collection> itemSupplier, Consumer selectionHandler) {
        StyledList list = new StyledList();

        list.setItems("File 1", "File 2");

        Cell cell = table.add(list);
        cell.expand();
        cell.fill();
        cell.pad(10);
        cell.row();

        lists.add(list);
    }

    public void insertTextField() {
        StyledTextField field = new StyledTextField();

        Cell cell = table.add(field);
        cell.width(224); //300
        cell.height(18);
        cell.padBottom(6);
        cell.fill();
        table.row();

        textFields.add(field);
    }


    public void setTextFieldBackground(Drawable drawable) {
        for (StyledTextField textField: textFields) {
            textField.setBackground(drawable);
        }
    }

    public void setTextFieldColor(Color color) {
        for (StyledTextField textField: textFields) {
            textField.setFontColour(color);
        }
    }


    public void setListBackground(Drawable drawable) {
        for (StyledList list: lists) {
            list.setBackground(drawable);
        }
    }

    public void setSelectedColour(Color color) {
        for (StyledList list: lists) {
            list.setSelectedColour(color);
        }
    }

    public void setListSelection(Drawable drawable) {
        for (StyledList list: lists) {
            list.setSelection(drawable);
        }
    }




    public void setBackground(Drawable background) {
        table.setBackground(background);
    }

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
        Table containerTable = createContainerTable();
        Table contentTable = createContentTable();

        Stage stage = getStage();
        stage.addActor(containerTable);
        containerTable.add(contentTable);

        return contentTable;
    }

    private Table createContainerTable() {
        Table table = new Table();
        table.setFillParent(true);
        table.center();
        return table;
    }

    private Table createContentTable() {
        Table Table = new Table();
        Table.center();
        Table.setWidth(256);
        Table.setHeight(288);
        return Table;
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
        Cell cell = button != null ? table.add(button) : table.add();
        cell.width(224);
        cell.height(28);
        cell.padBottom(6);
        cell.fill();
        table.row();
    }

    private StyledLabel createLabel(String text) {
        StyledLabel result = new StyledLabel(text);
        result.setFontColour(Color.WHITE);
        result.setAlignment(Align.center);
        return result;
    }

    private void insertLabel(Table table, StyledLabel label) {
        Cell cell = table.add(label);
        cell.fillX();
        cell.expandX();
        cell.center();
        cell.height(28);
        cell.padBottom(6);
        table.row();
    }
}
