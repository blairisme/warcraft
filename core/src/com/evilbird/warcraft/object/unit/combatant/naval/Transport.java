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
import com.evilbird.engine.object.GameObjectReference;
import com.evilbird.warcraft.object.common.capability.OffensiveCapability;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

import static com.evilbird.engine.common.collection.CollectionUtils.containsAny;
import static com.evilbird.engine.common.collection.CollectionUtils.convert;
import static com.evilbird.warcraft.object.common.capability.OffensiveCapability.None;

/**
 * A {@link Ship} specialization that transports land {@link Unit Units} across
 * water.
 *
 * @author Blair Butterworth
 */
public class Transport extends Ship
{
    private List<GameObjectReference<Unit>> passengers;

    /**
     * Constructs a new instance of this class given a {@link Skin} describing
     * its visual and auditory presentation.
     *
     * @param skin a {@link Skin} instance containing, amongst others, a
     *             {@link UnitStyle}.
     */
    public Transport(Skin skin) {
        super(skin);
        passengers = new ArrayList<>(10);
    }

    /**
     * Adds a {@link Unit passenger} to the transport.
     */
    public void addPassenger(Unit passenger) {
        passengers.add(new GameObjectReference<>(passenger));
    }

    /**
     * Returns the no offensive attack capability, as {@code Transports} cannot
     * attack.
     */
    @Override
    public OffensiveCapability getAttackCapability() {
        return None;
    }

    /**
     * Returns the passengers contained in the transport.
     */
    public List<Unit> getPassengers() {
        return convert(passengers, GameObjectReference::get);
    }

    /**
     * Determines if the {@link Unit} is a passenger of the transport.
     */
    public boolean hasPassenger(Unit passenger) {
       return containsAny(passengers, reference -> reference.contains(passenger));
    }

    /**
     * Determines if the transport currently contains any passengers or not.
     */
    public boolean hasPassengers() {
        return !passengers.isEmpty();
    }

    /**
     * Removes a {@link Unit passenger} from the transport.
     */
    public void removePassenger(Unit passenger) {
        passengers.remove(new GameObjectReference<>(passenger));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        Transport transport = (Transport)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(passengers, transport.passengers)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(passengers)
            .toHashCode();
    }
}
