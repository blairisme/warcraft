package com.evilbird.warcraft.action;

import com.evilbird.engine.action.ActionIdentifier;

public enum Actions implements ActionIdentifier
{
    Select,
    Move,

    Pan,
    Zoom,

    GatherGold,
    GatherWood,

    BuildFarm,
    BuildBarracks,

    Attack,
    Stop,

    Unknown
}
