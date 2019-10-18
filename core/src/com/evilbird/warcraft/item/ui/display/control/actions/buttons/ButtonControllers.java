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
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.common.BlacksmithButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.common.CombatantButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.common.FoundryButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.common.GathererButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.common.NoButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.common.OilTankerButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.common.TransportButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.human.BarracksButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.human.CastleButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.human.ChurchButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.human.GnomishInventorButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.human.GryphonAviaryButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.human.GuardTowerButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.human.KeepButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.human.LumberMillButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.human.MageTowerButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.human.OilTankerBuildings;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.human.PeasantAdvancedBuildings;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.human.PeasantSimpleBuildings;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.human.ScoutTowerButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.human.ShipyardButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.human.TownHallButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.orc.DockyardButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.orc.EncampmentButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.orc.GreatHallButtons;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.orc.PeonAdvancedBuildings;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.orc.PeonSimpleBuildings;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.orc.TrollTankerBuildings;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.human.StablesFactory;

import java.util.HashMap;
import java.util.Map;

import static com.evilbird.warcraft.item.ui.display.control.actions.ActionPaneView.Actions;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionPaneView.AdvancedBuildings;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionPaneView.SimpleBuildings;
import static com.evilbird.warcraft.item.ui.display.control.actions.buttons.common.NoButtons.emptyButtons;
import static com.evilbird.warcraft.item.unit.UnitType.AnduinLothar;
import static com.evilbird.warcraft.item.unit.UnitType.Barracks;
import static com.evilbird.warcraft.item.unit.UnitType.Battleship;
import static com.evilbird.warcraft.item.unit.UnitType.Blacksmith;
import static com.evilbird.warcraft.item.unit.UnitType.Castle;
import static com.evilbird.warcraft.item.unit.UnitType.Church;
import static com.evilbird.warcraft.item.unit.UnitType.Dockyard;
import static com.evilbird.warcraft.item.unit.UnitType.ElvenArcher;
import static com.evilbird.warcraft.item.unit.UnitType.ElvenDestroyer;
import static com.evilbird.warcraft.item.unit.UnitType.Encampment;
import static com.evilbird.warcraft.item.unit.UnitType.Farm;
import static com.evilbird.warcraft.item.unit.UnitType.Ferry;
import static com.evilbird.warcraft.item.unit.UnitType.Footman;
import static com.evilbird.warcraft.item.unit.UnitType.Foundry;
import static com.evilbird.warcraft.item.unit.UnitType.GnomishInventor;
import static com.evilbird.warcraft.item.unit.UnitType.GreatHall;
import static com.evilbird.warcraft.item.unit.UnitType.Grunt;
import static com.evilbird.warcraft.item.unit.UnitType.GryphonAviary;
import static com.evilbird.warcraft.item.unit.UnitType.GuardTower;
import static com.evilbird.warcraft.item.unit.UnitType.Keep;
import static com.evilbird.warcraft.item.unit.UnitType.Knight;
import static com.evilbird.warcraft.item.unit.UnitType.LumberMill;
import static com.evilbird.warcraft.item.unit.UnitType.MageTower;
import static com.evilbird.warcraft.item.unit.UnitType.Ogre;
import static com.evilbird.warcraft.item.unit.UnitType.OgreJuggernaught;
import static com.evilbird.warcraft.item.unit.UnitType.OilPlatform;
import static com.evilbird.warcraft.item.unit.UnitType.OilTanker;
import static com.evilbird.warcraft.item.unit.UnitType.Peasant;
import static com.evilbird.warcraft.item.unit.UnitType.Peon;
import static com.evilbird.warcraft.item.unit.UnitType.PigFarm;
import static com.evilbird.warcraft.item.unit.UnitType.Refinery;
import static com.evilbird.warcraft.item.unit.UnitType.ScoutTower;
import static com.evilbird.warcraft.item.unit.UnitType.Shipyard;
import static com.evilbird.warcraft.item.unit.UnitType.Stables;
import static com.evilbird.warcraft.item.unit.UnitType.TownHall;
import static com.evilbird.warcraft.item.unit.UnitType.Transport;
import static com.evilbird.warcraft.item.unit.UnitType.TrollAxethrower;
import static com.evilbird.warcraft.item.unit.UnitType.TrollDestroyer;
import static com.evilbird.warcraft.item.unit.UnitType.TrollTanker;
import static com.evilbird.warcraft.item.unit.UnitType.UtherLightbringer;
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
        return controllers.getOrDefault(view, emptyMap()).getOrDefault(type, emptyButtons());
    }

    private void registerCombatantButtons() {
        CombatantButtons combatantButtons = new CombatantButtons();

        registerController(Actions, Battleship, combatantButtons);
        registerController(Actions, ElvenArcher, combatantButtons);
        registerController(Actions, ElvenDestroyer, combatantButtons);
        registerController(Actions, Footman, combatantButtons);
        registerController(Actions, Knight, combatantButtons);
        registerController(Actions, AnduinLothar, combatantButtons);
        registerController(Actions, UtherLightbringer, combatantButtons);

        registerController(Actions, Grunt, combatantButtons);
        registerController(Actions, Ogre, combatantButtons);
        registerController(Actions, OgreJuggernaught, combatantButtons);
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
        registerHumanBuildingButtons();
        registerOrcBuildingButtons();
    }

    private void registerHumanBuildingButtons() {
        registerController(Actions, Barracks, new BarracksButtons());
        registerController(Actions, Blacksmith, new BlacksmithButtons());
        registerController(Actions, Castle, new CastleButtons());
        registerController(Actions, Church, new ChurchButtons());
        registerController(Actions, Farm, emptyButtons());
        registerController(Actions, Foundry, new FoundryButtons());
        registerController(Actions, GnomishInventor, new GnomishInventorButtons());
        registerController(Actions, GryphonAviary, new GryphonAviaryButtons());
        registerController(Actions, GuardTower, new GuardTowerButtons());
        registerController(Actions, Keep, new KeepButtons());
        registerController(Actions, LumberMill, new LumberMillButtons());
        registerController(Actions, MageTower, new MageTowerButtons());
        registerController(Actions, OilPlatform, emptyButtons());
        registerController(Actions, Refinery, emptyButtons());
        registerController(Actions, ScoutTower, new ScoutTowerButtons());
        registerController(Actions, Shipyard, new ShipyardButtons());
        registerController(Actions, Stables, emptyButtons());
        registerController(Actions, TownHall, new TownHallButtons());
    }

    private void registerOrcBuildingButtons() {
        registerController(Actions, Encampment, new EncampmentButtons());
        //registerController(Actions, TrollLumberMill, new LumberMillButtons());
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
