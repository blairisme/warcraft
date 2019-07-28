/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.terrain;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.item.layer.LayerIdentifier;
import com.evilbird.warcraft.item.layer.LayerUtils;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link Terrain} layers.
 *
 * @author Blair Butterworth
 */
public class TerrainFactory implements GameFactory<Terrain>
{
    @Inject
    public TerrainFactory() {
    }

    @Override
    public void load(GameContext context) {
    }

    @Override
    public void unload(GameContext context) {
    }

    @Override
    public Terrain get(Identifier identifier) {
        Validate.isInstanceOf(LayerIdentifier.class, identifier);
        LayerIdentifier layerIdentifier = (LayerIdentifier)identifier;

        Terrain terrain = new Terrain();
        terrain.setIdentifier(layerIdentifier);
        terrain.setType(layerIdentifier.getType());
        terrain.setLayer(LayerUtils.getLayer(layerIdentifier));
        terrain.setVisible(true);
        terrain.setTouchable(Touchable.enabled);
        return terrain;
    }
}
