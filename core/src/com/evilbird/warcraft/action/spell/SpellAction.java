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
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.warcraft.item.common.spell.Spell;
import com.evilbird.warcraft.item.effect.Effect;
import com.evilbird.warcraft.item.effect.EffectType;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.combatant.SpellCaster;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;
import static com.evilbird.engine.common.lang.Alignment.Center;
import static com.evilbird.warcraft.item.common.query.UnitOperations.reorient;
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
        if (!initialized()) {
            initialize();
        }
        if (!loaded()) {
            load();
        }
        if (!completed(time)) {
            return update();
        }
        return complete();
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
        timer = null;
    }

    private boolean initialized() {
        SpellCaster caster = (SpellCaster)getItem();
        return caster.isCasting();
    }

    protected void initialize() {
        SpellCaster caster = (SpellCaster) getItem();
        Item target = getTarget();
        initialize(caster, target);
    }

    protected void initialize(SpellCaster caster, Item target) {
        initializeCaster(caster, target);
        initializeMana(caster, target, spell);
        initializeTarget(caster, target);
        initializeEffect(caster, target);
    }

    protected void initializeCaster(SpellCaster caster, Item target) {
        caster.setAnimation(CastSpell);
        caster.setCastingProgress(0f);
        reorient(caster, target, false);
    }

    protected void initializeTarget(SpellCaster caster, Item target) {
    }

    protected void initializeEffect(SpellCaster caster, Item target) {
        Effect spellEffect = (Effect)factory.get(effect);
        spellEffect.setPosition(target.getPosition(Center), Center);

        ItemGroup parent = caster.getParent();
        parent.addItem(spellEffect);

        caster.setSpellEffect(spellEffect);
    }

    protected void initializeMana(SpellCaster caster, Item target, Spell spell) {
        caster.setMana(Math.max(0, caster.getMana() - spell.getManaCost()));
    }

    private boolean loaded() {
        return timer != null;
    }

    protected void load() {
        SpellCaster caster = (SpellCaster)getItem();
        timer = new GameTimer(spell.getCastTime());
        timer.advance(caster.getCastingProgress() * timer.duration());
    }

    private boolean completed(float time) {
        return timer.advance(time);
    }

    protected boolean update() {
        SpellCaster caster = (SpellCaster)getItem();
        caster.setCastingProgress(timer.completion());
        return ActionIncomplete;
    }

    protected boolean complete() {
        SpellCaster caster = (SpellCaster)getItem();
        caster.setCastingProgress(1);

        Effect effect = caster.getSpellEffect();
        if (effect != null) {
            ItemGroup parent = effect.getParent();
            parent.removeItem(caster.getSpellEffect());
            caster.setSpellEffect(null);
        }
        return ActionComplete;
    }
}
