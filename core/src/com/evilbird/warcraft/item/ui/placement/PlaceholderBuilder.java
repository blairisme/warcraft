/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.placement;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.warcraft.item.unit.UnitType;

/**
 * Creates new {@link Placeholder} objects, whose visual and audible
 * presentation are defined by the given {@link PlaceholderAssets}.
 *
 * @author Blair Butterworth
 */
public class PlaceholderBuilder
{
    private PlaceholderAssets assets;

    public PlaceholderBuilder(PlaceholderAssets assets) {
        this.assets = assets;
    }

    public Placeholder build(PlaceholderType type) {
        return build(type.getBuilding());
    }

    private Placeholder build(UnitType building) {
        Placeholder result = new Placeholder(getSkin(building));
        result.setSize(assets.getSize(building));
        result.setTouchable(Touchable.enabled);
        result.setVisible(true);
        return result;
    }

    private Skin getSkin(UnitType type) {
        Skin skin = new Skin();
        skin.add("default", getStyle(type), PlaceholderStyle.class);
        return skin;
    }

    private PlaceholderStyle getStyle(UnitType type) {
        PlaceholderStyle style = new PlaceholderStyle();
        style.building = assets.getBuilding(type);
        style.allowed = assets.getAllowed(type);
        style.prohibited = assets.getProhibited(type);
        return style;
    }
}
