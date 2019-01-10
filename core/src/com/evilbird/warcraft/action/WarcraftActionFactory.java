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
        ConfirmedMoveSequence moveActionProvider,
        PanSequence panActionProvider,
        AddBuildingSite buildSiteProvider,
        SelectionSequence selectActionProvider,
        StopSequence stopActionProvider,
        TrainSequence trainActionProvider,
        ZoomSequence zoomActionProvider,
        RepositionSequence repositionProvider)
    {
        actions = new HashMap<>();

        registerProvider(CommonAction.Attack, attackActionProvider);
        registerProvider(CommonAction.Cancel, cancelActionProvider);
        registerProvider(CommonAction.Select, selectActionProvider);
        registerProvider(CommonAction.Stop, stopActionProvider);
        registerProvider(CommonAction.Move, moveActionProvider);
        registerProvider(CommonAction.Reposition, repositionProvider);

        registerProvider(CameraAction.Drag, dragActionProvider);
        registerProvider(CameraAction.Pan, panActionProvider);
        registerProvider(CameraAction.Zoom, zoomActionProvider);

        registerProvider(GatherAction.GatherGold, gatherGoldSequence);
        registerProvider(GatherAction.GatherWood, gatherWoodSequence);

        registerProvider(BuildAction.values(), buildActionProvider);
        registerProvider(BuildSiteAction.values(), buildSiteProvider);
        registerProvider(TrainAction.values(), trainActionProvider);
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
