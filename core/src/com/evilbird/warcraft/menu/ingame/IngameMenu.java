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
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.common.control.SelectListener;
import com.evilbird.engine.common.control.SelectListenerAdapter;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.item.specialized.ListPane;
import com.evilbird.engine.item.specialized.ScrollBarPane;
import com.evilbird.engine.item.specialized.TextInput;
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

    public IngameMenu(IngameMenu menu) {
        super(menu);
        this.skin = menu.skin;
        this.container = menu.container;
    }

    public IngameMenu(DeviceDisplay display, Skin skin) {
        super(display);
        this.skin = skin;
        this.container = createContainer(skin);
    }

    @Override
    public void back() {
        controller.showState();
    }

    protected void setLayout(IngameMenuDimensions dimensions) {
        container.setBackground(dimensions.getBackground());
        container.setWidth(dimensions.getWidth());
        container.setHeight(dimensions.getHeight());
    }

    protected Skin getSkin() {
        return skin;
    }

    protected void addButton(String text) {
        Button button = new TextButton(text, skin);
        button.setDisabled(true);
        addControl(button);
    }

    protected void addButton(String text, SelectListener action) {
        Button button = new TextButton(text, skin);
        button.addListener(new SelectListenerAdapter(action));
        addControl(button);
    }

    @SafeVarargs
    protected final void addButtonRow(Pair<String, SelectListener> ... buttons) {
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

    protected Label addTitle(String text) {
        Label title = new Label(text, skin, "title");
        title.setWrap(true);
        title.setAlignment(Align.center);

        Cell cell = addControl(title);
        cell.padTop(12);
        cell.padBottom(12);
        return title;
    }

    protected Label addLabel(String text) {
        Label label = new Label(text, skin, "label");
        label.setAlignment(Align.left);
        addControl(label);
        return label;
    }

    protected Cell<Label> addErrorLabel() {
        Label label = new Label("", skin, "error");
        label.setAlignment(Align.left);
        label.setWrap(true);

        Cell cell = addControl(label);
        cell.height(0);
        cell.padBottom(0);
        return cell;
    }

    protected <T> ListPane<T> addList() {
        ListPane<T> listPane = new ListPane<>(skin);
        listPane.setItemHeight(18f);

        ScrollBarPane scrollBarPane = new ScrollBarPane(listPane, skin);
        scrollBarPane.setScrollbarsVisible(true);
        scrollBarPane.setFadeScrollBars(false);

        Cell cell = container.add(scrollBarPane);
        container.row();

        setPadding(cell);
        cell.height(200);
        cell.growX();

        return listPane;
    }

    protected TextInput addTextField() {
        TextInput textField = new TextInput("", skin);
        addControl(textField);
        return textField;
    }

    protected void addSpacer() {
        Cell cell = container.add();
        cell.expand();
        container.row();
    }

    protected Slider addSlider(String label, float value) {
        addLabel(label);
        return addSlider(value);
    }

    protected Slider addSlider(float value) {
        Slider slider = new Slider(0f, 1f, 0.01f, false, skin);
        slider.setValue(value);
        Cell cell = addControl(slider);
        cell.align(Align.left);
        return slider;
    }

    protected CheckBox addCheckbox(String label, boolean checked) {
        CheckBox checkBox = new CheckBox(label, skin);
        checkBox.setChecked(checked);
        checkBox.align(Align.left);
        addControl(checkBox);
        return checkBox;
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

    private <T extends Actor> Cell<T> addControl(T control) {
        Cell<T> cell = container.add(control);
        cell.expandX();
        cell.fill();
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
