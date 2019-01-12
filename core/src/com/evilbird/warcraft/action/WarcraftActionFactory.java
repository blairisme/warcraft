package com.evilbird.warcraft.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.identifier.*;
import com.evilbird.warcraft.action.sequence.*;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class WarcraftActionFactory implements ActionFactory
{
    private Map<ActionIdentifier, ActionProvider> actions;

    @Inject
    public WarcraftActionFactory(
        AttackSequence attackActionProvider,
        BuildSequence buildActionProvider,
        CancelSequence cancelActionProvider,
        RemoveBuildingSite cancelBuildingSiteSequence,
        DragSequence dragActionProvider,
        GatherGoldSequence gatherGoldSequence,
        GatherWoodSequence gatherWoodSequence,
        MoveSequence moveActionProvider,
        PanSequence panActionProvider,
        CreateBuildingSiteSequence buildSiteProvider,
        SelectionSequence selectActionProvider,
        StopSequence stopActionProvider,
        TrainSequence trainActionProvider,
        ZoomSequence zoomActionProvider,
        RepositionSequence repositionProvider)
    {
        actions = new HashMap<>();

        registerProvider(CommonActionType.Attack, attackActionProvider);
        registerProvider(CommonActionType.Cancel, cancelActionProvider);
        registerProvider(CommonActionType.Select, selectActionProvider);
        registerProvider(CommonActionType.Stop, stopActionProvider);
        registerProvider(CommonActionType.Move, moveActionProvider);
        registerProvider(CommonActionType.Reposition, repositionProvider);

        registerProvider(CameraActionType.Drag, dragActionProvider);
        registerProvider(CameraActionType.Pan, panActionProvider);
        registerProvider(CameraActionType.Zoom, zoomActionProvider);

        registerProvider(GatherActionType.GatherGold, gatherGoldSequence);
        registerProvider(GatherActionType.GatherWood, gatherWoodSequence);

        registerProvider(BuildActionType.values(), buildActionProvider);
        registerProvider(SiteActionType.values(), buildSiteProvider);
        registerProvider(TrainActionType.values(), trainActionProvider);
    }

    @Override
    public void load() {
    }

    @Override
    public Action newAction(ActionIdentifier action, Item item, Item target, UserInput input) {
        ActionProvider provider = actions.get(action);
        if (provider != null) {
            return provider.get(action, item, target, input);
        }
        throw new UnsupportedOperationException();
    }

    private void registerProvider(ActionIdentifier[] identifiers, ActionProvider provider) {
        for (ActionIdentifier identifier: identifiers) {
            actions.put(identifier, provider);
        }
    }

    private void registerProvider(ActionIdentifier identifier, ActionProvider provider) {
        actions.put(identifier, provider);
    }
}
