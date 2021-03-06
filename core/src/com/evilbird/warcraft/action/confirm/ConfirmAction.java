/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.confirm;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionResult;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.common.lang.RandomIdentifier;
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.engine.object.GameObjectGroup;
import com.evilbird.engine.object.GameObjectType;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.object.unit.Unit;

import static com.evilbird.engine.action.ActionResult.Complete;
import static com.evilbird.engine.action.ActionResult.Incomplete;
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
    public ActionResult act(float time) {
        if (! initialized()) {
            return initialize();
        }
        if (timer.advance(time)) {
            return removeAnimation();
        }
        return Incomplete;
    }

    protected boolean initialized() {
        return animation != null;
    }

    protected ActionResult initialize() {
        createTimer();
        createAnimation();
        playAcknowledgement();
        return Incomplete;
    }

    protected void createTimer() {
        timer = new GameTimer(ANIMATION_LIFETIME);
    }

    protected void playAcknowledgement() {
        GameObject subject = getSubject();
        if (subject instanceof Unit && preferences.isAcknowledgementEnabled()) {
            Unit unit = (Unit)subject;
            unit.setSound(Acknowledge);
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

    protected ActionResult removeAnimation() {
        GameObject gameObject = getSubject();
        GameObjectGroup parent = gameObject.getParent();
        parent.removeObject(animation);
        animation = null;
        return Complete;
    }

    protected abstract Vector2 getPosition();

    protected abstract Alignment getAlignment();

    protected abstract GameObjectType getEffectType();
}