/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.confirm;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemRoot;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.confirm.ConfirmActions.ConfirmLocation;

/**
 * Instances of this class show a confirmation effect at a given location.
 *
 * @author Blair Butterworth
 */
public class ConfirmLocation extends ConfirmAction
{
    @Inject
    public ConfirmLocation(ItemFactory itemFactory) {
        super(itemFactory);
        setIdentifier(ConfirmLocation);
    }

    @Override
    public void setCause(UserInput cause) {
        super.setCause(cause);
        Item item = getItem();
        ItemRoot root = item.getRoot();
        Vector2 position = root.unproject(cause.getPosition());
        Vector2 centeredPosition = new Vector2(position.x - 16, position.y - 16);
        setLocation(centeredPosition);
    }
}
