/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.creature;

import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.engine.object.GameObjectGroup;
import com.evilbird.warcraft.action.common.create.CreateEvents;
import com.evilbird.warcraft.action.spell.SpellAction;
import com.evilbird.warcraft.data.spell.Spell;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.effect.EffectType;
import com.evilbird.warcraft.object.unit.UnitType;

import javax.inject.Inject;

/**
 * A spell that creates a conjured creature.
 *
 * @author Blair Butterworth
 */
public class CreatureSpellAction extends SpellAction
{
    private UnitType creatureType;
    private CreateEvents createEvents;
    private CreatureSpellCancel cancelAction;

    @Inject
    public CreatureSpellAction(GameObjectFactory factory, CreateEvents events, CreatureSpellCancel expiry) {
        super(factory);
        this.createEvents = events;
        this.cancelAction = expiry;
    }

    public CreatureSpellAction(
        Spell spell,
        EffectType effect,
        UnitType creature,
        GameObjectFactory factory,
        CreateEvents createEvents,
        CreatureSpellCancel cancelAction)
    {
        super(spell, effect, factory);
        this.creatureType = creature;
        this.createEvents = createEvents;
        this.cancelAction = cancelAction;
    }

    /**
     * Returns the type of creature that will be produced when this spell action
     * is executed.
     */
    public UnitType getProduct() {
        return creatureType;
    }

    /**
     * Sets the type of creature that will be produced when this spell action
     * is executed.
     */
    public void setProduct(UnitType type) {
        this.creatureType = type;
    }

    @Override
    protected void initialize() {
        super.initialize();
        addCreature(getSubject(), getTarget());
    }

    protected void addCreature(GameObject caster, GameObject target) {
        GameObject creature = newCreature(caster, target);
        GameObjectGroup parent = getParent(caster, target);

        parent.addObject(creature);
        createEvents.notifyCreate(creature);
    }

    protected GameObject newCreature(GameObject caster, GameObject target) {
        GameObject creature = factory.get(creatureType);
        if (cancelAction != null) {
            cancelAction.setSubject(caster);
            cancelAction.setTarget(creature);
            creature.addAction(cancelAction, spell.getEffectDuration());
        }
        return creature;
    }

    protected GameObjectGroup getParent(GameObject caster, GameObject target) {
        return UnitOperations.getPlayer(caster);
    }
}
