package com.evilbird.warcraft.behaviour.user.interaction;

import com.evilbird.engine.device.UserInputType;
import com.evilbird.warcraft.action.ActionType;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class TargetInteractionFactory
{
    private Provider<TargetInteraction> provider;

    @Inject
    public TargetInteractionFactory(Provider<TargetInteraction> provider)
    {
        this.provider = provider;
    }

    public TargetInteraction get(
        UserInputType inputType,
        String targetType,
        String selectedType,
        String hudType,
        ActionType actionType)
    {
        TargetInteraction result = provider.get();
        result.setActionType(actionType);
        result.setInputType(inputType);
        result.setTargetType(targetType);
        result.setSelectedType(selectedType);
        result.setHudType(hudType);
        return result;
    }
}
