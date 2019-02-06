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
import com.evilbird.engine.action.common.AudibleAction;
import com.evilbird.engine.action.common.CreateAction;
import com.evilbird.engine.action.common.PositionAction;
import com.evilbird.engine.action.common.RemoveAction;
import com.evilbird.engine.action.framework.*;
import com.evilbird.engine.action.framework.duration.TimeDuration;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.warcraft.item.effect.EffectType;
import com.evilbird.warcraft.item.unit.UnitSound;

import javax.inject.Inject;

/**
 * Instances of this class show a confirmation effect at the given location.
 *
 * @author Blair Butterworth
 */
public class ConfirmAction extends DelegateAction
{
    private CreateAction create;

    @Inject
    public ConfirmAction(ItemFactory itemFactory) {
        Action delay = new DelayedAction(new TimeDuration(0.55f));
        Action remove = new RemoveAction();
        Action sound = new AudibleAction(UnitSound.Acknowledge);
        create = new CreateAction(itemFactory, EffectType.Confirm, remove);
        delegate = new SequenceAction(create, sound, delay, remove);
    }

    public void setLocation(Vector2 location) {
        create.setPosition(location);
    }

    @Override
    public boolean act(float delta) {
        return delegate.act(delta);
    }
}
