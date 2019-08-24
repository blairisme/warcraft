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
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.RandomIdentifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;
import com.evilbird.warcraft.action.common.scenario.ScenarioAction;
import com.evilbird.warcraft.item.ui.confirmation.ConfirmType;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.evilbird.engine.action.common.AudibleAction.play;
import static com.evilbird.engine.action.framework.DelayedAction.delay;
import static com.evilbird.engine.item.utility.ItemPredicates.itemWithId;
import static com.evilbird.warcraft.action.common.create.CreateAction.create;
import static com.evilbird.warcraft.action.common.remove.RemoveAction.remove;
import static com.evilbird.warcraft.item.unit.UnitSound.Acknowledge;

/**
 * Instances of this {@link Action} provide the basic implementation required
 * to show a confirmation effect.
 *
 * @author Blair Butterworth
 */
public abstract class ConfirmAction extends ScenarioAction
{
    private static final transient float ANIMATION_LIFETIME = 0.55f;

    private transient ConfirmType type;
    private transient Identifier effectId;

    public ConfirmAction(ConfirmType type) {
        this.type = type;
        initialize();
    }

    @Override
    protected void steps(Identifier identifier) {
        then(create(type, properties()));
        then(play(Acknowledge));
        then(delay(ANIMATION_LIFETIME));
        then(remove(confirm(), null));
    }

    @Override
    public void reset() {
        super.reset();
        initialize();
    }

    protected void initialize() {
        effectId = new RandomIdentifier();
    }

    protected Supplier<Item> confirm() {
        return () -> {
            ItemComposite parent = getItem().getParent();
            return parent.find(itemWithId(effectId));
        };
    }

    protected Consumer<Item> properties() {
        return (item) -> {
            item.setIdentifier(effectId);
            item.setPosition(getPosition(), getAlignment());
        };
    }

    protected abstract Vector2 getPosition();

    protected abstract Alignment getAlignment();
}