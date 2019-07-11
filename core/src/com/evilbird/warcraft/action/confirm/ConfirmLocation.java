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
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.RandomIdentifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.action.common.scenario.ScenarioAction;

import javax.inject.Inject;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.evilbird.engine.action.common.AudibleAction.play;
import static com.evilbird.engine.action.framework.DelayedAction.delay;
import static com.evilbird.engine.item.utility.ItemPredicates.itemWithId;
import static com.evilbird.warcraft.action.common.create.CreateAction.create;
import static com.evilbird.warcraft.action.common.remove.RemoveAction.remove;
import static com.evilbird.warcraft.action.confirm.ConfirmActions.ConfirmLocation;
import static com.evilbird.warcraft.item.ui.effect.EffectType.Confirm;
import static com.evilbird.warcraft.item.unit.UnitSound.Acknowledge;

/**
 * Instances of this class show a confirmation effect at a given location.
 *
 * @author Blair Butterworth
 */
public class ConfirmLocation extends ScenarioAction
{
    private transient Identifier effectId;

    @Inject
    public ConfirmLocation() {
        effectId = new RandomIdentifier();
        scenario(ConfirmLocation);
        then(create(Confirm, properties()));
        then(play(Acknowledge));
        then(delay(0.55f));
        then(remove(confirm(), null));
    }

    @Override
    public void reset() {
        super.reset();
        effectId = new RandomIdentifier();
    }

    private Supplier<Item> confirm() {
        return () -> {
            ItemComposite parent = getItem().getParent();
            return parent.find(itemWithId(effectId));
        };
    }

    private Consumer<Item> properties() {
        return (item) -> {
            item.setIdentifier(effectId);
            item.setPosition(getPosition());
        };
    }

    private Vector2 getPosition() {
        Item item = getItem();
        ItemRoot root = item.getRoot();
        UserInput cause = getCause();
        Vector2 position = root.unproject(cause.getPosition());
        return new Vector2(position.x - 16, position.y - 16);
    }
}
