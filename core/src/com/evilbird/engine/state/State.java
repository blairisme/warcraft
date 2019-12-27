/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
