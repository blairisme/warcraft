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
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.assets.AssetBundle;
import com.evilbird.engine.common.graphics.TextureUtils;
import com.evilbird.warcraft.common.WarcraftSeason;
import com.evilbird.warcraft.item.unit.UnitDimensions;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.state.WarcraftContext;

import static com.evilbird.engine.common.math.GridPoints.ZERO;
import static com.evilbird.engine.common.text.CaseUtils.toSnakeCase;

/**
 * Provides access to the assets that are required to display a
 * {@link Placeholder}.
 *
 * @author Blair Butterworth
 */
public class PlaceholderAssets extends AssetBundle
{
    public PlaceholderAssets(AssetManager assetManager, WarcraftContext context) {
        super(assetManager);
        registerOverlayTextures();
        registerBuildingTextures(context.getAssetSet());
    }

    private void registerOverlayTextures() {
        register("allowed", "data/textures/common/ui/building_allowed.png");
        register("prohibited", "data/textures/common/ui/building_prohibited.png");
    }

    private void registerBuildingTextures(WarcraftSeason seasonType) {
        for (UnitType type: UnitType.values()) {
            if (type.isBuilding() && !type.isNeutral()) {
                String name = toSnakeCase(type.name());
                String faction = toSnakeCase(type.getFaction().name());
                String season = toSnakeCase(seasonType.name());
                register(type, "data/textures/" + faction + "/building/" + season + "/" + name + ".png");
            }
        }
    }

    public Drawable getAllowed(UnitType type) {
        return getDrawable("allowed", type);
    }

    public Drawable getProhibited(UnitType type) {
        return getDrawable("prohibited", type);
    }

    public Drawable getBuilding(UnitType type) {
        return getDrawable(type, type);
    }

    public GridPoint2 getSize(UnitType type) {
        return UnitDimensions.getDimensions(type);
    }

    private Drawable getDrawable(Object id, UnitType type) {
        Texture texture = getTexture(id);
        GridPoint2 dimensions = getSize(type);
        return TextureUtils.getDrawable(texture, ZERO, dimensions);
    }
}
