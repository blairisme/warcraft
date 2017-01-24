package com.evilbird.warcraft.menu;

import com.evilbird.warcraft.GameScene;

public class MenuScreen extends GameScene
{
    /*
    private Stage stage;

    public MenuScreen(GameView view, Device device)
    {
        super(view, device);
        create();
    }

    //TODO - Move logic into factory
    public void create()
    {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        final AssetManager assetManager = getDevice().getAssetStorage().getAssets();
        assetManager.load("data/textures/menu/button.png", Texture.class);
        assetManager.load("data/textures/menu/menu.png", Texture.class);
        assetManager.finishLoading();

        Texture menuTexture = assetManager.get("data/textures/menu/menu.png");
        TextureRegion menuTextureRegion = new TextureRegion(menuTexture);
        Drawable menuDrawable = new TextureRegionDrawable(menuTextureRegion);

        Texture buttonTexture = assetManager.get("data/textures/menu/button.png");
        TextureRegion enabledTexture = new TextureRegion(buttonTexture, 0, 0, 224, 28);
        TextureRegion selectedTexture = new TextureRegion(buttonTexture, 0, 28, 224, 28);
        TextureRegion disabledTexture = new TextureRegion(buttonTexture, 0, 56, 224, 28);

        BitmapFont buttonFont = new BitmapFont();

        TextButtonStyle buttonStyle = new TextButtonStyle();
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

        table.add(button1).width(224).padBottom(5f);
        table.row();
        table.add(button2).width(224).padBottom(5f);
        table.row();
        table.add(button3).width(224).padBottom(5f);
        table.row();
        table.add(button4).width(224).padBottom(5f);
        table.row();
        table.add(button5).width(224).padBottom(5f);
        table.row();

        stage.addActor(table);

        button1.addListener(new ChangeListener()
        {
            public void changed (ChangeEvent event, Actor actor)
            {
                LevelFactory levelFactory = new LevelFactory(assetManager);
                Level level = levelFactory.newLevel(new Identifier("Level1"));
                setScreen(level);


                //setScreen(new LevelScreen(getView(), getDevice()));
            }
        });
    }

    public void render (float delta)
    {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize (int width, int height)
    {
        stage.getViewport().update(width, height, false);
    }

    @Override
    public void dispose ()
    {
        stage.dispose();
    }
    */
}
