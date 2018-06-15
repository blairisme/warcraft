package com.evilbird.warcraft.action;

import com.evilbird.engine.action.ActionIdentifier;

public enum ActionCategory implements ActionIdentifier
{
    Select,
    Move,

    Pan,
    Zoom,
    Drag,

    GatherGold,
    GatherWood,
    GatherOil,

    Repair,
    Build,
    BuildSite,
    Train,
    Upgrade,

    Attack,
    Stop,
    Cancel,
    CancelBuildSite,

    Unknown
}
