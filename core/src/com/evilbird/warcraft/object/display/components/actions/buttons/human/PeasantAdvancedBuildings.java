/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.components.actions.buttons.human;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.display.components.actions.ActionButtonType;
import com.evilbird.warcraft.object.display.components.actions.buttons.BasicButtonController;

import java.util.Collections;
import java.util.List;

import static com.evilbird.warcraft.object.common.query.UnitOperations.hasUnit;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.BuildCancelButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.ChurchButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.FoundryButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.GnomishInventorButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.GryphonAviaryButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.MageTowerButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.RefineryButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.ShipyardButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.StablesButton;
import static com.evilbird.warcraft.object.unit.UnitType.Castle;
import static com.evilbird.warcraft.object.unit.UnitType.Church;
import static com.evilbird.warcraft.object.unit.UnitType.Foundry;
import static com.evilbird.warcraft.object.unit.UnitType.GnomishInventor;
import static com.evilbird.warcraft.object.unit.UnitType.GryphonAviary;
import static com.evilbird.warcraft.object.unit.UnitType.Keep;
import static com.evilbird.warcraft.object.unit.UnitType.LumberMill;
import static com.evilbird.warcraft.object.unit.UnitType.MageTower;
import static com.evilbird.warcraft.object.unit.UnitType.Refinery;
import static com.evilbird.warcraft.object.unit.UnitType.Shipyard;
import static com.evilbird.warcraft.object.unit.UnitType.Stables;
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
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
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
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
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
