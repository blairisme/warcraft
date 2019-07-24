/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.common;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.graphics.TextureUtils;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.warcraft.common.WarcraftFaction;
import com.evilbird.warcraft.item.data.player.PlayerUpgrade;
import com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.UnitAttack;
import com.evilbird.warcraft.item.unit.UnitType;

import java.util.Objects;

/**
 * Represents an icon file, a formatted texture containing a variety of icons
 * Icons can be obtained by {@link ActionButtonType}, {@link UnitType} or
 * {@link PlayerUpgrade}. Some icons come in faction specific variants, attack
 * capability specific variants and upgraded variants.
 *
 * @author Blair Butterworth
 */
public class IconSet
{
    private Texture texture;
    private IconLayout layout;
    private IconSpecializations specializations;

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
        this.layout = new IconLayout();
        this.specializations = new IconSpecializations();
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
        UnitType type = (UnitType)unit.getType();
        WarcraftFaction faction = type.getFaction();
        UnitAttack attack = type.getAttack();
        IconLevel level = IconLevel.Basic;
        return getIcon(specializations.getSpecialization(button, faction, attack, level));
    }

    /**
     * Obtains the icon that represents the given {@link ActionButtonType}.
     *
     * @param type  the button type to whose icon is required.
     * @return      a {@link Drawable} containing the required icon.
     */
    public Drawable get(ActionButtonType type) {
        Objects.requireNonNull(type);
        return getIcon(type);
    }

    /**
     * Obtains the icon that represents the given {@link UnitType}.
     *
     * @param type  the unit type to whose icon is required.
     * @return      a {@link Drawable} containing the required icon.
     */
    public Drawable get(UnitType type) {
        Objects.requireNonNull(type);
        return getIcon(type);
    }

    /**
     * Obtains the icon that represents the given {@link PlayerUpgrade}.
     *
     * @param upgrade   the upgrade whose icon is required.
     * @return          a {@link Drawable} containing the required icon.
     */
    public Drawable get(PlayerUpgrade upgrade) {
        Objects.requireNonNull(upgrade);
        return getIcon(upgrade);
    }

    private Drawable getIcon(Identifier type) {
        GridPoint2 size = layout.getSize(type);
        GridPoint2 location = layout.getLocation(type);
        return location != null ? TextureUtils.getDrawable(texture, location, size) : null;
    }
}