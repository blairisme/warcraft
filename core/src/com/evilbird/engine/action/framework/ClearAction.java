///*
// * Copyright (c) 2019, Blair Butterworth
// *
// * This work is licensed under the MIT License. To view a copy of this
// * license, visit
// *
// *        https://opensource.org/licenses/MIT
// */
//
//package com.evilbird.engine.action.framework;
//
//import com.evilbird.engine.action.Action;
//import com.evilbird.engine.action.common.ActionRecipient;
//import com.evilbird.engine.action.common.ActionUtils;
//import com.evilbird.engine.common.collection.CollectionUtils;
//import com.evilbird.engine.common.collection.Maps;
//import com.evilbird.engine.object.GameObject;
//
//import java.util.Collection;
//import java.util.function.Predicate;
//
//import static com.evilbird.engine.common.function.Predicates.never;
//
///**
// * Instances of this {@link Action} clear the actions assigned to a specified
// * item.
// *
// * @author Blair Butterworth
// */
//public class ClearAction extends BasicAction
//{
//    private ActionRecipient recipient;
//    private Predicate<Action> condition;
//
//    public ClearAction(ActionRecipient recipient, Predicate<Action> condition) {
//        this.recipient = recipient;
//        this.condition = condition;
//    }
//
//    public static ClearAction clear(ActionRecipient recipient) {
//        return new ClearAction(recipient, never());
//    }
//
//    public static ClearAction clear(ActionRecipient recipient, Predicate<Action> condition) {
//        return new ClearAction(recipient, condition);
//    }
//
//    public static Predicate<Action> except(Action except) {
//        return action -> action == except;
//    }
//
//    @Override
//    public boolean act(float delta) {
//        GameObject gameObject = ActionUtils.getRecipient(this, recipient);
//
//        Collection<Action> oldActions = gameObject.getActions();
//        Collection<Action> newActions = CollectionUtils.filter(oldActions, condition);
//
//        gameObject.clearActions();
//        Maps.forEach(newActions, newAction -> gameObject.addAction(newAction));
//
//        return true;
//    }
//}
