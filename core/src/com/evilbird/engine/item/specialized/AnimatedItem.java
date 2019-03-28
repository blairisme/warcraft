/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.specialized;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.graphics.Colours;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.lang.Animated;
import com.evilbird.engine.common.lang.Audible;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemBasic;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Instances of this {@link Item} are drawn using a selection of given
 * {@link DirectionalAnimation animations} and {@link SoundEffect sounds}.
 *
 * @author Blair Butterworth
 */
//TODO: Move selected box drawing to unit
//TODO: Use style and skin for selection texture
public class AnimatedItem extends ItemBasic implements Animated, Audible
{
    private static transient final Logger logger = LoggerFactory.getLogger(AnimatedItem.class);

    private float direction;
    private transient Texture selection;

    private Identifier animationId;
    private transient float animationTime;
    private transient DirectionalAnimation currentAnimation;
    private transient Map<Identifier, DirectionalAnimation> animations;

    private Identifier soundId;
    private transient SoundEffect currentSound;
    private transient Map<Identifier, SoundEffect> sounds;

    public AnimatedItem() {
        this.direction = 0;
        this.animationTime = 0;
        this.currentAnimation = null;
        this.animationId = null;
        this.animations = new HashMap<>();
        this.sounds = new HashMap<>();
        this.currentSound = null;
        this.soundId = null;
    }

    /**
     * Returns the identifier of the currently displayed animation.
     *
     * @return  an {@link Identifier}. This methods may return
     *          <code>null</code>, indicating that no animation is current used.
     */
    public Identifier getAnimation() {
        return animationId;
    }

    /**
     * Returns the identifier of the currently playing sound.
     *
     * @return  a {@link Identifier}. This method may return
     *          <code>null</code>, indicating that no sound is current playing.
     */
    public Identifier getSound() {
        return soundId;
    }

    /**
     * Specifies that the AnimatedItem shouls be rendered using the animation
     * with the given identifier.
     *
     * @param animationId   the {@link Identifier} of the animation to use.
     *                      This parameter may be <code>null</code>, indicating
     *                      that no animation should be used.
     */
    public void setAnimation(Identifier animationId) {
        this.animationId = animationId;
        this.currentAnimation = null;
    }

    public void setAnimationAlias(Identifier animationId, Identifier aliasId) {
        DirectionalAnimation animation = animations.get(animationId);
        setAvailableAnimation(aliasId, animation);
    }

    public void setAvailableAnimation(Identifier id, DirectionalAnimation animation) {
        this.animations.put(id, animation);
    }

    public void setAvailableAnimations(Map<Identifier, DirectionalAnimation> animations) {
        this.animations.putAll(animations);
    }

    public void setAvailableSound(Identifier id, SoundEffect sound) {
        sounds.put(id, sound);
    }

    public void setAvailableSounds(Map<Identifier, SoundEffect> collection) {
        sounds.putAll(collection);
    }

    public void setSound(Identifier id) {
        if (currentSound != null) {
            currentSound.stop();
        }
        soundId = id;
        currentSound = null;
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
        drawSelection(batch);
        drawAnimation(batch);
    }

    private void drawAnimation(Batch batch) {
        if (currentAnimation != null) {
            TextureRegion region = currentAnimation.getKeyFrame(animationTime);
            float width = region.getRegionWidth();
            float height = region.getRegionHeight();
            float widthHalfDelta = (width - getWidth()) * 0.5f;
            float heightHalfDelta = (height - getHeight()) * 0.5f;
            float x = getX() - widthHalfDelta;
            float y = getY() - heightHalfDelta;
            batch.draw(region, x, y, width, height);
        }
    }

    private void drawSelection(Batch batch) {
        if (selection == null){
            Pixmap pixmap = new Pixmap((int)getWidth(), (int)getHeight(), Pixmap.Format.RGBA8888);
            pixmap.setColor(Colours.FOREST_GREEN);
            pixmap.drawRectangle(0, 0, pixmap.getWidth(), pixmap.getHeight());
            selection = new Texture(pixmap);
        }
        if (getSelected()) {
            batch.draw(selection, getX(), getY(), getWidth(), getHeight());
        }
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        loadAnimation();
        loadSound();
        updateAnimation(delta);
    }

    private void loadAnimation() {
        if (currentAnimation == null && animationId != null) {
            if (animations.containsKey(animationId)) {
                currentAnimation = animations.get(animationId);
                currentAnimation.setDirection(direction);
            } else {
                logger.warn("Missing animation: " + animationId);
                animationId = null;
            }
        }
    }

    private void loadSound() {
        if (currentSound == null && soundId != null) {
            if (sounds.containsKey(soundId)) {
                currentSound = sounds.get(soundId);
                currentSound.play();
            } else {
                logger.warn("Missing sound: " + soundId);
                soundId = null;
            }
        }
    }

    private void updateAnimation(float delta) {
        animationTime += delta;
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
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(direction)
            .append(animationId)
            .append(soundId)
            .toHashCode();
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
}
