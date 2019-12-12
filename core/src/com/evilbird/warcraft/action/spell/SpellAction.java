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
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.engine.object.GameObjectGroup;
import com.evilbird.warcraft.object.common.spell.Spell;
import com.evilbird.warcraft.object.effect.Effect;
import com.evilbird.warcraft.object.effect.EffectType;
import com.evilbird.warcraft.object.unit.combatant.spellcaster.SpellCaster;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;
import static com.evilbird.engine.common.lang.Alignment.Center;
import static com.evilbird.warcraft.object.common.query.UnitOperations.reorient;
import static com.evilbird.warcraft.object.unit.UnitAnimation.CastSpell;

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
    protected transient GameObjectFactory factory;

    public SpellAction(GameObjectFactory factory) {
        this.factory = factory;
    }

    public SpellAction(Spell spell, EffectType effect, GameObjectFactory factory) {
        this.spell = spell;
        this.effect = effect;
        this.factory = factory;
    }

    /**
     * Returns the effect that will be shown on the actions target when the
     * spell action is executed.
     */
    public EffectType getEffect() {
        return effect;
    }

    /**
     * Returns the spell that will be cast when the spell action is executed.
     */
    public Spell getSpell() {
        return spell;
    }

    /**
     * Sets the effect that will be shown on the actions target when the
     * spell action is executed.
     */
    public void setEffect(EffectType effect) {
        this.effect = effect;
    }

    /**
     * Sets the spell that will be cast when the spell action is executed.
     */
    public void setSpell(Spell spell) {
        this.spell = spell;
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
        SpellCaster caster = (SpellCaster) getSubject();
        return caster.isCasting();
    }

    protected void initialize() {
        SpellCaster caster = (SpellCaster) getSubject();
        GameObject target = getTarget();
        initialize(caster, target);
    }

    protected void initialize(SpellCaster caster, GameObject target) {
        initializeCaster(caster, target);
        initializeMana(caster, target, spell);
        initializeTarget(caster, target);
        initializeEffect(caster, target);
    }

    protected void initializeCaster(SpellCaster caster, GameObject target) {
        caster.setAnimation(CastSpell);
        caster.setCastingProgress(0f);
        reorient(caster, target, false);
    }

    protected void initializeTarget(SpellCaster caster, GameObject target) {
    }

    protected void initializeEffect(SpellCaster caster, GameObject target) {
        Effect spellEffect = (Effect)factory.get(effect);
        spellEffect.setPosition(target.getPosition(Center), Center);

        GameObjectGroup parent = caster.getParent();
        parent.addObject(spellEffect);

        caster.setSpellEffect(spellEffect);
    }

    protected void initializeMana(SpellCaster caster, GameObject target, Spell spell) {
        caster.setMana(Math.max(0, caster.getMana() - spell.getCastCost()));
    }

    private boolean loaded() {
        return timer != null;
    }

    protected void load() {
        SpellCaster caster = (SpellCaster)getSubject();
        timer = new GameTimer(spell.getCastTime());
        timer.advance(caster.getCastingProgress() * timer.duration());
    }

    private boolean completed(float time) {
        return timer.advance(time);
    }

    protected boolean update() {
        SpellCaster caster = (SpellCaster)getSubject();
        caster.setCastingProgress(timer.completion());
        return ActionIncomplete;
    }

    protected boolean complete() {
        SpellCaster caster = (SpellCaster)getSubject();
        caster.setCastingProgress(1);
        caster.setCastingSpell(null);

        Effect effect = caster.getSpellEffect();
        if (effect != null) {
            GameObjectGroup parent = effect.getParent();
            parent.removeObject(caster.getSpellEffect());
            caster.setSpellEffect(null);
        }
        return ActionComplete;
    }
}
