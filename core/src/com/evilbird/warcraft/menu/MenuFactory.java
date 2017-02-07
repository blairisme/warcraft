package com.evilbird.warcraft.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.evilbird.warcraft.level.Level;
import com.evilbird.warcraft.level.LevelFactory;
import com.evilbird.warcraft.utility.Identifier;

public class MenuFactory
{
    private AssetManager assetManager;
    private LevelFactory levelFactory;

    public MenuFactory(AssetManager assetManager, LevelFactory levelFactory)
    {
        this.levelFactory = levelFactory;
        this.assetManager = assetManager;
    }

    public void loadAssets()
    {
        assetManager.load("data/textures/menu/button.png", Texture.class);
        assetManager.load("data/textures/menu/menu.png", Texture.class);
    }

    public Menu newMenu(Identifier id)
    {

        Stage stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Texture menuTexture = assetManager.get("data/textures/menu/menu.png");
        TextureRegion menuTextureRegion = new TextureRegion(menuTexture);
        Drawable menuDrawable = new TextureRegionDrawable(menuTextureRegion);

        Texture buttonTexture = assetManager.get("data/textures/menu/button.png");
        TextureRegion enabledTexture = new TextureRegion(buttonTexture, 0, 0, 224, 28);
        TextureRegion selectedTexture = new TextureRegion(buttonTexture, 0, 28, 224, 28);
        TextureRegion disabledTexture = new TextureRegion(buttonTexture, 0, 56, 224, 28);

        BitmapFont buttonFont = new BitmapFont();

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = buttonFont;
        buttonStyle.up = new TextureRegionDrawable(enabledTexture);
        buttonStyle.down = new TextureRegionDrawable(selectedTexture);
        buttonStyle.over = new TextureRegionDrawable(enabledTexture);
        buttonStyle.checked = new TextureRegionDrawable(enabledTexture);
        buttonStyle.checkedOver = new TextureRegionDrawable(enabledTexture);
        buttonStyle.disabled = new TextureRegionDrawable(disabledTexture);

        TextButton button1 = new TextButton("Single Player Game", buttonStyle);
        TextButton button2 = new TextButton("Multi Player Game", buttonStyle);
        TextButton button3 = new TextButton("Replay Introduction", buttonStyle);
        TextButton button4 = new TextButton("Show Credits", buttonStyle);
        TextButton button5 = new TextButton("Exit Program", buttonStyle);

        button1.setDisabled(false);
        button2.setDisabled(true);
        button3.setDisabled(true);
        button4.setDisabled(true);
        button5.setDisabled(true);

        Table table = new Table();
        table.setFillParent(true);
        table.center();
        table.padTop(150f);
        table.setBackground(menuDrawable);

        addButton(table, button1);
        addButton(table, button2);
        addButton(table, button3);
        addButton(table, button4);
        addButton(table, button5);

        stage.addActor(table);

        final Menu menu = new Menu(stage);

        button1.addListener(new ChangeListener()
        {
            public void changed (ChangeEvent event, Actor actor)
            {
                Level level = levelFactory.newLevel(new Identifier("Level1"));
                menu.setScreen(level);


                //setScreen(new LevelScreen(getView(), getDevice()));
            }
        });
        return menu;
    }

    private void addButton(Table table, TextButton button)
    {
        Cell cell = table.add(button);
        cell.width(Value.percentWidth(0.3f, table));
        cell.height(Value.percentHeight(0.04f, table));
        cell.padBottom(Value.percentHeight(0.01f, table));
        cell.fill();
        table.row();
    }
}
