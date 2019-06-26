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
import com.evilbird.engine.behaviour.BehaviourIdentifier;
import com.evilbird.engine.behaviour.CompositeBehaviour;
import com.evilbird.warcraft.behaviour.ai.AiBehaviourFactory;
import com.evilbird.warcraft.behaviour.ai.AiBehaviours;
import com.evilbird.warcraft.behaviour.scenario.ScenarioBehaviourFactory;
import com.evilbird.warcraft.behaviour.scenario.ScenarioBehaviours;
import com.evilbird.warcraft.behaviour.ui.UiBehaviourFactory;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

import static com.evilbird.warcraft.behaviour.ai.AiBehaviours.HumanEasy;
import static com.evilbird.warcraft.behaviour.ai.AiBehaviours.OrcEasy;
import static com.evilbird.warcraft.behaviour.scenario.ScenarioBehaviours.Human1;
import static com.evilbird.warcraft.behaviour.scenario.ScenarioBehaviours.Human2;
import static com.evilbird.warcraft.behaviour.scenario.ScenarioBehaviours.Orc1;
import static com.evilbird.warcraft.behaviour.scenario.ScenarioBehaviours.Orc2;

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
    public void load() {
    }

    @Override
    public Behaviour newBehaviour(BehaviourIdentifier id) {
        Validate.isInstanceOf(WarcraftBehaviour.class, id);
        WarcraftBehaviour type = (WarcraftBehaviour)id;

        switch(type) {
            case Human1: return newLevelBehaviour(id, Human1, HumanEasy);
            case Human2: return newLevelBehaviour(id, Human2, HumanEasy);
            case Orc1: return newLevelBehaviour(id, Orc1, OrcEasy);
            case Orc2: return newLevelBehaviour(id, Orc2, OrcEasy);

            default: throw new UnsupportedOperationException();
        }
    }

    private Behaviour newLevelBehaviour(BehaviourIdentifier id, ScenarioBehaviours scenario, AiBehaviours ai) {
        Behaviour aiBehaviour = aiBehaviours.get(ai);
        Behaviour uiBehaviour = uiBehaviours.get();
        Behaviour scenarioBehaviour = scenarioBehaviours.get(scenario);
        return new CompositeBehaviour(id, aiBehaviour, uiBehaviour, scenarioBehaviour);
    }
}
