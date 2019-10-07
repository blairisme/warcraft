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
import com.evilbird.engine.common.audio.sound.LocalizedSound;
import com.evilbird.engine.common.audio.sound.SilentSound;
import com.evilbird.engine.common.audio.sound.Sound;
import com.evilbird.engine.common.graphics.Animation;
import com.evilbird.engine.common.graphics.AnimationRenderer;
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
 * {@link DirectionalAnimation animations} and {@link Sound sounds}.
 *
 * @author Blair Butterworth
 */
public class Viewable extends ItemBasic implements Animated, Audible, Directionable, Styleable
{
    private float direction;
    private Identifier animationId;
    private Identifier soundId;

    private transient Skin skin;
    private transient ViewableStyle style;
    private transient LocalizedSound sound;
    private transient AnimationRenderer animation;

    /**
     * Constructs a new instance of this class given a {@link Skin} containing
     * an {@link ViewableStyle}, specifying the visual and auditory
     * presentation of the new {@code AnimatedItem}.
     *
     * @param skin a {@code Skin} instance. This method cannot be {@code null}.
     */
    public Viewable(Skin skin) {
        this();
        setSkin(skin);
    }

    protected Viewable() {
        this.style = new ViewableStyle();
        this.animationId = null;
        this.animation = new AnimationRenderer();
        this.soundId = null;
        this.sound = new LocalizedSound(new SilentSound(), this);
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

    public boolean hasAnimation(Identifier id) {
        return style.animations.containsKey(id);
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
        setStyle(skin.get(name, ViewableStyle.class));
    }

    public void setStyle(ViewableStyle style) {
        Validate.notNull(style.animations);
        Validate.notNull(style.sounds);
        this.style = new ViewableStyle(style);
    }

    @Override
    public void setAnimation(Identifier id) {
        setAnimation(id, 0);
    }

    @Override
    public void setAnimation(Identifier id, float startTime) {
        Validate.notNull(id);
        Validate.isTrue(style.animations.containsKey(id) ,"%s is missing animation %s", getIdentifier(), id);
        animationId = id;
        animation.setAnimationTime(startTime);
        animation.setAnimation(style.animations.get(id));
        setDirection(animation.getAnimation(), direction);
    }

    @Override
    public void setAnimationAlias(Identifier id, Identifier alias) {
        Validate.notNull(id);
        Validate.notNull(alias);
        Validate.isTrue(style.animations.containsKey(id), "%s is missing sound %s", getIdentifier(), id);
        style.animations.put(alias, style.animations.get(id));
    }

    @Override
    public void setSound(Identifier id, float volume) {
        Validate.notNull(id);
        Validate.isTrue(style.sounds.containsKey(id), "%s is missing sound %s", getIdentifier(), id);
        soundId = id;
        sound.stop();
        sound = new LocalizedSound(style.sounds.get(id), this);
        sound.setVolume(volume);
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
        setDirection(animation.getAnimation(), direction);
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
        animation.draw(batch, getPosition(), getSize());
    }

    @Override
    public void update(float time) {
        super.update(time);
        animation.update(time);
        sound.update();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        Viewable that = (Viewable)obj;
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
