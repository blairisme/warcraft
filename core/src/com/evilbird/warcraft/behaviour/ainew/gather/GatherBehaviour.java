/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew.gather;

import com.badlogic.gdx.ai.btree.BranchTask;
import com.badlogic.gdx.ai.btree.Task;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.warcraft.object.data.player.Player;

import javax.inject.Inject;

/**
 * @author Blair Butterworth
 */
public class GatherBehaviour extends BranchTask<GameObjectContainer>
{
    private transient Player player;

    @Inject
    public GatherBehaviour() {
    }

    @Override
    public void run() {
        //find idle gatherer - assign them to a resource

//        GameObjectContainer container = getObject();
//        Player player = (Player)container.find(withId(Player1));
//        Collection<GameObject> gatherers = player.findAll(withType(Peon));
//        Collection<GameObject> idle = CollectionUtils.filter(gatherers, isIdle());



    }

    @Override
    public void childSuccess(Task<GameObjectContainer> task) {
    }

    @Override
    public void childFail(Task<GameObjectContainer> task) {
    }

    @Override
    public void childRunning(Task<GameObjectContainer> runningTask, Task<GameObjectContainer> reporter) {
    }

}
