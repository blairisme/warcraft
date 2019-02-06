/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.common;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.framework.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.lang.Directionable;
import com.evilbird.engine.common.lang.Positionable;
import com.evilbird.engine.item.Item;

import javax.inject.Inject;

/**
 * Instances of this {@link Action Action} orient an Item towards another
 * Item.
 *
 * @author Blair Butterworth
 */
public class DirectionAction extends BasicAction
{
    private Directionable item;
    private Positionable target;

    @Inject
    public DirectionAction() {
    }

    @Override
    public void setItem(Item item) {
        super.setItem(item);
        this.item = (Directionable)item;
    }

    @Override
    public void setTarget(Item target) {
        super.setTarget(target);
        this.target = target;
    }

    @Override
    public boolean act(float delta) {
        Vector2 itemPosition = item.getPosition();
        Vector2 targetPosition = target.getPosition();
        Vector2 direction = targetPosition.sub(itemPosition);
        Vector2 normalizedDirection = direction.nor();
        item.setDirection(normalizedDirection);
        return true;
    }
}
