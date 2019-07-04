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
import com.evilbird.warcraft.item.unit.building.BuildingAssets;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

import static com.evilbird.engine.common.graphics.TextureUtils.getDrawable;
import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.engine.common.math.GridPoints.ZERO;

/**
 * Instances of this factory create {@link Placeholder Placeholders}, visual
 * representations of buildings before their construction.
 *
 * @author Blair Butterworth
 */
public class PlaceholderFactory implements IdentifiedAssetProvider<Item>
{
    private static final String ALLOWED_TEXTURE = "data/textures/common/ui/building_allowed.png";
    private static final String PROHIBITED_TEXTURE = "data/textures/common/ui/building_prohibited.png";

    private AssetManager assets;
    private EventQueue events;

    @Inject
    public PlaceholderFactory(Device device, EventQueue events) {
        this.events = events;
        this.assets = device.getAssetStorage();
    }

    @Override
    public void load() {
        assets.load(ALLOWED_TEXTURE, Texture.class);
        assets.load(PROHIBITED_TEXTURE, Texture.class);
    }

    @Override
    public Item get(Identifier identifier) {
        Validate.isInstanceOf(PlaceholderType.class, identifier);
        return getPlaceholder((PlaceholderType)identifier);
    }

    private Placeholder getPlaceholder(PlaceholderType type) {
        BuildingAssets buildingAssets = new BuildingAssets(assets, type.getBuilding());

        Placeholder placeholder = new Placeholder(getSkin(buildingAssets));
        placeholder.setIdentifier(objectIdentifier("Placeholder", placeholder));
        placeholder.setType(type);
        placeholder.setSize(buildingAssets.getSize());
        placeholder.setEvents(events);
        return placeholder;
    }

    private Skin getSkin(BuildingAssets buildingAssets) {
        Skin skin = new Skin();
        skin.add("default", getStyle(buildingAssets), PlaceholderStyle.class);
        return skin;
    }

    private PlaceholderStyle getStyle(BuildingAssets buildingAssets) {
        Texture texture = buildingAssets.getBaseTexture();
        GridPoint2 size = buildingAssets.getSize();
        return getStyle(texture, size);
    }

    private PlaceholderStyle getStyle(Texture building, GridPoint2 size) {
        PlaceholderStyle style = new PlaceholderStyle();
        style.building = getDrawable(building, ZERO, size);
        style.allowed = getDrawable(assets, ALLOWED_TEXTURE, 0, 0, size.x, size.y);
        style.prohibited = getDrawable(assets, PROHIBITED_TEXTURE, 0, 0, size.x, size.y);
        return style;
    }
}