package com.evilbird.warcraft.behaviour.user.interaction;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.lang.Identifier;
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

    @Deprecated //TODO: Remove
    public TargetInteraction get(
        UserInputType inputType,
        String targetType,
        String selectedType,
        String hudType,
        ActionIdentifier actionType)
    {
        TargetInteraction result = provider.get();
        result.setActionType(actionType);
        result.setInputType(inputType);
        result.setTargetType(targetType);
        result.setSelectedType(selectedType);
        result.setHudType(hudType);
        return result;
    }

    public TargetInteraction get(
        UserInputType inputType,
        Identifier targetType,
        Identifier selectedType,
        Identifier hudType,
        ActionIdentifier actionType)
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
