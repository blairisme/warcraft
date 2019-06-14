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
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;
import javax.inject.Provider;

import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.warcraft.behaviour.scenario.condition.PlayerDestruction.playerDestroyed;
import static com.evilbird.warcraft.behaviour.scenario.condition.PlayerOwnership.playerOwns;
import static com.evilbird.warcraft.behaviour.scenario.condition.UnitPositioned.unitRescued;
import static com.evilbird.warcraft.item.unit.UnitType.Barracks;
import static com.evilbird.warcraft.item.unit.UnitType.CircleOfPower;
import static com.evilbird.warcraft.item.unit.UnitType.ElvenArcherCaptive;
import static com.evilbird.warcraft.item.unit.UnitType.Farm;
import static com.evilbird.warcraft.item.unit.UnitType.OrcBarracks;
import static com.evilbird.warcraft.item.unit.UnitType.PigFarm;
import static com.evilbird.warcraft.item.unit.UnitType.Zuljin;

/**
 * Instances of this factory create {@link ScenarioBehaviour ScenarioBehaviours}
 * whose conditions are determined by a given {@link ScenarioBehaviours} value.
 *
 * @author Blair Butterworth
 */
public class ScenarioBehaviourFactory implements IdentifiedProvider<Behaviour>
{
    private Provider<ScenarioBehaviour> factory;

    @Inject
    public ScenarioBehaviourFactory(Provider<ScenarioBehaviour> factory) {
        this.factory = factory;
    }

    @Override
    public Behaviour get(Identifier identifier) {
        Validate.isInstanceOf(ScenarioBehaviours.class, identifier);
        ScenarioBehaviours type = (ScenarioBehaviours)identifier;

        switch (type) {
            case Human1: return humanLevel1();
            case Human2: return humanLevel2();
            case Orc1: return orcLevel1();
            case Orc2: return orcLevel2();
            default: throw new UnsupportedOperationException();
        }
    }

    private Behaviour humanLevel1() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(both(playerOwns(Farm, 4), playerOwns(Barracks, 1)));
        result.setLoseCondition(playerDestroyed());
        return result;
    }

    private Behaviour humanLevel2() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(unitRescued(ElvenArcherCaptive, CircleOfPower));
        result.setLoseCondition(playerDestroyed());
        return result;
    }

    private Behaviour orcLevel1() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(both(playerOwns(PigFarm, 4), playerOwns(OrcBarracks, 1)));
        result.setLoseCondition(playerDestroyed());
        return result;
    }

    private Behaviour orcLevel2() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(unitRescued(Zuljin, CircleOfPower));
        result.setLoseCondition(playerDestroyed());
        return result;
    }
}
