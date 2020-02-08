/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.construct;

import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.UnitType;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.List;

import static com.evilbird.warcraft.object.unit.UnitType.Barracks;
import static com.evilbird.warcraft.object.unit.UnitType.Encampment;
import static com.evilbird.warcraft.object.unit.UnitType.Farm;
import static com.evilbird.warcraft.object.unit.UnitType.Forge;
import static com.evilbird.warcraft.object.unit.UnitType.GreatHall;
import static com.evilbird.warcraft.object.unit.UnitType.PigFarm;
import static com.evilbird.warcraft.object.unit.UnitType.TownHall;

/**
 * Specifies the order in which a players buildings should be constructed.
 *
 * @author Blair Butterworth
 */
public class ConstructOrder
{
    /**
     * Specifies the construction order for human players.
     */
    public static ConstructOrder HUMAN = new ConstructOrder(
        Pair.of(TownHall, 1),
        Pair.of(Farm, 1),
        Pair.of(Barracks, 1));

    /**
     * Specifies the construction order for orc players.
     */
    public static ConstructOrder ORC = new ConstructOrder(
        Pair.of(GreatHall, 1),
        Pair.of(PigFarm, 1),
        Pair.of(Encampment, 1),
        Pair.of(Forge, 1));

    /**
     * Returns the construction order appropriate for the given player.
     */
    public static ConstructOrder forPlayer(Player player) {
        switch (player.getFaction()) {
            case Orc: return ORC;
            case Human: return HUMAN;
            default: throw new UnsupportedOperationException();
        }
    }

    private List<Pair<UnitType, Integer>> order;

    @SafeVarargs
    private ConstructOrder(Pair<UnitType, Integer> ... entries) {
        this.order = Arrays.asList(entries);
    }

    /**
     * Returns the next building in the
     */
    public UnitType getNextBuilding(ConstructManifest manifest) {
        for (Pair<UnitType, Integer> entry: order) {
            if (! manifest.hasAtLeast(entry.getKey(), entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
