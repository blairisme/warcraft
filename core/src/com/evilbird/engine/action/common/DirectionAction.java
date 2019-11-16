///*
// * Blair Butterworth (c) 2019
// *
// * This work is licensed under the MIT License. To view a copy of this
// * license, visit
// *
// *      https://opensource.org/licenses/MIT
// */
//
//package com.evilbird.engine.action.common;
//
//import com.badlogic.gdx.math.Vector2;
//import com.evilbird.engine.action.Action;
//import com.evilbird.engine.action.framework.BasicAction;
//import com.evilbird.engine.common.lang.Directionable;
//import com.evilbird.engine.common.lang.Positionable;
//import org.apache.commons.lang3.builder.EqualsBuilder;
//import org.apache.commons.lang3.builder.HashCodeBuilder;
//
//import javax.inject.Inject;
//
///**
// * Instances of this {@link Action Action} orient an Item towards another
// * Item.
// *
// * @author Blair Butterworth
// */
//public class DirectionAction extends BasicAction
//{
//    private Direction direction;
//
//    @Inject
//    public DirectionAction() {
//        this(Direction.Facing);
//    }
//
//    public DirectionAction(Direction direction) {
//        this.direction = direction;
//    }
//
//    /**
//     * Creates a new {@link DirectionAction}, which when invoked will reorient
//     * the actions subject towards the actions target.
//     *
//     * @return  a {@code DirectionAction} instance. Will not return
//     *          {@code null}.
//     */
//    public static DirectionAction reorient() {
//        return new DirectionAction();
//    }
//
//    /**
//     * Creates a new {@link DirectionAction}, which when invoked will reorient
//     * the actions subject in the manner specified by the given
//     * {@link Direction}.
//     *
//     * @param direction specifies the new direction of the actions subject.
//     *
//     * @return  a {@code DirectionAction} instance. Will not return
//     *          {@code null}.
//     */
//    public static DirectionAction reorient(Direction direction) {
//        return new DirectionAction(direction);
//    }
//
//    @Override
//    public boolean act(float delta) {
//        Directionable item = (Directionable) getSubject();
//        Positionable target = getTarget();
//
//        Vector2 itemPosition = item.getPosition();
//        Vector2 targetPosition = target.getPosition();
//        Vector2 direction = targetPosition.sub(itemPosition);
//        Vector2 normalizedDirection = direction.nor();
//        Vector2 newDirection = normalizedDirection.rotate(getRotation());
//        item.setDirection(newDirection);
//
//        return true;
//    }
//
//    private int getRotation() {
//        return direction == Direction.Perpendicular ? 90 : 0;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (obj == null) { return false; }
//        if (obj == this) { return true; }
//        if (obj.getClass() != getClass()) { return false; }
//
//        DirectionAction that = (DirectionAction)obj;
//        return new EqualsBuilder()
//            .appendSuper(super.equals(obj))
//            .append(direction, that.direction)
//            .isEquals();
//    }
//
//    @Override
//    public int hashCode() {
//        return new HashCodeBuilder(17, 37)
//            .appendSuper(super.hashCode())
//            .append(direction)
//            .toHashCode();
//    }
//}
