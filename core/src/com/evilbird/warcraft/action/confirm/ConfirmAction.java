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
import com.evilbird.engine.action.common.CreateAction;
import com.evilbird.engine.action.common.RemoveAction;
import com.evilbird.engine.action.framework.Action;
import com.evilbird.engine.action.framework.DelayedAction;
import com.evilbird.engine.action.framework.DelegateAction;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.action.framework.duration.TimeDuration;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.warcraft.item.effect.EffectType;

import javax.inject.Inject;

/**
 * Instances of this class show a confirmation effect at the given location.
 *
 * @author Blair Butterworth
 */
//TODO: Reuse action sequence
public class ConfirmAction extends DelegateAction
{
    private ItemFactory itemFactory;

    @Inject
    public ConfirmAction(ItemFactory itemFactory) {
        this.itemFactory = itemFactory;
    }

    public void setLocation(Item target, ItemComposite parent) {
        initialize(parent, target.getPosition());
    }

    public void setLocation(Vector2 position, ItemComposite parent) {
        Vector2 centeredPosition = new Vector2(position.x - 16, position.y - 16);
        initialize(parent, centeredPosition);
    }

    private void initialize(ItemComposite parent, Vector2 position) {
        Identifier id = new NamedIdentifier();
        Action create = new CreateAction(parent, EffectType.Confirm, itemFactory, id, position, false);
        Action delay = new DelayedAction(new TimeDuration(0.55f));
        Action remove = new RemoveAction(parent, id);
        delegate = new SequenceAction(create, delay, remove);
    }

    @Override
    public boolean act(float delta) {
        return delegate.act(delta);
    }
}
