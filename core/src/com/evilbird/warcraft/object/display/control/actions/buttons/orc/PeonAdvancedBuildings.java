/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.control.actions.buttons.orc;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.object.display.control.actions.buttons.BasicButtonController;

import java.util.Collections;
import java.util.List;

import static com.evilbird.warcraft.object.common.query.UnitOperations.hasUnit;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.AltarOfStormsButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.BuildCancelButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.DockyardButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.DragonRoostButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.GoblinAlchemistButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.MetalworksButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.OgreMoundButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.OilRefineryButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.TempleOfTheDamnedButton;
import static com.evilbird.warcraft.object.unit.UnitType.AltarOfStorms;
import static com.evilbird.warcraft.object.unit.UnitType.Dockyard;
import static com.evilbird.warcraft.object.unit.UnitType.DragonRoost;
import static com.evilbird.warcraft.object.unit.UnitType.Fortress;
import static com.evilbird.warcraft.object.unit.UnitType.GoblinAlchemist;
import static com.evilbird.warcraft.object.unit.UnitType.LumberMill;
import static com.evilbird.warcraft.object.unit.UnitType.Metalworks;
import static com.evilbird.warcraft.object.unit.UnitType.OgreMound;
import static com.evilbird.warcraft.object.unit.UnitType.OilRefinery;
import static com.evilbird.warcraft.object.unit.UnitType.Stronghold;
import static com.evilbird.warcraft.object.unit.UnitType.TempleOfTheDamned;
import static java.util.Arrays.asList;

/**
 * Controls the buttons shown when a Peon is selected and the user navigates
 * to the advanced building menu.
 *
 * @author Blair Butterworth
 */
public class PeonAdvancedBuildings extends BasicButtonController
{
    private static final List<ActionButtonType> BASIC_BUTTONS =
        asList(DockyardButton, BuildCancelButton);

    private static final List<ActionButtonType> ADVANCED_SHIP_BUTTONS =
        asList(DockyardButton, MetalworksButton, OilRefineryButton, BuildCancelButton);

    private static final List<ActionButtonType> ADVANCED_MELEE_BUTTONS =
        asList(DockyardButton, MetalworksButton, OilRefineryButton,
                GoblinAlchemistButton, OgreMoundButton, BuildCancelButton);

    private static final List<ActionButtonType> SPELL_CASTER_BUTTONS =
        asList(DockyardButton, MetalworksButton, OilRefineryButton,
                GoblinAlchemistButton, OgreMoundButton, AltarOfStormsButton,
                DragonRoostButton, TempleOfTheDamnedButton, BuildCancelButton);

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
            case DockyardButton: return hasResources(player, Dockyard) && hasUnit(player, LumberMill);
            case MetalworksButton: return hasResources(player, Metalworks) && hasUnit(player, Dockyard);
            case OilRefineryButton: return hasResources(player, OilRefinery) && hasUnit(player, Dockyard);
            case OgreMoundButton: return hasResources(player, OgreMound) && hasUnit(player, Stronghold);
            case GoblinAlchemistButton: return hasResources(player, GoblinAlchemist) && hasUnit(player, Stronghold);
            case AltarOfStormsButton: return hasResources(player, AltarOfStorms) && hasUnit(player, Fortress);
            case DragonRoostButton: return hasResources(player, DragonRoost) && hasUnit(player, Fortress);
            case TempleOfTheDamnedButton: return hasResources(player, TempleOfTheDamned) && hasUnit(player, Fortress);
            default: return false;
        }
    }
}
