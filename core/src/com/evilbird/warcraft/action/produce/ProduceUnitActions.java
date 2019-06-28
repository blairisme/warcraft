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
import com.evilbird.warcraft.item.unit.UnitType;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

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
        return this.ordinal() >= TrainBallistaCancel.ordinal();
    }

    public UnitType getProduct() {
        return getProductValue(UnitType.class, getProductName());
    }

    private String getProductName() {
        String name = this.name();
        name = StringUtils.removeStart(name, "Train");
        name = StringUtils.removeEnd(name, "Cancel");
        return name;
    }

    private <T extends Enum<T>> T getProductValue(Class<T> type, String name) {
        if (EnumUtils.isValidEnum(type, name)) {
            return Enum.valueOf(type, name);
        }
        throw new UnsupportedOperationException();
    }
}
