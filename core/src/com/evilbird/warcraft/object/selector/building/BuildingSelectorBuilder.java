/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
