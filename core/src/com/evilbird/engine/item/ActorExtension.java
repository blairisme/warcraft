package com.evilbird.engine.item;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ActorExtension extends Actor
{
    private ActorObserver observer;

    public ActorExtension(ActorObserver observer)
    {
        this.observer = observer;
    }

    @Override
    public void act(float delta)
    {
        super.act(delta);
        observer.update(delta);
    }

    @Override
    public void draw(Batch batch, float alpha)
    {
        super.draw(batch, alpha);
        observer.draw(batch, alpha);
    }

    @Override
    public void positionChanged()
    {
        observer.positionChanged();
    }

    @Override
    public void sizeChanged()
    {
        observer.sizeChanged();
    }
}
