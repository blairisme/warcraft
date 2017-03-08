package com.evilbird.warcraft.item.hud.common;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.control.GridPane;
import com.evilbird.engine.item.control.Image;
import com.evilbird.warcraft.item.unit.Unit;

/**
 * Instances of this class TODO
 *
 * @author Blair Butterworth
 */
public class UnitPane extends GridPane
{
    private Image icon;
    private HealthBar healthBar;

    public UnitPane(HealthBarProvider healthBarProvider)
    {
        super(1,2);

        icon = new Image();
        icon.setSize(50, 42);
        icon.setPadding(2);

        healthBar = healthBarProvider.get();
        healthBar.setProgress(1);
        healthBar.setSize(46, 5);

        setSize(54, 53);
        setCellPadding(2);
        setCellSpacing(2);
        setCell(icon, 0, 0);
        setCell(healthBar, 0, 1);
        setType(new Identifier("UnitPane"));
        setTouchable(Touchable.enabled);
    }

    public void setItem(Item item)
    {
        if (item instanceof Unit) //TODO: Pass in unit?
        {
            Unit unit = (Unit)item;
            Drawable icon = unit.getIcon();

            float health = unit.getHealth();
            float healthMaximum = unit.getHealthMaximum();
            float healthPercent = health != 0 && healthMaximum != 0 ? health / healthMaximum : 0f;

            setImage(icon);
            setHealth(healthPercent);
        }
    }

    public void setImage(Drawable drawable)
    {
        icon.setImage(drawable);
    }

    public void setHealth(float health)
    {
        healthBar.setProgress(health);
    }
}
