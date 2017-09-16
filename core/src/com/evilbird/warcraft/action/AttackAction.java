package com.evilbird.warcraft.action;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;

/**
 * Created by blair on 15/09/2017.
 */

public class AttackAction extends Action
{
    private Attackable attackable;

    public AttackAction()
    {
        
    }

    @Override
    public boolean act(float delta)
    {
/*
        ItemProperty health = UnitProperties.Health;
        ActionValue value = new ItemValue(target, health);
        ActionModifier modifier = new DeltaModifier(-10f, DeltaType.PerSecond, 0f, 100f);
        ActionDuration duration = new PredicateDuration(target, health, 0f);
        return new ModifyAction(value, modifier, duration);

         */

/*
        float value = zoomable.getZoom();
        float delta = input.getDelta().x;
        float scale =  value * delta;
        float zoom = MathUtils.clamp(scale, 0.25f, 1.5f);
        zoomable.setZoom(zoom);
        */


        return false;
    }
}
