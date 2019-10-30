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
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.item.common.spell.Spell;
import com.evilbird.warcraft.item.effect.Effect;
import com.evilbird.warcraft.item.effect.EffectType;
import com.evilbird.warcraft.item.unit.combatant.SpellCaster;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;
import static com.evilbird.engine.common.lang.Alignment.Center;
import static com.evilbird.warcraft.item.unit.UnitAnimation.CastSpell;

/**
 * A base class for {@link Action Actions} involving spells. Sets the spell
 * cast animation on the actions subject, creates an effect appropriate to the
 * given spell and decrements the mana of the spell caster.
 *
 * @author Blair Butterworth
 */
public abstract class SpellAction extends BasicAction
{
    protected transient Spell spell;
    protected transient EffectType effect;
    protected transient GameTimer timer;
    protected transient ItemFactory factory;

    public SpellAction(Spell spell, EffectType effect, ItemFactory factory) {
        this.spell = spell;
        this.effect = effect;
        this.factory = factory;
    }

    @Override
    public boolean act(float time) {
        if (!isInitialized()) {
            initialize();
        }
        if (!isLoaded()) {
            load();
        }
        if (!isComplete(time)) {
            return update();
        }
        return complete();
    }

    @Override
    public void reset() {
        super.reset();
        timer = null;
    }

    @Override
    public void restart() {
        super.restart();
        timer = null;
    }

    private boolean isInitialized() {
        SpellCaster caster = (SpellCaster)getItem();
        return caster.isCasting();
    }

    protected void initialize() {
        SpellCaster caster = (SpellCaster)getItem();
        caster.setMana(Math.max(0, caster.getMana() - spell.getManaCost()));
        caster.setAnimation(CastSpell);

        Effect spellEffect = (Effect)factory.get(effect);
        caster.setSpell(spellEffect);

        Item target = getTarget();
        spellEffect.setPosition(target.getPosition(Center), Center);

        ItemRoot parent = caster.getRoot();
        parent.addItem(spellEffect);
    }

    private boolean isLoaded() {
        return timer != null;
    }

    protected void load() {
        SpellCaster caster = (SpellCaster)getItem();
        timer = new GameTimer(spell.getCastTime());
        timer.advance(caster.getCastProgress() * timer.duration());
    }

    private boolean isComplete(float time) {
        return timer.advance(time);
    }

    protected boolean update() {
        SpellCaster caster = (SpellCaster)getItem();
        caster.setCastProgress(timer.completion());
        return ActionIncomplete;
    }

    protected boolean complete() {
        SpellCaster caster = (SpellCaster)getItem();
        caster.setCastProgress(1);

        ItemRoot parent = caster.getRoot();
        parent.removeItem(caster.getSpell());
        caster.setSpell(null);

        return ActionComplete;
    }
}
