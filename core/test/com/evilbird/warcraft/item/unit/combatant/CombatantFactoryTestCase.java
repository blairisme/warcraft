/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.test.testcase.GameFactoryTestCase;
import com.evilbird.warcraft.common.WarcraftContext;
import com.evilbird.warcraft.common.WarcraftFaction;
import com.evilbird.warcraft.item.unit.UnitType;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static com.evilbird.warcraft.common.WarcraftAssetSet.Summer;
import static com.evilbird.warcraft.common.WarcraftAssetSet.Swamp;
import static com.evilbird.warcraft.common.WarcraftAssetSet.Winter;

/**
 * Common reusable test case for validate logic in building factories.
 *
 * @author Blair Butterworth
 */
public abstract class CombatantFactoryTestCase <T extends CombatantFactoryBase> extends GameFactoryTestCase<T>
{
    protected abstract UnitType getBuildType();

    @Override
    protected Collection<Identifier> getLoadContexts() {
        UnitType type = getBuildType();
        WarcraftFaction faction = type.getFaction();
        return Arrays.asList(
                new WarcraftContext(faction, Summer),
                new WarcraftContext(faction, Swamp),
                new WarcraftContext(faction, Winter));
    }

    @Override
    protected Collection<Identifier> getValueTypes() {
        UnitType type = getBuildType();
        return Collections.singleton(type);
    }
}