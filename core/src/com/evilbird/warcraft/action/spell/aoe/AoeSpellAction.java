/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.aoe;

import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.warcraft.action.spell.SpellAction;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.UnitType;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.Alignment.Center;

/**
 * A spell that creates a conjured area of effect object.
 *
 * @author Blair Butterworth
 */
public class AoeSpellAction extends SpellAction
{
    private transient UnitType type;
    private transient AoeSpellCancel expiry;

    @Inject
    public AoeSpellAction(AoeSpellCancel expiry, GameObjectFactory factory) {
        super(factory);
        this.expiry = expiry;
    }

    /**
     * Returns the AOE game object that will be produced when this spell action
     * is executed.
     */
    public UnitType getProduct() {
        return type;
    }

    /**
     * Sets the AOE game object that will be produced when this spell action
     * is executed.
     */
    public void setProduct(UnitType type) {
        this.type = type;
    }

    @Override
    protected void initialize() {
        super.initialize();

        GameObject caster = getSubject();
        GameObject selector = getTarget();

        GameObject aoe = factory.get(type);
        aoe.setPosition(selector.getPosition(Center), Center);

        Player player = UnitOperations.getPlayer(caster);
        player.addObject(aoe);

        expiry.setSubject(caster);
        expiry.setTarget(aoe);
        aoe.addAction(expiry, spell.getEffectDuration());
    }
}
