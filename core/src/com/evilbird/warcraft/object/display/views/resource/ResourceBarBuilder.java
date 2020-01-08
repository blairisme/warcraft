/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
