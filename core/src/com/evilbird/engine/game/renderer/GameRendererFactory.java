/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.game.renderer;

import com.evilbird.engine.state.StateIdentifier;
import com.evilbird.engine.state.StateService;
import com.evilbird.engine.state.State;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Convenience factory for creating {@link GameRenderer} instances for a given
 * {@link State}.
 *
 * @author Blair Butterworth
 */
public class GameRendererFactory
{
    private StateService stateService;
    private Provider<GameRenderer> rendererFactory;

    @Inject
    public GameRendererFactory(Provider<GameRenderer> rendererFactory, StateService stateService) {
        this.stateService = stateService;
        this.rendererFactory = rendererFactory;
    }

    public GameRenderer get(StateIdentifier identifier) {
        GameRenderer result = rendererFactory.get();
        result.setState(stateService.get(identifier));
        return result;
    }
}
