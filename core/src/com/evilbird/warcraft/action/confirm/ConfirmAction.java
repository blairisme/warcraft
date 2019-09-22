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
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.item.ItemType;
import com.evilbird.warcraft.common.WarcraftPreferences;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;
import static com.evilbird.warcraft.item.unit.UnitSound.Acknowledge;

/**
 * Instances of this {@link Action} provide the basic implementation required
 * to show a confirmation effect.
 *
 * @author Blair Butterworth
 */
abstract class ConfirmAction extends BasicAction
{
    private static final transient float ANIMATION_LIFETIME = 0.55f;

    private transient Item animation;
    private transient GameTimer timer;
    private transient ItemFactory factory;
    private transient WarcraftPreferences preferences;

    public ConfirmAction(ItemFactory factory, WarcraftPreferences preferences) {
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
            Audible audible = (Audible)getItem();
            audible.setSound(Acknowledge);
        }
    }

    protected void createAnimation() {
        animation = factory.get(getEffectType());
        animation.setIdentifier(new RandomIdentifier());
        animation.setPosition(getPosition(), getAlignment());

        Item item = getItem();
        ItemGroup parent = item.getParent();
        parent.addItem(animation);
    }

    protected boolean removeAnimation() {
        Item item = getItem();
        ItemGroup parent = item.getParent();
        parent.removeItem(animation);
        animation = null;
        return ActionComplete;
    }

    protected abstract Vector2 getPosition();

    protected abstract Alignment getAlignment();

    protected abstract ItemType getEffectType();
}