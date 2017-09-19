package com.evilbird.warcraft.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class WarcraftActionFactory implements ActionFactory
{
    private Map<ActionCategory, ActionProvider> actions;

    @Inject
    public WarcraftActionFactory(
        AttackActionProvider attackActionProvider,
        BuildActionProvider buildActionProvider,
        CancelActionProvider cancelActionProvider,
        DragActionProvider dragActionProvider,
        GatherActionProvider gatherActionProvider,
        MoveActionProvider moveActionProvider,
        PanActionProvider panActionProvider,
        BuildingSiteActionProvider buildingSiteActionProvider,
        SelectionActionProvider selectionActionProvider,
        StopActionProvider stopActionProvider,
        TrainActionProvider trainActionProvider,
        ZoomActionProvider zoomActionProvider)
    {
        actions = new HashMap<ActionCategory, ActionProvider>();
        actions.put(ActionCategory.Attack, attackActionProvider);
        actions.put(ActionCategory.Build, buildActionProvider);
        actions.put(ActionCategory.BuildSite, buildingSiteActionProvider);
        actions.put(ActionCategory.Cancel, cancelActionProvider);
        actions.put(ActionCategory.Drag, dragActionProvider);
        actions.put(ActionCategory.Gather, gatherActionProvider);
        actions.put(ActionCategory.Move, moveActionProvider);
        actions.put(ActionCategory.Pan, panActionProvider);
        actions.put(ActionCategory.Select, selectionActionProvider);
        actions.put(ActionCategory.Stop, stopActionProvider);
        actions.put(ActionCategory.Train, trainActionProvider);
        actions.put(ActionCategory.Zoom, zoomActionProvider);
    }

    @Override
    public void load()
    {
    }

    @Override
    public Action newAction(ActionIdentifier action, Item item, Item target, UserInput input)
    {
        if (action instanceof ActionType)
        {
            ActionType type = (ActionType)action;
            ActionProvider provider = actions.get(type.getCategory());
            return provider.get(type, item, target, input);
        }
        throw new UnsupportedOperationException();
    }
}
