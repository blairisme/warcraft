/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.combatant.CombatantAssets;
import com.evilbird.warcraft.item.unit.combatant.CombatantBuilder;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.unit.UnitType.TrollAxethrower;
import static com.evilbird.warcraft.item.unit.UnitType.Zuljin;

/**
 * Instances of this factory create the Troll Axethrower hero Zuljin.
 *
 * @author Blair Butterworth
 */
public class ZuljinFactory extends TrollAxethrowerFactory
{
    private static final GridPoint2 ICON = new GridPoint2(0, 266);
    private static final GridPoint2 SIZE = new GridPoint2(32, 32);

    protected CombatantAssets assets;
    protected CombatantBuilder builder;

    @Inject
    public ZuljinFactory(Device device) {
        this(device.getAssetStorage());
    }

    public ZuljinFactory(AssetManager manager) {
        super(manager);
        this.assets = new CombatantAssets(manager, TrollAxethrower, ICON, SIZE);
        this.builder = new CombatantBuilder(assets);
    }

    @Override
    public Item get() {
        Combatant result = (Combatant)super.get();
        result.setType(Zuljin);
        result.setIdentifier(objectIdentifier("Zuljin", result));
        result.setName("Zuljin");
        result.setType(Zuljin);
        return result;
    }
}



