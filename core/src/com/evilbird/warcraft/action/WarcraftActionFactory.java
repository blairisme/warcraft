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
    private Map<ActionCatagory, ActionProvider> actions;

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
        ZoomActionProvider zoomActionProvider)
    {
        actions = new HashMap<ActionCatagory, ActionProvider>();
        actions.put(ActionCatagory.Attack, attackActionProvider);
        actions.put(ActionCatagory.Build, buildActionProvider);
        actions.put(ActionCatagory.BuildSite, buildingSiteActionProvider);
        actions.put(ActionCatagory.Cancel, cancelActionProvider);
        actions.put(ActionCatagory.Drag, dragActionProvider);
        actions.put(ActionCatagory.Gather, gatherActionProvider);
        actions.put(ActionCatagory.Move, moveActionProvider);
        actions.put(ActionCatagory.Pan, panActionProvider);
        actions.put(ActionCatagory.Select, selectionActionProvider);
        actions.put(ActionCatagory.Stop, stopActionProvider);
        actions.put(ActionCatagory.Zoom, zoomActionProvider);
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
