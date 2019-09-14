/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.ingame;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.common.control.SelectListener;
import com.evilbird.engine.common.control.SelectListenerAdapter;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.menu.Menu;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Instances of this {@link Menu} user interface are shown as the user plays
 * the game. The user is presented with options to save and load the game as
 * well as to alter game settings.
 *
 * @author Blair Butterworth
 */
public class IngameMenu extends Menu
{
    private Skin skin;
    private Table container;

    public IngameMenu(DeviceDisplay display, Skin skin) {
        super(display);
        this.skin = skin;
        this.container = createContainer(skin);
    }

    @Override
    public void back() {
        controller.showState();
    }

    public void setLayout(IngameMenuDimensions dimensions) {
        container.setBackground(dimensions.getBackground());
        container.setWidth(dimensions.getWidth());
        container.setHeight(dimensions.getHeight());
    }

    public Skin getSkin() {
        return skin;
    }

    public void addButton(String text, SelectListener action) {
        Button button = new TextButton(text, skin);
        button.addListener(new SelectListenerAdapter(action));
        addControl(button);
    }

    @SafeVarargs
    public final void addButtonRow(Pair<String, SelectListener> ... buttons) {
        Table row = addButtonRowTable();
        for (Pair<String, SelectListener> button: buttons) {
            addButtonRowCell(row, button.getKey(), button.getValue());
        }
    }

    private Table addButtonRowTable() {
        Table row = new Table(skin);
        addControl(row);
        return row;
    }

    private void addButtonRowCell(Table row, String text, SelectListener action) {
        Button button = new TextButton(text, skin);
        button.addListener(new SelectListenerAdapter(action));

        Cell cell = row.add(button);
        setPadding(cell);
        cell.width(100);
    }

    public void addTitle(String text) {
        Cell cell = addControl(createLabel(text, skin, Align.center));
        cell.padTop(12);
    }

    public void addLabel(String text) {
        addControl(createLabel(text, skin, Align.left));
    }

    private Label createLabel(String text, Skin skin, int alignment) {
        Label result = new Label(text, skin);
        result.setAlignment(alignment);
        return result;
    }

    public List addList() {
        List list = new List<>(skin);

        ScrollPane scrolled = new ScrollPane(list, skin);
        scrolled.setScrollbarsVisible(true);

        Cell cell = container.add(scrolled);
        container.row();

        setPadding(cell);
        cell.height(250);
        cell.growX();

        return list;
    }

    public TextField addTextField(String text) {
        TextField textField = new TextField(text, skin);
        addControl(textField);
        return textField;
    }

    public void addSpacer() {
        Cell cell = container.add();
        cell.expand();
        container.row();
    }

    private Table createContainer(Skin skin) {
        Table containerTable = createContainerTable(skin);
        Table contentTable = createContentTable(skin);

        setContent(containerTable);
        containerTable.add(contentTable);

        return contentTable;
    }

    private Table createContainerTable(Skin skin) {
        Table table = new Table(skin);
        table.setFillParent(true);
        table.center();
        return table;
    }

    private Table createContentTable(Skin skin) {
        Table table = new Table(skin);
        table.center();
        return table;
    }

    private Cell addControl(Actor control) {
        Cell cell = container.add(control);
        cell.expandX();
        cell.fill();
        cell.height(28);
        setPadding(cell);
        container.row();
        return cell;
    }

    private void setPadding(Cell cell) {
        cell.padLeft(12);
        cell.padRight(12);
        cell.padBottom(6);
    }
}
