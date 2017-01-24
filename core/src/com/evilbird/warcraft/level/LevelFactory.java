package com.evilbird.warcraft.level;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.evilbird.warcraft.action.ActionFactory;
import com.evilbird.warcraft.graphics.DirectionalAnimation;
import com.evilbird.warcraft.interaction.InteractionAnalyzer;
import com.evilbird.warcraft.map.Map;
import com.evilbird.warcraft.unit.Unit;
import com.evilbird.warcraft.unit.UnitFactory;
import com.evilbird.warcraft.utility.Identifier;

import org.apache.commons.lang3.Range;

import java.util.HashMap;
import java.util.Objects;

import static com.badlogic.gdx.Gdx.graphics;

public class LevelFactory
{
    private AssetManager assets;
    private UnitFactory unitFactory;
    private ActionFactory actionFactory;

    public LevelFactory(AssetManager assets, UnitFactory unitFactory, ActionFactory actionFactory)
    {
        this.assets = assets;
        this.unitFactory = unitFactory;
        this.actionFactory = actionFactory;
    }

    public void loadAssets()
    {
    }

    public Level newLevel(Identifier identifier)
    {
        InteractionAnalyzer interactionAnalyzer = new InteractionAnalyzer(actionFactory);

        assets.load("data/levels/human/level1.tmx", TiledMap.class);
        assets.finishLoading();


        TiledMap map = assets.get("data/levels/human/level1.tmx", TiledMap.class);

        OrthographicCamera camera = new OrthographicCamera(graphics.getWidth(), graphics.getHeight());
        camera.setToOrtho(false, 30, 20);
        camera.position.x = 50;
        camera.position.y = 50;

        Stage world = new Stage(new ScreenViewport(camera));
        Stage hud = new Stage(new ScreenViewport());

        addItems(unitFactory, world, map);

        return new Level(world, hud, interactionAnalyzer);
    }



    private void addItems(UnitFactory unitFactory, Stage stage, TiledMap map)
    {
        addMapItem(stage, map);
        for (MapLayer layer: map.getLayers())
        {
            addAggregateItems(unitFactory, stage, layer);
            addObjectItems(unitFactory, stage, layer);
        }
    }

    private void addMapItem(Stage stage, TiledMap tiledMap)
    {
        Map map = new Map(tiledMap);
        map.setZIndex(0);
        map.setSize(1024, 1024);
        map.setPosition(0, 0);
        map.setScale(1f);
        stage.addActor(map);
    }

    private void addAggregateItems(UnitFactory unitFactory, Stage stage, MapLayer layer)
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

                        Unit unit = unitFactory.newUnit(new Identifier("Wood"), new Identifier(), additionalAnimations);
                        unit.setSize(width, height);
                        unit.setZIndex(5);
                        unit.setPosition(worldX, worldY);

                        stage.addActor(unit);
                    }
                }
            }
        }
    }

    private void addObjectItems(UnitFactory unitFactory, Stage stage, MapLayer layer)
    {
        for (MapObject object : layer.getObjects())
        {
            MapProperties properties = object.getProperties();
            String type = (String) properties.get("type");

            if (!Objects.equals(type, "Camera")) // TODO
            {
                Float x = (Float) properties.get("x");
                Float y = (Float) properties.get("y");
                Float width = (Float) properties.get("width");
                Float height = (Float) properties.get("height");

                Unit unit = unitFactory.newUnit(new Identifier(type), new Identifier());
                unit.setSize(width, height);
                unit.setZIndex(10);
                unit.setPosition(x, y);

                stage.addActor(unit);
            }
        }
    }
}
