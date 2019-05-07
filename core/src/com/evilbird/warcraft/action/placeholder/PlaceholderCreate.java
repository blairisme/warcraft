/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.placeholder;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.ScenarioAction;
import com.evilbird.engine.item.Item;

import javax.inject.Inject;
import java.util.function.Consumer;

import static com.evilbird.engine.item.utility.ItemOperations.getScreenCenter;
import static com.evilbird.warcraft.action.common.create.CreateAction.create;
import static com.evilbird.warcraft.action.placeholder.PlaceholderEvents.placeholderAdded;
import static com.evilbird.warcraft.action.placeholder.PlaceholderUtils.assignConstruction;
import static com.evilbird.warcraft.item.WarcraftItemConstants.TILE_HEIGHT;
import static com.evilbird.warcraft.item.WarcraftItemConstants.TILE_WIDTH;

/**
 * Instances of this class provide {@link Action Actions} that add a building
 * placeholder.
 *
 * @author Blair Butterworth
 */
public class PlaceholderCreate extends ScenarioAction<PlaceholderActions>
{
    private transient PlaceholderReporter reporter;

    @Inject
    public PlaceholderCreate(PlaceholderReporter reporter) {
        this.reporter = reporter;
    }

    @Override
    protected void steps(PlaceholderActions action) {
        scenario(action);
        thenUpdate(create(action.type(), properties(), observer -> {}));
        then(assignConstruction(), placeholderAdded(reporter));
    }

    private Consumer<Item> properties() {
        return (created) -> {
            Vector2 screenCenter = getScreenCenter(getItem());
            screenCenter.x = Math.round(screenCenter.x/TILE_WIDTH) * TILE_WIDTH;
            screenCenter.y = Math.round(screenCenter.y/TILE_HEIGHT) * TILE_HEIGHT;
            created.setPosition(screenCenter);
        };
    }
}
