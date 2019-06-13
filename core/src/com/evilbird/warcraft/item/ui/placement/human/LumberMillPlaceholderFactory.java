/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.placement.human;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.ui.placement.Placeholder;
import com.evilbird.warcraft.item.ui.placement.PlaceholderStyle;
import com.evilbird.warcraft.item.ui.placement.PlaceholderType;

import javax.inject.Inject;

import static com.evilbird.engine.common.graphics.TextureUtils.getDrawable;
import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;

/**
 * Instances of this factory create {@link Placeholder Placeholders} styled to
 * represent a barracks before construction.
 *
 * @author Blair Butterworth
 */
public class LumberMillPlaceholderFactory implements AssetProvider<Item>
{
    private static final String BUILDING_TEXTURE = "data/textures/human/winter/lumber_mill.png";
    private static final String ALLOWED_TEXTURE = "data/textures/neutral/hud/building_allowed.png";
    private static final String PROHIBITED_TEXTURE = "data/textures/neutral/hud/building_prohibited.png";

    private AssetManager assets;
    private EventQueue events;

    @Inject
    public LumberMillPlaceholderFactory(Device device, EventQueue events) {
        this.events = events;
        this.assets = device.getAssetStorage();
    }

    @Override
    public void load() {
        assets.load(BUILDING_TEXTURE, Texture.class);
        assets.load(ALLOWED_TEXTURE, Texture.class);
        assets.load(PROHIBITED_TEXTURE, Texture.class);
    }

    @Override
    public Item get() {
        Placeholder placeholder = new Placeholder(getSkin());
        placeholder.setIdentifier(objectIdentifier("Placeholder", placeholder));
        placeholder.setType(PlaceholderType.LumberMillPlaceholder);
        placeholder.setSize(96, 96);
        placeholder.setEvents(events);
        return placeholder;
    }

    private Skin getSkin() {
        Skin skin = new Skin();
        skin.add("default", getStyle(), PlaceholderStyle.class);
        return skin;
    }

    private PlaceholderStyle getStyle() {
        PlaceholderStyle style = new PlaceholderStyle();
        style.building = getDrawable(assets, BUILDING_TEXTURE, 0, 0, 96, 96);
        style.allowed = getDrawable(assets, ALLOWED_TEXTURE, 0, 0, 96, 96);
        style.prohibited = getDrawable(assets, PROHIBITED_TEXTURE, 0, 0, 96, 96);
        return style;
    }
}
