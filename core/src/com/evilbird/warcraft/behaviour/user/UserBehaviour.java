/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.user;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.state.State;
import com.evilbird.warcraft.item.data.DataType;

import javax.inject.Inject;
import java.util.*;

import static com.evilbird.engine.item.ItemPredicates.itemWithType;
import static com.evilbird.engine.item.ItemPredicates.selectedItem;

/**
 * Instances of this class modify the game state based on user input.
 *
 * @author Blair Butterworth
 */
public class UserBehaviour implements Behaviour
{
    private UserInteractions interactions;

    @Inject
    public UserBehaviour(UserInteractions interactions) {
        this.interactions = interactions;
    }

    @Override
    public Identifier getIdentifier() {
        return null; //TODO
    }

    @Override
    public void update(State state, List<UserInput> inputs) {
        if (! inputs.isEmpty()) {
            ItemRoot world = state.getWorld();
            ItemRoot hud = state.getHud();

            Collection<Item> hudSelection = hud.findAll(selectedItem());
            Collection<Item> worldSelection = world.findAll(selectedItem());

            for (UserInput input : inputs) {
                Collection<Item> targets = getTargets(world, hud, input);
                update(input, targets, worldSelection, hudSelection);
            }
        }
    }

    private void update(UserInput input, Collection<Item> target, Collection<Item> world, Collection<Item> hud) {
        if (world.isEmpty()) {
            update(input, target, (Item)null, hud);
        }
        for (Item worldSelected: world) {
            update(input, target, worldSelected, hud);
        }
    }

    // TODO: Remove hud
    private void update(UserInput input, Collection<Item> target, Item world, Collection<Item> hud) {
        if (hud.isEmpty()) {
            update(input, target, world);
        }
        for (Item hudSelected: hud) {
            update(input, target, world);
        }
    }

    private void update(UserInput input, Collection<Item> targets, Item world) {
        for (Item target: targets) {
            if (update(input, target, world)) {
                return;
            }
        }
    }

    private boolean update(UserInput input, Item target, Item selected) {
        //logUpdate(input, target, selected);
        Collection<Interaction> actions = interactions.getInteractions(input, target, selected);
        for (Interaction interaction: actions) {
            interaction.update(input, target, selected);
        }
        return !actions.isEmpty();
    }

//    private void logUpdate(UserInput input, Item target, Item world) {
//        String inputType = input.getType().toString();
//        String targetType = target != null ? target.getType().toString() : "<none>";
//        String worldType = world != null ? world.getType().toString() : "<none>";
//        System.out.println("Input: " + inputType + ", target: " + targetType + ", world: " + worldType);
//    }

    private Collection<Item> getTargets(ItemRoot world, ItemRoot hud, UserInput input) {
        Collection<Item> hudTargets = getHudTargets(hud, input);

        if (!hudTargets.isEmpty()) {
            return hudTargets;
        }
        return getWorldTargets(world, input);
    }

    private Collection<Item> getHudTargets(ItemRoot hud, UserInput userInput) {
        Vector2 position = userInput.getPosition();
        Vector2 hudPosition = hud.unproject(position);

        Item hudElement = hud.hit(hudPosition, true);
        if (hudElement != null) {
            return Arrays.asList(hudElement);
        }
        return Collections.emptyList();
    }

    private Collection<Item> getWorldTargets(ItemRoot world, UserInput userInput) {
        Collection<Item> result = new ArrayList<>();
        Item worldTarget = getWorldTarget(world, userInput);

        if (worldTarget != null) {
            result.add(worldTarget);
        }
        Item cameraTarget = getCameraTarget(world);

        if (cameraTarget != null) {
            result.add(cameraTarget);
        }
        return result;
    }

    // TODO: cache camera - theres only one?
    private Item getCameraTarget(ItemRoot world) {
        return world.find(itemWithType(DataType.Camera));
    }

    private Item getWorldTarget(ItemRoot world, UserInput userInput) {
        Vector2 position = userInput.getPosition();
        Vector2 worldPosition = world.unproject(position);
        return world.hit(worldPosition, true);
    }
}
