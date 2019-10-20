/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.common;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.test.data.assets.TestTextures;
import com.evilbird.test.data.item.TestBuildings;
import com.evilbird.test.data.item.TestCombatants;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.warcraft.item.common.upgrade.Upgrade;
import com.evilbird.warcraft.item.ui.display.control.ControlPaneFactory;
import com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.UnitType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.StopButton;

/**
 * Instances of this unit test validate the {@link ControlPaneFactory} class.
 *
 * @author Blair Butterworth
 */
public class IconSetTest extends GameTestCase
{
    private IconSet iconSet;

    @Before
    public void setup() {
        Texture texture = TestTextures.newTestTexture();
        iconSet = new IconSet(texture);
    }

    @Test
    public void getActionButtonCustomTest() {
        Unit unit = TestCombatants.newTestCombatant(new TextIdentifier("ElvenArcher"), UnitType.ElvenArcher);
        Drawable actual = iconSet.get(ActionButtonType.AttackButton, unit);
        Assert.assertNotNull(actual);
    }

    @Test
    public void getActionButtonPartialCustomTest() {
        Unit unit = TestCombatants.newTestCombatant(new TextIdentifier("ElvenArcher"), UnitType.ElvenArcher);
        Drawable actual = iconSet.get(ActionButtonType.DepositButton, unit);
        Assert.assertNotNull(actual);
    }

    @Test
    public void getUnitTypeIconTest() {
        Drawable actual = iconSet.get(UnitType.Footman);
        Assert.assertNotNull(actual);
    }

    @Test
    public void getShipStopIconTest() {
        Unit ship = TestCombatants.newTestCombatant(new TextIdentifier("ship"), UnitType.TrollDestroyer);
        Drawable actual = iconSet.get(StopButton, ship);
        Assert.assertNotNull(actual);
    }

    @Test
    public void getUpgradeProductIconTest() {
        Unit lumberMill = TestBuildings.newTestBuilding(new TextIdentifier("LumberMill"), UnitType.LumberMill);
        Drawable actual = iconSet.get(ActionButtonType.RangedDamage1Button, lumberMill);
        Assert.assertNotNull(actual);
    }

    @Test
    public void getUpgradeIconTest() {
        Unit lumberMill = TestBuildings.newTestBuilding(new TextIdentifier("LumberMill"), UnitType.LumberMill);
        Drawable actual = iconSet.get(Upgrade.RangedDamage1, lumberMill);
        Assert.assertNotNull(actual);
    }

    @Test
    public void getSealIconTest() {
        Drawable actual = iconSet.get(UnitType.Seal);
        Assert.assertNotNull(actual);
    }

    @Test
    public void getUnitIconTest() {
        for (UnitType unitType: UnitType.values()) {
            Drawable icon = iconSet.get(unitType);
            Assert.assertNotNull(unitType.name(), icon);
        }
    }

    @Test
    public void getActionButtonIconTest() {
        Unit unit = TestCombatants.newTestCombatant(new TextIdentifier("ElvenArcher"), UnitType.ElvenArcher);
        for (ActionButtonType actionButtonType: ActionButtonType.values()) {
            Drawable icon = iconSet.get(actionButtonType, unit);
            Assert.assertNotNull(actionButtonType.name(), icon);
        }
    }
}