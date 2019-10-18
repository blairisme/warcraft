/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.actions.buttons.common;

import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.BasicButtonController;

import java.util.ArrayList;
import java.util.List;

import static com.evilbird.warcraft.item.common.upgrade.Upgrade.MeleeDamage1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.MeleeDamage2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.MeleeDefence1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.MeleeDefence2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.SiegeDamage1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.SiegeDamage2;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.AdvancedMeleeDamageButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.AdvancedMeleeDefenceButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.AdvancedSiegeDamageButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.ImprovedMeleeDamageButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.ImprovedMeleeDefenceButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.ImprovedSiegeDamageButton;

/**
 * Controls the buttons shown when a Human Blacksmith is selected.
 *
 * @author Blair Butterworth
 */
public class BlacksmithButtons extends BasicButtonController
{
    @Override
    public List<ActionButtonType> getButtons(Item item) {
        Player player = UnitOperations.getPlayer(item);
        return player.getLevel() <= 5 ? getBasicUpgrades(player) : getAdvancedUpgrades(player);
    }

    private List<ActionButtonType> getBasicUpgrades(Player player) {
        List<ActionButtonType> buttons = new ArrayList<>();
        addUpgradeButton(player, buttons, ImprovedMeleeDamageButton, MeleeDamage1);
        addUpgradeButton(player, buttons, ImprovedMeleeDefenceButton, MeleeDefence1);
        addUpgradeButton(player, buttons, ImprovedSiegeDamageButton, SiegeDamage1);
        return buttons;
    }

    private List<ActionButtonType> getAdvancedUpgrades(Player player) {
        List<ActionButtonType> buttons = getBasicUpgrades(player);
        addUpgradeButton(player, buttons, AdvancedMeleeDamageButton, MeleeDamage1, MeleeDamage2);
        addUpgradeButton(player, buttons, AdvancedMeleeDefenceButton, MeleeDefence1, MeleeDefence2);
        addUpgradeButton(player, buttons, AdvancedSiegeDamageButton, SiegeDamage1, SiegeDamage2);
        return buttons;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, Item item) {
        Player player = UnitOperations.getPlayer(item);
        switch (button) {
            case ImprovedMeleeDamageButton: return hasResources(player, MeleeDamage1);
            case AdvancedMeleeDamageButton: return hasResources(player, MeleeDamage2);
            case ImprovedMeleeDefenceButton: return hasResources(player, MeleeDefence1);
            case AdvancedMeleeDefenceButton: return hasResources(player, MeleeDefence2);
            case ImprovedSiegeDamageButton: return hasResources(player, SiegeDamage1);
            case AdvancedSiegeDamageButton: return hasResources(player, SiegeDamage2);
            default: return false;
        }
    }
}
