package com.evilbird.warcraft.item.unit.combatant;

import com.evilbird.warcraft.action.Movable;
import com.evilbird.warcraft.item.unit.Unit;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class Combatant extends Unit implements Movable
{
    private float armour;
    private float damageMinimum;
    private float damageMaximum;
    private int level;
    private float speed;
    private float sight;
    private float range;


    public float getArmour()
    {
        return armour;
    }

    public float getDamageMinimum()
    {
        return damageMinimum;
    }

    public float getDamageMaximum()
    {
        return damageMaximum;
    }

    public int getLevel()
    {
        return level;
    }

    public float getSpeed()
    {
        return speed;
    }

    @Override
    public float getMovementSpeed()
    {
        return 64f; //TODO
    }

    public float getSight()
    {
        return sight;
    }

    public float getRange()
    {
        return range;
    }

    public void setArmour(float armour)
    {
        this.armour = armour;
    }

    public void setDamageMinimum(float damageMinimum)
    {
        this.damageMinimum = damageMinimum;
    }

    public void setDamageMaximum(float damageMaximum)
    {
        this.damageMaximum = damageMaximum;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public void setSpeed(float speed)
    {
        this.speed = speed;
    }

    public void setSight(float sight)
    {
        this.sight = sight;
    }

    @Override
    public void setMovementSpeed(float speed)
    {
        //TODO
    }

    public void setRange(float range)
    {
        this.range = range;
    }

    /*
    @Override //TODO
    public Item hit(Vector2 position, boolean touchable)
    {
        //if (touchable && delegate.getTouchable() != Touchable.enabled) return null;
        Vector2 center = new Vector2(36, 36);


        return position.x >= (center.x - 16) && position.x < (center.x + 16) &&
                position.y >= (center.y - 16) && position.y < (center.y + 16) ? this : null;
    }

    @Override
    protected void drawSelection(Batch batch)
    {
        if (getSelected()){
            Vector2 center = new Vector2(36, 36);

            batch.end(); //TODO: Inefficient. Better if draw occurs using batch.
            shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.LIME); //TODO: r70 g200 b60
            shapeRenderer.rect(getX() + 20, getY() + 20, 32, 32);
            shapeRenderer.end();
            batch.begin();
        }
    }

    @Override
    public Object getProperty(ItemProperty property)
    {
        if (UnitProperties.Actions.equals(property)){
            return getActions();
        }
        else if (UnitProperties.Health.equals(property)){
            return getHealth();
        }
        else if (UnitProperties.Icon.equals(property)){
            return getIcon();
        }
        else if (UnitProperties.Gold.equals(property)){
            return getGold();
        }
        else if (UnitProperties.Wood.equals(property)){
            return getWood();
        }
        else if (UnitProperties.Owner.equals(property)){
            return getOwner();
        }
        return super.getProperty(property);
    }

    @Override
    public void setProperty(ItemProperty property, Object value)
    {
        if (UnitProperties.Actions.equals(property)){
            setActions((EnumSet<ActionType>)value);
        }
        else if (UnitProperties.Health.equals(property)){
            setHealth((Float)value);
        }
        else if (UnitProperties.Icon.equals(property)){
            setIcon((Drawable)value);
        }
        else if (UnitProperties.Gold.equals(property)){
            setGold((Float)value);
        }
        else if (UnitProperties.Wood.equals(property)){
            setWood((Float)value);
        }
        else if (UnitProperties.Owner.equals(property)){
            setOwner((Identifier)value);
        }
        else{
            super.setProperty(property, value);
        }
    }
    */
}

