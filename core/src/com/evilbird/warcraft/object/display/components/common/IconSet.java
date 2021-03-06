/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.components.common;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.graphics.DrawableUtils;
import com.evilbird.warcraft.common.WarcraftFaction;
import com.evilbird.warcraft.data.upgrade.Upgrade;
import com.evilbird.warcraft.object.display.components.actions.ActionButtonType;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitAttack;
import com.evilbird.warcraft.object.unit.UnitType;

import java.util.Objects;

/**
 * Represents an icon file, a formatted texture containing a variety of icons
 * Icons can be obtained by {@link ActionButtonType}, {@link UnitType} or
 * {@link Upgrade}. Some icons come in faction specific variants, attack
 * capability specific variants and upgraded variants.
 *
 * @author Blair Butterworth
 */
public class IconSet
{
    private static final ButtonIconLayout buttonIconLayout = new ButtonIconLayout();
    private static final UpgradeIconLayout upgradeIconLayout = new UpgradeIconLayout();
    private static final UnitIconLayout unitIconLayout = new UnitIconLayout();

    private Texture texture;

    /**
     * Constructs a new instances of this class given a {@link Texture}
     * containing the icons that can be obtained from the IconSet.
     *
     * @param texture   a {@code Texture} instance. This parameter cannot be
     *                  {@code null}.
     */
    public IconSet(Texture texture) {
        Objects.requireNonNull(texture);
        this.texture = texture;
    }

    /**
     * Obtains the icon that represents the given {@link ActionButtonType}.
     *
     * @param button  the button type to whose icon is required.
     * @return      a {@link Drawable} containing the required icon.
     */
    public Drawable get(ActionButtonType button) {
        Objects.requireNonNull(button);

        GridPoint2 size = buttonIconLayout.getSize(button);
        GridPoint2 location = buttonIconLayout.getLocation(button);

        return location != null ? DrawableUtils.getDrawable(texture, location, size) : null;
    }

    /**
     * Obtains the icon that represents the given {@link ActionButtonType},
     * customized for the given {@link Unit}.
     *
     * @param button    the button type to whose icon is required.
     * @param unit      the unit for which the icon will be shown.
     * @return          a {@link Drawable} containing the required icon.
     */
    public Drawable get(ActionButtonType button, Unit unit) {
        Objects.requireNonNull(button);
        Objects.requireNonNull(unit);

        UnitType type = (UnitType)unit.getType();
        WarcraftFaction faction = type.getFaction();
        UnitAttack attack = type.getAttack();

        GridPoint2 size = buttonIconLayout.getSize(button, faction, attack);
        GridPoint2 location = buttonIconLayout.getLocation(button, faction, attack);

        return location != null ? DrawableUtils.getDrawable(texture, location, size) : null;
    }

    /**
     * Obtains the icon that represents the given {@link UnitType}.
     *
     * @param type  the unit type to whose icon is required.
     * @return      a {@link Drawable} containing the required icon.
     */
    public Drawable get(UnitType type) {
        Objects.requireNonNull(type);

        GridPoint2 size = unitIconLayout.getSize(type);
        GridPoint2 location = unitIconLayout.getLocation(type);

        return location != null ? DrawableUtils.getDrawable(texture, location, size) : null;
    }

    /**
     * Obtains the icon that represents the given {@link Upgrade}.
     *
     * @param upgrade   the upgrade whose icon is required.
     * @param unit      the unit producing the upgrade.
     * @return          a {@link Drawable} containing the required icon.
     */
    public Drawable get(Upgrade upgrade, Unit unit) {
        UnitType type = (UnitType)unit.getType();
        WarcraftFaction faction = type.getFaction();
        UnitAttack attack = type.getAttack();

        GridPoint2 size = upgradeIconLayout.getSize(upgrade, faction, attack);
        GridPoint2 location = upgradeIconLayout.getLocation(upgrade, faction, attack);

        return location != null ? DrawableUtils.getDrawable(texture, location, size) : null;
    }
}