/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.resource;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.common.graphics.Fonts;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.hud.HudControl;

import javax.inject.Inject;

import static com.evilbird.engine.common.graphics.TextureUtils.getDrawable;

/**
 * Instances of this factory create {@link ResourcePane ResourcePanes}.
 *
 * @author Blair Butterworth
 */
public class ResourcePaneFactory implements AssetProvider<Item>
{
    private static final String ICONS = "data/textures/neutral/hud/resource-icon.png";
    private static final String BACKGROUND = "data/textures/human/hud/resource_panel.png";

    private AssetManager assets;

    @Inject
    public ResourcePaneFactory(Device device) {
        this.assets = device.getAssetStorage();
    }

    @Override
    public void load() {
        this.assets.load(ICONS, Texture.class);
        this.assets.load(BACKGROUND, Texture.class);
    }

    @Override
    public Item get() {
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
        style.background = getDrawable(assets, BACKGROUND);
        style.goldIcon = getDrawable(assets, ICONS, 0, 0, 14, 14);
        style.oilIcon = getDrawable(assets, ICONS, 0, 28, 14, 14);
        style.woodIcon = getDrawable(assets, ICONS, 0, 14, 14, 14);
        return style;
    }
}
