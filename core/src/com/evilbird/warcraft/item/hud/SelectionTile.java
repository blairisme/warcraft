package com.evilbird.warcraft.item.hud;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.hud.control.Image;
import com.evilbird.warcraft.item.hud.control.Table;

import javax.inject.Provider;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class SelectionTile extends Item
{
    private Table container;
    private Image icon;
    private HealthBar healthBar;

    public SelectionTile(Provider<HealthBar> healthBarProvider)
    {
        icon = new Image();
        icon.setWidth(50);
        icon.setHeight(42);
        icon.setPadding(2);

        healthBar = healthBarProvider.get();
        healthBar.setProgress(1);
        healthBar.setWidth(46);
        healthBar.setHeight(5);

        container = new Table(1, 2);
        container.setWidth(54);
        container.setHeight(46);
        container.setCellPadding(2);
        container.setCellSpacing(2);
        container.setCell(icon, 0, 0);
        container.setCell(healthBar, 0, 1);
    }

    public void setBackground(Drawable background)
    {
        container.setBackground(background);
    }

    public void setImage(Drawable drawable)
    {
        icon.setImage(drawable);
    }

    public void setHealth(float health)
    {
        healthBar.setProgress(health);
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        container.draw(batch, parentAlpha);
    }

    @Override //TODO: Investigate better implementation
    protected void positionChanged()
    {
        container.setPosition(getX(), getY());
    }

    @Override //TODO: Investigate better implementation
    protected void sizeChanged()
    {
        container.setSize(getWidth(), getHeight());
    }
}
