/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.resource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.hud.HudControls;

import static com.evilbird.engine.common.graphics.DensityIndependentPixel.dp;

/**
 * Instances of this user interface control display the resources the user has
 * accumulated. By default the control is positioned along the top of the
 * screen.
 *
 * @author Blair Butterworth
 */
//TODO: Use table control based on items - or a grid pane...
//TODO: Use flexible scaling depending on screen size
public class ResourcePane extends Item
{
    private Table table;
    private Label goldText;
    private Image goldImage;
    private Label oilText;
    private Image oilImage;
    private Label woodText;
    private Image woodImage;

    public ResourcePane() {
        goldText = createLabel();
        oilText = createLabel();
        woodText = createLabel();
        goldImage = new Image();
        oilImage = new Image();
        woodImage = new Image();
        table = createTable();
        setId(HudControls.ResourcePane);
        setType(HudControls.ResourcePane);
        setTouchable(Touchable.disabled);
    }

    private Label createLabel() {
        BitmapFont font = new BitmapFont();
        LabelStyle labelStyle = new LabelStyle(font, Color.WHITE);
        return new Label("", labelStyle);
    }

    private Table createTable() {
        int viewHeight = Gdx.graphics.getHeight();
        int viewWidth = Gdx.graphics.getWidth();

        Table table = new Table();
        table.setBounds(0, viewHeight - dp(30), viewWidth, dp(30));
        table.align(Align.center);
        addCell(table, goldImage);
        addCell(table, goldText);
        addCell(table, woodImage);
        addCell(table, woodText);
        addCell(table, oilImage);
        addCell(table, oilText);
        return table;
    }

    private void addCell(Table table, Image image) {
        addCell(table, image, 25);
    }

    private void addCell(Table table, Label label) {
        addCell(table, label, 100);
    }

    private void addCell(Table table, Actor actor, int width) {
        Cell<Actor> cell = table.add(actor);
        cell.width(dp(width));
        cell.padRight(dp(10));
        cell.padTop(dp(3));
        cell.padBottom(dp(3));
        cell.expandY();
        cell.fillY();
    }

    public void setFont(BitmapFont font) {
        LabelStyle style = new LabelStyle(font, Color.WHITE);
        goldText.setStyle(style);
        woodText.setStyle(style);
        oilText.setStyle(style);
    }

    public void setBackground(TextureRegion texture) {
        Drawable drawable = new TextureRegionDrawable(texture);
        table.setBackground(drawable);
    }

    public void setGold(float gold) {
        setGoldText(String.valueOf(Math.round(gold)));
    }

    public void setGoldText(String text) {
        goldText.setText(text);
    }

    public void setGoldIcon(TextureRegion texture) {
        Drawable drawable = new TextureRegionDrawable(texture);
        goldImage.setDrawable(drawable);
    }

    public void setOil(float oil) {
        setOilText(String.valueOf(Math.round(oil)));
    }

    public void setOilText(String text) {
        oilText.setText(text);
    }

    public void setOilIcon(TextureRegion texture) {
        Drawable drawable = new TextureRegionDrawable(texture);
        oilImage.setDrawable(drawable);
    }

    public void setWood(float wood) {
        setWoodText(String.valueOf(Math.round(wood)));
    }

    public void setWoodText(String text) {
        woodText.setText(text);
    }

    public void setWoodIcon(TextureRegion texture) {
        Drawable drawable = new TextureRegionDrawable(texture);
        woodImage.setDrawable(drawable);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        table.draw(batch, alpha);
    }

    @Override
    public void update(float delta) {
        table.act(delta);
    }
}
