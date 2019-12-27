/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.confirm;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.common.lang.Audible;
import com.evilbird.engine.common.lang.RandomIdentifier;
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.engine.object.GameObjectGroup;
import com.evilbird.engine.object.GameObjectType;
import com.evilbird.warcraft.common.WarcraftPreferences;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;
import static com.evilbird.warcraft.object.unit.UnitSound.Acknowledge;

/**
 * Instances of this {@link Action} provide the basic implementation required
 * to show a confirmation effect.
 *
 * @author Blair Butterworth
 */
abstract class ConfirmAction extends BasicAction
{
    private static final transient float ANIMATION_LIFETIME = 0.55f;

    private transient GameObject animation;
    private transient GameTimer timer;
    private transient GameObjectFactory factory;
    private transient WarcraftPreferences preferences;

    public ConfirmAction(GameObjectFactory factory, WarcraftPreferences preferences) {
        this.factory = factory;
        this.preferences = preferences;
    }

    @Override
    public boolean act(float time) {
        if (! initialized()) {
            return initialize();
        }
        if (timer.advance(time)) {
            return removeAnimation();
        }
        return ActionIncomplete;
    }

    protected boolean initialized() {
        return animation != null;
    }

    protected boolean initialize() {
        createTimer();
        createAnimation();
        playAcknowledgement();
        return ActionIncomplete;
    }

    protected void createTimer() {
        timer = new GameTimer(ANIMATION_LIFETIME);
    }

    protected void playAcknowledgement() {
        if (preferences.isAcknowledgementEnabled()) {
            Audible audible = (Audible) getSubject();
            audible.setSound(Acknowledge);
        }
    }

    protected void createAnimation() {
        animation = factory.get(getEffectType());
        animation.setIdentifier(new RandomIdentifier());
        animation.setPosition(getPosition(), getAlignment());

        GameObject gameObject = getSubject();
        GameObjectGroup parent = gameObject.getParent();
        parent.addObject(animation);
    }

    protected boolean removeAnimation() {
        GameObject gameObject = getSubject();
        GameObjectGroup parent = gameObject.getParent();
        parent.removeObject(animation);
        animation = null;
        return ActionComplete;
    }

    protected abstract Vector2 getPosition();

    protected abstract Alignment getAlignment();

    protected abstract GameObjectType getEffectType();
}