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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.audio.SilentSoundEffect;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.graphics.Animation;
import com.evilbird.engine.common.graphics.Animator;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.lang.Animated;
import com.evilbird.engine.common.lang.Audible;
import com.evilbird.engine.common.lang.Directionable;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Styleable;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemBasic;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Instances of this {@link Item} are drawn using a selection of given
 * {@link DirectionalAnimation animations} and {@link SoundEffect sounds}.
 *
 * @author Blair Butterworth
 */
public class AnimatedItem extends ItemBasic implements Animated, Audible, Directionable, Styleable
{
    private float direction;
    private Identifier animationId;
    private Identifier soundId;

    private transient Skin skin;
    private transient AnimatedItemStyle style;
    private transient Animator animator;
    private transient SoundEffect sound;

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
        this.style = new AnimatedItemStyle();
        this.animationId = null;
        this.animator = new Animator();
        this.soundId = null;
        this.sound = new SilentSoundEffect();
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

    @Override
    public void setSkin(Skin skin) {
        Validate.notNull(skin);
        this.skin = skin;
        setStyle("default");
    }

    @Override
    public void setStyle(String name) {
        Validate.validState(skin != null);
        setStyle(skin.get(name, AnimatedItemStyle.class));
    }

    public void setStyle(AnimatedItemStyle style) {
        Validate.notNull(style.animations);
        Validate.notNull(style.sounds);
        this.style = new AnimatedItemStyle(style);
    }

    @Override
    public void setAnimation(Identifier id) {
        setAnimation(id, 0);
    }

    @Override
    public void setAnimation(Identifier id, float startTime) {
        Validate.notNull(id);
        Validate.isTrue(style.animations.containsKey(id));
        animationId = id;
        animator.setAnimationTime(startTime);
        animator.setAnimation(style.animations.get(id));
        setDirection(animator.getAnimation(), direction);
    }

    @Override
    public void setAnimationAlias(Identifier animationId, Identifier aliasId) {
        Validate.notNull(animationId);
        Validate.notNull(aliasId);
        Validate.isTrue(style.animations.containsKey(animationId));
        style.animations.put(aliasId, style.animations.get(animationId));
    }

    @Override
    public void setSound(Identifier id) {
        Validate.notNull(id);
        Validate.isTrue(style.sounds.containsKey(id));
        soundId = id;
        sound.stop();
        sound = style.sounds.get(id);
        sound.play();
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
        setDirection(animator.getAnimation(), direction);
    }

    private void setDirection(Animation animation, float direction) {
        if (animation instanceof DirectionalAnimation) {
            DirectionalAnimation directionalAnimation = (DirectionalAnimation)animation;
            directionalAnimation.setDirection(direction);
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
        super.draw(batch, alpha);
        animator.draw(batch, getPosition(), getSize());
    }

    @Override
    public void update(float time) {
        super.update(time);
        animator.update(time);
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
