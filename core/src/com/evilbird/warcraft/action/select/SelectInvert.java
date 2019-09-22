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
import com.evilbird.engine.common.lang.Selectable;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.specialized.Viewable;
import com.evilbird.warcraft.common.WarcraftPreferences;

import javax.inject.Inject;
import java.util.function.Predicate;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.common.function.Predicates.either;
import static com.evilbird.warcraft.action.select.SelectEvents.notifySelected;
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
    private transient Events events;
    private transient WarcraftPreferences preferences;

    @Inject
    public SelectInvert(EventQueue events, WarcraftPreferences preferences) {
        this.events = events;
        this.preferences = preferences;
    }

    @Override
    public boolean act(float delta) {
        Selectable item = (Selectable) getItem();
        if (item.getSelected()) {
            deselect(item);
        } else {
            select(item);
        }
        return ActionComplete;
    }

    private void select(Selectable entity) {
        updateSelected(entity);
        updateSound(entity);
        setSelected(entity, true);
    }

    private void updateSelected(Selectable entity) {
        ItemRoot root = entity.getRoot();
        if (isCorporeal(entity) && isCombatant(entity)) {
            setSelected(root, either(isNonCombatant(), isAi()), false);
        } else {
            setSelected(root, isSelected(), false);
        }
    }

    private void updateSound(Selectable entity) {
        if (isCorporeal(entity) || isNeutral(entity)) {
            setSelectedSound(entity);
        }
    }

    private void deselect(Selectable entity) {
        if (isCombatant(entity)) {
            setSelected(entity, false);
        } else {
            setSelected(entity.getRoot(), isSelected(), false);
        }
    }

    private void setSelectedSound(Item entity) {
        if (entity instanceof Viewable && preferences.isAcknowledgementEnabled()) {
            Viewable viewable = (Viewable)entity;
            viewable.setSound(Selected);
        }
    }

    private void setSelected(ItemRoot root, Predicate<Item> condition, boolean selected) {
        for (Item item: root.findAll(condition)) {
            if (item instanceof Selectable) {
                setSelected((Selectable)item, selected);
            }
        }
    }

    private void setSelected(Selectable selectable, boolean selected) {
        selectable.setSelected(selected);
        notifySelected(events, selectable, selected);
    }
}