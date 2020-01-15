/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.state.State;

import javax.inject.Inject;
import java.util.List;

public class AiBehaviour implements Behaviour
{
    private BehaviorTree<State> tree;

    @Inject
    public AiBehaviour() {
    }

    @Override
    public void update(State state, List<UserInput> input, float time) {
        if (tree == null) {
            tree = new BehaviorTree<>();
            //tree.addChild(gather);
            //tree.addChild(construct);
            //tree.addChild(produce);
            //tree.addChild(attack);
            tree.setObject(state);
        }
        tree.step();
    }
}
