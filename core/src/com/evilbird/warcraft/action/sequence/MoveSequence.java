package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.common.AnimateAction;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.animated.Animated;
import com.evilbird.engine.item.specialized.animated.AnimationIdentifier;
import com.evilbird.warcraft.action.common.MoveAction;
import com.evilbird.warcraft.item.common.capability.Movable;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;
import com.evilbird.warcraft.item.unit.resource.ResourceType;

import javax.inject.Inject;

public class MoveSequence
{
    @Inject
    public MoveSequence() {
    }

    public Action get(Item item, Item destination)
    {
        Action move = new MoveAction((Movable)item, destination);
        return animate(item, move);
    }

    public Action get(Item item, Vector2 destination)
    {
        Action move = new MoveAction((Movable)item, destination);
        return animate(item, move);
    }

    private Action animate(Item item, Action move)
    {
        Action animateMove = new AnimateAction((Animated)item, UnitAnimation.Move);
        Action animateIdle = new AnimateAction((Animated)item, UnitAnimation.Idle);
        return new SequenceAction(animateMove, move, animateIdle);
    }

}
