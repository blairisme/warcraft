/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ActionContext;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.UnknownActionException;
import com.evilbird.engine.action.common.ReplacementAction;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.warcraft.action.identifier.*;
import com.evilbird.warcraft.action.sequence.*;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Instances of this class provide access to {@link Action Actions}, self
 * contained "bundles" of behaviour, that modify the game state to implement
 * Warcraft game play.
 *
 * @author Blair Butterworth
 */
//TODO: Consider using an action pool
public class WarcraftActionFactory implements ActionFactory
{
    private Map<ActionIdentifier, ActionProvider> actions;

    @Inject
    public WarcraftActionFactory(
        Attack attackProvider,
        AttackCancel attackCancelProvider,
        CameraPan cameraPanProvider,
        CameraZoom cameraZoomProvider,
        Construct constructProvider,
        ConstructCancel constructCancelProvider,
        GatherGold gatherGoldProvider,
        GatherWood gatherWoodProvider,
        GatherCancel gatherCancelProvider,
        Move moveProvider,
        MoveCancel moveCancelProvider,
        Navigate navigateProvider,
        Placeholder placeholderProvider,
        PlaceholderCancel placeholderCancelProvider,
        Reposition repositionProvider,
        Select selectProvider,
        Train trainProvider,
        TrainCancel trainCancelProvider)
    {
        actions = new HashMap<>();

        registerProvider(GeneralActions.Attack, attackProvider);
        registerProvider(CancelActions.CancelAttack, attackCancelProvider);

        registerProvider(CameraActions.Pan, cameraPanProvider);
        registerProvider(CameraActions.Zoom, cameraZoomProvider);

        registerProvider(ConstructionActions.values(), constructProvider);
        registerProvider(CancelActions.CancelConstruct, constructCancelProvider);

        registerProvider(GatherActions.GatherGold, gatherGoldProvider);
        registerProvider(GatherActions.GatherWood, gatherWoodProvider);
        registerProvider(CancelActions.CancelGather, gatherCancelProvider);

        registerProvider(GeneralActions.Move, moveProvider);
        registerProvider(CancelActions.CancelMove, moveCancelProvider);

        registerProvider(NavigateActions.values(), navigateProvider);

        registerProvider(PlaceholderActions.values(), placeholderProvider);
        registerProvider(GeneralActions.Reposition, repositionProvider);
        registerProvider(CancelActions.CancelPlaceholder, placeholderCancelProvider);

        registerProvider(GeneralActions.Select, selectProvider);

        registerProvider(TrainActions.values(), trainProvider);
        registerProvider(CancelActions.CancelTrain, trainCancelProvider);
    }

    @Override
    public void load() {
    }

    @Override
    public Action newAction(ActionIdentifier identifier, ActionContext context) {
        ActionProvider provider = actions.get(identifier);
        if (provider != null) {
            Action result = provider.get(identifier, context);
            updateIdentifier(result, identifier);
            return result;
        }
        throw new UnknownActionException(identifier);
    }

    private void updateIdentifier(Action action, ActionIdentifier identifier) {
        if (action instanceof BasicAction) {
            BasicAction basicAction = (BasicAction)action;
            basicAction.setIdentifier(identifier);
        }
        if (action instanceof ReplacementAction) {
            ReplacementAction replacementAction = (ReplacementAction)action;
            updateIdentifier(replacementAction.getReplacement(), identifier);
        }
    }

    private void registerProvider(ActionIdentifier identifier, ActionProvider provider) {
        actions.put(identifier, provider);
    }

    private void registerProvider(ActionIdentifier[] identifiers, ActionProvider provider) {
        for (ActionIdentifier identifier: identifiers) {
            registerProvider(identifier, provider);
        }
    }
}
