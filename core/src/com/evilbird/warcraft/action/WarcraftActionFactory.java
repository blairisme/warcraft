/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.error.UnknownEntityException;
import com.evilbird.warcraft.action.attack.AttackActions;
import com.evilbird.warcraft.action.attack.AttackFactory;
import com.evilbird.warcraft.action.camera.CameraActions;
import com.evilbird.warcraft.action.camera.CameraFactory;
import com.evilbird.warcraft.action.confirm.ConfirmActions;
import com.evilbird.warcraft.action.confirm.ConfirmFactory;
import com.evilbird.warcraft.action.construct.ConstructActions;
import com.evilbird.warcraft.action.construct.ConstructFactory;
import com.evilbird.warcraft.action.gather.GatherActions;
import com.evilbird.warcraft.action.gather.GatherFactory;
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
        GatherFactory gatherFactory,
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
        registerProvider(GatherActions.values(), gatherFactory);
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
    public Action newAction(ActionIdentifier identifier) {
        ActionProvider provider = actions.get(identifier);
        if (provider != null) {
            Action result = provider.get(identifier);
            result.setIdentifier(identifier); //TODO: Evaluate if needed
            return result;
        }
        throw new UnknownEntityException(identifier);
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
