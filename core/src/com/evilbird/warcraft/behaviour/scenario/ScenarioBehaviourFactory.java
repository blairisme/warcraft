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
import com.evilbird.warcraft.behaviour.scenario.supplement.UnitCapture;
import com.evilbird.warcraft.common.WarcraftPreferences;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;
import javax.inject.Provider;

import static com.evilbird.engine.common.function.Predicates.all;
import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.warcraft.behaviour.scenario.condition.PlayerDestruction.playerCaptured;
import static com.evilbird.warcraft.behaviour.scenario.condition.PlayerDestruction.playerDestroyed;
import static com.evilbird.warcraft.behaviour.scenario.condition.PlayerOwnership.playerOwns;
import static com.evilbird.warcraft.behaviour.scenario.condition.UnitDestruction.unitsDestroyed;
import static com.evilbird.warcraft.behaviour.scenario.condition.UnitPositioned.unitRepositionedTo;
import static com.evilbird.warcraft.item.data.player.PlayerIds.Neutral;
import static com.evilbird.warcraft.item.data.player.PlayerIds.Player1;
import static com.evilbird.warcraft.item.data.player.PlayerIds.Player2;
import static com.evilbird.warcraft.item.data.player.PlayerIds.Player3;
import static com.evilbird.warcraft.item.data.player.PlayerIds.Player4;
import static com.evilbird.warcraft.item.unit.UnitType.Barracks;
import static com.evilbird.warcraft.item.unit.UnitType.Castle;
import static com.evilbird.warcraft.item.unit.UnitType.Chogall;
import static com.evilbird.warcraft.item.unit.UnitType.CircleOfPower;
import static com.evilbird.warcraft.item.unit.UnitType.DarkPortal;
import static com.evilbird.warcraft.item.unit.UnitType.Dockyard;
import static com.evilbird.warcraft.item.unit.UnitType.Encampment;
import static com.evilbird.warcraft.item.unit.UnitType.Farm;
import static com.evilbird.warcraft.item.unit.UnitType.Ferry;
import static com.evilbird.warcraft.item.unit.UnitType.Fortress;
import static com.evilbird.warcraft.item.unit.UnitType.OilPlatform;
import static com.evilbird.warcraft.item.unit.UnitType.OilRefinery;
import static com.evilbird.warcraft.item.unit.UnitType.OilRig;
import static com.evilbird.warcraft.item.unit.UnitType.PigFarm;
import static com.evilbird.warcraft.item.unit.UnitType.Refinery;
import static com.evilbird.warcraft.item.unit.UnitType.Runestone;
import static com.evilbird.warcraft.item.unit.UnitType.Shipyard;
import static com.evilbird.warcraft.item.unit.UnitType.UtherLightbringer;
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
    private WarcraftPreferences preferences;

    @Inject
    public ScenarioBehaviourFactory(
        Provider<ScenarioBehaviour> factory,
        WarcraftPreferences preferences)
    {
        this.factory = factory;
        this.preferences = preferences;
    }

    @Override
    public Behaviour get(Identifier identifier) {
        Validate.isInstanceOf(ScenarioBehaviours.class, identifier);
        ScenarioBehaviours type = (ScenarioBehaviours)identifier;
        return type.isHuman() ? humanCampaign(type): orcCampaign(type);
    }

    private Behaviour humanCampaign(ScenarioBehaviours type) {
        switch (type) {
            case HumanEasy: return conquest();
            case Human1: return humanCampaign1();
            case Human2: return humanCampaign2();
            case Human3: return humanCampaign3();
            case Human4: return humanCampaign4();
            case Human5: return humanCampaign5();
            case Human6: return humanCampaign6();
            case Human7: return humanCampaign7();
            case Human8: return humanCampaign8();
            case Human9: return humanCampaign9();
            case Human10: return humanCampaign10();
            case Human11: return humanCampaign11();
            case Human12: return humanCampaign12();
            case Human13: return humanCampaign13();
            case Human14: return humanCampaign14();
            default: throw new UnsupportedOperationException();
        }
    }

    private Behaviour conquest() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(playerDestroyed(Player2));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private Behaviour humanCampaign1() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(both(playerOwns(Farm, 4), playerOwns(Barracks, 1)));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private Behaviour humanCampaign2() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(unitRepositionedTo("ElvenArcherCaptive", CircleOfPower));
        result.setLoseCondition(playerDestroyed(Player1));
        result.addBehaviour(new UnitCapture(preferences));
        return result;
    }

    private Behaviour humanCampaign3() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(both(playerOwns(Shipyard, 1), playerOwns(OilPlatform, 4)));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private Behaviour humanCampaign4() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(playerDestroyed(Player2));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private Behaviour humanCampaign5() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(playerDestroyed(Player2));
        result.setLoseCondition(playerDestroyed(Player1));
        result.addBehaviour(new UnitCapture(preferences));
        return result;
    }

    private Behaviour humanCampaign6() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(playerDestroyed(Player2));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private Behaviour humanCampaign7() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(unitsDestroyed(Player2, OilRefinery));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private Behaviour humanCampaign8() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(both(playerOwns(Castle, 1), playerDestroyed(Player2)));
        result.setLoseCondition(playerDestroyed(Player1));
        result.addBehaviour(new UnitCapture(preferences));
        return result;
    }

    private Behaviour humanCampaign9() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(unitRepositionedTo(UtherLightbringer, CircleOfPower));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private Behaviour humanCampaign10() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(unitRepositionedTo("AlteracTraitor", CircleOfPower, 4));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private Behaviour humanCampaign11() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(
            playerCaptured(Player2)
            .and(playerDestroyed(Player3))
            .and(playerDestroyed(Player4)));
        result.setLoseCondition(playerDestroyed(Player1));
        result.addBehaviour(new UnitCapture(preferences));
        return result;
    }

    private Behaviour humanCampaign12() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(all(
            unitsDestroyed(Player2, Ferry),
            unitsDestroyed(Player2, OilRig),
            unitsDestroyed(Player2, Dockyard)));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private Behaviour humanCampaign13() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(playerDestroyed(Player3));
        result.setLoseCondition(playerDestroyed(Player1));
        result.addBehaviour(new UnitCapture(preferences));
        return result;
    }

    private Behaviour humanCampaign14() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(unitsDestroyed(Neutral, DarkPortal));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private Behaviour orcCampaign(ScenarioBehaviours type) {
        switch (type) {
            case OrcEasy: return conquest();
            case Orc1: return orcCampaign1();
            case Orc2: return orcCampaign2();
            case Orc3: return orcCampaign3();
            case Orc4: return orcCampaign4();
            case Orc5: return orcCampaign5();
            case Orc6: return orcCampaign6();
            case Orc7: return orcCampaign7();
            case Orc8: return orcCampaign8();
            case Orc9: return orcCampaign9();
            case Orc10: return orcCampaign10();
            case Orc11: return orcCampaign11();
            case Orc12: return orcCampaign12();
            case Orc13: return orcCampaign13();
            case Orc14: return orcCampaign14();
            default: throw new UnsupportedOperationException();
        }
    }

    private Behaviour orcCampaign1() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(both(playerOwns(PigFarm, 4), playerOwns(Encampment, 1)));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private Behaviour orcCampaign2() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(unitRepositionedTo(Zuljin, CircleOfPower));
        result.setLoseCondition(playerDestroyed(Player1));
        result.addBehaviour(new UnitCapture(preferences));
        return result;
    }

    private Behaviour orcCampaign3() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(both(playerOwns(Dockyard, 1), playerOwns(OilRig, 4)));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private Behaviour orcCampaign4() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(playerDestroyed(Player2));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private Behaviour orcCampaign5() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(playerDestroyed(Player2));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private Behaviour orcCampaign6() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(unitRepositionedTo(Chogall, CircleOfPower));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private Behaviour orcCampaign7() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(playerDestroyed(Player2));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private Behaviour orcCampaign8() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(both(playerOwns(Runestone, 1), playerDestroyed(Player2)));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private Behaviour orcCampaign9() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(both(playerOwns(Fortress, 1), playerOwns(Dockyard, 1)));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private Behaviour orcCampaign10() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(all(
                unitsDestroyed(Player2, OilPlatform),
                unitsDestroyed(Player2, Refinery),
                playerDestroyed(Player3)));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private Behaviour orcCampaign11() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(playerDestroyed(Player2));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private Behaviour orcCampaign12() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(both(playerDestroyed(Player2), playerDestroyed(Player3)));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private Behaviour orcCampaign13() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(both(playerDestroyed(Player2), playerDestroyed(Player3)));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private Behaviour orcCampaign14() {
        ScenarioBehaviour result = factory.get();
        result.setWinCondition(playerDestroyed(Player2));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }
}