package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.common.CreateAction;
import com.evilbird.engine.action.framework.DelayedAction;
import com.evilbird.engine.action.common.RemoveAction;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.action.framework.duration.TimeDuration;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.item.effect.EffectType;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ConfirmSequence
{
    private ItemFactory itemFactory;

    @Inject
    public ConfirmSequence(ItemFactory itemFactory)
    {
        this.itemFactory = itemFactory;
    }

    public Action get(Item item, UserInput input)
    {
        ItemRoot parent = item.getRoot();
        Vector2 position = parent.unproject(input.getPosition());
        return get(parent, position);
    }

    public Action get(ItemComposite parent, Vector2 position)
    {
        Identifier id = new NamedIdentifier();
        Action create = new CreateAction(parent, EffectType.Confirm, itemFactory, id, position, false);
        Action delay = new DelayedAction(new TimeDuration(0.55f));
        Action remove = new RemoveAction(parent, id);
        return new SequenceAction(create, delay, remove);
    }
}
