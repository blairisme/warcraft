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
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.inject.Inject;

import static com.evilbird.engine.action.common.AudibleAction.play;
import static com.evilbird.warcraft.action.select.SelectAction.deselect;
import static com.evilbird.warcraft.action.select.SelectAction.select;
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
    private transient SelectReporter observer;

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
            .then(select(observer), play(Selected));

        scenario("Deselect")
            .given(isAlive())
            .when(isSelected(getItem()))
            .then(deselect(observer));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;

        SelectToggle that = (SelectToggle)obj;
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
