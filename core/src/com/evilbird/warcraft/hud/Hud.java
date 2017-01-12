package com.evilbird.warcraft.hud;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Hud extends Actor
{
    private TextureRegion region;

    public Hud(TextureRegion region)
    {
        this.region = region;
    }
    
    @Override
    public void draw(Batch batch, float alpha)
    {
        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
}
