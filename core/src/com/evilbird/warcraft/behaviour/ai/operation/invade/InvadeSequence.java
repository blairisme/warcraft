/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.invade;

import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.evilbird.engine.behaviour.framework.guard.RandomWait;
import com.evilbird.engine.behaviour.framework.select.SelectFirstSubject;
import com.evilbird.engine.behaviour.framework.select.SelectRandomSubjects;
import com.evilbird.engine.behaviour.framework.select.SelectSubject;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.utility.GameObjectComparators;
import com.evilbird.warcraft.object.common.query.UnitOperations;

import javax.inject.Inject;
import java.util.Comparator;

import static com.evilbird.engine.common.function.Predicates.both;

/**
 * A behaviour sequence that instructs groups of idle attackers to attack an
 * enemy.
 *
 * @author Blair Butterworth
 */
public class InvadeSequence extends Sequence<InvadeData>
{
    @Inject
    @SuppressWarnings("unchecked")
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
            .waitMinimum(1f)
            .waitMaximum(2f);
    }

    private static Task<InvadeData> selectAttackers() {
//        Task<InvadeData> minimumAttackers = new SizeGuard<InvadeData>()
//            .from(InvadeData::getAttackers)
//            .minimum(4);

        return new SelectRandomSubjects<InvadeData>()
            .size(4)
            .when(both(UnitOperations::isAvailableAttacker, UnitOperations::isMovable))
            .from(InvadeData::getPlayer)
            .into(InvadeData::setAttackers);
//            .guard(minimumAttackers);
    }

    private static Task<InvadeData> selectEnemy() {
        return new SelectSubject<InvadeData>()
            .when(UnitOperations::isCorporeal) //needs to be other player
            .from(InvadeData::getWorld)
            .into(InvadeData::setEnemy);
    }

    private static Task<InvadeData> selectCommandCenter() {
        return new SelectSubject<InvadeData>()
            .when(UnitOperations::isCommandCentre)
            .from(InvadeData::getPlayer)
            .into(InvadeData::setPlayerCommand);
    }

    private static Task<InvadeData> selectTarget() {
        return new SelectFirstSubject<InvadeData>()
            .when(UnitOperations::isCommandCentre)
            .sort(InvadeSequence::closestToPlayer)
            .from(InvadeData::getEnemy)
            .into(InvadeData::setEnemyCommand);
    }

    private static Comparator<GameObject> closestToPlayer(InvadeData data) {
        return GameObjectComparators.closestItem(data.getPlayerCommand());
    }
}
