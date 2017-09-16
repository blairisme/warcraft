package com.evilbird.warcraft.action.common;

import com.badlogic.gdx.scenes.scene2d.Action;

/**
 * Created by blair on 15/09/2017.
 */

public class AttackAction extends Action
{
    @Override
    public boolean act(float delta)
    {
        /*
        private Action newAttackAction(Item target)
    {
        ItemProperty health = UnitProperties.Health;
        ActionValue value = new ItemValue(target, health);
        ActionModifier modifier = new DeltaModifier(-10f, DeltaType.PerSecond, 0f, 100f);
        ActionDuration duration = new PredicateDuration(target, health, 0f);
        return new ModifyAction(value, modifier, duration);
    }
         */
        return false;
    }
}
