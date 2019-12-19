/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.views.map;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.warcraft.object.display.components.map.MapPaneStyle;

/**
 * Creates a new {@link MapOverlay} instance whose visual presentation is
 * defined by the given {@link MapOverlayAssets}.
 *
 * @author Blair Butterworth
 */
public class MapOverlayBuilder 
{
    private MapOverlayAssets assets;

    public MapOverlayBuilder(MapOverlayAssets assets) {
        this.assets = assets;
    }

    public MapOverlay build() {
        return new MapOverlay(getSkin());
    }

    private Skin getSkin() {
        Skin skin = new Skin();
        skin.add("default", getMapPaneStyle());
        return skin;
    }

    private MapPaneStyle getMapPaneStyle() {
        MapPaneStyle style = new MapPaneStyle();
        style.background = assets.getBackground();
        style.mapSize = new Vector2(224.0f, 224.0f);
        return style;
    }
}
