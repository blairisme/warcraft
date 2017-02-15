package com.evilbird.warcraft.item;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.utility.Identifier;

import java.util.Objects;

public class ResourceBar extends Item
{
    private Table table;
    private Label label;

    public ResourceBar(AssetManager assets)
    {
        BitmapFont labelFont = new BitmapFont();
        Color labelColor = Color.WHITE;
        Label.LabelStyle labelStyle = new  Label.LabelStyle(labelFont, labelColor);
        label = new Label("0", labelStyle);

        Texture imageTexture = assets.get("data/textures/neutral/hud/resource-icon.png");
        TextureRegion imageRegion = new TextureRegion(imageTexture, 0, 14, 14, 14);
        Image image = new Image(imageRegion);

        Texture resourceTexture = assets.get("data/textures/human/hud/resource.png");
        TextureRegion resourceTextureRegion = new TextureRegion(resourceTexture);
        Drawable resourceDrawable = new TextureRegionDrawable(resourceTextureRegion);

        table = new Table();
        table.setBackground(resourceDrawable);
        table.setBounds(0, Gdx.graphics.getHeight() - 20, Gdx.graphics.getWidth(), 20);
        table.align(Align.right);
        table.add(image).width(14).padRight(5f);
        table.add(label).width(50).padRight(5f);

        setProperty(new Identifier("Id"), new Identifier("ResourceBar"));
    }
    
    @Override
    public void draw(Batch batch, float alpha)
    {
        table.draw(batch, alpha);
    }

    @Override
    public void act(float delta)
    {
        table.act(delta);
    }

    @Override
    public void setProperty(Identifier property, Object value)
    {
        super.setProperty(property, value);

        if (Objects.equals(property, new Identifier("Wood")))
        {
            label.setText(value.toString());
        }
    }
}
