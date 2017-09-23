package com.evilbird.warcraft.action.common;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.warcraft.item.common.capability.Destructible;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

/**
 * Created by blair on 15/09/2017.
 */
//TODO: Randomly choose attack damage between min and max
//TODO: Negate attack by armour of target
public class AttackAction extends Action
{
    private Combatant combatant;
    private Destructible destructible;

    public AttackAction(Combatant combatant, Destructible destructible)
    {
        this.combatant = combatant;
        this.destructible = destructible;
    }

    @Override
    public boolean act(float time)
    {
        float health = destructible.getHealth();
        float damage = combatant.getDamageMaximum();
        float delta = time * damage;
        float update = health - delta;
        float result = MathUtils.clamp(update, 0f, 100f);
        destructible.setHealth(result);
        return result == 0f;
    }
}
