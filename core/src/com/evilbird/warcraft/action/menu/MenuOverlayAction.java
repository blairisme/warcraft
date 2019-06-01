/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.menu;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.game.GameController;
import com.evilbird.engine.game.GameService;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.menu.MenuIdentifier;

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
        controller.showMenuOverlay(getMenuIdentifier());
        return true;
    }

    private MenuIdentifier getMenuIdentifier() {
        MenuActions identifier = (MenuActions)getIdentifier();
        return (MenuIdentifier)identifier.getMenuIdentifier();
    }

    private GameController getController() {
        Item item = getItem();
        if (item == null) {
            return getSingletonController();
        }
        return getItemController(item);
    }

    private GameController getItemController(Item item) {
        ItemRoot root = item.getRoot();
        return root.getController();
    }

    private GameController getSingletonController() {
        GameService gameService = GameService.getInstance();
        return gameService.getEngine();
    }
}
