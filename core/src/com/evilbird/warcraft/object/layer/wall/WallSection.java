/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer.wall;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.object.spatial.SpatialObject;
import com.evilbird.warcraft.object.common.capability.PerishableObject;
import com.evilbird.warcraft.object.common.capability.TerrainType;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.layer.LayerGroupCell;
import com.evilbird.warcraft.object.layer.LayerGroupStyle;
import com.evilbird.warcraft.object.layer.LayerType;

/**
 * Represents a section of a wall, typically spanning one standard tile.
 *
 * @author Blair Butterworth
 */
public class WallSection extends LayerGroupCell implements PerishableObject, SpatialObject
{
    private static final transient float DEFAULT_HEALTH = 100;

    public WallSection(LayerGroupStyle style, GridPoint2 location) {
        this(style, location, DEFAULT_HEALTH);
    }

    public WallSection(LayerGroupStyle style, GridPoint2 location, float value) {
        super(style, location, value);
        setType(LayerType.WallSection);
    }

    /**
     * Returns how much damage the {@code WallSection} absorbs with each attack.
     */
    @Override
    public int getArmour() {
        return 0;
    }

    /**
     * Returns the health of the {@code WallSection}.
     */
    @Override
    public float getHealth() {
        return getValue();
    }

    /**
     * Returns the team the {@code WallSection} belongs to.
     */
    @Override
    public Player getTeam() {
        return null;
    }

    /**
     * Returns the type of terrain the {@code WallSection} resides in.
     */
    @Override
    public TerrainType getTerrainType() {
        return TerrainType.Land;
    }

    /**
     * Returns whether the {@code WallSection} is visible to potential
     * attackers.
     */
    @Override
    public boolean isAttackable() {
        return false;
    }

    /**
     * Sets the health of the {@code WallSection}.
     */
    @Override
    public void setHealth(float health) {
        setValue(health);
    }

    @Override
    public void showEmpty() {
        super.showEmpty();
        setType(LayerType.Map);
        setTouchable(Touchable.disabled);
    }

    @Override
    public void showFull() {
        setType(LayerType.WallSection);
        setTouchable(Touchable.enabled);
    }
}
