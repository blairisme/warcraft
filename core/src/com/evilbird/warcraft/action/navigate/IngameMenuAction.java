/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.navigate;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.game.GameController;
import com.evilbird.engine.game.GameEngine;
import com.evilbird.engine.game.GameService;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.menu.ingame.IngameMenus;

public class IngameMenuAction extends BasicAction
{
    @Override
    public boolean act(float delta) {
//        Item item = getItem();
//        ItemRoot root = item.getRoot();
//        GameController controller = root.getController();

        //TODO: this is crapola

        GameService gameService = GameService.getInstance();
        GameController controller = gameService.getGameEngine();
        controller.showMenuOverlay(IngameMenus.Root);
        return true;
    }
}
