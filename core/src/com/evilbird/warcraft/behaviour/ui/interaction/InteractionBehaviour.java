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
import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.state.State;
import com.evilbird.warcraft.action.selection.SelectEvent;
import com.evilbird.warcraft.object.data.camera.Camera;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.evilbird.engine.object.utility.GameObjectPredicates.withClazz;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.isSelected;

/**
 * Instances of this class modify the game state based on user input.
 *
 * @author Blair Butterworth
 */
public class InteractionBehaviour implements Behaviour
{
    private static final Logger logger = LoggerFactory.getLogger(InteractionBehaviour.class);

    private GameObject camera;
    private Collection<GameObject> selected;

    private GameObject target;
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
    public void update(State state, List<UserInput> inputs, float time) {
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
        GameObjectContainer world = state.getWorld();
        camera = world.find(withClazz(Camera.class));
        selected = world.findAll(isSelected());
    }

    private void synchronizeCache() {
        for (SelectEvent event: events.getEvents(SelectEvent.class)) {
            GameObject subject = event.getSubject();
            if (event.getSelected()) {
                selected.add(subject);
            } else {
                selected.remove(subject);
            }
        }
    }

    private void evaluateInputs(State state, List<UserInput> inputs) {
        if (! inputs.isEmpty()) {
            GameObjectContainer world = state.getWorld();
            GameObjectContainer hud = state.getHud();

            for (UserInput input : inputs) {
                if (input.getCount() == 1) {
                    evaluateInput(input, world, hud, selected);
                } else {
                    applyPrevious(input, selected);
                }
            }
        }
    }

    private void evaluateInput(
        UserInput input,
        GameObjectContainer world,
        GameObjectContainer hud,
        Collection<GameObject> selected)
    {
        target = getTargets(world, hud, input);
        previous = evaluateSelection(input, target, selected);
        if (previous.isEmpty()) {
            target = camera;
            previous = evaluateSelection(input, target, selected);
        }
    }

    private void applyPrevious(UserInput input, Collection<GameObject> selected) {
        if (selected.isEmpty()) {
            apply(previous, input, target, (GameObject)null);
        } else {
            apply(previous, input, target, selected);
        }
    }

    private Collection<Interaction> evaluateSelection(
        UserInput input,
        GameObject target,
        Collection<GameObject> selected)
    {
        Collection<Interaction> result = new ArrayList<>();
        if (selected.isEmpty()) {
            result.add(evaluateInteractions(input, target, null));
        }
        else if (selected.contains(target)) {
            result.add(evaluateInteractions(input, target, target));
        }
        else {
            for (GameObject selectedGameObject : selected) {
                result.add(evaluateInteractions(input, target, selectedGameObject));
            }
        }
        CollectionUtils.removeIf(result, it -> it == null);
        return result;
    }

    private Interaction evaluateInteractions(UserInput input, GameObject target, GameObject selected) {
        logInput(input, target, selected);
        Interaction interaction = interactions.getInteraction(input, target, selected);
        apply(interaction, input, target, selected);
        return interaction;
    }

    private void apply(
        Collection<Interaction> interactions,
        UserInput input,
        GameObject target,
        Collection<GameObject> selected)
    {
        for (GameObject selectedGameObject : selected) {
            apply(interactions, input, target, selectedGameObject);
        }
    }

    private void apply(Collection<Interaction> interactions, UserInput input, GameObject target, GameObject selected) {
        for (Interaction interaction: interactions) {
            apply(interaction, input, target, selected);
        }
    }

    private void apply(Interaction interaction, UserInput input, GameObject target, GameObject selected) {
        if (interaction != null) {
            interaction.apply(input, target, selected);
        }
    }

    private GameObject getTargets(GameObjectContainer world, GameObjectContainer hud, UserInput input) {
        GameObject result = getHudTarget(hud, input);
        if (result == null) {
            result = getWorldTarget(world, input);
        }
        return result;
    }

    private GameObject getHudTarget(GameObjectContainer hud, UserInput userInput) {
        Vector2 position = userInput.getPosition();
        Vector2 hudPosition = hud.unproject(position);
        return hud.hit(hudPosition, true);
    }

    private GameObject getWorldTarget(GameObjectContainer world, UserInput userInput) {
        Vector2 position = userInput.getPosition();
        Vector2 worldPosition = world.unproject(position);
        return world.hit(worldPosition, true);
    }

    private void logInput(UserInput input, GameObject target, GameObject selected) {
        logger.debug("User input - type: '{}' target: '{}' selected: '{}'",
            getType(input), getType(target), getType(selected));
    }

    private Identifier getType(GameObject gameObject) {
        if (gameObject != null) {
            return gameObject.getType();
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
