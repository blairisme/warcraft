/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.views.resource;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.warcraft.object.display.components.UserInterfaceComponent;

/**
 * Creates a new {@link ResourceBar} instance whose visual presentation is
 * defined by the given {@link ResourceBarAssets}.
 *
 * @author Blair Butterworth
 */
public class ResourceBarBuilder
{
    private ResourceBarAssets assets;

    public ResourceBarBuilder(ResourceBarAssets assets) {
        this.assets = assets;
    }

    public ResourceBar build() {
        ResourceBar result = new ResourceBar(getStyle());
        result.setIdentifier(UserInterfaceComponent.ResourcePane);
        result.setType(UserInterfaceComponent.ResourcePane);
        result.setTouchable(Touchable.disabled);
        result.setVisible(true);
        return result;
    }

    private ResourceBarStyle getStyle() {
        ResourceBarStyle style = new ResourceBarStyle();
        style.font = assets.getFont();
        style.colour = Color.WHITE;
        style.background = assets.getBackground();
        style.goldIcon = assets.getGoldIcon();
        style.oilIcon = assets.getOilIcon();
        style.woodIcon = assets.getWoodIcon();
        return style;
    }
}
