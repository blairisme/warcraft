/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.spell.buff;

import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.engine.object.GameObjectGroup;
import com.evilbird.warcraft.action.spell.SpellAction;
import com.evilbird.warcraft.data.spell.Spell;
import com.evilbird.warcraft.object.badge.Badge;
import com.evilbird.warcraft.object.effect.EffectType;
import com.evilbird.warcraft.object.unit.combatant.Combatant;

import javax.inject.Inject;

import static com.evilbird.warcraft.object.badge.BadgeType.InvisibilityBadge;

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
        setBuff(target);
        setBadge(target);
        setExpiry(target);
    }

    private void setBuff(Combatant target) {
        target.setAttackable(false);
    }

    private void setBadge(Combatant target) {
        GameObjectGroup group = target.getParent();
        Badge badge = (Badge)factory.get(InvisibilityBadge);
        group.addObject(badge);

        badge.setTarget(target);
        target.setEffect(badge);
    }

    private void setExpiry(Combatant target) {
        cancel.setSubject(getSubject());
        cancel.setTarget(target);
        target.addAction(cancel, new GameTimer(spell.getEffectDuration()));
    }
}
