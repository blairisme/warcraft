package com.evilbird.warcraft.action;

import com.evilbird.engine.action.ActionIdentifier;

public enum ActionType implements ActionIdentifier
{
    Select,
    Move,

    Pan,
    Zoom,

    GatherGold,
    GatherWood,

    CreateBarracks,
    CreateBarracksPrototype,

    CreateFarm,
    CreateFarmPrototype,

    Attack,
    Stop,

    Drag,

    Unknown
}
