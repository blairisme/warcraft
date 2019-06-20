/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.hud.control.actions.buttons;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.ui.hud.control.actions.ActionPaneView;
import com.evilbird.warcraft.item.ui.hud.control.actions.buttons.common.CombatantButtons;
import com.evilbird.warcraft.item.ui.hud.control.actions.buttons.common.GathererButtons;
import com.evilbird.warcraft.item.ui.hud.control.actions.buttons.common.NoButtons;
import com.evilbird.warcraft.item.ui.hud.control.actions.buttons.human.BarracksButtons;
import com.evilbird.warcraft.item.ui.hud.control.actions.buttons.human.LumberMillButtons;
import com.evilbird.warcraft.item.ui.hud.control.actions.buttons.human.PeasantAdvancedBuildings;
import com.evilbird.warcraft.item.ui.hud.control.actions.buttons.human.PeasantSimpleBuildings;
import com.evilbird.warcraft.item.ui.hud.control.actions.buttons.human.TownHallButtons;
import com.evilbird.warcraft.item.unit.UnitType;

import java.util.HashMap;
import java.util.Map;

import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionPaneView.Actions;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionPaneView.AdvancedBuildings;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionPaneView.SimpleBuildings;
import static com.evilbird.warcraft.item.ui.hud.control.actions.buttons.common.NoButtons.emptyButtons;
import static com.evilbird.warcraft.item.unit.UnitType.Barracks;
import static com.evilbird.warcraft.item.unit.UnitType.ElvenArcher;
import static com.evilbird.warcraft.item.unit.UnitType.ElvenArcherCaptive;
import static com.evilbird.warcraft.item.unit.UnitType.ElvenDestroyer;
import static com.evilbird.warcraft.item.unit.UnitType.Farm;
import static com.evilbird.warcraft.item.unit.UnitType.Footman;
import static com.evilbird.warcraft.item.unit.UnitType.Grunt;
import static com.evilbird.warcraft.item.unit.UnitType.LumberMill;
import static com.evilbird.warcraft.item.unit.UnitType.Peasant;
import static com.evilbird.warcraft.item.unit.UnitType.Peon;
import static com.evilbird.warcraft.item.unit.UnitType.TownHall;
import static com.evilbird.warcraft.item.unit.UnitType.TrollAxethrower;
import static com.evilbird.warcraft.item.unit.UnitType.TrollDestroyer;
import static java.util.Collections.emptyMap;

/**
 * Defines which {@link ButtonController} is applicable to which {@link Item}
 * and {@link ActionPaneView} types.
 *
 * @author Blair Butterworth
 */
public class ButtonControllers
{
    private Map<ActionPaneView, Map<Identifier, ButtonController>> controllers;

    public ButtonControllers() {
        controllers = new HashMap<>();
        registerBuildingButtons();
        registerBuildingMenus();
        registerCombatantButtons();
        registerGathererButtons();
    }

    public ButtonController getButtonController(Item item, ActionPaneView view) {
        Identifier type = item.getType();
        return controllers.getOrDefault(view, emptyMap()).getOrDefault(type, emptyButtons());
    }

    private void registerCombatantButtons() {
        CombatantButtons combatantButtons = new CombatantButtons();

        registerController(Actions, ElvenArcher, combatantButtons);
        registerController(Actions, ElvenArcherCaptive, combatantButtons);
        registerController(Actions, ElvenDestroyer, combatantButtons);
        registerController(Actions, Footman, combatantButtons);

        registerController(Actions, Grunt, combatantButtons);
        registerController(Actions, TrollAxethrower, combatantButtons);
        registerController(Actions, TrollDestroyer, combatantButtons);
    }

    private void registerGathererButtons() {
        GathererButtons gathererButtons = new GathererButtons();

        registerController(Actions, Peasant, gathererButtons);
        registerController(Actions, Peon, gathererButtons);
    }

    private void registerBuildingButtons() {
        registerController(Actions, Barracks, new BarracksButtons());
        registerController(Actions, LumberMill, new LumberMillButtons());
        registerController(Actions, TownHall, new TownHallButtons());
        registerController(Actions, Farm, new NoButtons());
    }

    private void registerBuildingMenus() {
        registerController(SimpleBuildings, Peasant, new PeasantSimpleBuildings());
        registerController(AdvancedBuildings, Peasant, new PeasantAdvancedBuildings());
    }

    private void registerController(ActionPaneView view, UnitType type, ButtonController controller) {
        Map<Identifier, ButtonController> viewControllers = controllers.computeIfAbsent(view, key -> new HashMap<>());
        viewControllers.put(type, controller);
    }
}
