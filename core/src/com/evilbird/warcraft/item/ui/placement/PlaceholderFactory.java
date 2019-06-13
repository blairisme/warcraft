/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.placement;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.inject.IdentifiedAssetProvider;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

import static com.evilbird.engine.common.graphics.TextureUtils.getDrawable;
import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;

/**
 * Instances of this factory create {@link Placeholder Placeholders}, visual
 * representations of a buildings before their construction.
 *
 * @author Blair Butterworth
 */
public class PlaceholderFactory implements IdentifiedAssetProvider<Item>
{
    private static final String BARRACKS_TEXTURE = "data/textures/human/winter/barracks.png";
    private static final String FARM_TEXTURE = "data/textures/human/winter/farm.png";
    private static final String LUMBER_MILL_TEXTURE = "data/textures/human/winter/lumber_mill.png";
    private static final String TOWN_HALL_TEXTURE = "data/textures/human/winter/town_hall.png";
    private static final String ALLOWED_TEXTURE = "data/textures/neutral/hud/building_allowed.png";
    private static final String PROHIBITED_TEXTURE = "data/textures/neutral/hud/building_prohibited.png";

    private AssetManager assets;
    private EventQueue events;

    @Inject
    public PlaceholderFactory(Device device, EventQueue events) {
        this.events = events;
        this.assets = device.getAssetStorage();
    }

    @Override
    public void load() {
        assets.load(BARRACKS_TEXTURE, Texture.class);
        assets.load(FARM_TEXTURE, Texture.class);
        assets.load(LUMBER_MILL_TEXTURE, Texture.class);
        assets.load(TOWN_HALL_TEXTURE, Texture.class);
        assets.load(ALLOWED_TEXTURE, Texture.class);
        assets.load(PROHIBITED_TEXTURE, Texture.class);
    }

    @Override
    public Item get(Identifier identifier) {
        Validate.isInstanceOf(PlaceholderType.class, identifier);
        return getPlaceholder((PlaceholderType)identifier);
    }

    private Placeholder getPlaceholder(PlaceholderType type) {
        Placeholder placeholder = new Placeholder(getSkin(type));
        placeholder.setIdentifier(objectIdentifier("Placeholder", placeholder));
        placeholder.setType(type);
        placeholder.setSize(getSize(type));
        placeholder.setEvents(events);
        return placeholder;
    }

    private Skin getSkin(PlaceholderType type) {
        Skin skin = new Skin();
        skin.add("default", getStyle(type), PlaceholderStyle.class);
        return skin;
    }

    private PlaceholderStyle getStyle(PlaceholderType type) {
        String texture = getTexture(type);
        GridPoint2 size = getSize(type);
        return getStyle(texture, size);
    }

    private PlaceholderStyle getStyle(String texture, GridPoint2 size) {
        PlaceholderStyle style = new PlaceholderStyle();
        style.building = getDrawable(assets, texture, 0, 0, size.x, size.y);
        style.allowed = getDrawable(assets, ALLOWED_TEXTURE, 0, 0, 96, 96);
        style.prohibited = getDrawable(assets, PROHIBITED_TEXTURE, 0, 0, 96, 96);
        return style;
    }

    private GridPoint2 getSize(PlaceholderType type) {
        switch (type) {
            case FarmPlaceholder: return new GridPoint2(64, 64);
            case BarracksPlaceholder:
            case LumberMillPlaceholder: return new GridPoint2(96, 96);
            case TownHallPlaceholder: return new GridPoint2(128, 128);
            default: throw new UnsupportedOperationException();
        }
    }

    private String getTexture(PlaceholderType type) {
        switch (type) {
            case FarmPlaceholder: return FARM_TEXTURE;
            case BarracksPlaceholder: return BARRACKS_TEXTURE;
            case LumberMillPlaceholder: return LUMBER_MILL_TEXTURE;
            case TownHallPlaceholder: return TOWN_HALL_TEXTURE;
            default: throw new UnsupportedOperationException();
        }
    }
}