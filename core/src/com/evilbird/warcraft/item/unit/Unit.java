package com.evilbird.warcraft.item.unit;

import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.warcraft.item.unit.common.AnimatedItem;

import java.util.Map;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class Unit extends AnimatedItem
{
    private float health;

    public Unit(Map<Identifier, Object> properties, Map<Identifier, DirectionalAnimation> animations)
    {
        super(properties, animations);
    }

    public float getHealth()
    {
        return health;
    }

    public void setHealth(float health)
    {
        this.health = health;
    }

    //HealthMax

    //Icon

    //Name

    //Level

    //Speed

    //Sight

    //Damage

    //Armour
}
