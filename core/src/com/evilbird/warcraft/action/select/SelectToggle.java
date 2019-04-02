/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.select;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.FeatureAction;
import com.evilbird.engine.item.Item;

import javax.inject.Inject;

import static com.evilbird.engine.action.common.AudibleAction.play;
import static com.evilbird.engine.action.common.SelectAction.deselect;
import static com.evilbird.engine.action.common.SelectAction.select;
import static com.evilbird.warcraft.action.select.SelectReporter.notifySelected;
import static com.evilbird.warcraft.action.select.SelectReporter.notifyUnselected;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.*;
import static com.evilbird.warcraft.item.unit.UnitSound.Selected;

/**
 * Instances of this {@link Action} invert the selected status of a given
 * {@link Item}.
 *
 * @author Blair Butterworth
 */
public class SelectToggle extends FeatureAction
{
    private SelectReporter observer;

    @Inject
    public SelectToggle(SelectReporter reporter) {
        feature(SelectActions.SelectToggle);
        observer = reporter;
    }

    @Override
    protected void features() {
        scenario("Select")
            .given(isAlive())
            .when(isUnselected(getItem()))
            .then(select(), play(Selected))
            .then(notifySelected(observer));

        scenario("Deselect")
            .given(isAlive())
            .when(isSelected(getItem()))
            .then(deselect())
            .then(notifyUnselected(observer));
    }
}
