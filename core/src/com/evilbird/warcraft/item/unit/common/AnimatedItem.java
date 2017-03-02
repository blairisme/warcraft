package com.evilbird.warcraft.item.unit.common;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;

import java.util.Map;
import java.util.Objects;

//TODO: Rename to anmiation
//TODO: Move to common controls
//TODO: Replace constructor with zero argument version
public class AnimatedItem extends Item
{
    private static final Identifier ANIMATION_PROPERTY = new Identifier("Animation");
    private static final Identifier SOUND_PROPERTY = new Identifier("Sound");
    private static final Identifier SOUNDS_PROPERTY = new Identifier("Sounds");

    private Identifier animationId;
    private DirectionalAnimation animation;
    private Map<Identifier, DirectionalAnimation> animations;

    private float animationTime;
    private float direction;

    private ShapeRenderer shapeRenderer;

    public AnimatedItem(Map<Identifier, Object> properties, Map<Identifier, DirectionalAnimation> animations)
    {
        //super(properties);

        this.properties = properties;  //TODO: Remove
        this.animations = animations;
        this.shapeRenderer = new ShapeRenderer();

        setAnimation(new Identifier("Idle")); // TODO: Read from properties
    }

    @Override
    public void update(float delta)
    {
        super.update(delta);
        animationTime += delta;
        play();
    }

    //TODO: Does this belong here? Feels like it should be in some audio manager class.
    //TODO: Log if sound is missing
    //TODO: Need to stop if item disappears
    public void play()
    {
        Identifier sound = (Identifier)getProperty(SOUND_PROPERTY);
        if (sound != null)
        {
            Map<Identifier, Sound> sounds = (Map<Identifier, Sound>)getProperty(SOUNDS_PROPERTY);
            Sound audio = sounds != null ? sounds.get(sound) : null;

            if (audio != null)
            {
                audio.play(1f);
                setProperty(SOUND_PROPERTY, null);
            }
        }
    }

    @Override
    public void draw(Batch batch, float alpha)
    {
        TextureRegion region = animation.getKeyFrame(animationTime);
        //batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
        //        getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());



        batch.draw(region, getX(), getY(), getWidth(), getHeight());

        Boolean selected = (Boolean)getProperty(new Identifier("Selected"));
        if (Boolean.TRUE == selected)
        {
            batch.end();

            shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
            shapeRenderer.begin(ShapeType.Line);
            shapeRenderer.setColor(Color.LIME); //TODO: r70 g200 b60
            shapeRenderer.rect(getX(), getY(), getWidth(), getHeight());
            shapeRenderer.end();

            batch.begin();
        }
    }

    public Identifier getAnimation()
    {
        return animationId;
    }

    public void setAnimation(Identifier animationId)
    {
        this.animationTime = 0;
        this.animationId = animationId;
        this.animation = animations.get(animationId);
        this.animation.setDirection(direction);
    }

    @Override
    public Object getProperty(Identifier property)
    {
        if (Objects.equals(property, ANIMATION_PROPERTY))
        {
            return getAnimation();
        }
        return super.getProperty(property);
    }

    @Override
    public void setProperty(Identifier property, Object value)
    {
        if (Objects.equals(property, ANIMATION_PROPERTY))
        {
            setAnimation((Identifier) value);
        }
        super.setProperty(property, value);
    }

    @Override
    public void setPosition(float newX, float newY)
    {
        float previousX = getX();
        float previousY = getY();
        super.setPosition(newX, newY);
        this.setDirection(previousX, previousY, newX, newY);
    }

    @Override
    public void setPosition(Vector2 position)
    {
        Vector2 previous = getPosition();
        super.setPosition(position);
        this.setDirection(previous.x, previous.y, position.x, position.y);
    }

    private void setDirection(float previousX, float previousY, float newX, float newY)
    {
        Vector2 destination = new Vector2(newX, newY);
        Vector2 position = new Vector2(previousX, previousY);
        Vector2 directionVector = destination.sub(position);
        Vector2 normalizedDirection = directionVector.nor();

        direction = normalizedDirection.angle();
        animation.setDirection(direction);
    }





    public void setAnimationDefinition(Identifier id, DirectionalAnimation animation)
    {
        this.animations.put(id, animation);
    }
}
