/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.component;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.lang.Directionable;
import com.evilbird.engine.common.lang.Positionable;

/**
 * Instances of this {@link BasicAction Action} orient an Item towards another
 * Item.
 *
 * @author Blair Butterworth
 */
public class DirectionAction extends BasicAction
{
    private Directionable item;
    private Positionable target;

    public DirectionAction(Directionable item, Positionable target) {
        this.item = item;
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
