package com.evilbird.engine.item.control;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemProperty;

import java.util.HashMap;
import java.util.Map;

//TODO: Rename to Animation
//TODO: Move selected box drawing to unit
//TODO: Use real logging
public class AnimatedItem extends Item
{
    private float direction;
    private ShapeRenderer shapeRenderer;

    private boolean updateAnimation;
    private float animationTime;
    private Identifier currentAnimationId;
    private DirectionalAnimation currentAnimation;
    private Map<Identifier, DirectionalAnimation> animations;

    private boolean updateSound;
    private SoundEffect currentSound;
    private Identifier currentSoundId;
    private Map<Identifier, SoundEffect> sounds;

    public AnimatedItem()
    {
        this.shapeRenderer = new ShapeRenderer();
        this.direction = 0;
        this.animationTime = 0;
        this.currentAnimation = null;
        this.currentAnimationId = null;
        this.animations = new HashMap<Identifier, DirectionalAnimation>();
        this.sounds = new HashMap<Identifier, SoundEffect>();
        this.currentSound = null;
        this.currentSoundId = null;
        this.updateSound = false;
        this.updateAnimation = false;
    }

    public Identifier getAnimation()
    {
        return currentAnimationId;
    }

    protected TextureRegion getAnimationFrame()
    {
        return currentAnimation.getKeyFrame(animationTime);
    }

    public void setAnimation(Identifier animationId)
    {
        this.currentAnimationId = animationId;
        this.updateAnimation = true;
    }

    public void setAvailableAnimation(Identifier id, DirectionalAnimation animation)
    {
        this.animations.put(id, animation);
        this.updateAnimation = true;
    }

    public void setAvailableAnimations(Map<Identifier, DirectionalAnimation> animations)
    {
        this.animations.putAll(animations);
        this.updateAnimation = true;
    }

    public Identifier getSound()
    {
        return currentSoundId;
    }

    public void setSound(Identifier id)
    {
        currentSoundId = id;
        updateSound = true;
    }

    public void setAvailableSound(Identifier id, SoundEffect sound)
    {
        sounds.put(id, sound);
    }

    public void setAvailableSounds(Map<Identifier, SoundEffect> sounds)
    {
        this.sounds.putAll(sounds);
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

    public void setDirection(float previousX, float previousY, float newX, float newY)
    {
        Vector2 destination = new Vector2(newX, newY);
        Vector2 position = new Vector2(previousX, previousY);
        Vector2 directionVector = destination.sub(position);
        Vector2 normalizedDirection = directionVector.nor();
        setDirection(normalizedDirection);
    }

    public void setDirection(Vector2 normalizedDirection)
    {
        direction = normalizedDirection.angle();
        if (currentAnimation != null){
            currentAnimation.setDirection(direction);
        }
    }

    @Override
    public void draw(Batch batch, float alpha)
    {
        updateAnimation();
        drawAnimation(batch);
        drawSelection(batch);
    }

    protected void updateAnimation()
    {
        if (updateAnimation){
            updateAnimation = false;
            if (animations.containsKey(currentAnimationId)){
                currentAnimation = animations.get(currentAnimationId);
                currentAnimation.setDirection(direction);
            }
            else {
                System.out.println("Missing animation: " + currentAnimationId.toString());
            }
        }
    }

    protected void drawAnimation(Batch batch)
    {
        TextureRegion region = getAnimationFrame();
        batch.draw(region, getX(), getY(), getWidth(), getHeight());
    }

    protected void drawSelection(Batch batch)
    {
        if (getSelected()){
            batch.end(); //TODO: Inefficient. Better if draw occurs using batch.
            shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
            shapeRenderer.begin(ShapeType.Line);
            shapeRenderer.setColor(Color.LIME); //TODO: r70 g200 b60
            shapeRenderer.rect(getX(), getY(), getWidth(), getHeight());
            shapeRenderer.end();
            batch.begin();
        }
    }

    @Override
    public void update(float delta)
    {
        super.update(delta);
        updateVisual(delta);
        updateAudio();
    }

    protected void updateVisual(float delta)
    {
        animationTime += delta;
    }

    protected void updateAudio()
    {
        if (updateSound){
            updateSound = false;
            stopCurrentSound();
            startNewSound();
        }
    }

    protected void stopCurrentSound()
    {
        if (currentSound != null){
            currentSound.stop();
        }
    }

    protected void startNewSound()
    {
        if (sounds.containsKey(currentSoundId)){
            currentSound = sounds.get(currentSoundId);
            currentSound.play();
        }
        else {
            System.out.println("Missing sound: " + currentSoundId.toString());
        }
    }

    @Override
    public Object getProperty(ItemProperty property)
    {
        if (AnimationProperties.Animation.equals(property)){
            return getAnimation();
        }
        else if (AnimationProperties.Sound.equals(property)){
            return getSound();
        }
        return super.getProperty(property);
    }

    @Override
    public void setProperty(ItemProperty property, Object value)
    {
        if (AnimationProperties.Animation.equals(property)){
            setAnimation((Identifier) value);
        }
        else if (AnimationProperties.Sound.equals(property)){
            setSound((Identifier)value);
        }
        else{
            super.setProperty(property, value);
        }
    }
}
