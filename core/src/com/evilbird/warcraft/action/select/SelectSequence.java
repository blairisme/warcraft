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
import com.evilbird.engine.action.framework.ScenarioSetAction;
import com.evilbird.engine.common.lang.Selectable;
import com.evilbird.engine.item.Item;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.inject.Inject;

import static com.evilbird.engine.action.common.AudibleAction.play;
import static com.evilbird.warcraft.action.select.SelectAction.deselect;
import static com.evilbird.warcraft.action.select.SelectAction.select;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isAlive;
import static com.evilbird.warcraft.item.unit.UnitSound.Selected;

/**
 * Instances of this {@link Action} invert the selected status of a given
 * {@link Item}.
 *
 * @author Blair Butterworth
 */
public class SelectSequence extends ScenarioSetAction
{
    private transient SelectReporter observer;
    private transient Boolean selected;

    @Inject
    public SelectSequence(SelectReporter reporter) {
        feature(SelectActions.SelectToggle);
        observer = reporter;
    }

    @Override
    protected void features() {
        initializeSelection();

        scenario("Select")
            .given(isAlive())
            .when((item) -> !selected)
            .then(select(observer), play(Selected));

        scenario("Deselect")
            .given(isAlive())
            .when((item) -> selected)
            .then(deselect(observer));
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;

        SelectSequence that = (SelectSequence)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(observer, that.observer)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(observer)
            .toHashCode();
    }
}
