package com.evilbird.warcraft.item.hud;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.item.control.GridPanel;
import com.evilbird.engine.item.control.Image;

import javax.inject.Provider;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class SelectionTile extends GridPanel//ItemGroup
{
    private Image icon;
    private HealthBar healthBar;

    public SelectionTile(Provider<HealthBar> healthBarProvider)
    {
        super(1,2);

        icon = new Image();
        icon.setSize(50, 42);
        icon.setPadding(2);

        healthBar = healthBarProvider.get();
        healthBar.setProgress(1);
        healthBar.setSize(46, 5);

        setSize(54, 46);
        setCellPadding(2);
        setCellSpacing(2);
        setCell(icon, 0, 0);
        setCell(healthBar, 0, 1);
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
