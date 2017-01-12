package com.evilbird.warcraft.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.evilbird.warcraft.GameScene;
import com.evilbird.warcraft.GameView;
import com.evilbird.warcraft.device.Device;
import com.evilbird.warcraft.device.UserInput;
import com.evilbird.warcraft.graphics.DirectionalAnimation;
import com.evilbird.warcraft.interaction.InteractionAnalyzer;
import com.evilbird.warcraft.map.Map;
import com.evilbird.warcraft.unit.Unit;
import com.evilbird.warcraft.unit.UnitFactory;
import com.evilbird.warcraft.utility.Identifier;

import org.apache.commons.lang3.Range;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class LevelScreen extends GameScene
{
    private Stage stage;
    private Stage hud;

    private Viewport viewport;
    private OrthographicCamera camera;

    private InteractionAnalyzer interactionService;

    public LevelScreen(GameView view, Device device)
    {
       super(view, device);
    }

    @Override
    public void create()
    {
        AssetManager assets = getDevice().getAssetStorage().getAssets();
        assets.load("data/levels/human/level1.tmx", TiledMap.class);
        assets.load("data/textures/human/perennial/footman.png", Texture.class);
        assets.load("data/textures/human/hud/resource.png", Texture.class);
        assets.load("data/textures/neutral/hud/resource-icon.png", Texture.class);
        assets.finishLoading();

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false, 30, 20);
        viewport = new ScreenViewport(camera);

        stage = new Stage(viewport);
        hud = new Stage(new ScreenViewport());


        Map map = new Map(assets);
        map.setZIndex(0);
        map.setSize(1024, 1024);
        map.setPosition(0, 0);
        map.setScale(1f);
        stage.addActor(map);

        UnitFactory unitFactory = new UnitFactory(assets);

        TiledMap mapData = assets.get("data/levels/human/level1.tmx", TiledMap.class);
        for (MapLayer layer: mapData.getLayers())
        {
            if (Objects.equals(layer.getName(), "Wood")) // swap for property aggregated=true
            {
                TiledMapTileLayer tileLayer = (TiledMapTileLayer)layer;

                for (int x = 0; x < tileLayer.getWidth(); x++)
                {
                    for (int y = 0; y < tileLayer.getHeight(); ++y)
                    {
                        TiledMapTileLayer.Cell cell = tileLayer.getCell(x, y);

                        if (cell != null)
                        {
                            TiledMapTile tile = cell.getTile();
                            Array<TextureRegion> textures = Array.with(tile.getTextureRegion());

                            java.util.Map<Range<Float>, Array<TextureRegion>> frames = new HashMap<Range<Float>, Array<TextureRegion>>(1);
                            frames.put(Range.between(0.0f, 360.0f), textures);

                            DirectionalAnimation animation = new DirectionalAnimation(0f, 0.15f, frames, Animation.PlayMode.LOOP);

                            java.util.Map<Identifier, DirectionalAnimation> additionalAnimations = new HashMap<Identifier, DirectionalAnimation>();
                            additionalAnimations.put(new Identifier("Idle"), animation);

                            Float width = tileLayer.getTileWidth();
                            Float height = tileLayer.getTileHeight();
                            Float worldX = x * width;
                            Float worldY = y * height;

                            Unit unit = unitFactory.newUnit(new Identifier("Wood"), additionalAnimations);
                            unit.setSize(width, height);
                            unit.setZIndex(5);
                            unit.setPosition(worldX, worldY);

                            stage.addActor(unit);

                        }
                    }
                }
            }

            for (MapObject object: layer.getObjects())
            {
                MapProperties properties = object.getProperties();
                String type = (String)properties.get("type");

                Float x = (Float)properties.get("x");
                Float y = (Float)properties.get("y");
                Float width = (Float)properties.get("width");
                Float height = (Float)properties.get("height");

                Unit unit = unitFactory.newUnit(new Identifier(type));
                unit.setSize(width, height);
                unit.setZIndex(10);
                unit.setPosition(x, y);

                stage.addActor(unit);
            }
        }


        interactionService = new InteractionAnalyzer();
        //userInputService = new UserInputService();
        //userInputService.install();

        getDevice().getDeviceInput().install();




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

        hud.addActor(table);


    }


    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height);
    }

    @Override
    public void render(float delta)
    {
        handleInput();
        camera.update();

        List<UserInput> inputs = getDevice().getDeviceInput().readInput();
        interactionService.update(stage, inputs);

        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();


        hud.act(delta);
        hud.draw();
    }

    @Override
    public void dispose()
    {
        stage.dispose();
    }

    private void handleInput()
    {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            camera.zoom -= 0.02;
        }
        /*
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            camera.translate(-10, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.translate(10, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            camera.translate(0, -10, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            camera.translate(0, 10, 0);
        }
        */

        camera.zoom = MathUtils.clamp(camera.zoom, 0.50f, 1.5f);

        /*
        float mapWidth = 1024f;
        float mapHeight = 1024f;

        float effectiveViewportWidth = camera.viewportWidth * camera.zoom;
        float effectiveViewportHeight = camera.viewportHeight * camera.zoom;

        float viewportWidthCenter = effectiveViewportWidth / 2f;
        float viewportHeightCenter = effectiveViewportHeight / 2f;

        camera.position.x = MathUtils.clamp(camera.position.x, viewportWidthCenter, mapWidth - viewportWidthCenter);
        camera.position.y = MathUtils.clamp(camera.position.y, viewportHeightCenter, mapHeight - viewportHeightCenter);
        */
    }
}
