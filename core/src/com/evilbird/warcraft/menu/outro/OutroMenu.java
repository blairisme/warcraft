/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.outro;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;
import com.evilbird.engine.control.SelectListener;
import com.evilbird.engine.control.SelectListenerAdapter;
import com.evilbird.engine.control.TextProgressBar;
import com.evilbird.engine.game.GameController;
import com.evilbird.engine.menu.Menu;

import javax.inject.Inject;

import static com.badlogic.gdx.scenes.scene2d.ui.Value.percentHeight;

public class OutroMenu extends Menu
{
    private Skin skin;
    private I18NBundle strings;
    private Table container;
    private Table summary;
    private Table details;
    private Button button;

    @Inject
    public OutroMenu(Skin skin) {
        this.skin = skin;
        this.container = createContainer();
        this.summary = createSummary();
        this.details = createDetails();
        this.button = createButton();
    }

    public Skin getSkin() {
        return skin;
    }

    public void setBackground(String name) {
        container.setBackground(name);
    }

    public void setLabelBundle(I18NBundle bundle) {
        strings = bundle;
    }

    public void setButtonAction(SelectListener action) {
        button.addListener(new SelectListenerAdapter(action));
    }

    @Override
    public void setController(GameController controller) {
        super.setController(controller);

//        GameEngine engine = (GameEngine)controller;
//        State state = engine.getState();
//        ItemRoot world = state.getWorld();
//        Player humanPlayer = getHumanPlayer(world);

        updateSummary();
        updateDetails();
    }

    private Table createContainer() {
        Table table = new Table(skin);
        table.setFillParent(true);

        Stage stage = getStage();
        stage.addActor(table);

        return table;
    }

    private Table createSummary() {
        Table summary = new Table(skin);
        Cell cell = container.add(summary);
        cell.align(Align.top);
        cell.growX();
        cell.height(percentHeight(0.45f, container));

        cell.row();
        return summary;
    }

    private void updateSummary() {
        summary.clear();
        addSummaryLabel("Outcome", "Defeat!");
        addSummaryLabel("Rank", "Servant");
        addSummaryLabel("Score", "0");
    }

    private void addSummaryLabel(String title, String value) {
        Table labelSet = new Table();
        Cell cell = summary.add(labelSet);
        cell.growX();

        addSummaryLabel(labelSet, title, "default");
        addSummaryLabel(labelSet, value, "font-large");
    }

    private void addSummaryLabel(Table labelset, String text, String font) {
        Label label = new Label(text, skin, font);
        label.setAlignment(Align.center);

        Cell cell = labelset.add(label);
        cell.expand();
        cell.fill();
        cell.row();
    }

    private Table createDetails() {
        Table details = new Table(skin);
        Cell cell = container.add(details);
        cell.align(Align.top);
        cell.growX();
        cell.height(percentHeight(0.45f, container));
        cell.row();
        return details;
    }

    private void updateDetails() {
        details.clear();
        addDetailsHeaders();
        addDetailsValues();
        addDetailsValues();
    }

    private void addDetailsHeaders() {
        addDetailsHeader("Units");
        addDetailsHeader("Buildings");
        addDetailsHeader("Gold");
        addDetailsHeader("Lumber");
        addDetailsHeader("Oil");
        addDetailsHeader("Kills");
        addDetailsHeaderEnd("Razings");
    }

    private void addDetailsValues() {
        addDetailsValue(4);
        addDetailsValue(0);
        addDetailsValue(0);
        addDetailsValue(0);
        addDetailsValue(0);
        addDetailsValue(0);
        addDetailsValueEnd(0);

        addDetailsTextLine("Nation of Azeroth - You");
    }

    private void addDetailsHeaderEnd(String text) {
        Cell cell = addDetailsHeader(text);
        cell.row();
    }

    private Cell addDetailsHeader(String text) {
        Label label = new Label(text, skin);
        label.setAlignment(Align.center);

        Cell cell = details.add(label);
        cell.expandX();
        cell.fillX();
        cell.padBottom(12);

        return cell;
    }

    private Cell addDetailsValue(int value) {
        TextProgressBar bar = new TextProgressBar(0, 1, 1, "2", skin, "progress-outro");
        bar.setValue(1);

        Cell cell = details.add(bar);
        cell.align(Align.center);
        cell.width(94);
        cell.height(28);
        cell.padLeft(8);
        cell.padRight(8);

        return cell;
    }

    private void addDetailsValueEnd(int value) {
        Cell cell = addDetailsValue(value);
        cell.row();
    }

    private void addDetailsTextLine(String text){
        Label textLine = new Label(text, skin, "font-large");
        textLine.setAlignment(Align.center);

        Cell cell = details.add(textLine);
        cell.colspan(7);
        cell.expandX();
        cell.fillX();
        cell.padTop(10);
        cell.padBottom(60);

        cell.row();
    }

    private TextButton createButton() {
        TextButton result = new TextButton("Continue", skin);

        Cell cell = container.add(result);
        cell.align(Align.right);
        cell.pad(20);
        container.row();

        return result;
    }
}
