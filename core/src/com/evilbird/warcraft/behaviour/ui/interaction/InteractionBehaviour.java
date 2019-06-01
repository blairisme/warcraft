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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.evilbird.engine.item.utility.ItemPredicates.itemWithType;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isSelected;
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

    private Item target;
    private Collection<Interaction> previous;

    private EventQueue events;
    private Interactions interactions;

    @Inject
    public InteractionBehaviour(Interactions interactions, EventQueue events) {
        this.events = events;
        this.interactions = interactions;
        this.previous = Collections.emptyList();
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
        selected = world.findAll(isSelected());
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
        previous = evaluateSelection(input, target, selected);
        if (previous.isEmpty()) {
            target = camera;
            previous = evaluateSelection(input, target, selected);
        }
    }

    private void applyPrevious(UserInput input, Collection<Item> selected) {
        if (selected.isEmpty()) {
            apply(previous, input, target, (Item)null);
        } else {
            apply(previous, input, target, selected);
        }
    }

    private Collection<Interaction> evaluateSelection(UserInput input, Item target, Collection<Item> selected) {
        Collection<Interaction> result = new ArrayList<>();
        if (selected.isEmpty()) {
            result.add(evaluateInteractions(input, target, null));
        }
        else if (selected.contains(target)) {
            result.add(evaluateInteractions(input, target, target));
        }
        else {
            for (Item selectedItem: selected) {
                result.add(evaluateInteractions(input, target, selectedItem));
            }
        }
        result.removeIf(Objects::isNull);
        return result;
    }

    private Interaction evaluateInteractions(UserInput input, Item target, Item selected) {
        logInput(input, target, selected);
        Interaction interaction = interactions.getInteraction(input, target, selected);
        apply(interaction, input, target, selected);
        return interaction;
    }

    private void apply(Collection<Interaction> interactions, UserInput input, Item target, Collection<Item> selected) {
        for (Item selectedItem : selected) {
            apply(interactions, input, target, selectedItem);
        }
    }

    private void apply(Collection<Interaction> interactions, UserInput input, Item target, Item selected) {
        for (Interaction interaction: interactions) {
            apply(interaction, input, target, selected);
        }
    }

    private void apply(Interaction interaction, UserInput input, Item target, Item selected) {
        if (interaction != null) {
            interaction.apply(input, target, selected);
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
