/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.placeholder;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;

import javax.inject.Inject;

import static com.evilbird.warcraft.item.WarcraftItemConstants.TILE_HEIGHT;
import static com.evilbird.warcraft.item.WarcraftItemConstants.TILE_WIDTH;

/**
 * Instances of this class reposition a given placeholder to the location of
 * the given input.
 *
 * @author Blair Butterworth
 */
public class PlaceholderMove extends BasicAction
{
    @Inject
    public PlaceholderMove() {
        setIdentifier(PlaceholderActions.PlaceholderMove);
    }

    @Override
    public boolean act(float delta) {
        Item item = getItem();
        UserInput input = getCause();

        ItemRoot root = item.getRoot();
        Vector2 position = root.unproject(input.getPosition());

        position.x -= item.getWidth()/2;
        position.y -= item.getHeight()/2;

        position.x = Math.round(position.x/TILE_WIDTH) * TILE_WIDTH;
        position.y = Math.round(position.y/TILE_HEIGHT) * TILE_HEIGHT;

        item.setPosition(position);
        return true;
    }
}
