/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.spell;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.spell.aoe.AoeSpellFactory;
import com.evilbird.warcraft.action.spell.attack.AttackSpellFactory;
import com.evilbird.warcraft.action.spell.buff.BuffSpellFactory;
import com.evilbird.warcraft.action.spell.creature.CreatureSpellFactory;
import com.evilbird.warcraft.action.spell.novel.NovelSpellFactory;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * A factory that creates spell actions.
 *
 * @author Blair Butterworth
 */
public class SpellFactory extends SpellProvider implements ActionProvider
{
    @Inject
    public SpellFactory(
        AoeSpellFactory aoeSpells,
        AttackSpellFactory attackSpells,
        BuffSpellFactory buffSpells,
        CreatureSpellFactory creatureSpells,
        NovelSpellFactory novelSpells)
    {
        addActionProvider(aoeSpells);
        addActionProvider(attackSpells);
        addActionProvider(buffSpells);
        addActionProvider(creatureSpells);
        addActionProvider(novelSpells);
    }

    @Override
    public Action get(ActionIdentifier identifier) {
        Validate.isInstanceOf(SpellActions.class, identifier);
        return getAction(identifier);
    }
}
