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
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.ui.confirmation.ConfirmType;

import javax.inject.Inject;

/**
 * Instances of this {@link Action} show an attack confirmation effect over a
 * Item that's about to be attacked.
 *
 * @author Blair Butterworth
 */
public class ConfirmAttack extends ConfirmAction
{
    @Inject
    public ConfirmAttack() {
        super(ConfirmType.Attack);
        setIdentifier(ConfirmActions.ConfirmAttack);
    }

    @Override
    protected Vector2 getPosition() {
        Item target = getTarget();
        return target.getPosition(Alignment.Center);
    }

    @Override
    protected Alignment getAlignment() {
        return Alignment.Center;
    }
}
