/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.gatherer;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.warcraft.object.common.capability.TerrainType;
import com.evilbird.warcraft.object.unit.UnitStyle;

import static com.evilbird.warcraft.object.common.capability.TerrainType.Land;

/**
 * A {@link Gatherer} specialization that gathers resources on land.
 *
 * @author Blair Butterworth
 */
public class LandGatherer extends Gatherer
{
    /**
     * Constructs a new instance of this class given a {@link Skin} describing
     * its visual and auditory presentation.
     *
     * @param skin  a {@link Skin} instance containing, amongst others, a
     *              {@link UnitStyle}.
     */
    public LandGatherer(Skin skin) {
        super(skin);
        super.setMovementCapability(Land);
    }

    /**
     * Returns the {@link TerrainType movement capability} of the
     * {@code LandGatherer}, those types of terrain the gatherer can
     * traverse across.
     */
    @Override
    public TerrainType getMovementCapability() {
        return Land;
    }

    /**
     * Unsupported, as {@code SeaGatherers} can only traverse across water
     * terrain.
     */
    @Override
    public void setMovementCapability(TerrainType capability) {
        throw new UnsupportedOperationException();
    }
}
