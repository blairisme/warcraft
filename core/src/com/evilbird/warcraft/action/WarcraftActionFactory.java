package com.evilbird.warcraft.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.sequence.*;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class WarcraftActionFactory implements ActionFactory
{
    private Map<ActionCategory, ActionProvider> actions;

    @Inject
    public WarcraftActionFactory(
        AttackSequence attackSequence,
        BuildSequence buildSequence,
        CancelSequence cancelSequence,
        CancelBuildingSiteSequence cancelBuildingSiteSequence,
        DragSequence dragSequence,
        GatherGoldSequence gatherGoldSequence,
        GatherWoodSequence gatherWoodSequence,
        ConfirmedMoveSequence confirmedMoveSequence,
        PanSequence panActionProvider,
        BuildingSiteSequence buildingSiteSequence,
        SelectionSequence selectionSequence,
        StopSequence stopSequence,
        TrainSequence trainSequence,
        ZoomSequence zoomSequence)
    {
        actions = new HashMap<ActionCategory, ActionProvider>();
        actions.put(ActionCategory.Attack, attackSequence);
        actions.put(ActionCategory.Build, buildSequence);
        actions.put(ActionCategory.BuildSite, buildingSiteSequence);
        actions.put(ActionCategory.CancelBuildSite, cancelBuildingSiteSequence);
        actions.put(ActionCategory.Cancel, cancelSequence);
        actions.put(ActionCategory.Drag, dragSequence);
        actions.put(ActionCategory.GatherGold, gatherGoldSequence);
        actions.put(ActionCategory.GatherWood, gatherWoodSequence);
        actions.put(ActionCategory.Move, confirmedMoveSequence);
        actions.put(ActionCategory.Pan, panActionProvider);
        actions.put(ActionCategory.Select, selectionSequence);
        actions.put(ActionCategory.Stop, stopSequence);
        actions.put(ActionCategory.Train, trainSequence);
        actions.put(ActionCategory.Zoom, zoomSequence);
    }

    @Override
    public void load() {
    }

    @Override
    public Action newAction(ActionIdentifier action, Item item, Item target, UserInput input) {
        if (action instanceof ActionType) {
            ActionType type = (ActionType)action;
            ActionProvider provider = actions.get(type.getCategory());
            return provider.get(type, item, target, input);
        }
        throw new UnsupportedOperationException();
    }
}
