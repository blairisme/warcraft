/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.selector.building;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.assets.AssetBundle;
import com.evilbird.engine.common.graphics.DrawableUtils;
import com.evilbird.warcraft.common.WarcraftFaction;
import com.evilbird.warcraft.common.WarcraftSeason;
import com.evilbird.warcraft.object.unit.UnitArchetype;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.state.WarcraftContext;

import static com.evilbird.engine.common.math.GridPoints.Zero;
import static com.evilbird.engine.common.text.StringUtils.toSnakeCase;
import static com.evilbird.warcraft.common.WarcraftFaction.Neutral;

/**
 * Provides access to the assets that are required to display a
 * {@link BuildingSelector}.
 *
 * @author Blair Butterworth
 */
public class BuildingSelectorAssets extends AssetBundle
{
    public BuildingSelectorAssets(AssetManager assetManager, WarcraftContext context) {
        super(assetManager);
        registerOverlayTextures();
        registerBuildingTextures(context.getAssetSet());
        registerSelectTextures();
    }

    private void registerOverlayTextures() {
        register("allowed", "data/textures/common/ui/building_allowed.png");
        register("prohibited", "data/textures/common/ui/building_prohibited.png");
    }

    private void registerBuildingTextures(WarcraftSeason seasonType) {
        for (UnitType type: UnitType.values()) {
            UnitArchetype archetype = type.getArchetype();
            WarcraftFaction faction = type.getFaction();

            if (archetype.isBuilding() && faction != Neutral) {
                String name = toSnakeCase(type.name());
                String group = toSnakeCase(faction.name());
                String season = toSnakeCase(seasonType.name());
                register(type, "data/textures/" + group + "/building/" + season + "/" + name + ".png");
            }
        }
    }

    private void registerSelectTextures() {
        register("rune_select", "data/textures/common/menu/rune_select.png");
    }

    public Drawable getAllowed(UnitType type) {
        return getDrawable("allowed", type);
    }

    public Drawable getProhibited(UnitType type) {
        return getDrawable("prohibited", type);
    }

    public Drawable getRuneSelect() {
        return getDrawable("rune_select");
    }

    public Drawable getBuilding(UnitType type) {
        return getDrawable(type, type);
    }

    public GridPoint2 getSize(UnitType type) {
        return type.getSize().getDimensions();
    }

    private Drawable getDrawable(Object id, UnitType type) {
        Texture texture = getTexture(id);
        GridPoint2 dimensions = getSize(type);
        return DrawableUtils.getDrawable(texture, Zero, dimensions);
    }
}
