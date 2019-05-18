/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.confirm;

import com.evilbird.warcraft.action.common.scenario.ScenarioAction;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.RandomIdentifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;

import javax.inject.Inject;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.evilbird.engine.action.common.AudibleAction.play;
import static com.evilbird.engine.action.framework.DelayedAction.delay;
import static com.evilbird.engine.item.utility.ItemPredicates.itemWithId;
import static com.evilbird.warcraft.action.common.create.CreateAction.create;
import static com.evilbird.warcraft.action.common.remove.RemoveAction.remove;
import static com.evilbird.warcraft.action.confirm.ConfirmActions.ConfirmTarget;
import static com.evilbird.warcraft.item.effect.EffectType.Confirm;
import static com.evilbird.warcraft.item.unit.UnitSound.Acknowledge;

/**
 * Instances of this class show a confirmation effect over a given {@link Item}.
 *
 * @author Blair Butterworth
 */
public class ConfirmItem extends ScenarioAction
{
    private transient Identifier effectId;

    @Inject
    public ConfirmItem(ConfirmReporter reporter) {
        effectId = new RandomIdentifier();
        scenario(ConfirmTarget);
        then(create(Confirm, properties(), reporter));
        then(play(Acknowledge));
        then(delay(0.55f));
        then(remove(confirm(), reporter));
    }

    @Override
    public void reset() {
        super.reset();
        effectId = new RandomIdentifier();
    }

    private Consumer<Item> properties() {
        return (item) -> {
            item.setIdentifier(effectId);
            item.setPosition(getTarget().getPosition());
        };
    }

    private Supplier<Item> confirm() {
        return () -> {
            ItemComposite parent = getItem().getParent();
            return parent.find(itemWithId(effectId));
        };
    }
}
