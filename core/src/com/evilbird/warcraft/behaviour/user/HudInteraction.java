package com.evilbird.warcraft.behaviour.user;

import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.action.type.*;
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
        interactions.add(selectionFactory.get(UserInputType.Action, CommonAction.Stop, UnitType.Footman, null, CommonAction.Stop));
        interactions.add(selectionFactory.get(UserInputType.Action, CommonAction.Stop, UnitType.Peasant, null, CommonAction.Stop));

        //TODO: Move into build class
        interactions.add(selectionFactory.get(UserInputType.Action, BuildAction.BuildBarracks, UnitType.Peasant, null, BuildSiteAction.BuildBarracksSite));
        interactions.add(selectionFactory.get(UserInputType.Action, BuildAction.BuildFarm, UnitType.Peasant, null, BuildSiteAction.BuildFarmSite));
        interactions.add(selectionFactory.get(UserInputType.Action, BuildAction.BuildTownHall, UnitType.Peasant, null, BuildSiteAction.BuildTownHallSite));

        interactions.add(selectionFactory.get(UserInputType.Action, BuildSiteAction.BuildBarracksSite, UnitType.Peasant, null, BuildAction.BuildBarracks));
        interactions.add(selectionFactory.get(UserInputType.Action, BuildSiteAction.BuildFarmSite, UnitType.Peasant, null, BuildAction.BuildFarm));
        interactions.add(selectionFactory.get(UserInputType.Action, BuildSiteAction.BuildTownHallSite, UnitType.Peasant, null, BuildAction.BuildTownHall));

        //TODO: Move into drag class
        interactions.add(targetFactory.get(UserInputType.Drag, BuildSiteType.BarracksBuildSite, null, null, CameraAction.Drag));
        interactions.add(targetFactory.get(UserInputType.Drag, BuildSiteType.FarmBuildSite, null, null, CameraAction.Drag));
        interactions.add(targetFactory.get(UserInputType.Drag, BuildSiteType.TownHallBuildSite, null, null, CameraAction.Drag));

        //TODO: Move into cancel class
        interactions.add(selectionFactory.get(UserInputType.Action, CommonAction.Cancel, UnitType.TownHall, null, CommonAction.Cancel));
        interactions.add(selectionFactory.get(UserInputType.Action, CommonAction.Cancel, UnitType.Barracks, null, CommonAction.Cancel));
//        interactions.add(selectionFactory.get(UserInputType.Action, CommonAction.Cancel, BuildSiteType.BarracksBuildSite, null, ActionType.BuildSiteCancel));
//        interactions.add(selectionFactory.get(UserInputType.Action, CommonAction.Cancel, BuildSiteType.FarmBuildSite, null, ActionType.BuildSiteCancel));
//        interactions.add(selectionFactory.get(UserInputType.Action, CommonAction.Cancel, BuildSiteType.TownHallBuildSite, null, ActionType.BuildSiteCancel));

        //TODO: Move into train class
        interactions.add(selectionFactory.get(UserInputType.Action, TrainAction.TrainPeasant, UnitType.TownHall, null, TrainAction.TrainPeasant));
        interactions.add(selectionFactory.get(UserInputType.Action, TrainAction.TrainFootman, UnitType.Barracks, null, TrainAction.TrainFootman));
    }

    @Override
    public boolean update(UserInput input, Item target, Item worldSelection, Item hudSelection)
    {
        return interactions.update(input, target, worldSelection, hudSelection);
    }
}
