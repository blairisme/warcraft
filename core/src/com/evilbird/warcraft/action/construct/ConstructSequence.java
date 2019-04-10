/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.action.framework.FeatureAction;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.placeholder.Placeholder;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;
import java.util.function.Consumer;

import static com.evilbird.engine.action.common.ActionTarget.Target;
import static com.evilbird.engine.action.common.AnimateAction.animate;
import static com.evilbird.engine.action.common.AudibleAction.play;
import static com.evilbird.engine.action.common.VisibleAction.hide;
import static com.evilbird.engine.action.common.VisibleAction.show;
import static com.evilbird.engine.item.utility.ItemPredicates.isType;
import static com.evilbird.engine.item.utility.ItemSuppliers.closest;
import static com.evilbird.warcraft.action.common.create.CreateAction.create;
import static com.evilbird.warcraft.action.common.remove.RemoveAction.remove;
import static com.evilbird.warcraft.action.common.resource.ResourceTransferAction.purchase;
import static com.evilbird.warcraft.action.construct.ConstructAction.construct;
import static com.evilbird.warcraft.action.move.MoveToItemAction.move;
import static com.evilbird.warcraft.action.select.SelectAction.deselect;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.*;
import static com.evilbird.warcraft.item.unit.UnitAnimation.*;
import static com.evilbird.warcraft.item.unit.UnitSound.Build;

/**
 * Instances of this class construct a building.
 *
 * @author Blair Butterworth
 */
public class ConstructSequence extends FeatureAction
{
    private transient ConstructReporter reporter;

    @Inject
    public ConstructSequence(ConstructReporter reporter) {
        this.reporter = reporter;
        reevaluate();
    }

    @Override
    protected void features() {
        ConstructActions building = (ConstructActions)getIdentifier();
        reposition();
        prepare(building);
        build(building);
    }

    private void reposition() {
        scenario("reposition")
            .given(isAlive())
            .givenItem(isAlive())
            .when(notAdjacent(getTarget()))
            .then(animate(Move))
            .then(move(reporter))
            .then(animate(Idle));
    }

    private void prepare(ConstructActions building) {
        scenario("prepare")
            .whenTarget(isType(Placeholder.class))
            .then(remove(Target, reporter), purchase(building, reporter))
            .then(create(building.getItemType(), properties(), reporter));
    }

    private void build(ConstructActions building) {
        scenario("construct")
            .withTarget(closest(building.getItemType(), getTarget()))
            .whenItem(isAdjacent(getTarget()))
            .whenTarget(isConstructing())
            .then(hide(), deselect(reporter), animate(Target, Construct))
            .then(construct(Target, building), play(Target, Build))
            .then(show(), animate(Idle), animate(Target, Idle));
    }

    private Consumer<Item> properties() {
        return (item) -> {
            setPosition(item);
            setBuilding(item);
        };
    }

    private void setPosition(Item item) {
        Item target = getTarget();
        item.setPosition(target.getPosition());
    }

    private void setBuilding(Item item) {
        Building building = (Building)item;
        building.setConstructionProgress(0);
    }
}
