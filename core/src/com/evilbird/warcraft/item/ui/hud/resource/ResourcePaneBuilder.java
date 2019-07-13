/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.hud.resource;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.common.graphics.Fonts;
import com.evilbird.warcraft.item.ui.hud.HudControl;

/**
 * Creates a new {@link ResourcePane} instance whose visual presentation is
 * defined by the given {@link ResourcePaneAssets}.
 *
 * @author Blair Butterworth
 */
public class ResourcePaneBuilder
{
    private ResourcePaneAssets assets;

    public ResourcePaneBuilder(ResourcePaneAssets assets) {
        this.assets = assets;
    }

    public ResourcePane build() {
        ResourcePane result = new ResourcePane(getStyle());
        result.setIdentifier(HudControl.ResourcePane);
        result.setType(HudControl.ResourcePane);
        result.setTouchable(Touchable.disabled);
        result.setVisible(true);
        return result;
    }

    private ResourcePaneStyle getStyle() {
        ResourcePaneStyle style = new ResourcePaneStyle();
        style.font = Fonts.ARIAL;
        style.colour = Color.WHITE;
        style.background = assets.getBackground();
        style.goldIcon = assets.getGoldIcon();
        style.oilIcon = assets.getOilIcon();
        style.woodIcon = assets.getWoodIcon();
        return style;
    }
}