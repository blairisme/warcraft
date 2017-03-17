package com.evilbird.warcraft.action;

import com.evilbird.engine.action.ActionIdentifier;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public enum ActionType implements ActionIdentifier
{
    Select  (ActionCatagory.Select),
    Move    (ActionCatagory.Move),

    Pan     (ActionCatagory.Pan),
    Zoom    (ActionCatagory.Zoom),
    Drag    (ActionCatagory.Drag),

    Attack  (ActionCatagory.Attack),
    Stop    (ActionCatagory.Stop),
    Cancel  (ActionCatagory.Cancel),
    Repair  (ActionCatagory.Repair),

    GatherGold  (ActionCatagory.Gather),
    GatherOil   (ActionCatagory.Gather),
    GatherWood  (ActionCatagory.Gather),

    BuildBarracks   (ActionCatagory.Build),
    BuildFarm       (ActionCatagory.Build),
    BuildTownHall   (ActionCatagory.Build),

    BuildBarracksSite   (ActionCatagory.BuildSite),
    BuildFarmSite       (ActionCatagory.BuildSite),
    BuildTownHallSite   (ActionCatagory.BuildSite),

    TrainPeasant    (ActionCatagory.Train),
    TrainFootman    (ActionCatagory.Train),

    UpgradeArmour (ActionCatagory.Upgrade),

    Unknown (ActionCatagory.Unknown);

    private ActionCatagory category;

    ActionType(ActionCatagory catagory)
    {
        this.category = catagory;
    }

    public ActionCatagory getCategory()
    {
        return category;
    }
}
