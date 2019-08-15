/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.game.GameContext;
import com.evilbird.test.testcase.GameFactoryTestCase;
import com.evilbird.warcraft.behaviour.ai.AiBehaviourFactory;
import com.evilbird.warcraft.behaviour.scenario.ScenarioBehaviourFactory;
import com.evilbird.warcraft.behaviour.ui.UiBehaviourFactory;
import com.evilbird.warcraft.state.WarcraftContext;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftFaction.Orc;
import static com.evilbird.warcraft.common.WarcraftSeason.Summer;
import static com.evilbird.warcraft.common.WarcraftSeason.Winter;

/**
 * Instances of this unit test validate the {@link WarcraftBehaviourFactory} class.
 *
 * @author Blair Butterworth
 */
public class WarcraftBehaviourFactoryTest extends GameFactoryTestCase<WarcraftBehaviourFactory> {

    @Override
    protected WarcraftBehaviourFactory newFactory(DeviceDisplay display, AssetManager assets) {
        AiBehaviourFactory aiBehaviours = Mockito.mock(AiBehaviourFactory.class);
        UiBehaviourFactory uiBehaviours = Mockito.mock(UiBehaviourFactory.class);
        ScenarioBehaviourFactory scenarioBehaviours = Mockito.mock(ScenarioBehaviourFactory.class);
        return new WarcraftBehaviourFactory(aiBehaviours, uiBehaviours, scenarioBehaviours);
    }

    @Override
    protected Collection<GameContext> getLoadContexts() {
        return Arrays.asList(
            new WarcraftContext(Human, Summer),
            new WarcraftContext(Orc, Winter));
    }

    @Override
    protected Collection<Identifier> getProductIdentifiers() {
        return Arrays.asList(WarcraftBehaviour.values());
    }

    @Override
    protected Map<String, Object> getProductProperties() {
        return Collections.emptyMap();
    }

    @Override
    protected boolean isAssetLoader() {
        return false;
    }
}