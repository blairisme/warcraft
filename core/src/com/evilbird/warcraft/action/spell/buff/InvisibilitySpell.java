/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.buff;

import com.evilbird.engine.common.graphics.Renderable;
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.warcraft.action.spell.SpellAction;
import com.evilbird.warcraft.object.badge.BadgeType;
import com.evilbird.warcraft.object.common.spell.Spell;
import com.evilbird.warcraft.object.effect.EffectType;
import com.evilbird.warcraft.object.unit.combatant.Combatant;

import javax.inject.Inject;

/**
 * A spell that renders its target invisible to enemy players. The target
 * remains invisible until it moves or attacks. The spell is cast
 * instantaneously and won't be repeated until cast again.
 *
 * @author Blair Butterworth
 */
public class InvisibilitySpell extends SpellAction
{
    private InvisibilityCancel cancel;

    @Inject
    public InvisibilitySpell(GameObjectFactory factory, InvisibilityCancel cancel) {
        super(Spell.Invisibility, EffectType.Spell, factory);
        this.cancel = cancel;
    }

    @Override
    protected void initialize() {
        super.initialize();

        Combatant target = (Combatant)getTarget();
        target.setEffect((Renderable)factory.get(BadgeType.InvisibilityBadge));

        cancel.setItem(getSubject());
        cancel.setTarget(target);

        target.setAttackable(false);
        target.addAction(cancel, new GameTimer(spell.getEffectDuration()));
    }
}
