/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.control.actions;

import com.evilbird.warcraft.data.upgrade.Upgrade;
import com.evilbird.warcraft.object.display.components.actions.ActionButtonType;
import com.evilbird.warcraft.object.unit.UnitType;
import org.junit.Assert;
import org.junit.Test;

import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.AltarOfStormsButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.BallistaButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.CastleButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.KnightButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.NavalDamage2Button;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.OgreButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.RepairButton;

/**
 * Instances of this unit test validate the {@link com.evilbird.warcraft.object.display.components.actions.ActionButtonType} class.
 *
 * @author Blair Butterworth
 */
public class ActionButtonTypeTest
{
    @Test
    public void isBuildButtonTest() {
        Assert.assertTrue(AltarOfStormsButton.isBuildButton());
        Assert.assertFalse(BallistaButton.isBuildButton());
    }

    @Test
    public void isTrainButtonTest() {
        Assert.assertTrue(OgreButton.isTrainButton());
        Assert.assertFalse(RepairButton.isTrainButton());
    }

    @Test
    public void getBuildProductTest() {
        UnitType expected = UnitType.Castle;
        UnitType actual = CastleButton.getBuildProduct();
        Assert.assertEquals(expected, actual);

        for (com.evilbird.warcraft.object.display.components.actions.ActionButtonType button: com.evilbird.warcraft.object.display.components.actions.ActionButtonType.values()) {
            if (button.isBuildButton()) {
                UnitType type = button.getBuildProduct();
                Assert.assertNotNull(type);
            }
        }
    }

    @Test
    public void getTrainProductTest() {
        UnitType expected = UnitType.Knight;
        UnitType actual = KnightButton.getTrainProduct();
        Assert.assertEquals(expected, actual);

        for (com.evilbird.warcraft.object.display.components.actions.ActionButtonType button: com.evilbird.warcraft.object.display.components.actions.ActionButtonType.values()) {
            if (button.isTrainButton()) {
                UnitType type = button.getTrainProduct();
                Assert.assertNotNull(type);
            }
        }
    }

    @Test
    public void getUpgradeProductTest() {
        Upgrade expected = Upgrade.NavalDamage2;
        Upgrade actual = NavalDamage2Button.getUpgradeProduct();
        Assert.assertEquals(expected, actual);

        for (com.evilbird.warcraft.object.display.components.actions.ActionButtonType button: ActionButtonType.values()) {
            if (button.isUpgradeButton()) {
                Upgrade type = button.getUpgradeProduct();
                Assert.assertNotNull(type);
            }
        }
    }
}