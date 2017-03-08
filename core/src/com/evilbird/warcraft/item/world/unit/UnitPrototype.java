package com.evilbird.warcraft.item.world.unit;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class UnitPrototype extends Unit
{
    private Color tint;

    public UnitPrototype()
    {
        tint = new Color(0x00ff0080);
    }

    @Override
    public void draw(Batch batch, float alpha)
    {
        Color previous = batch.getColor();
        batch.setColor(tint);
        super.draw(batch, alpha);
        batch.setColor(previous);
    }
}
