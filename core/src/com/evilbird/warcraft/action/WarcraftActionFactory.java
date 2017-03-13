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
    private Map<ActionIdentifier, ActionProvider> actions;

    @Inject
    public WarcraftActionFactory(
        AttackActionProvider attackActionProvider,
        BuildActionProvider buildActionProvider,
        DragActionProvider dragActionProvider,
        GatherActionProvider gatherActionProvider,
        MoveActionProvider moveActionProvider,
        PanActionProvider panActionProvider,
        PrototypeActionProvider buildingSiteActionProvider,
        SelectionActionProvider selectionActionProvider,
        StopActionProvider stopActionProvider,
        ZoomActionProvider zoomActionProvider)
    {
        actions = new HashMap<ActionIdentifier, ActionProvider>();
        actions.put(ActionType.Attack, attackActionProvider);
        actions.put(ActionType.Build, buildActionProvider);
        actions.put(ActionType.BuildingSite, buildingSiteActionProvider);
        actions.put(ActionType.Drag, dragActionProvider);
        actions.put(ActionType.Gather, gatherActionProvider);
        actions.put(ActionType.Move, moveActionProvider);
        actions.put(ActionType.Pan, panActionProvider);
        actions.put(ActionType.Select, selectionActionProvider);
        actions.put(ActionType.Stop, stopActionProvider);
        actions.put(ActionType.Zoom, zoomActionProvider);
    }

    @Override
    public void load()
    {
    }

    @Override
    public Action newAction(ActionIdentifier type, Item item, Item target, UserInput input)
    {
        ActionProvider provider = actions.get(type);
        Action action = provider.get(item, target, input);
        if (action != null){
            return action;
        }
        throw new UnsupportedOperationException();
    }
}
