package com.evilbird.warcraft.item.hud;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.control.GridPane;
import com.evilbird.engine.item.control.Image;

import javax.inject.Provider;

/**
 * Instances of this class TODO
 *
 * @author Blair Butterworth
 */
public class UnitPane extends GridPane
{
    private Image icon;
    private HealthBar healthBar;

    public UnitPane(Provider<HealthBar> healthBarProvider)
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
        Drawable icon = (Drawable)item.getProperty(new Identifier("Icon")); //TODO
        //float health = item.getProperty(new Identifier("Health")); //TODO
        //float healthMaximum = item.getProperty(new Identifier("HealthMaximum")); //TODO

        setImage(icon);
        setHealth(0.5f);
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
