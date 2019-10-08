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
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.game.GameContext;
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
import com.evilbird.warcraft.action.menu.MenuActions;
import com.evilbird.warcraft.action.menu.MenuFactory;
import com.evilbird.warcraft.action.move.MoveActions;
import com.evilbird.warcraft.action.move.MoveFactory;
import com.evilbird.warcraft.action.placeholder.PlaceholderActions;
import com.evilbird.warcraft.action.placeholder.PlaceholderFactory;
import com.evilbird.warcraft.action.produce.ProduceFactory;
import com.evilbird.warcraft.action.produce.ProduceUnitActions;
import com.evilbird.warcraft.action.produce.ProduceUpgradeActions;
import com.evilbird.warcraft.action.select.SelectActions;
import com.evilbird.warcraft.action.select.SelectFactory;
import com.evilbird.warcraft.action.transport.TransportActions;
import com.evilbird.warcraft.action.transport.TransportFactory;
import org.apache.commons.lang3.Validate;

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
        MenuFactory menuFactory,
        PlaceholderFactory placeholderFactory,
        SelectFactory selectFactory,
        ProduceFactory produceFactory,
        TransportFactory transportFactory)
    {
        actions = new HashMap<>();

        registerProvider(AttackActions.values(), attackProvider);
        registerProvider(CameraActions.values(), cameraFactory);
        registerProvider(ConstructActions.values(), constructFactory);
        registerProvider(ConfirmActions.values(), confirmFactory);
        registerProvider(GatherActions.values(), gatherFactory);
        registerProvider(MoveActions.values(), moveFactory);
        registerProvider(MenuActions.values(), menuFactory);
        registerProvider(PlaceholderActions.values(), placeholderFactory);
        registerProvider(SelectActions.values(), selectFactory);
        registerProvider(ProduceUnitActions.values(), produceFactory);
        registerProvider(ProduceUpgradeActions.values(), produceFactory);
        registerProvider(TransportActions.values(), transportFactory);
    }

    @Override
    public void load(GameContext context) {
    }

    @Override
    public void unload(GameContext context) {
    }

    @Override
    public Action get(Identifier identifier) {
        Validate.isInstanceOf(ActionIdentifier.class, identifier);
        ActionIdentifier actionIdentifier = (ActionIdentifier)identifier;

        ActionProvider provider = actions.get(identifier);
        if (provider != null) {
            return provider.get(actionIdentifier);
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
