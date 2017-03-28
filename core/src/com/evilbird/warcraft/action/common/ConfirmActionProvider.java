package com.evilbird.warcraft.action.common;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.CreateAction;
import com.evilbird.engine.action.DelayedAction;
import com.evilbird.engine.action.RemoveAction;
import com.evilbird.engine.action.SequenceAction;
import com.evilbird.engine.action.duration.TimeDuration;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.item.ItemComposite;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.warcraft.item.effect.EffectType;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ConfirmActionProvider
{
    private ItemFactory itemFactory;

    @Inject
    public ConfirmActionProvider(ItemFactory itemFactory)
    {
        this.itemFactory = itemFactory;
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
