/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.selector.building;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.warcraft.object.selector.SelectorType;
import com.evilbird.warcraft.object.unit.UnitType;

/**
 * Creates new {@link BuildingSelector} objects, whose visual and audible
 * presentation are defined by the given {@link BuildingSelectorAssets}.
 *
 * @author Blair Butterworth
 */
public class BuildingSelectorBuilder
{
    private BuildingSelectorAssets assets;

    public BuildingSelectorBuilder(BuildingSelectorAssets assets) {
        this.assets = assets;
    }

    public BuildingSelector build(SelectorType type) {
        return build(type.getBuilding());
    }

    private BuildingSelector build(UnitType building) {
        BuildingSelector result = new BuildingSelector(getSkin(building));
        result.setSize(assets.getSize(building));
        result.setTouchable(Touchable.enabled);
        result.setVisible(true);
        return result;
    }

    private Skin getSkin(UnitType type) {
        Skin skin = new Skin();
        skin.add("default", getStyle(type), BuildingSelectorStyle.class);
        return skin;
    }

    private BuildingSelectorStyle getStyle(UnitType type) {
        BuildingSelectorStyle style = new BuildingSelectorStyle();
        style.building = assets.getBuilding(type);
        style.allowed = assets.getAllowed(type);
        style.prohibited = assets.getProhibited(type);
        return style;
    }
}
