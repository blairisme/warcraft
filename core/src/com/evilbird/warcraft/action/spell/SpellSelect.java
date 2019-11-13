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
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.warcraft.action.common.create.CreateEvent;
import com.evilbird.warcraft.object.common.spell.Spell;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.combatant.SpellCaster;

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
    private transient Predicate<GameObject> condition;

    public SpellSelect(Events events, Spell spell, Predicate<GameObject> condition) {
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
            GameObject subject = event.getSubject();
            if (condition.test(subject)) {
                setHighlighted(subject);
            }
        }
        return ActionIncomplete;
    }

    protected void setCastingSpell() {
        SpellCaster spellCaster = (SpellCaster) getSubject();
        spellCaster.setCastingSpell(spell);
    }

    protected void setHighlighted(Collection<GameObject> targets) {
        for (GameObject target: targets) {
            setHighlighted(target);
        }
    }

    protected void setHighlighted(GameObject target) {
        Unit unit = (Unit)target;
        unit.setHighlighted(true);
    }

    protected Collection<GameObject> getTargets() {
        GameObject gameObject = getSubject();
        GameObjectContainer root = gameObject.getRoot();
        return root.findAll(condition);
    }
}
