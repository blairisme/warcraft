/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.ingame;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.inject.IdentifiedAssetProvider;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.menu.Menu;

import javax.inject.Inject;

import static com.evilbird.engine.common.graphics.TextureUtils.getDrawable;
import static com.evilbird.engine.common.graphics.TextureUtils.getTiledDrawable;

public class IngameMenuFactory implements IdentifiedAssetProvider<Menu>
{
    private static final String BUTTON_ENABLED = "data/textures/human/menu/button-large-normal.png";
    private static final String BUTTON_DISABLED = "data/textures/human/menu/button-large-grayed.png";
    private static final String BUTTON_SELECTED = "data/textures/human/menu/button-large-pressed.png";

    private static final String TEXT_PANEL_NORMAL = "data/textures/human/menu/text_panel_normal.png";
    //private static final String TEXT_PANEL_DISABLED = "data/textures/human/menu/text_panel_normal.png";

    private static final String BACKGROUND = "data/textures/human/menu/panel_1.png";
    private static final String CLICK = "data/sounds/menu/click.mp3";

    private AssetManager assets;

    @Inject
    public IngameMenuFactory(Device device) {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load() {
        assets.load(BACKGROUND, Texture.class);
        assets.load(BUTTON_ENABLED, Texture.class);
        assets.load(BUTTON_DISABLED, Texture.class);
        assets.load(BUTTON_SELECTED, Texture.class);
        assets.load(TEXT_PANEL_NORMAL, Texture.class);
        assets.load(CLICK, Sound.class);
    }

    @Override
    public Menu get(Identifier identifier) {
        IngameMenu menu = createMenu((IngameMenus)identifier);
        setAssets(menu);
        return menu;
    }

    private IngameMenu createMenu(IngameMenus type) {
        switch (type) {
            case Root: return new RootMenu();
            case Save: return new SaveMenu();
            default: throw new UnsupportedOperationException();
        }
    }

    private void setAssets(IngameMenu menu) {
        menu.setBackground(getDrawable(assets, BACKGROUND));
        menu.setButtonEnabled(getDrawable(assets, BUTTON_ENABLED));
        menu.setButtonDisabled(getDrawable(assets, BUTTON_DISABLED));
        menu.setButtonSelected(getDrawable(assets, BUTTON_SELECTED));


        menu.setTextFieldColor(Color.GOLD);
        menu.setTextFieldBackground(getDrawable(assets, TEXT_PANEL_NORMAL));


        menu.setListBackground(getTiledDrawable(assets, TEXT_PANEL_NORMAL));
        menu.setSelectedColour(Color.GOLD);
        //menu.setListSelection(getDrawable(assets, TEXT_PANEL_NORMAL));

        menu.setButtonSound(assets.get(CLICK));
    }
}
