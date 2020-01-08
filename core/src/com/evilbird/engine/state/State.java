/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.state;

import com.badlogic.gdx.utils.Disposable;
import com.evilbird.engine.audio.music.Music;
import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.object.GameObjectContainer;

/**
 * Instances of class represent the game state, a snapshot of the all game
 * objects and their properties at a given point in time.
 *
 * @author Blair Butterworth
 */
public interface State extends Disposable
{
    GameObjectContainer getWorld();

    GameObjectContainer getHud();

    Behaviour getBehaviour();

    Music getMusic();
}
