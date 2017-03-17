package com.evilbird.warcraft.item.hud.building;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.item.Item;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class BuildSite extends Item
{
    private Drawable building;
    private Drawable overlay;
    private Drawable allowed;
    private Drawable prohibited;

    public BuildSite()
    {
    }

    @Override
    public void draw(Batch batch, float alpha)
    {
        float x = getX();
        float y = getY();
        float width = getWidth();
        float height = getHeight();
        building.draw(batch, x, y, width, height);
        overlay.draw(batch, x, y, width, height);
    }

    public void setBuildingTexture(Drawable texture)
    {
        this.building = texture;
    }

    public void setAllowedTexture(Drawable texture)
    {
        this.allowed = texture;
        this.overlay = texture;
    }

    public void setProhibitedTexture(Drawable texture)
    {
        this.prohibited = texture;
    }
}
