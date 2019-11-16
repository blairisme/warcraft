/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.control.actions.buttons.human;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.object.display.control.actions.buttons.BasicButtonController;

import java.util.ArrayList;
import java.util.List;

import static com.evilbird.warcraft.object.common.upgrade.Upgrade.BlizzardUpgrade;
import static com.evilbird.warcraft.object.common.upgrade.Upgrade.FlameShieldUpgrade;
import static com.evilbird.warcraft.object.common.upgrade.Upgrade.InvisibilityUpgrade;
import static com.evilbird.warcraft.object.common.upgrade.Upgrade.PolymorphUpgrade;
import static com.evilbird.warcraft.object.common.upgrade.Upgrade.SlowUpgrade;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.BlizzardUpgradeButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.FlameShieldUpgradeButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.InvisibilityUpgradeButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.MageButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.PolymorphUpgradeButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.SlowUpgradeButton;
import static com.evilbird.warcraft.object.unit.UnitType.Mage;
import static java.util.Collections.singletonList;

/**
 * Controls the buttons shown when a Human Mage Tower is selected.
 *
 * @author Blair Butterworth
 */
public class MageTowerButtons extends BasicButtonController
{
    private static final List<ActionButtonType> basicButtons = singletonList(MageButton);

    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        List<ActionButtonType> buttons = new ArrayList<>(basicButtons);
        addUpgradeButton(player, buttons, SlowUpgradeButton, SlowUpgrade);
        addUpgradeButton(player, buttons, FlameShieldUpgradeButton, FlameShieldUpgrade);
        addUpgradeButton(player, buttons, InvisibilityUpgradeButton, InvisibilityUpgrade);
        addUpgradeButton(player, buttons, PolymorphUpgradeButton, PolymorphUpgrade);
        addUpgradeButton(player, buttons, BlizzardUpgradeButton, BlizzardUpgrade);
        return buttons;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        switch (button) {
            case MageButton: return hasResources(player, Mage);
            case SlowUpgradeButton: return hasResources(player, SlowUpgrade);
            case FlameShieldUpgradeButton: return hasResources(player, FlameShieldUpgrade);
            case InvisibilityUpgradeButton: return hasResources(player, InvisibilityUpgrade);
            case PolymorphUpgradeButton: return hasResources(player, PolymorphUpgrade);
            case BlizzardUpgradeButton: return hasResources(player, BlizzardUpgrade);
            default: return false;
        }
    }
}
