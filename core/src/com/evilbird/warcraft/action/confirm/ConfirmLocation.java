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
import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.ItemType;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.item.ui.confirmation.ConfirmType;

import javax.inject.Inject;

/**
 * Instances of this {@link Action} show a confirmation effect at a given
 * location.
 *
 * @author Blair Butterworth
 */
public class ConfirmLocation extends ConfirmAction
{
    @Inject
    public ConfirmLocation(ItemFactory factory, WarcraftPreferences preferences) {
        super(factory, preferences);
        setIdentifier(ConfirmActions.ConfirmLocation);
    }

    @Override
    protected ItemType getEffectType() {
        return ConfirmType.Confirm;
    }

    @Override
    protected Vector2 getPosition() {
        Item item = getItem();
        ItemRoot root = item.getRoot();
        UserInput cause = getCause();
        Vector2 position = root.unproject(cause.getPosition());
        return new Vector2(position.x - 16, position.y - 16);
    }

    @Override
    protected Alignment getAlignment() {
        return Alignment.BottomLeft;
    }
}
