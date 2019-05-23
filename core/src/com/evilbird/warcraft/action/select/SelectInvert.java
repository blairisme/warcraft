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
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.common.scenario.ScenarioSetAction;

import javax.inject.Inject;

import static com.evilbird.engine.action.common.AudibleAction.play;
import static com.evilbird.warcraft.action.select.DeselectAction.deselectAll;
import static com.evilbird.warcraft.action.select.SelectAction.deselect;
import static com.evilbird.warcraft.action.select.SelectAction.select;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.*;
import static com.evilbird.warcraft.item.unit.UnitSound.Selected;

/**
 * Instances of this {@link Action} invert the selected status of a given
 * {@link Item} in conjunction with deselecting all currently selected items.
 *
 * @author Blair Butterworth
 */
public class SelectInvert extends ScenarioSetAction
{
    private transient SelectReporter observer;
    private transient Boolean selected;

    @Inject
    public SelectInvert(SelectReporter reporter) {
        feature(SelectActions.SelectInvert);
        observer = reporter;
    }

    @Override
    protected void features() {
        initializeSelection();
        selectFeatures();
        deselectFeatures();
    }

    private void selectFeatures() {
        scenario("Select Owned Item Inclusive")
            .given(isAlive())
            .when(isCorporeal())
            .when(isCombatant())
            .when((item) -> !selected)
            .then(deselectAll(notCombatant(), observer), deselectAll(isAi(), observer))
            .then(select(observer), play(Selected));

        scenario("Select Owned Item Exclusive")
            .given(isAlive())
            .when(isCorporeal())
            .when(notCombatant())
            .when((item) -> !selected)
            .then(deselectAll(observer))
            .then(select(observer), play(Selected));

        scenario("Select Enemy Item Exclusive")
            .given(isAlive())
            .when(isAi())
            .when((item) -> !selected)
            .then(deselectAll(observer))
            .then(select(observer));
    }

    private void deselectFeatures() {
        scenario("Deselect Inclusive")
            .given(isAlive())
            .when(isCombatant())
            .when((item) -> selected)
            .then(deselect(observer));

        scenario("Deselect Exclusive")
            .given(isAlive())
            .when(notCombatant())
            .when((item) -> selected)
            .then(deselectAll(observer));
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