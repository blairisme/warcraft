/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ui.objective;

import com.evilbird.engine.behaviour.BehaviourElement;
import com.evilbird.engine.common.inject.IdentifiedProvider;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.warcraft.behaviour.WarcraftBehaviour;
import com.evilbird.warcraft.behaviour.ui.objective.condition.PlayerDestruction;
import com.evilbird.warcraft.behaviour.ui.objective.supplement.UnitCapture;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;
import javax.inject.Provider;

import static com.evilbird.engine.common.function.Predicates.all;
import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.warcraft.behaviour.ui.objective.condition.PlayerDestruction.playerDestroyed;
import static com.evilbird.warcraft.behaviour.ui.objective.condition.PlayerOwnership.playerOwns;
import static com.evilbird.warcraft.behaviour.ui.objective.condition.UnitDestruction.unitsDestroyed;
import static com.evilbird.warcraft.behaviour.ui.objective.condition.UnitPositioned.unitRepositionedTo;
import static com.evilbird.warcraft.object.data.player.PlayerIds.Neutral;
import static com.evilbird.warcraft.object.data.player.PlayerIds.Player1;
import static com.evilbird.warcraft.object.data.player.PlayerIds.Player2;
import static com.evilbird.warcraft.object.data.player.PlayerIds.Player3;
import static com.evilbird.warcraft.object.data.player.PlayerIds.Player4;
import static com.evilbird.warcraft.object.data.player.PlayerIds.Player5;
import static com.evilbird.warcraft.object.data.player.PlayerIds.Player6;
import static com.evilbird.warcraft.object.unit.UnitType.Barracks;
import static com.evilbird.warcraft.object.unit.UnitType.Castle;
import static com.evilbird.warcraft.object.unit.UnitType.Chogall;
import static com.evilbird.warcraft.object.unit.UnitType.CircleOfPower;
import static com.evilbird.warcraft.object.unit.UnitType.DarkPortal;
import static com.evilbird.warcraft.object.unit.UnitType.Dockyard;
import static com.evilbird.warcraft.object.unit.UnitType.Encampment;
import static com.evilbird.warcraft.object.unit.UnitType.Farm;
import static com.evilbird.warcraft.object.unit.UnitType.Ferry;
import static com.evilbird.warcraft.object.unit.UnitType.Fortress;
import static com.evilbird.warcraft.object.unit.UnitType.OilPlatform;
import static com.evilbird.warcraft.object.unit.UnitType.OilRefinery;
import static com.evilbird.warcraft.object.unit.UnitType.OilRig;
import static com.evilbird.warcraft.object.unit.UnitType.PigFarm;
import static com.evilbird.warcraft.object.unit.UnitType.Refinery;
import static com.evilbird.warcraft.object.unit.UnitType.Runestone;
import static com.evilbird.warcraft.object.unit.UnitType.Shipyard;
import static com.evilbird.warcraft.object.unit.UnitType.UtherLightbringer;
import static com.evilbird.warcraft.object.unit.UnitType.Zuljin;

/**
 * Instances of this factory create {@link ObjectiveBehaviour ScenarioBehaviours}
 * whose conditions are determined by a given {@link WarcraftBehaviour} value.
 *
 * @author Blair Butterworth
 */
public class ObjectiveBehaviourFactory implements IdentifiedProvider<BehaviourElement>
{
    private Provider<ObjectiveBehaviour> factory;

    @Inject
    public ObjectiveBehaviourFactory(Provider<ObjectiveBehaviour> factory) {
        this.factory = factory;
    }

    @Override
    public BehaviourElement get(Identifier identifier) {
        Validate.isInstanceOf(WarcraftBehaviour.class, identifier);
        WarcraftBehaviour type = (WarcraftBehaviour)identifier;

        if (type.isHumanCampaign()) {
            return humanCampaign(type);
        }
        if (type.isOrcCampaign()) {
            return orcCampaign(type);
        }
        return conquest();
    }

    private BehaviourElement humanCampaign(WarcraftBehaviour type) {
        switch (type) {
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

    private BehaviourElement conquest() {
        ObjectiveBehaviour result = factory.get();
        result.setWinCondition(playerDestroyed(Player2));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private BehaviourElement humanCampaign1() {
        ObjectiveBehaviour result = factory.get();
        result.setWinCondition(both(playerOwns(Farm, 4), playerOwns(Barracks, 1)));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private BehaviourElement humanCampaign2() {
        ObjectiveBehaviour result = factory.get();
        result.setWinCondition(unitRepositionedTo("ElvenArcherCaptive", CircleOfPower));
        result.setLoseCondition(playerDestroyed(Player1));
        result.addBehaviour(new UnitCapture());
        return result;
    }

    private BehaviourElement humanCampaign3() {
        ObjectiveBehaviour result = factory.get();
        result.setWinCondition(both(playerOwns(Shipyard, 1), playerOwns(OilPlatform, 4)));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private BehaviourElement humanCampaign4() {
        ObjectiveBehaviour result = factory.get();
        result.setWinCondition(playerDestroyed(Player2));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private BehaviourElement humanCampaign5() {
        ObjectiveBehaviour result = factory.get();
        result.setWinCondition(playerDestroyed(Player2));
        result.setLoseCondition(playerDestroyed(Player1));
        result.addBehaviour(new UnitCapture());
        return result;
    }

    private BehaviourElement humanCampaign6() {
        ObjectiveBehaviour result = factory.get();
        result.setWinCondition(playerDestroyed(Player2));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private BehaviourElement humanCampaign7() {
        ObjectiveBehaviour result = factory.get();
        result.setWinCondition(unitsDestroyed(Player3, OilRefinery));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private BehaviourElement humanCampaign8() {
        ObjectiveBehaviour result = factory.get();
        result.setWinCondition(
            playerOwns(Castle, 1)
            .and(PlayerDestruction.playerCaptured(Player2))
            .and(playerDestroyed(Player3))
            .and(playerDestroyed(Player4))
            .and(playerDestroyed(Player5))
            .and(playerDestroyed(Player6)));
        result.setLoseCondition(playerDestroyed(Player1));
        result.addBehaviour(new UnitCapture());
        return result;
    }

    private BehaviourElement humanCampaign9() {
        ObjectiveBehaviour result = factory.get();
        result.setWinCondition(unitRepositionedTo(UtherLightbringer, CircleOfPower));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private BehaviourElement humanCampaign10() {
        ObjectiveBehaviour result = factory.get();
        result.setWinCondition(unitRepositionedTo("AlteracTraitor", CircleOfPower, 4));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private BehaviourElement humanCampaign11() {
        ObjectiveBehaviour result = factory.get();
        result.setWinCondition(
            PlayerDestruction.playerCaptured(Player2)
            .and(playerDestroyed(Player3))
            .and(playerDestroyed(Player4)));
        result.setLoseCondition(playerDestroyed(Player1));
        result.addBehaviour(new UnitCapture());
        return result;
    }

    private BehaviourElement humanCampaign12() {
        ObjectiveBehaviour result = factory.get();
        result.setWinCondition(all(
            unitsDestroyed(Player2, Ferry),
            unitsDestroyed(Player2, OilRig),
            unitsDestroyed(Player2, Dockyard)));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private BehaviourElement humanCampaign13() {
        ObjectiveBehaviour result = factory.get();
        result.setWinCondition(playerDestroyed(Player3));
        result.setLoseCondition(playerDestroyed(Player1));
        result.addBehaviour(new UnitCapture());
        return result;
    }

    private BehaviourElement humanCampaign14() {
        ObjectiveBehaviour result = factory.get();
        result.setWinCondition(unitsDestroyed(Neutral, DarkPortal));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private BehaviourElement orcCampaign(WarcraftBehaviour type) {
        switch (type) {
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

    private BehaviourElement orcCampaign1() {
        ObjectiveBehaviour result = factory.get();
        result.setWinCondition(both(playerOwns(PigFarm, 4), playerOwns(Encampment, 1)));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private BehaviourElement orcCampaign2() {
        ObjectiveBehaviour result = factory.get();
        result.setWinCondition(unitRepositionedTo(Zuljin, CircleOfPower));
        result.setLoseCondition(playerDestroyed(Player1));
        result.addBehaviour(new UnitCapture());
        return result;
    }

    private BehaviourElement orcCampaign3() {
        ObjectiveBehaviour result = factory.get();
        result.setWinCondition(both(playerOwns(Dockyard, 1), playerOwns(OilRig, 4)));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private BehaviourElement orcCampaign4() {
        ObjectiveBehaviour result = factory.get();
        result.setWinCondition(playerDestroyed(Player2));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private BehaviourElement orcCampaign5() {
        ObjectiveBehaviour result = factory.get();
        result.setWinCondition(playerDestroyed(Player2));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private BehaviourElement orcCampaign6() {
        ObjectiveBehaviour result = factory.get();
        result.setWinCondition(unitRepositionedTo(Chogall, CircleOfPower));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private BehaviourElement orcCampaign7() {
        ObjectiveBehaviour result = factory.get();
        result.setWinCondition(playerDestroyed(Player2));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private BehaviourElement orcCampaign8() {
        ObjectiveBehaviour result = factory.get();
        result.setWinCondition(both(playerOwns(Runestone, 1), playerDestroyed(Player2)));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private BehaviourElement orcCampaign9() {
        ObjectiveBehaviour result = factory.get();
        result.setWinCondition(both(playerOwns(Fortress, 1), playerOwns(Dockyard, 1)));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private BehaviourElement orcCampaign10() {
        ObjectiveBehaviour result = factory.get();
        result.setWinCondition(all(
                unitsDestroyed(Player2, OilPlatform),
                unitsDestroyed(Player2, Refinery),
                playerDestroyed(Player3)));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private BehaviourElement orcCampaign11() {
        ObjectiveBehaviour result = factory.get();
        result.setWinCondition(playerDestroyed(Player2));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private BehaviourElement orcCampaign12() {
        ObjectiveBehaviour result = factory.get();
        result.setWinCondition(both(playerDestroyed(Player2), playerDestroyed(Player3)));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private BehaviourElement orcCampaign13() {
        ObjectiveBehaviour result = factory.get();
        result.setWinCondition(both(playerDestroyed(Player2), playerDestroyed(Player3)));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }

    private BehaviourElement orcCampaign14() {
        ObjectiveBehaviour result = factory.get();
        result.setWinCondition(playerDestroyed(Player2));
        result.setLoseCondition(playerDestroyed(Player1));
        return result;
    }
}