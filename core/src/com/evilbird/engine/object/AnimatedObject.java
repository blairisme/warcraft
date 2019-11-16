/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.object;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.audio.sound.LocalizedSound;
import com.evilbird.engine.common.audio.sound.SilentSound;
import com.evilbird.engine.common.audio.sound.Sound;
import com.evilbird.engine.common.graphics.animation.Animation;
import com.evilbird.engine.common.graphics.animation.DirectionalAnimation;
import com.evilbird.engine.common.graphics.renderable.AnimationRenderable;
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.common.lang.Animated;
import com.evilbird.engine.common.lang.Audible;
import com.evilbird.engine.common.lang.Directionable;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Styleable;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Instances of this {@link GameObject} are drawn using a selection of given
 * {@link DirectionalAnimation animations} and {@link Sound sounds}.
 *
 * @author Blair Butterworth
 */
public class AnimatedObject extends BasicGameObject implements Animated, Audible, Directionable, Styleable
{
    protected float direction;
    protected Identifier animationId;
    protected Identifier soundId;

    protected transient Skin skin;
    protected transient AnimatedObjectStyle style;
    protected transient LocalizedSound sound;
    protected transient AnimationRenderable animation;

    /**
     * Constructs a new instance of this class given a {@link Skin} containing
     * an {@link AnimatedObjectStyle}, specifying the visual and auditory
     * presentation of the new {@code AnimatedItem}.
     *
     * @param skin a {@code Skin} instance. This method cannot be {@code null}.
     */
    public AnimatedObject(Skin skin) {
        this();
        setSkin(skin);
    }

    protected AnimatedObject() {
        this.style = new AnimatedObjectStyle();

        this.animationId = null;
        this.animation = new AnimationRenderable();

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

    public AnimatedObjectStyle getStyle() {
        return style;
    }

    public boolean hasAnimation(Identifier id) {
        return style.animations.containsKey(id);
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
        animation = new AnimationRenderable(style.animations.get(id));
        animation.setAnimationTime(startTime);
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
    public void setSkin(Skin skin) {
        Validate.notNull(skin);
        this.skin = skin;
        setStyle("default");
    }

    @Override
    public void setStyle(String name) {
        Validate.validState(skin != null);
        setStyle(skin.get(name, AnimatedObjectStyle.class));
    }

    public void setStyle(AnimatedObjectStyle style) {
        Validate.notNull(style.animations);
        Validate.notNull(style.sounds);
        this.style = new AnimatedObjectStyle(style);
    }

    @Override
    public void setPosition(float x, float y) {
        Vector2 previous = getPosition();
        super.setPosition(x, y);
        setDirection(previous.x, previous.y, x, y);
    }

    @Override
    public void setPosition(float x, float y, Alignment alignment) {
        Vector2 previous = getPosition();
        super.setPosition(x, y, alignment);
        Vector2 current = getPosition();
        setDirection(previous.x, previous.y, current.x, current.y);
    }

    @Override
    public void setPosition(Vector2 position) {
        Vector2 previous = getPosition();
        super.setPosition(position);
        setDirection(previous.x, previous.y, position.x, position.y);
    }

    @Override
    public void setPosition(Vector2 position, Alignment alignment) {
        Vector2 previous = getPosition();
        super.setPosition(position, alignment);
        Vector2 current = getPosition();
        setDirection(previous.x, previous.y, current.x, current.y);
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
        animation.draw(batch, position, size);
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

        AnimatedObject that = (AnimatedObject)obj;
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
