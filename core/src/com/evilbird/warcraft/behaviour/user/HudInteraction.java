package com.evilbird.warcraft.behaviour.user;

import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.behaviour.user.interaction.CompositeInteraction;
import com.evilbird.warcraft.behaviour.user.interaction.Interaction;
import com.evilbird.warcraft.behaviour.user.interaction.SelectionInteractionFactory;
import com.evilbird.warcraft.behaviour.user.interaction.TargetInteractionFactory;
import com.evilbird.warcraft.item.hud.building.BuildSiteType;
import com.evilbird.warcraft.item.unit.UnitType;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class HudInteraction implements Interaction
{
    private CompositeInteraction interactions;

    @Inject
    public HudInteraction(SelectionInteractionFactory selectionFactory, TargetInteractionFactory targetFactory)
    {
        interactions = new CompositeInteraction();

        //TODO: Move into stop class
        interactions.add(selectionFactory.get(UserInputType.Action, ActionType.Stop, UnitType.Footman, null, ActionType.Stop));
        interactions.add(selectionFactory.get(UserInputType.Action, ActionType.Stop, UnitType.Peasant, null, ActionType.Stop));

        //TODO: Move into build class
        interactions.add(selectionFactory.get(UserInputType.Action, ActionType.BuildBarracks, UnitType.Peasant, null, ActionType.BuildBarracksSite));
        interactions.add(selectionFactory.get(UserInputType.Action, ActionType.BuildFarm, UnitType.Peasant, null, ActionType.BuildFarmSite));
        interactions.add(selectionFactory.get(UserInputType.Action, ActionType.BuildTownHall, UnitType.Peasant, null, ActionType.BuildTownHallSite));

        interactions.add(selectionFactory.get(UserInputType.Action, ActionType.BuildBarracksSite, UnitType.Peasant, null, ActionType.BuildBarracks));
        interactions.add(selectionFactory.get(UserInputType.Action, ActionType.BuildFarmSite, UnitType.Peasant, null, ActionType.BuildFarm));
        interactions.add(selectionFactory.get(UserInputType.Action, ActionType.BuildTownHallSite, UnitType.Peasant, null, ActionType.BuildTownHall));

        //TODO: Move into drag class
        interactions.add(targetFactory.get(UserInputType.Drag, BuildSiteType.BarracksBuildSite, null, null, ActionType.Drag));
        interactions.add(targetFactory.get(UserInputType.Drag, BuildSiteType.FarmBuildSite, null, null, ActionType.Drag));
        interactions.add(targetFactory.get(UserInputType.Drag, BuildSiteType.TownHallBuildSite, null, null, ActionType.Drag));

        //TODO: Move into cancel class
        interactions.add(selectionFactory.get(UserInputType.Action, ActionType.Cancel, UnitType.TownHall, null, ActionType.Cancel));
        interactions.add(selectionFactory.get(UserInputType.Action, ActionType.Cancel, UnitType.Barracks, null, ActionType.Cancel));
        interactions.add(selectionFactory.get(UserInputType.Action, ActionType.Cancel, BuildSiteType.BarracksBuildSite, null, ActionType.BuildSiteCancel));
        interactions.add(selectionFactory.get(UserInputType.Action, ActionType.Cancel, BuildSiteType.FarmBuildSite, null, ActionType.BuildSiteCancel));
        interactions.add(selectionFactory.get(UserInputType.Action, ActionType.Cancel, BuildSiteType.TownHallBuildSite, null, ActionType.BuildSiteCancel));

        //TODO: Move into train class
        interactions.add(selectionFactory.get(UserInputType.Action, ActionType.TrainPeasant, UnitType.TownHall, null, ActionType.TrainPeasant));
        interactions.add(selectionFactory.get(UserInputType.Action, ActionType.TrainFootman, UnitType.Barracks, null, ActionType.TrainFootman));
    }

    @Override
    public boolean update(UserInput input, Item target, Item worldSelection, Item hudSelection)
    {
        return interactions.update(input, target, worldSelection, hudSelection);
    }
}
