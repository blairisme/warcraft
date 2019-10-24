/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell;

import com.evilbird.engine.item.ItemFactory;
import com.evilbird.warcraft.action.death.DeathAction;
import com.evilbird.warcraft.item.effect.EffectType;
import com.evilbird.warcraft.item.unit.Unit;

import javax.inject.Inject;

import static com.evilbird.engine.item.utility.ItemOperations.assignIfAbsent;
import static com.evilbird.warcraft.item.common.spell.Spell.Exorcism;

/**
 * A spell that degrades the health of a given game object.
 *
 * @author Blair Butterworth
 */
public class ExorcismSpell extends SpellAction
{
    private transient DeathAction death;

    @Inject
    public ExorcismSpell(ItemFactory factory, DeathAction death) {
        super(Exorcism, EffectType.Exorcism, factory);
        this.death = death;
    }

    @Override
    protected void initialize() {
        super.initialize();

        Unit target = (Unit)getTarget();
        target.setHealth(Math.max(0, target.getHealth() - Exorcism.getValue()));

        if (target.getHealth() == 0) {
            assignIfAbsent(target, death);
        }
    }
}
