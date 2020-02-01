/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew.invade;

import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.utility.GameObjectComparators;
import com.evilbird.warcraft.behaviour.ainew.common.framework.RandomWait;
import com.evilbird.warcraft.behaviour.ainew.common.select.SelectFirstSubject;
import com.evilbird.warcraft.behaviour.ainew.common.select.SelectSubject;
import com.evilbird.warcraft.behaviour.ainew.common.select.SelectSubjects;
import com.evilbird.warcraft.object.common.query.UnitOperations;

import javax.inject.Inject;
import java.util.Comparator;

/**
 * A behaviour sequence that instructs groups of idle attackers to attack an
 * enemy.
 *
 * @author Blair Butterworth
 */
public class InvadeSequence extends Sequence<InvadeData>
{
    @Inject
    public InvadeSequence(InvadeTask invade) {
        super(delayEvaluation(),
            selectAttackers(),
            selectEnemy(),
            selectCommandCenter(),
            selectTarget(),
            invade);
    }

    private static Task<InvadeData> delayEvaluation() {
        return new RandomWait<InvadeData>()
            .setWaitMinimum(1f)
            .setWaitMaximum(2f);
    }

    private static Task<InvadeData> selectEnemy() {
        return new SelectSubject<InvadeData>()
            .setCondition(UnitOperations::isArtificial)
            .setTarget(InvadeData::getWorld)
            .setReceiver(InvadeData::setEnemy);
    }

    private static Task<InvadeData> selectAttackers() {
        return new SelectSubjects<InvadeData>()
            .setCondition(UnitOperations::isAvailableAttacker)
            .setTarget(InvadeData::getPlayer)
            .setReceiver(InvadeData::setAttackers);
    }

    private static Task<InvadeData> selectCommandCenter() {
        return new SelectSubject<InvadeData>()
            .setCondition(UnitOperations::isCommandCentre)
            .setTarget(InvadeData::getPlayer)
            .setReceiver(InvadeData::setPlayerCommand);
    }

    private static Task<InvadeData> selectTarget() {
        return new SelectFirstSubject<InvadeData>()
            .setCondition(UnitOperations::isCommandCentre)
            .setComparator(InvadeSequence::closestToPlayer)
            .setTarget(InvadeData::getEnemy)
            .setReceiver(InvadeData::setEnemyCommand);
    }

    private static Comparator<GameObject> closestToPlayer(InvadeData data) {
        return GameObjectComparators.closestItem(data.getPlayerCommand());
    }
}
