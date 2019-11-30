/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.naval;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectReference;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitStyle;

import java.util.ArrayList;
import java.util.List;

public class Transport extends Ship
{
    private List<GameObjectReference> passengers;

    /**
     * Constructs a new instance of this class given a {@link Skin} describing
     * its visual and auditory presentation.
     *
     * @param skin a {@link Skin} instance containing, amongst others, a
     *             {@link UnitStyle}.
     */
    public Transport(Skin skin) {
        super(skin);
        passengers = new ArrayList<>();
    }

    public void addPassenger(Unit passenger) {
        passengers.add(new GameObjectReference(passenger));
    }

    public boolean hasPassenger(Unit passenger) {
        for (GameObjectReference reference: passengers) {
            if (reference.get() == passenger) {
                return true;
            }
        }
        return false;
    }

    public boolean hasPassengers() {

    }

    public List<Unit> getPassengers() {

    }

    public void removePassenger(Unit passenger) {

    }
}
