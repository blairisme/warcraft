/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.animated;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.item.Item;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//TODO: Move selected box drawing to unit
//TODO: Use real logging
public class AnimatedItem extends Item implements Animated, Audible
{
    private float direction;
    private transient ShapeRenderer shapeRenderer;

    private boolean updateAnimation;
    private AnimationIdentifier animationId;
    private transient float animationTime;
    private transient DirectionalAnimation currentAnimation;
    private transient Map<AnimationIdentifier, DirectionalAnimation> animations;

    private boolean updateSound;
    private SoundIdentifier soundId;
    private transient SoundEffect currentSound;
    private transient Map<SoundIdentifier, SoundEffect> sounds;

    public AnimatedItem() {
        this.direction = 0;
        this.animationTime = 0;
        this.currentAnimation = null;
        this.animationId = null;
        this.animations = new HashMap<>();
        this.sounds = new HashMap<>();
        this.currentSound = null;
        this.soundId = null;
        this.updateSound = false;
        this.updateAnimation = false;
    }

    public AnimationIdentifier getAnimation() {
        return animationId;
    }

    protected TextureRegion getAnimationFrame() {
        return currentAnimation.getKeyFrame(animationTime);
    }

    public DirectionalAnimation getAvailableAnimation(AnimationIdentifier id) {
        return animations.get(id);
    }

    public void setAnimation(AnimationIdentifier animationId) {
        this.animationId = animationId;
        this.updateAnimation = true;
    }

    public void setAvailableAnimation(AnimationIdentifier id, DirectionalAnimation animation) {
        this.animations.put(id, animation);
        this.updateAnimation = true;
    }

    public void setAvailableAnimations(Map<AnimationIdentifier, DirectionalAnimation> animations) {
        this.animations.putAll(animations);
        this.updateAnimation = true;
    }

    public SoundIdentifier getSound() {
        return soundId;
    }

    public void setSound(SoundIdentifier id) {
        soundId = id;
        updateSound = id != null;
    }

    public void setAvailableSound(SoundIdentifier id, SoundEffect sound) {
        sounds.put(id, sound);
    }

    public void setAvailableSounds(Map<SoundIdentifier, SoundEffect> sounds) {
        this.sounds.putAll(sounds);
    }

    @Override
    public void setPosition(float newX, float newY) {
        float previousX = getX();
        float previousY = getY();
        super.setPosition(newX, newY);
        this.setDirection(previousX, previousY, newX, newY);
    }

    @Override
    public void setPosition(Vector2 position) {
        Vector2 previous = getPosition();
        super.setPosition(position);
        this.setDirection(previous.x, previous.y, position.x, position.y);
    }

    public void setDirection(float previousX, float previousY, float newX, float newY) {
        Vector2 destination = new Vector2(newX, newY);
        Vector2 position = new Vector2(previousX, previousY);
        Vector2 directionVector = destination.sub(position);
        Vector2 normalizedDirection = directionVector.nor();
        setDirection(normalizedDirection);
    }

    public void setDirection(Vector2 normalizedDirection) {
        direction = normalizedDirection.angle();
        if (currentAnimation != null) {
            currentAnimation.setDirection(direction);
        }
    }

    @Override
    public void draw(Batch batch, float alpha) {
        updateAnimation();
        drawSelection(batch);
        drawAnimation(batch);
    }

    protected void updateAnimation() {
        if (updateAnimation) {
            updateAnimation = false;
            if (animations.containsKey(animationId)) {
                currentAnimation = animations.get(animationId);
                currentAnimation.setDirection(direction);
            } else {
                System.out.println("Missing animation: " + Objects.toString(animationId, "null"));
            }
        }
    }

    protected void drawAnimation(Batch batch) {
        TextureRegion region = getAnimationFrame();

        float width = region.getRegionWidth();
        float height = region.getRegionHeight();

        float widthHalfDelta = (width - getWidth()) * 0.5f;
        float heightHalfDelta = (height - getHeight()) * 0.5f;

        float x = getX() - widthHalfDelta;
        float y = getY() - heightHalfDelta;

        batch.draw(region, x, y, width, height);
    }

    protected void drawSelection(Batch batch) {
        if (getSelected()) {
            if (shapeRenderer == null) {
                this.shapeRenderer = new ShapeRenderer();
            }

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
    public void update(float delta) {
        super.update(delta);
        updateVisual(delta);
        updateAudio();
    }

    protected void updateVisual(float delta) {
        animationTime += delta;
    }

    protected void updateAudio() {
        if (updateSound) {
            updateSound = false;
            stopCurrentSound();
            startNewSound();
        }
    }

    protected void stopCurrentSound() {
        if (currentSound != null) {
            currentSound.stop();
        }
    }

    protected void startNewSound() {
        if (sounds.containsKey(soundId)) {
            currentSound = sounds.get(soundId);
            currentSound.play();
        } else {
            System.out.println("Missing sound: " +  Objects.toString(soundId, "null"));
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper("item")
            .append("direction", direction)
            .append("animation", animationId)
            .append("sound", soundId)
            .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (obj.getClass() != getClass()) return false;

        AnimatedItem that = (AnimatedItem)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(direction, that.direction)
            .append(animationId, that.animationId)
            .append(soundId, that.soundId)
            .append(updateAnimation, that.updateAnimation)
            .append(updateSound, that.updateSound)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(direction)
            .append(animationId)
            .append(soundId)
            .append(updateAnimation)
            .append(updateSound)
            .toHashCode();
    }
}
