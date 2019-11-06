/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.action.common.create.CreateEvent;
import com.evilbird.warcraft.item.common.spell.Spell;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.combatant.SpellCaster;

import java.util.Collection;
import java.util.function.Predicate;

import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;

/**
 * An {@link Action} that highlights units.
 *
 * @author Blair Butterworth
 */
public abstract class SpellSelect extends BasicAction
{
    private transient Spell spell;
    private transient Events events;
    private transient boolean initialized;
    private transient Predicate<Item> condition;

    public SpellSelect(Events events, Spell spell, Predicate<Item> condition) {
        this.spell = spell;
        this.events = events;
        this.condition = condition;
    }

    @Override
    public boolean act(float time) {
        if (! initialized()) {
            initialize();
        }
        return update();
    }

    @Override
    public void reset() {
        super.reset();
        clear();
    }

    @Override
    public void restart() {
        super.restart();
        clear();
    }

    protected void clear() {
        initialized = false;
    }

    private boolean initialized() {
        return initialized;
    }

    protected void initialize() {
        initialized = true;
        setCastingSpell();
        setHighlighted(getTargets());
    }

    protected boolean update() {
        for (CreateEvent event: events.getEvents(CreateEvent.class)) {
            Item subject = event.getSubject();
            if (condition.test(subject)) {
                setHighlighted(subject);
            }
        }
        return ActionIncomplete;
    }

    protected void setCastingSpell() {
        SpellCaster spellCaster = (SpellCaster)getItem();
        spellCaster.setCastingSpell(spell);
    }

    protected void setHighlighted(Collection<Item> targets) {
        for (Item target: targets) {
            setHighlighted(target);
        }
    }

    protected void setHighlighted(Item target) {
        Unit unit = (Unit)target;
        unit.setHighlighted(true);
    }

    protected Collection<Item> getTargets() {
        Item item = getItem();
        ItemRoot root = item.getRoot();
        return root.findAll(condition);
    }
}
