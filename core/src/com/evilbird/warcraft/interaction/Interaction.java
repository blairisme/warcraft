package com.evilbird.warcraft.interaction;

import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.device.UserInputType;

public class Interaction
{
    private UserInputType inputType;
    private String targetType;
    private String selectedType;
    private ActionType commandType;

    public Interaction(Interaction action)
    {
        this.inputType = action.inputType;
        this.targetType = action.targetType;
        this.selectedType = action.selectedType;
        this.commandType = action.commandType;
    }

    public Interaction(UserInputType inputType, String targetType, String selectedType, ActionType commandType)
    {
        this.inputType = inputType;
        this.targetType = targetType;
        this.selectedType = selectedType;
        this.commandType = commandType;
    }

    public UserInputType getInputType()
    {
        return inputType;
    }

    public String getTargetType()
    {
        return targetType;
    }

    public String getSelectedType()
    {
        return selectedType;
    }

    public ActionType getCommandType()
    {
        return commandType;
    }
}
