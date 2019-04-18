/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.scenario;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.common.inject.IdentifiedProvider;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.warcraft.item.unit.Unit;

import javax.inject.Inject;
import javax.inject.Provider;

import static com.evilbird.engine.common.collection.Maps.of;
import static com.evilbird.warcraft.behaviour.scenario.ScenarioConditions.playerHasMinimum;
import static com.evilbird.warcraft.behaviour.scenario.ScenarioConditions.playerHasNone;
import static com.evilbird.warcraft.item.unit.UnitType.Farm;
import static com.evilbird.warcraft.item.unit.UnitType.Footman;

public class ScenarioBehaviourFactory implements IdentifiedProvider<Behaviour>
{
    private Provider<ScenarioBehaviour> factory;

    @Inject
    public ScenarioBehaviourFactory(Provider<ScenarioBehaviour> factory) {
        this.factory = factory;
    }

    @Override
    public Behaviour get(Identifier identifier) {
        ScenarioBehaviours type = (ScenarioBehaviours)identifier;

        switch (type) {
            case Human1: return humanLevel1();
            default: throw new UnsupportedOperationException();
        }
    }

    private Behaviour humanLevel1() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(playerHasMinimum(of(Farm, 3, Footman, 3)));
        result.setLoseCondition(playerHasNone(Unit.class));
        return result;
    }
}
