/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew;

import com.badlogic.gdx.ai.btree.branch.Parallel;
import com.evilbird.warcraft.behaviour.ainew.attack.AttackTree;
import com.evilbird.warcraft.behaviour.ainew.construct.ConstructTree;
import com.evilbird.warcraft.behaviour.ainew.gather.GatherTree;
import com.evilbird.warcraft.behaviour.ainew.invade.InvadeTree;
import com.evilbird.warcraft.behaviour.ainew.reorient.ReorientTree;

import javax.inject.Inject;

/**
 * A {@link PlayerBehaviour} specialization specifying the behaviour of enemy
 * players.
 *
 * @author Blair Butterworth
 */
public class EnemyBehaviour extends PlayerBehaviour
{
    @Inject
    public EnemyBehaviour(
        AttackTree attack,
        ConstructTree construct,
        GatherTree gather,
        InvadeTree invade,
        ReorientTree reorient)
    {
        super(new Parallel<>(
            attack,
            construct,
            gather,
            invade,
            reorient));
    }
}
