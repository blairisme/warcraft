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
import com.evilbird.engine.common.lang.Selectable;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.common.scenario.ScenarioSetAction;

import javax.inject.Inject;

import static com.evilbird.engine.action.common.AudibleAction.play;
import static com.evilbird.engine.common.function.Predicates.not;
import static com.evilbird.warcraft.action.select.DeselectAction.deselectAll;
import static com.evilbird.warcraft.action.select.SelectAction.deselect;
import static com.evilbird.warcraft.action.select.SelectAction.select;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isAi;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isAlive;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isCombatant;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isCorporeal;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isNeutral;
import static com.evilbird.warcraft.item.unit.UnitSound.Selected;

/**
 * Instances of this {@link Action} invert the selected status of a given
 * {@link Item} in conjunction with deselecting all currently selected items.
 *
 * @author Blair Butterworth
 */
public class SelectInvert extends ScenarioSetAction
{
    private transient Events events;
    private transient Boolean selected;

    @Inject
    public SelectInvert(EventQueue events) {
        this.events = events;
        feature(SelectActions.SelectInvert);
    }

    @Override
    protected void features() {
        initializeSelection();
        selectPlayerFeatures();
        selectEnemyFeatures();
        deselectFeatures();
    }

    private void selectPlayerFeatures() {
        scenario("Select Owned Item Inclusive")
            .given(isAlive())
            .when(isCorporeal())
            .when(isCombatant())
            .when((item) -> !selected)
            .then(deselectAll(not(isCombatant()), events), deselectAll(isAi(), events))
            .then(select(events), play(Selected));

        scenario("Select Owned Item Exclusive")
            .given(isAlive())
            .when(isCorporeal())
            .when(not(isCombatant()))
            .when((item) -> !selected)
            .then(deselectAll(events))
            .then(select(events), play(Selected));
    }

    private void selectEnemyFeatures() {
        scenario("Select Neutral Item Exclusive ")
            .given(isAlive())
            .when(isNeutral())
            .when((item) -> !selected)
            .then(deselectAll(events))
            .then(select(events), play(Selected));

        scenario("Select Enemy Item Exclusive")
            .given(isAlive())
            .when(isAi().and(not(isNeutral())))
            .when((item) -> !selected)
            .then(deselectAll(events))
            .then(select(events));
    }

    private void deselectFeatures() {
        scenario("Deselect Inclusive")
            .given(isAlive())
            .when(isCombatant())
            .when((item) -> selected)
            .then(deselect(events));

        scenario("Deselect Exclusive")
            .given(isAlive())
            .when(not(isCombatant()))
            .when((item) -> selected)
            .then(deselectAll(events));
    }

    @Override
    public void restart() {
        super.restart();
        updateSelection();
    }

    @Override
    public void reset() {
        super.reset();
        updateSelection();
    }

    private void initializeSelection() {
        if (selected == null) {
            updateSelection();
        }
    }

    private void updateSelection() {
        Selectable selectable = (Selectable)getItem();
        selected = selectable.getSelected();
    }
}