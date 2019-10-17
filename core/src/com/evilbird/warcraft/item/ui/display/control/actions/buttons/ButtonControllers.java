/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.actions.buttons;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.ui.display.control.actions.ActionPaneView;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.common.CombatantButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.common.GathererButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.common.LumberMillButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.common.NoButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.common.OilTankerButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.common.TransportButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.human.BarracksButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.human.OilTankerBuildings;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.human.PeasantAdvancedBuildings;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.human.PeasantSimpleBuildings;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.human.ShipyardButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.human.TownHallButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.orc.DockyardButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.orc.EncampmentButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.orc.GreatHallButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.orc.PeonAdvancedBuildings;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.orc.PeonSimpleBuildings;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.orc.TrollTankerBuildings;
import com.evilbird.warcraft.item.unit.UnitType;

import java.util.HashMap;
import java.util.Map;

import static com.evilbird.warcraft.item.ui.display.control.actions.ActionPaneView.Actions;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionPaneView.AdvancedBuildings;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionPaneView.SimpleBuildings;
import static com.evilbird.warcraft.item.unit.UnitType.Barracks;
import static com.evilbird.warcraft.item.unit.UnitType.Dockyard;
import static com.evilbird.warcraft.item.unit.UnitType.ElvenArcher;
import static com.evilbird.warcraft.item.unit.UnitType.ElvenDestroyer;
import static com.evilbird.warcraft.item.unit.UnitType.Encampment;
import static com.evilbird.warcraft.item.unit.UnitType.Farm;
import static com.evilbird.warcraft.item.unit.UnitType.Ferry;
import static com.evilbird.warcraft.item.unit.UnitType.Footman;
import static com.evilbird.warcraft.item.unit.UnitType.GreatHall;
import static com.evilbird.warcraft.item.unit.UnitType.Grunt;
import static com.evilbird.warcraft.item.unit.UnitType.LumberMill;
import static com.evilbird.warcraft.item.unit.UnitType.OilTanker;
import static com.evilbird.warcraft.item.unit.UnitType.Peasant;
import static com.evilbird.warcraft.item.unit.UnitType.Peon;
import static com.evilbird.warcraft.item.unit.UnitType.PigFarm;
import static com.evilbird.warcraft.item.unit.UnitType.Shipyard;
import static com.evilbird.warcraft.item.unit.UnitType.TownHall;
import static com.evilbird.warcraft.item.unit.UnitType.Transport;
import static com.evilbird.warcraft.item.unit.UnitType.TrollAxethrower;
import static com.evilbird.warcraft.item.unit.UnitType.TrollDestroyer;
import static com.evilbird.warcraft.item.unit.UnitType.TrollLumberMill;
import static com.evilbird.warcraft.item.unit.UnitType.TrollTanker;
import static com.evilbird.warcraft.item.unit.UnitType.Zuljin;
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
        registerTransportButtons();
    }

    public ButtonController getButtonController(Item item, ActionPaneView view) {
        Identifier type = item.getType();
        return controllers.getOrDefault(view, emptyMap()).getOrDefault(type, NoButtons.emptyButtons());
    }

    private void registerCombatantButtons() {
        CombatantButtons combatantButtons = new CombatantButtons();

        registerController(Actions, ElvenArcher, combatantButtons);
        registerController(Actions, ElvenDestroyer, combatantButtons);
        registerController(Actions, Footman, combatantButtons);

        registerController(Actions, Grunt, combatantButtons);
        registerController(Actions, TrollAxethrower, combatantButtons);
        registerController(Actions, TrollDestroyer, combatantButtons);
        registerController(Actions, Zuljin, combatantButtons);
    }

    private void registerGathererButtons() {
        GathererButtons gathererButtons = new GathererButtons();
        registerController(Actions, Peasant, gathererButtons);
        registerController(Actions, Peon, gathererButtons);

        OilTankerButtons tankerButtons = new OilTankerButtons();
        registerController(Actions, OilTanker, tankerButtons);
        registerController(Actions, TrollTanker, tankerButtons);
    }

    private void registerBuildingButtons() {
        registerController(Actions, Barracks, new BarracksButtons());
        registerController(Actions, LumberMill, new LumberMillButtons());
        registerController(Actions, TownHall, new TownHallButtons());
        registerController(Actions, Farm, new NoButtons());
        registerController(Actions, Shipyard, new ShipyardButtons());

        registerController(Actions, Encampment, new EncampmentButtons());
        registerController(Actions, TrollLumberMill, new LumberMillButtons());
        registerController(Actions, GreatHall, new GreatHallButtons());
        registerController(Actions, PigFarm, new NoButtons());
        registerController(Actions, Dockyard, new DockyardButtons());
    }

    private void registerBuildingMenus() {
        registerController(SimpleBuildings, Peasant, new PeasantSimpleBuildings());
        registerController(AdvancedBuildings, Peasant, new PeasantAdvancedBuildings());

        registerController(SimpleBuildings, Peon, new PeonSimpleBuildings());
        registerController(AdvancedBuildings, Peon, new PeonAdvancedBuildings());

        registerController(SimpleBuildings, OilTanker, new OilTankerBuildings());
        registerController(SimpleBuildings, TrollTanker, new TrollTankerBuildings());
    }

    private void registerTransportButtons() {
        TransportButtons transportButtons = new TransportButtons();
        registerController(Actions, Transport, transportButtons);
        registerController(Actions, Ferry, transportButtons);
    }

    private void registerController(ActionPaneView view, UnitType type, ButtonController controller) {
        Map<Identifier, ButtonController> viewControllers = controllers.computeIfAbsent(view, key -> new HashMap<>());
        viewControllers.put(type, controller);
    }
}
