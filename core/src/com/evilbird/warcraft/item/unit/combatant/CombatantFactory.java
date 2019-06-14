/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.combatant.human.ElvenArcherCaptiveFactory;
import com.evilbird.warcraft.item.unit.combatant.human.ElvenArcherFactory;
import com.evilbird.warcraft.item.unit.combatant.human.ElvenDestroyerFactory;
import com.evilbird.warcraft.item.unit.combatant.human.FootmanFactory;
import com.evilbird.warcraft.item.unit.combatant.orc.GruntFactory;
import com.evilbird.warcraft.item.unit.combatant.orc.TrollAxeThrowerFactory;
import com.evilbird.warcraft.item.unit.combatant.orc.TrollDestroyerFactory;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link Combatant Combatants}, a
 * {@link Unit} specialization that can move and attack other Units.
 *
 * @author Blair Butterworth
 */
public class CombatantFactory extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public CombatantFactory(
        ElvenArcherFactory elvenArcherFactory,
        ElvenArcherCaptiveFactory elvenArcherCaptiveFactory,
        ElvenDestroyerFactory elvenDestroyerFactory,
        FootmanFactory footmanFactory,
        GruntFactory gruntFactory,
        TrollAxeThrowerFactory trollAxeThrowerFactory,
        TrollDestroyerFactory trollDestroyerFactory)
    {
        addProvider(UnitType.ElvenArcher, elvenArcherFactory);
        addProvider(UnitType.ElvenArcherCaptive, elvenArcherCaptiveFactory);
        addProvider(UnitType.ElvenDestroyer,  elvenDestroyerFactory);
        addProvider(UnitType.Footman, footmanFactory);
        addProvider(UnitType.Grunt, gruntFactory);
        addProvider(UnitType.TrollAxethrower, trollAxeThrowerFactory);
        addProvider(UnitType.TrollDestroyer, trollDestroyerFactory);
    }
}
