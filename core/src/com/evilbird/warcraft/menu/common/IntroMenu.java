/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.common;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.evilbird.engine.menu.IndexMenu;

import javax.inject.Inject;
import java.util.List;

public class IntroMenu extends IndexMenu
{
    private Table table;
    private TextButton button;
    private TextButtonStyle style;
    private Label title;
    private Label description;
    private Label objectives;

    @Inject
    public IntroMenu() {
        table = createTable();
        title = createTitle(table);
        description = createDescription(table);
        objectives = createObjectives(table);
        style = createButtonStyle();
        button = createButton(table, style);
    }

    public void setBackground(Drawable background) {
        table.setBackground(background);
    }

    public void setButtonAction(SelectListener action) {
        button.addListener(new SelectListenerAdapter(action));
    }

    public void setButtonTexture(TextureRegion texture) {
        setButtonTexture(texture, texture, texture);
    }

    public void setButtonTexture(TextureRegion enabled, TextureRegion selected, TextureRegion disabled) {
        style.up = new TextureRegionDrawable(enabled);
        style.down = new TextureRegionDrawable(selected);
        style.over = new TextureRegionDrawable(enabled);
        style.checked = new TextureRegionDrawable(enabled);
        style.checkedOver = new TextureRegionDrawable(enabled);
        style.disabled = new TextureRegionDrawable(disabled);
    }

    public void setFont(BitmapFont font) {
        Label.LabelStyle style = new Label.LabelStyle(font, Color.WHITE);
        title.setStyle(style);
        description.setStyle(style);
        objectives.setStyle(style);
    }

    public void setMusic(List<Music> music) {
        //todo
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public void setObjectives(String objectives) {
        this.objectives.setText(objectives);
    }

    private Table createTable() {
        Table table = new Table();
        table.setFillParent(true);

        Stage stage = getStage();
        stage.addActor(table);

        return table;
    }

    private Label createTitle(Table table) {
        Label result = createLabel("Title");

        Cell cell = table.add(result);
        cell.fillX();
        //cell.width(Value.percentWidth(0.3f, table));
        cell.height(Value.percentHeight(0.04f, table));
        table.row();

        return result;
    }

    private Label createDescription(Table table) {
        Label result = createLabel("Description");

        Cell cell = table.add(result);
        cell.fill();
        cell.expand();
        table.add();
        table.row();

        return result;
    }

    private Label createObjectives(Table table) {
        Label result = createLabel("Objectives");

        table.add();
        Cell cell = table.add(result);
        cell.fill();
        cell.expand();
        table.row();

        return result;
    }

    private Label createLabel(String text) {
        BitmapFont font = new BitmapFont(); //TODO - Use singleton
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);
        return new Label(text, labelStyle);
    }

    private TextButtonStyle createButtonStyle() {
        TextButtonStyle buttonStyle = new TextButtonStyle();
        buttonStyle.font = new BitmapFont(); //TODO - Use singleton
        return buttonStyle;
    }

    private TextButton createButton(Table table, TextButtonStyle style) {
        TextButton result = new TextButton("Continue", style);

        table.add();
        Cell cell = table.add(result);

        cell.width(Value.percentWidth(0.1f, table));
        cell.height(Value.percentHeight(0.04f, table));

        table.row();

        return result;
    }
}
