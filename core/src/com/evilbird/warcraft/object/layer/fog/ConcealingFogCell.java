/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer.fog;

import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.warcraft.object.layer.LayerGroupStyle;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A {@link FogCell} specialization that stores the identifiers of the units
 * occupying the same location as the cell.
 *
 * @author Blair Butterworth
 */
public class ConcealingFogCell extends FogCell
{
    private Collection<Identifier> occupants;

    public ConcealingFogCell(ConcealingFog parent, LayerGroupStyle style, GridPoint2 location) {
        this(parent, style, location, DEFAULT_VALUE);
    }

    public ConcealingFogCell(ConcealingFog parent, LayerGroupStyle style, GridPoint2 location, float value) {
        super(parent, style, location, value);
        this.occupants = new ArrayList<>(2);
    }

    public void addOccupant(Identifier occupant) {
        this.occupants.add(occupant);
    }

    public void addOccupants(Collection<Identifier> occupants) {
        this.occupants.addAll(occupants);
    }

    public void removeOccupant(Identifier occupant) {
        this.occupants.remove(occupant);
    }

    public Collection<Identifier> getOccupants() {
        return this.occupants;
    }

    public boolean isUnoccupied() {
        return this.occupants.isEmpty();
    }
}
