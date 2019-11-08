/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.display.control.actions.buttons.human;

import com.evilbird.engine.common.collection.Lists;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.display.control.actions.buttons.BasicButtonController;

import java.util.ArrayList;
import java.util.List;

import static com.evilbird.warcraft.item.common.query.UnitOperations.hasUnit;
import static com.evilbird.warcraft.item.common.query.UnitOperations.hasUnits;
import static com.evilbird.warcraft.item.common.query.UnitOperations.hasUpgrade;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.MeleeType1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.RangedType1;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.BallistaButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.ElvenArcherButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.ElvenRangerButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.FootmanButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.KnightButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.PaladinButton;
import static com.evilbird.warcraft.item.unit.UnitType.Ballista;
import static com.evilbird.warcraft.item.unit.UnitType.Blacksmith;
import static com.evilbird.warcraft.item.unit.UnitType.ElvenArcher;
import static com.evilbird.warcraft.item.unit.UnitType.ElvenRanger;
import static com.evilbird.warcraft.item.unit.UnitType.Footman;
import static com.evilbird.warcraft.item.unit.UnitType.Knight;
import static com.evilbird.warcraft.item.unit.UnitType.LumberMill;
import static com.evilbird.warcraft.item.unit.UnitType.Paladin;
import static com.evilbird.warcraft.item.unit.UnitType.Stables;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/**
 * Controls the buttons shown when a Human Barracks is selected.
 *
 * @author Blair Butterworth
 */
public class BarracksButtons extends BasicButtonController
{
    private static final List<ActionButtonType> BASIC_BUTTONS =
        singletonList(FootmanButton);

    private static final List<ActionButtonType> IMPROVED_BUTTONS =
        asList(FootmanButton, ElvenArcherButton);

    private static final List<ActionButtonType> ADVANCED_BUTTONS =
        asList(FootmanButton, ElvenArcherButton, BallistaButton);

    private static final List<ActionButtonType> ALL_BUTTONS =
        asList(FootmanButton, ElvenArcherButton, BallistaButton, KnightButton);

    @Override
    public List<ActionButtonType> getButtons(Item item) {
        Player player = UnitOperations.getPlayer(item);
        List<ActionButtonType> buttons = new ArrayList<>(getButtons(player.getLevel()));

        if (hasUpgrade(player, RangedType1)) {
            Lists.replace(buttons, ElvenArcherButton, ElvenRangerButton);
        }
        if (hasUpgrade(player, MeleeType1)) {
            Lists.replace(buttons, KnightButton, PaladinButton);
        }
        return buttons;
    }

    public List<ActionButtonType> getButtons(int level) {
        if (level <= 2) {
            return BASIC_BUTTONS;
        }
        else if (level <= 4) {
            return IMPROVED_BUTTONS;
        }
        else if (level <= 5) {
            return ADVANCED_BUTTONS;
        }
        return ALL_BUTTONS;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, Item item) {
        Player player = UnitOperations.getPlayer(item);
        switch (button) {
            case FootmanButton: return hasResources(player, Footman);
            case ElvenArcherButton: return hasResources(player, ElvenArcher) && hasUnit(player, LumberMill);
            case ElvenRangerButton: return hasResources(player, ElvenRanger) && hasUnit(player, LumberMill);
            case KnightButton: return hasResources(player, Knight) && hasUnits(player, LumberMill, Stables);
            case PaladinButton: return hasResources(player, Paladin) && hasUnits(player, LumberMill, Stables);
            case BallistaButton: return hasResources(player, Ballista) && hasUnits(player, LumberMill, Blacksmith);
            default: return false;
        }
    }
}
