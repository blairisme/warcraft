package com.evilbird.warcraft.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.evilbird.warcraft.utility.Identifier;

public class HudFactory
{
    private AssetManager assets;

    public HudFactory(AssetManager assets)
    {
        this.assets = assets;
    }

    public void loadAssets()
    {
        assets.load("data/textures/neutral/hud/resource-icon.png", Texture.class);
        assets.load("data/textures/human/hud/resource.png", Texture.class);
    }

    public Hud newHud(Identifier identifier)
    {
        BitmapFont labelFont = new BitmapFont();
        Color labelColor = Color.WHITE;
        Label.LabelStyle labelStyle = new  Label.LabelStyle(labelFont, labelColor);
        Label label = new Label("100", labelStyle);

        Texture imageTexture = assets.get("data/textures/neutral/hud/resource-icon.png");
        TextureRegion imageRegion = new TextureRegion(imageTexture, 0, 14, 14, 14);
        Image image = new Image(imageRegion);

        Texture resourceTexture = assets.get("data/textures/human/hud/resource.png");
        TextureRegion resourceTextureRegion = new TextureRegion(resourceTexture);
        Drawable resourceDrawable = new TextureRegionDrawable(resourceTextureRegion);

        Table table = new Table();
        table.setBackground(resourceDrawable);
        table.setBounds(0, Gdx.graphics.getHeight() - 20, Gdx.graphics.getWidth(), 20);
        table.align(Align.right);
        table.add(image).width(14).padRight(5f);
        table.add(label).width(50).padRight(5f);

        //hud.addActor(table);

        return null;
    }
}
