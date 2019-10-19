/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.actions;

import com.evilbird.warcraft.item.common.upgrade.Upgrade;
import com.evilbird.warcraft.item.unit.UnitType;
import org.junit.Assert;
import org.junit.Test;

import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.AdvancedNavalDamageButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildAltarOfStormsButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildCastleButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.RepairButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainBallistaButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainKnightButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainOgreButton;

/**
 * Instances of this unit test validate the {@link ActionButtonType} class.
 *
 * @author Blair Butterworth
 */
public class ActionButtonTypeTest
{
    @Test
    public void isBuildButtonTest() {
        Assert.assertTrue(BuildAltarOfStormsButton.isBuildButton());
        Assert.assertFalse(TrainBallistaButton.isBuildButton());
    }

    @Test
    public void isTrainButtonTest() {
        Assert.assertTrue(TrainOgreButton.isTrainButton());
        Assert.assertFalse(RepairButton.isTrainButton());
    }

    @Test
    public void getBuildProductTest() {
        UnitType expected = UnitType.Castle;
        UnitType actual = BuildCastleButton.getBuildProduct();
        Assert.assertEquals(expected, actual);

        for (ActionButtonType button: ActionButtonType.values()) {
            if (button.isBuildButton()) {
                UnitType type = button.getBuildProduct();
                Assert.assertNotNull(type);
            }
        }
    }

    @Test
    public void getTrainProductTest() {
        UnitType expected = UnitType.Knight;
        UnitType actual = TrainKnightButton.getTrainProduct();
        Assert.assertEquals(expected, actual);

        for (ActionButtonType button: ActionButtonType.values()) {
            if (button.isTrainButton()) {
                UnitType type = button.getTrainProduct();
                Assert.assertNotNull(type);
            }
        }
    }

    @Test
    public void getUpgradeProductTest() {
        Upgrade expected = Upgrade.NavalDamage2;
        Upgrade actual = AdvancedNavalDamageButton.getUpgradeProduct();
        Assert.assertEquals(expected, actual);

        for (ActionButtonType button: ActionButtonType.values()) {
            if (button.isUpgradeButton()) {
                Upgrade type = button.getUpgradeProduct();
                Assert.assertNotNull(type);
            }
        }
    }
}