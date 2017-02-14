package com.evilbird.engine.loader;

import com.badlogic.gdx.Screen;
import com.evilbird.engine.game.GameScreenManager;

/**
 * Implementors of this interface provice methods that load the assets and state required to render
 * and interact with the game engine.
 *
 * @author Blair Butterworth
 */
public interface GameLoader extends Screen
{
    public void setScreenManager(GameScreenManager screenManager);
}
