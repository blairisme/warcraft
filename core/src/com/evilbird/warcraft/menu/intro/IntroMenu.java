/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.intro;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.common.control.SelectListenerAdapter;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.menu.Menu;
import com.evilbird.warcraft.state.WarcraftCampaign;

import javax.inject.Inject;
import java.util.concurrent.Future;

import static com.badlogic.gdx.scenes.scene2d.ui.Value.percentHeight;
import static com.badlogic.gdx.scenes.scene2d.ui.Value.percentWidth;

/**
 * Instances of this {@link Menu} user interface are shown before the user
 * starts a scenario. The user is presented with a description of the events
 * that led up to the scenario and the objectives required to win it.
 *
 * @author Blair Butterworth
 */
public class IntroMenu extends Menu
{
    private Skin skin;
    private Table table;
    private TextButton button;
    private Label title;
    private Label description;
    private Label objectives;
    private WarcraftCampaign campaign;
    private Future<?> loading;

    @Inject
    public IntroMenu(DeviceDisplay display, Skin skin) {
        super(display);
        this.skin = skin;
        this.table = createTable(skin);
        this.title = createTitle(skin, table);
        this.description = createDescription(skin, table);
        this.objectives = createObjectives(skin, table);
        this.button = createButton(skin, table);
    }

    @Override
    public void show() {
        super.show();
        if (loading == null) {
            loading = controller.loadState(campaign);
        }
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (loading.isDone()) {
            button.setDisabled(false);
        }
    }

    public Skin getSkin() {
        return skin;
    }

    public void setCampaign(WarcraftCampaign campaign) {
        this.campaign = campaign;
    }

    public void setText(IntroMenuStrings strings) {
        title.setText(strings.getTitle());
        description.setText(strings.getDescription());
        objectives.setText(strings.getObjectives());
    }

    private Table createTable(Skin skin) {
        Table table = new Table(skin);
        table.setFillParent(true);
        table.setBackground("intro-background");

        Stage stage = getStage();
        stage.addActor(table);

        return table;
    }

    private Label createTitle(Skin skin, Table table) {
        Label result = new Label("Title", skin);
        result.setAlignment(Align.center);

        Cell cell = table.add(result);
        cell.fillX();
        cell.expandX();
        cell.center();
        cell.height(percentHeight(0.10f, table));
        table.row();

        return result;
    }

    private Label createDescription(Skin skin, Table table) {
        Label result = new Label("Description", skin);
        result.setWrap(true);
        result.setAlignment(Align.topLeft);

        ScrollPane scrollPane = new ScrollPane(result);

        Cell cell = table.add(scrollPane);
        cell.align(Align.topLeft);
        cell.width(percentWidth(0.70f, table));
        cell.height(percentHeight(0.50f, table));
        cell.pad(10, 30, 10, 10);
        table.row();

        return result;
    }

    private Label createObjectives(Skin skin, Table table) {
        Label result = new Label("Objectives", skin);
        result.setAlignment(Align.topLeft);

        Cell cell = table.add(result);
        cell.align(Align.right);
        cell.width(percentWidth(0.4f, table));
        cell.height(percentHeight(0.25f, table));
        table.row();

        return result;
    }

    private TextButton createButton(Skin skin, Table table) {
        TextButton result = new TextButton("Continue", skin);
        result.setDisabled(true);
        result.addListener(new SelectListenerAdapter(() -> showState(campaign)));

        Cell cell = table.add(result);
        cell.align(Align.right);
        cell.pad(10, 10, 10, 20);
        table.row();

        return result;
    }
}
