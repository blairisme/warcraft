/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.highlight;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.action.common.create.CreateEvent;
import com.evilbird.warcraft.item.unit.Unit;

import java.util.Collection;
import java.util.function.Predicate;

import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;

/**
 * An {@link Action} that highlights units.
 *
 * @author Blair Butterworth
 */
public abstract class HighlightAction extends BasicAction
{
    private transient Events events;
    private transient boolean initialized;
    private transient Predicate<Item> condition;

    public HighlightAction(Events events) {
        this.events = events;
    }

    @Override
    public boolean act(float time) {
        initialize();
        update();
        return ActionIncomplete;
    }

    @Override
    public void reset() {
        super.reset();
        initialized = false;
    }

    @Override
    public void restart() {
        super.restart();
        initialized = false;
    }

    protected abstract Predicate<Item> getCondition();

    private void initialize() {
        if (! initialized) {
            initialized = true;
            condition = getCondition();
            setHighlighted(getTargets());
        }
    }

    private void update() {
        for (CreateEvent event: events.getEvents(CreateEvent.class)) {
            Item subject = event.getSubject();
            if (condition.test(subject)) {
                setHighlighted(subject);
            }
        }
    }

    private Collection<Item> getTargets() {
        Item item = getItem();
        ItemRoot root = item.getRoot();
        return root.findAll(condition);
    }

    private void setHighlighted(Collection<Item> targets) {
        for (Item target: targets) {
            setHighlighted(target);
        }
    }

    private void setHighlighted(Item target) {
        Unit unit = (Unit)target;
        unit.setHighlighted(true);
    }
}
