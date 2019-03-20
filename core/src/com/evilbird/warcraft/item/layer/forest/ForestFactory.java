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
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.inject.IdentifiedAssetProvider;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.item.layer.LayerIdentifier;
import com.evilbird.warcraft.item.layer.LayerUtils;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link Forest} instances, loading the
 * appropriate assets to display the forest, and its cells, in different
 * states.
 *
 * @author Blair Butterworth
 */
public class ForestFactory implements IdentifiedAssetProvider<Forest>
{
    private static final String TERRAIN = "data/textures/neutral/winter/terrain.png";
    private AssetManager assets;

    @Inject
    public ForestFactory(Device device) {
        this.assets = device.getAssetStorage();
    }

    @Override
    public void load() {
        assets.load(TERRAIN, Texture.class);
    }

    @Override
    public Forest get(Identifier identifier) {
        Validate.isInstanceOf(LayerIdentifier.class, identifier);
        LayerIdentifier layerIdentifier = (LayerIdentifier)identifier;

        Forest forest = new Forest();
        forest.setSkin(getSkin());
        forest.setIdentifier(layerIdentifier);
        forest.setType(layerIdentifier.getType());
        forest.setLayer(LayerUtils.getLayer(layerIdentifier));
        forest.setVisible(true);
        forest.setSelected(false);
        forest.setSelectable(false);
        forest.setTouchable(Touchable.childrenOnly);
        return forest;
    }

    private Skin getSkin() {
        Skin skin = new Skin();
        skin.add("default", getForestStyle());
        return skin;
    }

    private ForestStyle getForestStyle() {
        ForestStyle forestStyle = new ForestStyle();
        //forestStyle.deadCenter = getRegion(assets, TERRAIN, 448, 224, 32, 32);
        return forestStyle;
    }
}
