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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.evilbird.engine.item.utility.ItemPredicates.itemWithType;
import static com.evilbird.engine.item.utility.ItemPredicates.selectedItem;
import static com.evilbird.warcraft.item.data.DataType.Camera;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singleton;

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
        evaluateInput(state, inputs);
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

    private void evaluateInput(State state, List<UserInput> inputs) {
        if (! inputs.isEmpty()) {
            ItemRoot world = state.getWorld();
            ItemRoot hud = state.getHud();

            for (UserInput input : inputs) {
                Collection<Item> targets = getTargets(world, hud, input);
                evaluateTargets(input, targets, selected);
            }
        }
    }

    private void evaluateTargets(UserInput input, Collection<Item> targets, Collection<Item> selected) {
        for (Item target: targets) {
            evaluateSelection(input, target, selected);
        }
    }

    private void evaluateSelection(UserInput input, Item target, Collection<Item> selected) {
        if (selected.isEmpty()) {
            applyInteractions(input, target, null);
        }
        if (selected.contains(target)) {
            applyInteractions(input, target, target);
        }
        else for (Item selectedItem: selected) {
            applyInteractions(input, target, selectedItem);
        }
    }

    private boolean applyInteractions(UserInput input, Item target, Item selected) {
        log(input, target, selected);
        Collection<Interaction> actions = interactions.getInteractions(input, target, selected);
        actions.forEach(interaction -> interaction.update(input, target, selected));
        return !actions.isEmpty();
    }

    private Collection<Item> getTargets(ItemRoot world, ItemRoot hud, UserInput input) {
        Collection<Item> result = getHudTargets(hud, input);
        if (result.isEmpty()) {
            result = getWorldTargets(world, input);
        }
        return result;
    }

    private Collection<Item> getHudTargets(ItemRoot hud, UserInput userInput) {
        Vector2 position = userInput.getPosition();
        Vector2 hudPosition = hud.unproject(position);

        Item hudElement = hud.hit(hudPosition, true);
        if (hudElement != null) {
            return singleton(hudElement);
        }
        return emptyList();
    }

    private Collection<Item> getWorldTargets(ItemRoot world, UserInput userInput) {
        Item worldTarget = getWorldTarget(world, userInput);
        return worldTarget != null ? asList(worldTarget, camera) : singleton(camera);
    }

    private Item getWorldTarget(ItemRoot world, UserInput userInput) {
        Vector2 position = userInput.getPosition();
        Vector2 worldPosition = world.unproject(position);
        return world.hit(worldPosition, true);
    }

    private void log(UserInput input, Item target, Item selected) {
        logger.debug("Input {}, target {}, selected {}", getType(input), getType(target), getType(selected));
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
