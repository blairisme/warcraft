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
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.spell.aoe.BlizzardSpell;
import com.evilbird.warcraft.action.spell.aoe.WhirlwindSpell;
import com.evilbird.warcraft.action.spell.aoe.DeathAndDecaySpell;
import com.evilbird.warcraft.action.spell.buff.DeathCoilSpell;
import com.evilbird.warcraft.action.spell.attack.ExorcismSpell;
import com.evilbird.warcraft.action.spell.attack.FlameShieldSpell;
import com.evilbird.warcraft.action.spell.creature.RunesSpell;
import com.evilbird.warcraft.action.spell.attack.UnholyArmourSpell;
import com.evilbird.warcraft.action.spell.buff.BloodlustSpell;
import com.evilbird.warcraft.action.spell.buff.HasteSpell;
import com.evilbird.warcraft.action.spell.buff.HealSpell;
import com.evilbird.warcraft.action.spell.buff.InvisibilitySpell;
import com.evilbird.warcraft.action.spell.buff.SlowSpell;
import com.evilbird.warcraft.action.spell.creature.EyeOfKilroggSpell;
import com.evilbird.warcraft.action.spell.creature.PolymorphSpell;
import com.evilbird.warcraft.action.spell.creature.RaiseDeadSpell;
import com.evilbird.warcraft.action.spell.power.HolyVisionSpell;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * A factory that creates spell actions.
 *
 * @author Blair Butterworth
 */
public class SpellFactory implements ActionProvider
{
    private InjectedPool<BlizzardSpell> blizzardPool;
    private InjectedPool<BloodlustSpell> bloodlustPool;
    private InjectedPool<DeathAndDecaySpell> deathAndDecayPool;
    private InjectedPool<DeathCoilSpell> deathCoilPool;
    private InjectedPool<ExorcismSpell> exorcismPool;
    private InjectedPool<EyeOfKilroggSpell> eyeOfKilroggPool;
    private InjectedPool<FlameShieldSpell> flameShieldPool;
    private InjectedPool<HasteSpell> hastePool;
    private InjectedPool<HealSpell> healPool;
    private InjectedPool<HolyVisionSpell> holyVisionPool;
    private InjectedPool<InvisibilitySpell> invisibilityPool;
    private InjectedPool<PolymorphSpell> polymorphPool;
    private InjectedPool<RaiseDeadSpell> raiseDeadPool;
    private InjectedPool<RunesSpell> runesPool;
    private InjectedPool<SlowSpell> slowPool;
    private InjectedPool<UnholyArmourSpell> unholyArmourPool;
    private InjectedPool<WhirlwindSpell> whirlwindPool;

    @Inject
    public SpellFactory(
        InjectedPool<BlizzardSpell> blizzardPool,
        InjectedPool<BloodlustSpell> bloodlustPool,
        InjectedPool<DeathAndDecaySpell> deathAndDecayPool,
        InjectedPool<DeathCoilSpell> deathCoilPool,
        InjectedPool<ExorcismSpell> exorcismPool,
        InjectedPool<EyeOfKilroggSpell> eyeOfKilroggPool,
        InjectedPool<FlameShieldSpell> flameShieldPool,
        InjectedPool<HasteSpell> hastePool,
        InjectedPool<HealSpell> healPool,
        InjectedPool<HolyVisionSpell> holyVisionPool,
        InjectedPool<InvisibilitySpell> invisibilityPool,
        InjectedPool<PolymorphSpell> polymorphPool,
        InjectedPool<RaiseDeadSpell> raiseDeadPool,
        InjectedPool<RunesSpell> runesPool,
        InjectedPool<SlowSpell> slowPool,
        InjectedPool<UnholyArmourSpell> unholyArmourPool,
        InjectedPool<WhirlwindSpell> whirlwindPool)
    {
        this.blizzardPool = blizzardPool;
        this.bloodlustPool = bloodlustPool;
        this.deathAndDecayPool = deathAndDecayPool;
        this.deathCoilPool = deathCoilPool;
        this.exorcismPool = exorcismPool;
        this.eyeOfKilroggPool = eyeOfKilroggPool;
        this.flameShieldPool = flameShieldPool;
        this.hastePool = hastePool;
        this.healPool = healPool;
        this.holyVisionPool = holyVisionPool;
        this.invisibilityPool = invisibilityPool;
        this.polymorphPool = polymorphPool;
        this.raiseDeadPool = raiseDeadPool;
        this.runesPool = runesPool;
        this.slowPool = slowPool;
        this.unholyArmourPool = unholyArmourPool;
        this.whirlwindPool = whirlwindPool;
    }

    @Override
    public Action get(ActionIdentifier action) {
        Validate.isInstanceOf(SpellActions.class, action);
        switch ((SpellActions)action) {
            case BlizzardSpell: getAction(blizzardPool, action);
            case BloodlustSpell: getAction(bloodlustPool, action);
            case DeathAndDecaySpell: getAction(deathAndDecayPool, action);
            case DeathCoilSpell: getAction(deathCoilPool, action);
            case ExorcismSpell: getAction(exorcismPool, action);
            case EyeOfKilroggSpell: getAction(eyeOfKilroggPool, action);
            case FlameShieldSpell: getAction(flameShieldPool, action);
            case HasteSpell: getAction(hastePool, action);
            case HealSpell: getAction(healPool, action);
            case HolyVisionSpell: getAction(holyVisionPool, action);
            case InvisibilitySpell: getAction(invisibilityPool, action);
            case PolymorphSpell: getAction(polymorphPool, action);
            case RaiseDeadSpell: getAction(raiseDeadPool, action);
            case RunesSpell: getAction(runesPool, action);
            case SlowSpell: getAction(slowPool, action);
            case UnholyArmourSpell: getAction(unholyArmourPool, action);
            case WhirlwindSpell: getAction(whirlwindPool, action);
            default: throw new UnsupportedOperationException();
        }
    }

    private <T extends Action> T getAction(InjectedPool<T> pool, ActionIdentifier identifier) {
        T result = pool.obtain();
        result.setIdentifier(identifier);
        return result;
    }
}
