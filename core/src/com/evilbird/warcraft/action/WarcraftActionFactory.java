/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action;

import com.evilbird.engine.action.ActionContext;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.UnknownActionException;
import com.evilbird.engine.action.common.ReplacementAction;
import com.evilbird.engine.action.framework.Action;
import com.evilbird.warcraft.action.attack.AttackActions;
import com.evilbird.warcraft.action.attack.AttackFactory;
import com.evilbird.warcraft.action.camera.CameraActions;
import com.evilbird.warcraft.action.camera.CameraFactory;
import com.evilbird.warcraft.action.common.CancelActions;
import com.evilbird.warcraft.action.confirm.ConfirmActions;
import com.evilbird.warcraft.action.confirm.ConfirmFactory;
import com.evilbird.warcraft.action.construct.ConstructActions;
import com.evilbird.warcraft.action.construct.ConstructFactory;
import com.evilbird.warcraft.action.gather.GatherActions;
import com.evilbird.warcraft.action.gather.GatherCancel;
import com.evilbird.warcraft.action.gather.GatherGold;
import com.evilbird.warcraft.action.gather.GatherWood;
import com.evilbird.warcraft.action.move.MoveActions;
import com.evilbird.warcraft.action.move.MoveFactory;
import com.evilbird.warcraft.action.navigate.NavigateActions;
import com.evilbird.warcraft.action.navigate.NavigateFactory;
import com.evilbird.warcraft.action.placeholder.PlaceholderActions;
import com.evilbird.warcraft.action.placeholder.PlaceholderFactory;
import com.evilbird.warcraft.action.select.SelectActions;
import com.evilbird.warcraft.action.select.SelectFactory;
import com.evilbird.warcraft.action.train.TrainActions;
import com.evilbird.warcraft.action.train.TrainFactory;

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
public class WarcraftActionFactory implements ActionFactory
{
    private Map<ActionIdentifier, ActionProvider> actions;

    @Inject
    public WarcraftActionFactory(
        AttackFactory attackProvider,
        CameraFactory cameraFactory,
        ConfirmFactory confirmFactory,
        ConstructFactory constructFactory,
        GatherGold gatherGoldProvider,
        GatherWood gatherWoodProvider,
        GatherCancel gatherCancelProvider,
        MoveFactory moveFactory,
        NavigateFactory navigateFactory,
        PlaceholderFactory placeholderFactory,
        SelectFactory selectFactory,
        TrainFactory trainFactory)
    {
        actions = new HashMap<>();

        registerProvider(AttackActions.values(), attackProvider);
        registerProvider(CameraActions.values(), cameraFactory);
        registerProvider(ConstructActions.values(), constructFactory);
        registerProvider(ConfirmActions.values(), confirmFactory);

        registerProvider(GatherActions.GatherGold, gatherGoldProvider);
        registerProvider(GatherActions.GatherWood, gatherWoodProvider);
        registerProvider(CancelActions.CancelGather, gatherCancelProvider);

        registerProvider(MoveActions.values(), moveFactory);
        registerProvider(NavigateActions.values(), navigateFactory);
        registerProvider(PlaceholderActions.values(), placeholderFactory);
        registerProvider(SelectActions.values(), selectFactory);
        registerProvider(TrainActions.values(), trainFactory);
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
        action.setIdentifier(identifier);
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
