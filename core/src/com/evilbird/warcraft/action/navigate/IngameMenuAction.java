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
import com.evilbird.engine.game.GameService;
import com.evilbird.warcraft.menu.ingame.IngameMenuType;

public class IngameMenuAction extends BasicAction
{
    @Override
    public boolean act(float delta) {
//        Item item = getItem();
//        ItemRoot root = item.getRoot();
//        GameController controller = root.getController();

        //TODO: this is muchos crapola

        GameService gameService = GameService.getInstance();
        GameController controller = gameService.getGameEngine();
        controller.showMenuOverlay(IngameMenuType.Root);
        return true;
    }
}
