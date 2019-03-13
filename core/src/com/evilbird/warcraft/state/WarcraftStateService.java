/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.evilbird.engine.common.error.UnknownEntityException;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.state.State;
import com.evilbird.engine.state.StateService;
import com.evilbird.engine.state.StateType;
import com.evilbird.warcraft.state.asset.AssetState;
import com.evilbird.warcraft.state.asset.AssetStateService;
import com.evilbird.engine.state.StateIdentifier;
import com.evilbird.warcraft.state.user.UserStateIdentifier;
import com.evilbird.warcraft.state.user.UserStateService;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

/**
 * Instances of this {@link StateService} provide access to Warcraft states.
 *
 * @author Blair Butterworth
 */
public class WarcraftStateService implements StateService
{
    private AssetStateService assetStateService;
    private UserStateService userStateService;

    @Inject
    public WarcraftStateService(AssetStateService assetStateService, UserStateService userStateService) {
        this.assetStateService = assetStateService;
        this.userStateService = userStateService;
    }

    @Override
    public void load() {
    }

    @Override
    public List<Identifier> list(StateType category) throws IOException {
        Validate.isInstanceOf(WarcraftStateType.class, category);
        if (category == WarcraftStateType.AssetState) {
            return assetStateService.list();
        }
        return userStateService.list();
    }

    @Override
    public State get(StateIdentifier identifier) throws IOException {
        if (identifier instanceof AssetState) {
            return assetStateService.get((AssetState)identifier);
        }
        if (identifier instanceof UserStateIdentifier) {
            return userStateService.get((UserStateIdentifier)identifier);
        }
        throw new UnknownEntityException(identifier);
    }


    @Override
    public void set(StateIdentifier identifier, State state) throws IOException {
        Validate.isInstanceOf(UserStateIdentifier.class, identifier);
        userStateService.set((UserStateIdentifier)identifier, state);
    }

    @Override
    public void remove(StateIdentifier identifier) throws IOException {
        Validate.isInstanceOf(UserStateIdentifier.class, identifier);
        userStateService.remove((UserStateIdentifier)identifier);
    }
}
