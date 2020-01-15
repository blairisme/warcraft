/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.object.GameObjectType;
import com.evilbird.engine.state.State;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.List;

import static com.evilbird.warcraft.object.unit.UnitType.Encampment;
import static com.evilbird.warcraft.object.unit.UnitType.Farm;
import static com.evilbird.warcraft.object.unit.UnitType.Peon;
import static com.evilbird.warcraft.object.unit.UnitType.TownHall;

public class ProduceBehaviour extends BehaviorTree<State>
{
    private Identifier playerId;

    private List<Pair<GameObjectType, Integer>> schedule = Arrays.asList(
        Pair.of(Peon, 1),
        Pair.of(TownHall, 1),
        Pair.of(Farm, 1),
        Pair.of(Peon, 2),
        Pair.of(Encampment, 1),
        Pair.of(Peon, 2));

}
