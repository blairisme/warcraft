package com.evilbird.warcraft.item.hud.resource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.item.Item;

//TODO: Use item table
public class ResourcePanel extends Item
{
    private Table table;
    private Label goldText;
    private Image goldImage;
    private Label oilText;
    private Image oilImage;
    private Label woodText;
    private Image woodImage;

    public ResourcePanel()
    {
        goldText = createLabel();
        oilText = createLabel();
        woodText = createLabel();
        goldImage = new Image();
        oilImage = new Image();
        woodImage = new Image();
        table = createTable();
        setId(new Identifier("ResourcePane"));
        setType(new Identifier("ResourcePane"));
        setTouchable(Touchable.disabled);
    }

    private Label createLabel()
    {
        BitmapFont labelFont = new BitmapFont();
        Color labelColor = Color.WHITE;
        Label.LabelStyle labelStyle = new  Label.LabelStyle(labelFont, labelColor);
        return new Label("", labelStyle);
    }

    private Table createTable()
    {
        Table table = new Table();
        table.setBounds(0, Gdx.graphics.getHeight() - 20, Gdx.graphics.getWidth(), 20); //TODO
        table.align(Align.center);
        table.add(goldImage).width(14).padRight(5f);
        table.add(goldText).width(50).padRight(5f);
        table.add(oilImage).width(14).padRight(5f);
        table.add(oilText).width(50).padRight(5f);
        table.add(woodImage).width(14).padRight(5f);
        table.add(woodText).width(50).padRight(5f);
        return table;
    }

    public void setBackground(TextureRegion texture)
    {
        Drawable drawable = new TextureRegionDrawable(texture);
        table.setBackground(drawable);
    }

    public void setGoldText(String text)
    {
        goldText.setText(text);
    }

    public void setGoldIcon(TextureRegion texture)
    {
        Drawable drawable = new TextureRegionDrawable(texture);
        goldImage.setDrawable(drawable);
    }

    public void setOilText(String text)
    {
        oilText.setText(text);
    }

    public void setOilIcon(TextureRegion texture)
    {
        Drawable drawable = new TextureRegionDrawable(texture);
        oilImage.setDrawable(drawable);
    }

    public void setWoodText(String text)
    {
        woodText.setText(text);
    }

    public void setWoodIcon(TextureRegion texture)
    {
        Drawable drawable = new TextureRegionDrawable(texture);
        woodImage.setDrawable(drawable);
    }

    @Override
    public void draw(Batch batch, float alpha)
    {
        table.draw(batch, alpha);
    }

    @Override
    public void update(float delta)
    {
        table.act(delta);
    }

    @Override
    public void setProperty(Identifier property, Object value)
    {
        super.setProperty(property, value);

        if (Objects.equals(property, new Identifier("Gold"))){
            setGoldText(value.toString());
        }
        if (Objects.equals(property, new Identifier("Oil"))){
            setOilText(value.toString());
        }
        if (Objects.equals(property, new Identifier("Wood"))){
            setWoodText(value.toString());
        }
    }
}
