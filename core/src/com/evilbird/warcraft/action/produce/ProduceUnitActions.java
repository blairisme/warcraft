/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.produce;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.warcraft.object.unit.UnitType;
import org.apache.commons.lang3.Validate;

import static com.evilbird.engine.common.collection.EnumUtils.getName;
import static com.evilbird.engine.common.collection.EnumUtils.isBetween;

/**
 * Defines options of specifying production action varieties.
 *
 * @author Blair Butterworth
 */
public enum ProduceUnitActions implements ActionIdentifier
{
    TrainBallista,
    TrainBattleship,
    TrainDwarvenDemolitionSquad,
    TrainElvenArcher,
    TrainElvenDestroyer,
    TrainElvenRanger,
    TrainFootman,
    TrainGnomishFlyingMachine,
    TrainGnomishSubmarine,
    TrainGryphonRider,
    TrainKnight,
    TrainMage,
    TrainOilTanker,
    TrainPaladin,
    TrainPeasant,
    TrainTransport,

    TrainCatapult,
    TrainDeathKnight,
    TrainDragon,
    TrainEyeOfKilrogg,
    TrainFerry,
    TrainGiantTurtle,
    TrainGoblinSappers,
    TrainGoblinZeppelin,
    TrainGrunt,
    TrainOgre,
    TrainOgreJuggernaught,
    TrainOgreMage,
    TrainPeon,
    TrainTrollAxethrower,
    TrainTrollBerserker,
    TrainTrollDestroyer,
    TrainTrollTanker,

    TrainBallistaCancel,
    TrainBattleshipCancel,
    TrainDwarvenDemolitionSquadCancel,
    TrainElvenArcherCancel,
    TrainElvenDestroyerCancel,
    TrainElvenRangerCancel,
    TrainFootmanCancel,
    TrainGnomishFlyingMachineCancel,
    TrainGnomishSubmarineCancel,
    TrainGryphonRiderCancel,
    TrainKnightCancel,
    TrainMageCancel,
    TrainOilTankerCancel,
    TrainPaladinCancel,
    TrainPeasantCancel,
    TrainTransportCancel,

    TrainCatapultCancel,
    TrainDeathKnightCancel,
    TrainDragonCancel,
    TrainEyeOfKilroggCancel,
    TrainFerryCancel,
    TrainGiantTurtleCancel,
    TrainGoblinSappersCancel,
    TrainGoblinZeppelinCancel,
    TrainGruntCancel,
    TrainOgreCancel,
    TrainOgreJuggernaughtCancel,
    TrainOgreMageCancel,
    TrainPeonCancel,
    TrainTrollAxethrowerCancel,
    TrainTrollBerserkerCancel,
    TrainTrollDestroyerCancel,
    TrainTrollTankerCancel;

    public boolean isCancel() {
        return isBetween(this, TrainBallistaCancel, TrainTrollTankerCancel);
    }

    public UnitType getProduct() {
        return UnitType.valueOf(getName(this, "Train", "Cancel"));
    }

    public static ProduceUnitActions forProduct(UnitType type) {
        Validate.isTrue(type.isCombatant());
        return ProduceUnitActions.valueOf("Train" + type.name());
    }

    public static ProduceUnitActions forProductCancel(UnitType type) {
        Validate.isTrue(type.isCombatant());
        return ProduceUnitActions.valueOf("Train" + type.name() + "Cancel");
    }
}
