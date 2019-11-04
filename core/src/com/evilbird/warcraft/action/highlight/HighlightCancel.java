/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.highlight;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.item.unit.Unit;

import javax.inject.Inject;
import java.util.Collection;
import java.util.function.Predicate;

import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;

/**
 * An {@link Action} that unhighlights highlighted units.
 *
 * @author Blair Butterworth
 */
public class HighlightCancel extends BasicAction
{
    @Inject
    public HighlightCancel() {
        setIdentifier(HighlightActions.HighlightCancel);
    }

    @Override
    public boolean act(float time) {
        setUnhighlighted(getTargets());
        return ActionIncomplete;
    }

    private Collection<Item> getTargets() {
        Item item = getItem();
        ItemRoot root = item.getRoot();
        return root.findAll(isHighlighted());
    }

    private Predicate<Item> isHighlighted() {
        return item -> {
            if (item instanceof Unit) {
                Unit unit = (Unit)item;
                return unit.getHighlighted();
            }
            return false;
        };
    }

    private void setUnhighlighted(Collection<Item> targets) {
        for (Item target: targets) {
            setUnhighlighted(target);
        }
    }

    private void setUnhighlighted(Item target) {
        Unit unit = (Unit)target;
        unit.setHighlighted(false);
    }
}
