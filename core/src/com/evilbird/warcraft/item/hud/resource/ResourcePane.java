/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.resource;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.common.control.StyledLabel;
import com.evilbird.engine.item.specialized.TableItem;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.hud.HudControl;

/**
 * Instances of this user interface control display the resources the user has
 * accumulated. By default the control is positioned along the top of the
 * screen.
 *
 * @author Blair Butterworth
 */
public class ResourcePane extends TableItem
{
    private Label goldLabel;
    private Label oilLabel;
    private Label woodLabel;

    public ResourcePane(ResourcePaneStyle style) {
        setFillParent(true);
        setIdentifier(HudControl.ResourcePane);
        Table container = addContainer(style.background);
        goldLabel = addResource(container, style.goldIcon, style.font, style.colour);
        woodLabel = addResource(container, style.woodIcon, style.font, style.colour);
        oilLabel = addResource(container, style.oilIcon, style.font, style.colour);
    }

    public String getResourceText(ResourceType resource) {
        if (resource == ResourceType.Gold) {
            return goldLabel.getText().toString();
        }
        else if (resource == ResourceType.Oil) {
            return oilLabel.getText().toString();
        }
        else if (resource == ResourceType.Wood) {
            return woodLabel.getText().toString();
        }
        return "";
    }

    public void setResourceText(ResourceType resource, float value) {
        setResourceText(resource, String.valueOf(Math.round(value)));
    }

    public void setResourceText(ResourceType resource, String text) {
        if (resource == ResourceType.Gold) {
            goldLabel.setText(text);
        }
        else if (resource == ResourceType.Oil) {
            oilLabel.setText(text);
        }
        else if (resource == ResourceType.Wood) {
            woodLabel.setText(text);
        }
    }

    private Table addContainer(Drawable background) {
        Table table = createTable(background);
        insertTable(table);
        return table;
    }

    private Label addResource(Table parent, Drawable icon, BitmapFont font, Color colour) {
        Image image = createImage(icon);
        Label label = createLabel(font, colour);
        insertImage(parent, image);
        insertLabel(parent, label);
        return label;
    }

    private Table createTable(Drawable background) {
        Table table = new Table();
        table.setBackground(background);
        return table;
    }

    private Image createImage(Drawable background) {
        return new Image(background);
    }

    private Label createLabel(BitmapFont font, Color colour) {
        StyledLabel label = new StyledLabel("0");
        label.setFont(font);
        label.setFontColour(colour);
        return label;
    }

    private void insertTable(Table child) {
        Cell cell = add(child);
        cell.align(Align.top);
        cell.height(16);
        cell.width(1408);
        cell.expand();
        cell.row();
    }

    private void insertImage(Table table, Image image) {
        Cell cell = table.add(image);
        cell.width(14);
        cell.height(16);
        cell.padLeft(50);
        cell.padTop(1);
        cell.padBottom(1);
    }

    private void insertLabel(Table table, Label label) {
        Cell cell = table.add(label);
        cell.width(50);
        cell.height(16);
        cell.padLeft(8);
        cell.padTop(1);
        cell.padBottom(1);
    }
}
