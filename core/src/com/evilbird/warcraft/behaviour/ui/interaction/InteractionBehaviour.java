/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.state.State;
import com.evilbird.warcraft.action.select.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.evilbird.engine.item.utility.ItemPredicates.itemWithType;
import static com.evilbird.engine.item.utility.ItemPredicates.selectedItem;
import static com.evilbird.warcraft.item.data.DataType.Camera;

/**
 * Instances of this class modify the game state based on user input.
 *
 * @author Blair Butterworth
 */
public class InteractionBehaviour implements Behaviour
{
    private static final Logger logger = LoggerFactory.getLogger(InteractionBehaviour.class);

    private Item camera;
    private Collection<Item> selected;

    private EventQueue events;
    private Interactions interactions;

    @Inject
    public InteractionBehaviour(Interactions interactions, EventQueue events) {
        this.events = events;
        this.interactions = interactions;
    }

    @Override
    public void update(State state, List<UserInput> inputs) {
        updateCache(state);
        evaluateInputs(state, inputs);
    }

    private void updateCache(State state) {
        if (selected == null) {
            initializeCache(state);
        } else {
            synchronizeCache();
        }
    }

    private void initializeCache(State state) {
        ItemRoot world = state.getWorld();
        camera = world.find(itemWithType(Camera));
        selected = world.findAll(selectedItem());
    }

    private void synchronizeCache() {
        for (SelectEvent event: events.getEvents(SelectEvent.class)) {
            Item subject = event.getSubject();
            if (event.getSelected()) {
                selected.add(subject);
            } else {
                selected.remove(subject);
            }
        }
    }

    private Item target;
    private Collection<Interaction> actions;

    private void evaluateInputs(State state, List<UserInput> inputs) {
        if (! inputs.isEmpty()) {
            ItemRoot world = state.getWorld();
            ItemRoot hud = state.getHud();

            for (UserInput input : inputs) {
                if (input.getCount() == 1) {
                    evaluateInput(input, world, hud, selected);
                } else {
                    applyPrevious(input, selected);
                }
            }
        }
    }

    private void evaluateInput(UserInput input, ItemRoot world, ItemRoot hud, Collection<Item> selected) {
        target = getTargets(world, hud, input);
        actions = evaluateSelection(input, target, selected);
        if (actions.isEmpty()) {
            target = camera;
            actions = evaluateSelection(input, target, selected);
        }
    }

    private void applyPrevious(UserInput input, Collection<Item> selected) {
        if (selected.isEmpty()) {
            applyInteractions(actions, input, target, (Item)null);
        } else {
            applyInteractions(actions, input, target, selected);
        }
    }

    private Collection<Interaction> evaluateSelection(UserInput input, Item target, Collection<Item> selected) {
        if (selected.isEmpty()) {
            return evaluateInteractions(input, target, null);
        }
        else if (selected.contains(target)) {
            return evaluateInteractions(input, target, target);
        }
        else for (Item selectedItem: selected) {
            return evaluateInteractions(input, target, selectedItem);
        }
        return Collections.emptyList();
    }

    private Collection<Interaction> evaluateInteractions(UserInput input, Item target, Item selected) {
        logInput(input, target, selected);
        Collection<Interaction> applicable = interactions.getInteractions(input, target, selected);
        applyInteractions(applicable, input, target, selected);
        return applicable;
    }

    private void applyInteractions(Collection<Interaction> actions, UserInput input, Item target, Collection<Item> selected) {
        for (Item selectedItem: selected) {
            applyInteractions(actions, input, target, selectedItem);
        }
    }
    private void applyInteractions(Collection<Interaction> actions, UserInput input, Item target, Item selected) {
        for (Interaction interaction: actions) {
            interaction.update(input, target, selected);
        }
    }

    private Item getTargets(ItemRoot world, ItemRoot hud, UserInput input) {
        Item result = getHudTarget(hud, input);
        if (result == null) {
            result = getWorldTarget(world, input);
        }
        return result;
    }

    private Item getHudTarget(ItemRoot hud, UserInput userInput) {
        Vector2 position = userInput.getPosition();
        Vector2 hudPosition = hud.unproject(position);
        return hud.hit(hudPosition, true);
    }

    private Item getWorldTarget(ItemRoot world, UserInput userInput) {
        Vector2 position = userInput.getPosition();
        Vector2 worldPosition = world.unproject(position);
        return world.hit(worldPosition, true);
    }

    private void logInput(UserInput input, Item target, Item selected) {
        logger.debug("User input - type: '{}' target: '{}' selected: '{}'",
            getType(input), getType(target), getType(selected));
    }

    private Identifier getType(Item item) {
        if (item != null) {
            return item.getType();
        }
        return null;
    }

    private UserInputType getType(UserInput input) {
        if (input != null) {
            return input.getType();
        }
        return null;
    }
}
