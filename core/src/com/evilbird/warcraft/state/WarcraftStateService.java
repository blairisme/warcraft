/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.state.State;
import com.evilbird.engine.state.StateService;
import com.evilbird.warcraft.state.asset.AssetState;
import com.evilbird.warcraft.state.asset.AssetStateService;
import com.evilbird.warcraft.state.user.UserState;
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
    public List<Identifier> list(Identifier category) throws IOException {
        Validate.isInstanceOf(StateType.class, category);
        if (category == StateType.AssetState) {
            return assetStateService.list();
        }
        return userStateService.list();
    }

    @Override
    public State get(Identifier identifier) throws IOException {
        Validate.isInstanceOf(StateIdentifier.class, identifier);
        if (identifier instanceof AssetState) {
            return assetStateService.get((AssetState)identifier);
        }
        return userStateService.get((UserState)identifier);
    }


    @Override
    public void set(Identifier identifier, State state) throws IOException {
        Validate.isInstanceOf(UserState.class, identifier);
        userStateService.set((UserState)identifier, state);
    }

    @Override
    public void remove(Identifier identifier) throws IOException {
        Validate.isInstanceOf(UserState.class, identifier);
        userStateService.remove((UserState)identifier);
    }
}
