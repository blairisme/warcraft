/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.intro;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.menu.Menu;
import com.evilbird.warcraft.menu.common.controls.StyledButton;
import com.evilbird.warcraft.menu.common.controls.StyledLabel;
import com.evilbird.warcraft.menu.common.events.SelectListener;
import com.evilbird.warcraft.menu.common.events.SelectListenerAdapter;

import javax.inject.Inject;

import static com.badlogic.gdx.scenes.scene2d.ui.Value.percentHeight;
import static com.badlogic.gdx.scenes.scene2d.ui.Value.percentWidth;

public class IntroMenu extends Menu
{
    private Table table;
    private StyledButton button;
    private StyledLabel title;
    private StyledLabel description;
    private StyledLabel objectives;

    @Inject
    public IntroMenu() {
        table = createTable();
        title = createTitle(table);
        description = createDescription(table);
        objectives = createObjectives(table);
        button = createButton(table);
    }

    public void setBackground(Drawable background) {
        table.setBackground(background);
    }

    public void setButtonAction(SelectListener action) {
        button.addListener(new SelectListenerAdapter(action));
    }

    public void setButtonTexture(Drawable texture) {
        setButtonTexture(texture, texture, texture);
    }

    public void setButtonTexture(Drawable enabled, Drawable selected, Drawable disabled) {
        button.setEnabledTexture(enabled);
        button.setDisabledTexture(disabled);
        button.setSelectedTexture(selected);
    }

    public void setButtonSound(Sound sound) {
        button.setClickSound(sound);
    }

    public void setFont(BitmapFont font) {
        title.setFont(font);
        description.setFont(font);
        objectives.setFont(font);
        button.setFont(font);
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

    private StyledLabel createTitle(Table table) {
        StyledLabel result = new StyledLabel("Title");
        result.setFontColour(Color.WHITE);
        result.setAlignment(Align.center);

        Cell cell = table.add(result);
        cell.fillX();
        cell.expandX();
        cell.center();
        table.row();

        return result;
    }

    private StyledLabel createDescription(Table table) {
        StyledLabel result = new StyledLabel("Description");
        result.setFontColour(Color.WHITE);

        Cell cell = table.add(result);
        cell.align(Align.left);
        cell.width(percentWidth(0.65f, table));
        cell.height(percentHeight(0.35f, table));
        cell.pad(30);
        table.row();

        return result;
    }

    private StyledLabel createObjectives(Table table) {
        StyledLabel result = new StyledLabel("Objectives");
        result.setFontColour(Color.WHITE);

        Cell cell = table.add(result);
        cell.align(Align.right);
        cell.width(percentWidth(0.4f, table));
        cell.height(percentHeight(0.4f, table));
        table.row();

        return result;
    }

    private StyledButton createButton(Table table) {
        StyledButton result = new StyledButton("Continue");

        Cell cell = table.add(result);
        cell.align(Align.right);
        cell.padRight(20);
        table.row();

        return result;
    }
}
