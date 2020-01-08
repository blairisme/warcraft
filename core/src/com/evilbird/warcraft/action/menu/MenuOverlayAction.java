/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.menu;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.game.GameController;
import com.evilbird.engine.game.GameService;
import com.evilbird.engine.menu.MenuIdentifier;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;

import javax.inject.Inject;

/**
 * Instances of this class show overlay menus, menus rendered on top of the
 * game world.
 *
 * @author Blair Butterworth
 */
public class MenuOverlayAction extends BasicAction
{
    @Inject
    public MenuOverlayAction() {
    }

    @Override
    public boolean act(float delta) {
        GameController controller = getController();
        if (! controller.isMenuOverlayShown()) {
            controller.showMenuOverlay(getMenuIdentifier());
        } else {
            controller.showState();
        }
        return true;
    }

    private MenuIdentifier getMenuIdentifier() {
        MenuActions identifier = (MenuActions)getIdentifier();
        return (MenuIdentifier)identifier.getMenuIdentifier();
    }

    private GameController getController() {
        GameObject gameObject = getSubject();
        if (gameObject == null) {
            return getSingletonController();
        }
        return getItemController(gameObject);
    }

    private GameController getItemController(GameObject gameObject) {
        GameObjectContainer root = gameObject.getRoot();
        return root.getController();
    }

    private GameController getSingletonController() {
        GameService gameService = GameService.getInstance();
        return gameService.getEngine();
    }
}
