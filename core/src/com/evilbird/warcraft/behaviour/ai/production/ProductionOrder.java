/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.production;

import com.evilbird.warcraft.data.product.Product;
import com.evilbird.warcraft.object.data.player.Player;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.List;

import static com.evilbird.warcraft.object.unit.UnitType.Barracks;
import static com.evilbird.warcraft.object.unit.UnitType.Encampment;
import static com.evilbird.warcraft.object.unit.UnitType.Farm;
import static com.evilbird.warcraft.object.unit.UnitType.GreatHall;
import static com.evilbird.warcraft.object.unit.UnitType.Peon;
import static com.evilbird.warcraft.object.unit.UnitType.PigFarm;
import static com.evilbird.warcraft.object.unit.UnitType.TownHall;
import static com.evilbird.warcraft.object.unit.UnitType.TrollLumberMill;

/**
 * Specifies the order in which a players buildings should be constructed.
 *
 * @author Blair Butterworth
 */
//TODO: Add support for simplified campaign production orders
public class ProductionOrder
{
    /**
     * Specifies the production order for human players.
     */
    public static ProductionOrder HUMAN = new ProductionOrder(
        Pair.of(TownHall, 1),
        Pair.of(Farm, 1),
        Pair.of(Barracks, 1));

    /**
     * Specifies the production order for orc players.
     */
    public static ProductionOrder ORC = new ProductionOrder(
        Pair.of(GreatHall, 1),
        Pair.of(PigFarm, 1),
        Pair.of(Peon, 2),
        Pair.of(Encampment, 1),
        Pair.of(TrollLumberMill, 1));

    /**
     * Returns the production order appropriate for the given player.
     */
    public static ProductionOrder forPlayer(Player player) {
        switch (player.getFaction()) {
            case Orc: return ORC;
            case Human: return HUMAN;
            default: throw new UnsupportedOperationException();
        }
    }

    private List<Pair<Product, Integer>> order;

    @SafeVarargs
    private ProductionOrder(Pair<Product, Integer> ... entries) {
        this.order = Arrays.asList(entries);
    }

    /**
     * Returns the next product that should be produced.
     */
    public Product getNextProduct(ProductionManifest manifest) {
        for (Pair<Product, Integer> entry: order) {
            if (! manifest.hasAtLeast(entry.getKey(), entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
