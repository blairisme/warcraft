package com.evilbird.warcraft.action;

import com.evilbird.engine.action.ActionIdentifier;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public enum ActionType implements ActionIdentifier
{
    Select  (ActionCategory.Select),
    Move    (ActionCategory.Move),

    Pan     (ActionCategory.Pan),
    Zoom    (ActionCategory.Zoom),
    Drag    (ActionCategory.Drag),

    Attack  (ActionCategory.Attack),
    Stop    (ActionCategory.Stop),
    Cancel  (ActionCategory.Cancel),
    Repair  (ActionCategory.Repair),

    GatherGold  (ActionCategory.GatherGold),
    GatherOil   (ActionCategory.GatherOil),
    GatherWood  (ActionCategory.GatherWood),

    BuildBarracks   (ActionCategory.Build),
    BuildFarm       (ActionCategory.Build),
    BuildTownHall   (ActionCategory.Build),

    BuildBarracksSite   (ActionCategory.BuildSite),
    BuildFarmSite       (ActionCategory.BuildSite),
    BuildTownHallSite   (ActionCategory.BuildSite),

    TrainPeasant    (ActionCategory.Train),
    TrainFootman    (ActionCategory.Train),

    UpgradeArmour (ActionCategory.Upgrade),

    Unknown (ActionCategory.Unknown);

    private ActionCategory category;

    ActionType(ActionCategory catagory)
    {
        this.category = catagory;
    }

    public ActionCategory getCategory()
    {
        return category;
    }
}
