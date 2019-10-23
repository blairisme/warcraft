/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.select;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.specialized.Viewable;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.item.common.capability.SelectableObject;

import javax.inject.Inject;
import java.util.function.Predicate;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.common.function.Predicates.either;
import static com.evilbird.warcraft.item.common.query.UnitOperations.isCombatant;
import static com.evilbird.warcraft.item.common.query.UnitOperations.isCorporeal;
import static com.evilbird.warcraft.item.common.query.UnitOperations.isNeutral;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isAi;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isNonCombatant;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isSelected;
import static com.evilbird.warcraft.item.unit.UnitSound.Selected;

/**
 * Instances of this {@link Action} invert the selected status of a given
 * {@link Item} in conjunction with deselecting all currently selected items.
 *
 * @author Blair Butterworth
 */
public class SelectInvert extends BasicAction
{
    private transient SelectEvents events;
    private transient WarcraftPreferences preferences;

    @Inject
    public SelectInvert(SelectEvents events, WarcraftPreferences preferences) {
        this.events = events;
        this.preferences = preferences;
    }

    @Override
    public boolean act(float delta) {
        SelectableObject item = (SelectableObject) getItem();
        if (item.getSelected()) {
            deselect(item);
        } else {
            select(item);
        }
        return ActionComplete;
    }

    private void select(SelectableObject entity) {
        updateSelected(entity);
        updateSound(entity);
        setSelected(entity, true);
    }

    private void updateSelected(SelectableObject entity) {
        ItemRoot root = entity.getRoot();
        if (isCorporeal(entity) && isCombatant(entity)) {
            setSelected(root, either(isNonCombatant(), isAi()), false);
        } else {
            setSelected(root, isSelected(), false);
        }
    }

    private void updateSound(SelectableObject entity) {
        if (isCorporeal(entity) || isNeutral(entity)) {
            setSelectedSound(entity);
        }
    }

    private void deselect(SelectableObject entity) {
        if (isCombatant(entity)) {
            setSelected(entity, false);
        } else {
            setSelected(entity.getRoot(), isSelected(), false);
        }
    }

    private void setSelectedSound(Item entity) {
        if (entity instanceof Viewable && preferences.isAcknowledgementEnabled()) {
            Viewable viewable = (Viewable)entity;
            viewable.setSound(Selected, preferences.getEffectsVolume());
        }
    }

    private void setSelected(ItemRoot root, Predicate<Item> condition, boolean selected) {
        for (Item item: root.findAll(condition)) {
            if (item instanceof SelectableObject) {
                setSelected((SelectableObject)item, selected);
            }
        }
    }

    private void setSelected(SelectableObject selectable, boolean selected) {
        if (selectable.getSelected() != selected) {
            selectable.setSelected(selected);
            events.notifySelected(selectable, selected);
        }
    }
}