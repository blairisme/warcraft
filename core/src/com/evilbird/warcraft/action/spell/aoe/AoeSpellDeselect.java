/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.aoe;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.warcraft.action.placeholder.PlaceholderActions;
import com.evilbird.warcraft.action.placeholder.PlaceholderEvents;
import com.evilbird.warcraft.item.unit.Unit;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;

public class AoeSpellDeselect extends BasicAction
{
    @Inject
    public AoeSpellDeselect() {
    }

    @Override
    public boolean act(float time) {
        Unit caster = (Unit)getItem();
        Item selector = caster.getAssociatedItem();

        ItemGroup parent = selector.getParent();
        parent.removeItem(selector);

        caster.setAssociatedItem(null);
        return ActionComplete;
    }
}
