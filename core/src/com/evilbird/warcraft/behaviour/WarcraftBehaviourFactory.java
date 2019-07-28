/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.behaviour.BehaviourFactory;
import com.evilbird.engine.behaviour.CompositeBehaviour;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.game.GameContext;
import com.evilbird.warcraft.behaviour.ai.AiBehaviourFactory;
import com.evilbird.warcraft.behaviour.ai.AiBehaviours;
import com.evilbird.warcraft.behaviour.scenario.ScenarioBehaviourFactory;
import com.evilbird.warcraft.behaviour.scenario.ScenarioBehaviours;
import com.evilbird.warcraft.behaviour.ui.UiBehaviourFactory;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

import static com.evilbird.warcraft.behaviour.ai.AiBehaviours.HumanEasy;
import static com.evilbird.warcraft.behaviour.ai.AiBehaviours.OrcEasy;

/**
 * Instances of this class defines the game logic that modifies the state of
 * the game in response to user input, time and other factors.
 *
 * @author Blair Butterworth
 */
public class WarcraftBehaviourFactory implements BehaviourFactory
{
    private AiBehaviourFactory aiBehaviours;
    private UiBehaviourFactory uiBehaviours;
    private ScenarioBehaviourFactory scenarioBehaviours;

    @Inject
    public WarcraftBehaviourFactory(
        AiBehaviourFactory aiBehaviours,
        UiBehaviourFactory uiBehaviours,
        ScenarioBehaviourFactory scenarioBehaviours)
    {
        this.aiBehaviours = aiBehaviours;
        this.uiBehaviours = uiBehaviours;
        this.scenarioBehaviours = scenarioBehaviours;
    }

    @Override
    public void load(GameContext context) {
    }

    @Override
    public void unload(GameContext context) {
    }

    @Override
    public Behaviour get(Identifier id) {
        Validate.isInstanceOf(WarcraftBehaviour.class, id);
        WarcraftBehaviour type = (WarcraftBehaviour)id;

        ScenarioBehaviours scenario = ScenarioBehaviours.valueOf(type.name());
        AiBehaviours difficulty = type.isHuman() ? HumanEasy : OrcEasy;
        return newLevelBehaviour(type, scenario, difficulty);
    }

    private Behaviour newLevelBehaviour(WarcraftBehaviour id, ScenarioBehaviours scenario, AiBehaviours ai) {
        Behaviour aiBehaviour = aiBehaviours.get(ai);
        Behaviour uiBehaviour = uiBehaviours.get();
        Behaviour scenarioBehaviour = scenarioBehaviours.get(scenario);
        return new CompositeBehaviour(id, aiBehaviour, uiBehaviour, scenarioBehaviour);
    }
}
