/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.specialized;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.graphics.Animation;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.lang.Animated;
import com.evilbird.engine.common.lang.Audible;
import com.evilbird.engine.common.lang.Directionable;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Styleable;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemBasic;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Map;

/**
 * Instances of this {@link Item} are drawn using a selection of given
 * {@link DirectionalAnimation animations} and {@link SoundEffect sounds}.
 *
 * @author Blair Butterworth
 */
public class AnimatedItem extends ItemBasic implements Animated, Audible, Directionable, Styleable
{
    private static final transient Logger logger = LoggerFactory.getLogger(AnimatedItem.class);

    private float direction;
    private Identifier animationId;
    private Identifier soundId;

    private transient Skin skin;
    private transient float animationTime;
    private transient Animation currentAnimation;
    private transient SoundEffect currentSound;
    private transient Map<Identifier, Animation> animations;
    private transient Map<Identifier, SoundEffect> sounds;

    /**
     * Constructs a new instance of this class given a {@link Skin} containing
     * an {@link AnimatedItemStyle}, specifying the visual and auditory
     * presentation of the new {@code AnimatedItem}.
     *
     * @param skin a {@code Skin} instance. This method cannot be {@code null}.
     */
    public AnimatedItem(Skin skin) {
        this();
        setSkin(skin);
    }

    protected AnimatedItem() {
        this.direction = 0;
        this.animationTime = 0;
        this.animationId = null;
        this.soundId = null;
        this.animations = Collections.emptyMap();
        this.sounds = Collections.emptyMap();
    }

    @Override
    public Identifier getAnimation() {
        return animationId;
    }

    @Override
    public Identifier getSound() {
        return soundId;
    }

    @Override
    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
        setStyle("default");
    }

    @Override
    public void setStyle(String name) {
        AnimatedItemStyle style = skin.get(name, AnimatedItemStyle.class);

        this.animations = style.animations;
        this.currentAnimation = null;
        this.animationId = null;
        this.animationTime = 0;

        this.sounds = style.sounds;
        this.currentSound = null;
        this.soundId = null;
    }

    @Override
    public void setAnimation(Identifier id) {
        animationId = id;
        currentAnimation = null;
    }

    public void setAnimationAlias(Identifier animationId, Identifier aliasId) {
        Animation animation = animations.get(animationId);
        animations.put(aliasId, animation);
    }

    public void resetAnimation() {
        animationTime = 0;
    }

    @Override
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

    @Override
    public void setDirection(Vector2 normalizedDirection) {
        direction = normalizedDirection.angle();
        setDirection(direction);
    }

    private void setDirection(float direction) {
        if (currentAnimation instanceof DirectionalAnimation) {
            DirectionalAnimation animation = (DirectionalAnimation)currentAnimation;
            animation.setDirection(direction);
        }
    }

    private void setDirection(float previousX, float previousY, float newX, float newY) {
        Vector2 destination = new Vector2(newX, newY);
        Vector2 position = new Vector2(previousX, previousY);
        Vector2 directionVector = destination.sub(position);
        Vector2 normalizedDirection = directionVector.nor();
        setDirection(normalizedDirection);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        if (currentAnimation != null) {
            TextureRegion region = currentAnimation.getFrame(animationTime);
            float width = region.getRegionWidth();
            float height = region.getRegionHeight();
            float widthHalfDelta = (width - getWidth()) * 0.5f;
            float heightHalfDelta = (height - getHeight()) * 0.5f;
            float x = getX() - widthHalfDelta;
            float y = getY() - heightHalfDelta;
            batch.draw(region, x, y, width, height);
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
                setDirection(direction);
            } else {
                logger.warn("{} missing animation: {}", getType(), animationId);
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
                logger.warn("{} missing sound: {}", getType(), soundId);
                soundId = null;
            }
        }
    }

    private void updateAnimation(float delta) {
        animationTime += delta;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

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
