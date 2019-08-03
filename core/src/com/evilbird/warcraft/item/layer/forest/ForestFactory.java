/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.forest;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.collection.BitMatrix;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.item.layer.LayerGroupStyle;
import com.evilbird.warcraft.item.layer.LayerIdentifier;
import com.evilbird.warcraft.item.layer.LayerUtils;
import com.evilbird.warcraft.state.WarcraftContext;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

import static com.evilbird.engine.common.collection.BitMatrix.matrix3;
import static com.evilbird.warcraft.item.layer.LayerUtils.unpaddedCell;

/**
 * Instances of this factory create {@link Forest} instances, loading the
 * appropriate assets to display the forest and its cells in different
 * states.
 *
 * @author Blair Butterworth
 */
public class ForestFactory implements GameFactory<Forest>
{
    private AssetManager manager;
    private ForestAssets assets;

    @Inject
    public ForestFactory(Device device) {
        this(device.getAssetStorage());
    }

    public ForestFactory(AssetManager manager) {
        this.manager = manager;
    }

    @Override
    public void load(GameContext context) {
        Validate.isInstanceOf(WarcraftContext.class, context);
        load((WarcraftContext)context);
    }

    private void load(WarcraftContext context) {
        assets = new ForestAssets(manager, context);
        assets.load();
    }

    @Override
    public void unload(GameContext context) {
        if (assets != null) {
            assets.unload();
        }
    }

    @Override
    public Forest get(Identifier identifier) {
        Validate.isInstanceOf(LayerIdentifier.class, identifier);
        return get((LayerIdentifier)identifier);
    }

    private Forest get(LayerIdentifier layerType) {
        Forest forest = new Forest(getSkin());
        forest.setIdentifier(layerType);
        forest.setType(layerType.getType());
        forest.setLayer(LayerUtils.getLayer(layerType));
        forest.setVisible(true);
        forest.setTouchable(Touchable.childrenOnly);
        return forest;
    }

    private Skin getSkin() {
        Skin skin = new Skin();
        skin.add("default", getStyle());
        return skin;
    }

    private LayerGroupStyle getStyle() {
        Texture terrain = assets.getTerrainTexture();
        LayerGroupStyle forestStyle = new LayerGroupStyle();
        forestStyle.empty = unpaddedCell(terrain, 448, 224, 32, 32);
        forestStyle.patterns = getCellStyles(terrain);
        return forestStyle;
    }

    private Map<BitMatrix, TiledMapTileLayer.Cell> getCellStyles(Texture texture) {
        Map<BitMatrix, TiledMapTileLayer.Cell> styles = new HashMap<>();
        addStraightEdges(styles, texture);
        addPeninsulas(styles, texture);
        return styles;
    }

    private void addStraightEdges(Map<BitMatrix, TiledMapTileLayer.Cell> styles, Texture texture) {
        addLeftEdge(styles, texture);
        addRightEdge(styles, texture);
        addTopEdge(styles, texture);
        addBottomEdge(styles, texture);
    }

    private void addLeftEdge(Map<BitMatrix, TiledMapTileLayer.Cell> styles, Texture texture) {
        TiledMapTileLayer.Cell style = unpaddedCell(texture, 96, 224, 32, 32);
        styles.put(matrix3("0,1,1,0,1,1,0,1,1"), style); //left
        styles.put(matrix3("0,1,1,0,1,1,1,1,1"), style); //top overhang
        styles.put(matrix3("1,1,1,0,1,1,0,1,1"), style); //bottom overhang
    }

    private void addRightEdge(Map<BitMatrix, TiledMapTileLayer.Cell> styles, Texture texture) {
        TiledMapTileLayer.Cell style = unpaddedCell(texture, 128, 224, 32, 32);
        styles.put(matrix3("1,1,0,1,1,0,1,1,0"), style); //right
        styles.put(matrix3("1,1,0,1,1,0,1,1,1"), style); //top overhang
        styles.put(matrix3("1,1,1,1,1,0,1,1,0"), style); //bottom overhang
    }

    private void addTopEdge(Map<BitMatrix, TiledMapTileLayer.Cell> styles, Texture texture) {
        TiledMapTileLayer.Cell style = unpaddedCell(texture, 320, 192, 32, 32);
        styles.put(matrix3("1,1,1,1,1,1,0,0,0"), style); //top
        styles.put(matrix3("1,1,1,1,1,1,1,0,0"), style); //left overhang
        styles.put(matrix3("1,1,1,1,1,1,0,0,1"), style); //right overhang
        styles.put(matrix3("1,1,1,1,1,1,1,0,1"), style); //both overhangs
    }

    private void addBottomEdge(Map<BitMatrix, TiledMapTileLayer.Cell> styles, Texture texture) {
        TiledMapTileLayer.Cell style = unpaddedCell(texture, 384, 224, 32, 32);
        styles.put(matrix3("0,0,0,1,1,1,1,1,1"), style); //bottom
        styles.put(matrix3("1,0,0,1,1,1,1,1,1"), style); //left overhang
        styles.put(matrix3("0,0,1,1,1,1,1,1,1"), style); //right overhang
        styles.put(matrix3("1,0,1,1,1,1,1,1,1"), style); //both overhangs
    }

    private void addPeninsulas(Map<BitMatrix, TiledMapTileLayer.Cell> styles, Texture texture) {
        styles.put(matrix3("0,1,0,0,1,0,0,1,0"), unpaddedCell(texture, 320, 224, 32, 32)); //vertical peninsula

        styles.put(matrix3("1,1,1,0,1,0,0,0,0"), unpaddedCell(texture, 288, 224, 32, 32)); //top peninsula
        styles.put(matrix3("0,1,1,0,1,0,0,0,0"), unpaddedCell(texture, 288, 224, 32, 32)); //top peninsula (left missing)
        styles.put(matrix3("1,1,0,0,1,0,0,0,0"), unpaddedCell(texture, 288, 224, 32, 32)); //top peninsula (right missing)

        styles.put(matrix3("0,0,0,0,1,0,1,1,1"), unpaddedCell(texture, 352, 224, 32, 32)); //bottom peninsula
        styles.put(matrix3("0,0,0,0,1,0,0,1,1"), unpaddedCell(texture, 352, 224, 32, 32)); //bottom peninsula (left missing)
        styles.put(matrix3("0,0,0,0,1,0,1,1,0"), unpaddedCell(texture, 352, 224, 32, 32)); //bottom peninsula (right missing)
    }
}
