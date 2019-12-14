/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.layer.wall;

import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.object.spatial.SpatialObject;
import com.evilbird.warcraft.object.common.capability.PerishableObject;
import com.evilbird.warcraft.object.common.capability.TerrainType;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.layer.LayerCell;
import com.evilbird.warcraft.object.layer.LayerType;

/**
 * Represents a section of a wall, typically spanning one standard tile.
 *
 * @author Blair Butterworth
 */
public class WallSection extends LayerCell implements PerishableObject, SpatialObject
{
    private static final transient float DEFAULT_HEALTH = 100;

    public WallSection(GridPoint2 location) {
        this(location, DEFAULT_HEALTH);
    }

    public WallSection(GridPoint2 location, float value) {
        super(location, value);
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
    public void setEmpty() {
        super.setEmpty();
        setType(LayerType.Map);
    }
}
