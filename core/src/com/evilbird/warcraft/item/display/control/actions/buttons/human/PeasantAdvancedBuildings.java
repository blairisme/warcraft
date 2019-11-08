/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.display.control.actions.buttons.human;

import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.display.control.actions.buttons.BasicButtonController;

import java.util.Collections;
import java.util.List;

import static com.evilbird.warcraft.item.common.query.UnitOperations.hasUnit;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.BuildCancelButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.ChurchButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.FoundryButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.GnomishInventorButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.GryphonAviaryButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.MageTowerButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.RefineryButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.ShipyardButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.StablesButton;
import static com.evilbird.warcraft.item.unit.UnitType.Castle;
import static com.evilbird.warcraft.item.unit.UnitType.Church;
import static com.evilbird.warcraft.item.unit.UnitType.Foundry;
import static com.evilbird.warcraft.item.unit.UnitType.GnomishInventor;
import static com.evilbird.warcraft.item.unit.UnitType.GryphonAviary;
import static com.evilbird.warcraft.item.unit.UnitType.Keep;
import static com.evilbird.warcraft.item.unit.UnitType.LumberMill;
import static com.evilbird.warcraft.item.unit.UnitType.MageTower;
import static com.evilbird.warcraft.item.unit.UnitType.Refinery;
import static com.evilbird.warcraft.item.unit.UnitType.Shipyard;
import static com.evilbird.warcraft.item.unit.UnitType.Stables;
import static java.util.Arrays.asList;

/**
 * Controls the buttons shown when a Peasant is selected and the user navigates
 * to the advanced building menu.
 *
 * @author Blair Butterworth
 */
public class PeasantAdvancedBuildings extends BasicButtonController
{
    private static final List<ActionButtonType> BASIC_BUTTONS =
        asList(ShipyardButton, BuildCancelButton);

    private static final List<ActionButtonType> ADVANCED_SHIP_BUTTONS =
        asList(ShipyardButton, FoundryButton, RefineryButton, BuildCancelButton);

    private static final List<ActionButtonType> ADVANCED_MELEE_BUTTONS =
        asList(ShipyardButton, FoundryButton, RefineryButton,
                GnomishInventorButton, StablesButton, BuildCancelButton);

    private static final List<ActionButtonType> SPELL_CASTER_BUTTONS =
            asList(ShipyardButton, FoundryButton, RefineryButton,
                    GnomishInventorButton, StablesButton, ChurchButton,
                    GryphonAviaryButton, MageTowerButton, BuildCancelButton);

    @Override
    public List<ActionButtonType> getButtons(Item item) {
        Player player = UnitOperations.getPlayer(item);
        return getButtons(player.getLevel());
    }

    private List<ActionButtonType> getButtons(int level) {
        if (level == 3) {
            return BASIC_BUTTONS;
        }
        if (level == 4) {
            return ADVANCED_SHIP_BUTTONS;
        }
        if (level > 4 && level <= 9) {
            return ADVANCED_MELEE_BUTTONS;
        }
        if (level > 9) {
            return SPELL_CASTER_BUTTONS;
        }
        return Collections.emptyList();
    }

    @Override
    public boolean getEnabled(ActionButtonType button, Item item) {
        Player player = UnitOperations.getPlayer(item);
        switch (button) {
            case BuildCancelButton: return true;
            case ShipyardButton: return hasResources(player, Shipyard) && hasUnit(player, LumberMill);
            case FoundryButton: return hasResources(player, Foundry) && hasUnit(player, Shipyard);
            case RefineryButton: return hasResources(player, Refinery) && hasUnit(player, Shipyard);
            case StablesButton: return hasResources(player, Stables) && hasUnit(player, Keep);
            case GnomishInventorButton: return hasResources(player, GnomishInventor) && hasUnit(player, Keep);
            case ChurchButton: return hasResources(player, Church) && hasUnit(player, Castle);
            case GryphonAviaryButton: return hasResources(player, GryphonAviary) && hasUnit(player, Castle);
            case MageTowerButton: return hasResources(player, MageTower) && hasUnit(player, Castle);
            default: return false;
        }
    }
}
