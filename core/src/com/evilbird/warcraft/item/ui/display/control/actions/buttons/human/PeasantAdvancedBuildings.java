/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.actions.buttons.human;

import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.BasicButtonController;

import java.util.Collections;
import java.util.List;

import static com.evilbird.warcraft.item.common.query.UnitOperations.hasUnit;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildCancelButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildChurchButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildFoundryButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildGnomishInventorButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildGryphonAviaryButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildMageTowerButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildRefineryButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildShipyardButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildStablesButton;
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
        asList(BuildShipyardButton, BuildCancelButton);

    private static final List<ActionButtonType> ADVANCED_SHIP_BUTTONS =
        asList(BuildShipyardButton, BuildFoundryButton, BuildRefineryButton, BuildCancelButton);

    private static final List<ActionButtonType> ADVANCED_MELEE_BUTTONS =
        asList(BuildShipyardButton, BuildFoundryButton, BuildRefineryButton,
               BuildGnomishInventorButton, BuildStablesButton, BuildCancelButton);

    private static final List<ActionButtonType> SPELL_CASTER_BUTTONS =
            asList(BuildShipyardButton, BuildFoundryButton, BuildRefineryButton,
                    BuildGnomishInventorButton, BuildStablesButton, BuildChurchButton,
                    BuildGryphonAviaryButton, BuildMageTowerButton, BuildCancelButton);

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
            case BuildShipyardButton: return hasResources(player, Shipyard) && hasUnit(player, LumberMill);
            case BuildFoundryButton: return hasResources(player, Foundry) && hasUnit(player, Shipyard);
            case BuildRefineryButton: return hasResources(player, Refinery) && hasUnit(player, Shipyard);
            case BuildStablesButton: return hasResources(player, Stables) && hasUnit(player, Keep);
            case BuildGnomishInventorButton: return hasResources(player, GnomishInventor) && hasUnit(player, Keep);
            case BuildChurchButton: return hasResources(player, Church) && hasUnit(player, Castle);
            case BuildGryphonAviaryButton: return hasResources(player, GryphonAviary) && hasUnit(player, Castle);
            case BuildMageTowerButton: return hasResources(player, MageTower) && hasUnit(player, Castle);
            default: return false;
        }
    }
}
