/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.query;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.movement.Movable;
import com.evilbird.warcraft.item.unit.Unit;

import java.util.function.Supplier;

/**
 * Instances of this class define commonly used {@link Supplier Suppliers}
 * that operate on {@link Unit Units}.
 *
 * @author Blair Butterworth
 */
public class UnitSuppliers
{
    private UnitSuppliers() {
    }

    public static Supplier<Item> closest(Movable source, Identifier type, Item locus) {
        return () -> UnitOperations.findClosest(source, type, locus);
    }

    public static Supplier<Item> closest(Movable source, Identifier type) {
        return () -> UnitOperations.findClosest(source, type);
    }
}
