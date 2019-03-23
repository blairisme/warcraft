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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.hud.HudControl;

import javax.inject.Inject;

import static com.evilbird.engine.common.assets.AssetUtilities.fontSize;

/**
 * Instances of this factory create {@link ResourcePane ResourcePanes}.
 *
 * @author Blair Butterworth
 */
public class ResourcePaneFactory implements AssetProvider<Item>
{
    private static final String ICONS = "data/textures/neutral/hud/resource-icon.png";
    private static final String BACKGROUND = "data/textures/human/hud/resource_panel.png";
    private static final String FONT = "data/fonts/lifecraft.ttf";
    private AssetManager assets;

    @Inject
    public ResourcePaneFactory(Device device) {
        this.assets = device.getAssetStorage();
    }

    @Override
    public void load() {
        this.assets.load(ICONS, Texture.class);
        this.assets.load(BACKGROUND, Texture.class);
        this.assets.load(FONT, BitmapFont.class, fontSize(16));
    }

    @Override
    public Item get() {
        ResourcePane result = new ResourcePane();
        result.setIdentifier(HudControl.ResourcePane);
        result.setType(HudControl.ResourcePane);
        result.setTouchable(Touchable.disabled);
        result.setVisible(true);
        result.setFont(getFont());
        result.setBackground(getBackground());
        result.setGoldIcon(getGoldIcon());
        result.setGoldText("0");
        result.setOilIcon(getOilIcon());
        result.setOilText("0");
        result.setWoodIcon(getWoodIcon());
        result.setWoodText("0");
        return result;
    }

    private BitmapFont getFont() {
        return assets.get(FONT);
    }

    private TextureRegion getBackground() {
        Texture texture = assets.get(BACKGROUND);
        return new TextureRegion(texture);
    }

    private TextureRegion getGoldIcon() {
        Texture texture = assets.get(ICONS);
        return new TextureRegion(texture, 0, 0, 14, 14);
    }

    private TextureRegion getOilIcon() {
        Texture texture = assets.get(ICONS);
        return new TextureRegion(texture, 0, 28, 14, 14);
    }

    private TextureRegion getWoodIcon() {
        Texture texture = assets.get(ICONS);
        return new TextureRegion(texture, 0, 14, 14, 14);
    }
}
