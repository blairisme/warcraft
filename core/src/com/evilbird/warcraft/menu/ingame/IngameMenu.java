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
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.control.*;
import com.evilbird.engine.menu.Menu;
import org.apache.commons.lang3.tuple.Pair;

public class IngameMenu extends Menu
{
    private Skin skin;
    private Table container;

    public IngameMenu(Skin skin) {
        this.skin = skin;
        this.container = createContainer(skin);
    }

    public void setLayout(IngameMenuLayout layout) {
        container.setBackground(layout.getBackground());
        container.setWidth(layout.getWidth());
        container.setHeight(layout.getHeight());
    }

    public Skin getSkin() {
        return skin;
    }

    public void addButton(String text, SelectListener action) {
        addControl(new StyledButton(text, action, skin));
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
        Button button = new StyledButton(text, action, skin);
        Cell cell = row.add(button);
        setPadding(cell);
        cell.width(100);
    }

    public void addTitle(String text) {
        Cell cell = addControl(new StyledLabel(text, skin, Align.center));
        cell.padTop(12);
    }

    public void addLabel(String text) {
        addControl(new StyledLabel(text, skin, Align.left));
    }

    public StyledList addList() {
        StyledList list = new StyledList<>(skin);
        ScrollPane scrolled = new ScrollPane(list);

        Cell cell = container.add(scrolled);
        container.row();
        setPadding(cell);
        cell.grow();

        return list;
    }

    public StyledField addTextField(String text) {
        StyledField textField = new StyledField(text, skin);
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
