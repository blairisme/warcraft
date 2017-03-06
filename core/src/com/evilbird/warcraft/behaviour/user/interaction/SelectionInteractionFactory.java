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
public class SelectionInteractionFactory
{
    private Provider<SelectionInteraction> provider;

    @Inject
    public SelectionInteractionFactory(Provider<SelectionInteraction> provider)
    {
        this.provider = provider;
    }

    public SelectionInteraction get(
        UserInputType inputType,
        String targetType,
        String selectedType,
        String hudType,
        ActionType actionType)
    {
        SelectionInteraction result = provider.get();
        result.setActionType(actionType);
        result.setInputType(inputType);
        result.setTargetType(targetType);
        result.setSelectedType(selectedType);
        result.setHudType(hudType);
        return result;
    }
}
