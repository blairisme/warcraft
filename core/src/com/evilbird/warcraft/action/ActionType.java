package com.evilbird.warcraft.action;

import com.evilbird.engine.action.ActionIdentifier;

public enum ActionType implements ActionIdentifier
{
    Select,
    Move,

    Pan,
    Zoom,

    Gather,
    Build,
    BuildingSite,

    CreateBarracks,
    CreateFarm,

    Attack,
    Stop,

    Drag,

    Unknown
}
