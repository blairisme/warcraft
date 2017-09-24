package com.evilbird.warcraft.item.unit.gatherer;

import com.evilbird.engine.item.specialized.animated.AnimationIdentifier;
import com.evilbird.warcraft.item.common.capability.ResourceContainer;
import com.evilbird.warcraft.item.common.capability.ResourceIdentifier;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.resource.ResourceType;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by blair on 21/09/2017.
 */

public class Gatherer extends Combatant implements ResourceContainer
{
    private Map<ResourceIdentifier, Float> resources;

    @Inject
    public Gatherer()
    {
        resources = new HashMap<ResourceIdentifier, Float>();
    }

    @Override
    public float getResource(ResourceIdentifier resource)
    {
        Float result = resources.get(resource);
        return result != null ? result : 0f;
    }

    @Override
    public void setResource(ResourceIdentifier type, float value)
    {
        this.resources.put(type, value);
    }

    @Override
    public void setAnimation(AnimationIdentifier animationId)
    {
        if (animationId == UnitAnimation.Idle){
            animationId = getIdleIdentifier();
        }
        else if (animationId == UnitAnimation.Move){
            animationId = getMoveIdentifier();
        }
        super.setAnimation(animationId);
    }

    private AnimationIdentifier getIdleIdentifier()
    {
        if (getResource(ResourceType.Gold) > 0){
            return UnitAnimation.IdleWithGold;
        }
        else if (getResource(ResourceType.Wood) > 0){
            return UnitAnimation.IdleWithWood;
        }
        return UnitAnimation.Idle;
    }

    private AnimationIdentifier getMoveIdentifier()
    {
        if (getResource(ResourceType.Gold) > 0){
            return UnitAnimation.MoveWithGold;
        }
        else if (getResource(ResourceType.Wood) > 0){
            return UnitAnimation.MoveWithWood;
        }
        return UnitAnimation.Move;
    }
}
