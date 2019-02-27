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
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.lang.Positionable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.inject.Inject;

public class PositionAction extends BasicAction
{
    private Vector2 position;

    @Inject
    public PositionAction() {
        this(Vector2.Zero);
    }

    public PositionAction(Vector2 position) {
        this.position = position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    @Override
    public boolean act(float time) {
        Positionable positionable = getItem();
        positionable.setPosition(position);
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;

        PositionAction that = (PositionAction)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(position, that.position)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(position)
            .toHashCode();
    }
}
