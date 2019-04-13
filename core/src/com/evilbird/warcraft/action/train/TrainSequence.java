/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.train;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.LambdaAction;
import com.evilbird.engine.action.framework.ScenarioAction;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;
import java.util.function.Consumer;

import static com.evilbird.warcraft.action.common.create.CreateAction.create;
import static com.evilbird.warcraft.action.common.resource.ResourceTransferAction.purchase;
import static com.evilbird.warcraft.action.train.TrainAction.startProducing;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isAlive;

/**
 * Instances of this action sequence create a new unit, decrementing the
 * players resources and adding delay before the new unit can be used.
 *
 * @author Blair Butterworth
 */
public class TrainSequence extends ScenarioAction<TrainActions>
{
    private transient TrainReporter reporter;

    @Inject
    public TrainSequence(TrainReporter reporter) {
        this.reporter = reporter;
    }

    @Override
    protected void steps(TrainActions type) {
        scenario(type);
        given(isAlive());
        then(purchase(type, reporter), notifyStarted());
        then(startProducing(type));
        then(create(type.getItemType(), properties(), reporter), notifyComplete());
    }

    private Consumer<Item> properties() {
        return (item) -> {
            Vector2 position = getPosition(item);
            item.setPosition(position);
        };
    }

    private Vector2 getPosition(Item item) {
        Item producer = getItem();
        Vector2 itemSize = item.getSize();
        Vector2 referencePosition = producer.getPosition();
        return new Vector2(referencePosition.x - itemSize.x, referencePosition.y);
    }

    private Action notifyStarted() {
        return new LambdaAction((item, target) ->
            reporter.onTrainStarted((Building)item));
    }

    private Action notifyComplete() {
        return new LambdaAction((item, target) ->
            reporter.onTrainCompleted((Building)item));
    }
}