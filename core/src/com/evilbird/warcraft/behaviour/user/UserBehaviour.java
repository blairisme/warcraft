package com.evilbird.warcraft.behaviour.user;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.behaviour.user.interaction.CompositeInteraction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import static com.evilbird.engine.item.ItemPredicates.itemWithId;
import static com.evilbird.engine.item.ItemPredicates.selectedItem;

public class UserBehaviour implements Behaviour
{
    private CompositeInteraction interactions;

    @Inject
    public UserBehaviour(
        AttackInteraction attackInteraction,
        CameraInteraction cameraInteraction,
        GatherInteraction gatherInteraction,
        MoveInteraction moveInteraction,
        SelectInteraction selectInteraction)
    {
        interactions = new CompositeInteraction();
        interactions.add(attackInteraction);
        interactions.add(cameraInteraction);
        interactions.add(gatherInteraction);
        interactions.add(moveInteraction);
        interactions.add(selectInteraction);
    }

    @Override
    public void update(ItemRoot world, ItemRoot hud, List<UserInput> inputs)
    {
        if (! inputs.isEmpty())
        {
            Collection<Item> hudSelection = hud.findAll(selectedItem());
            Collection<Item> worldSelection = world.findAll(selectedItem());

            for (UserInput input : inputs)
            {
                Collection<Item> targets = getTargets(world, hud, input);
                update(input, targets, worldSelection, hudSelection);
            }
        }
    }

    private void update(UserInput input, Collection<Item> targets, Collection<Item> world, Collection<Item> hud)
    {
        for (Item target: targets)
        {
            update(input, target, world, hud);
        }
    }

    private void update(UserInput input, Item target, Collection<Item> world, Collection<Item> hud)
    {
        if (world.isEmpty())
        {
            update(input, target, (Item)null, hud);
        }
        for (Item worldSelected: world)
        {
            update(input, target, worldSelected, hud);
        }
    }

    private void update(UserInput input, Item target, Item world, Collection<Item> hud)
    {
        if (hud.isEmpty())
        {
            update(input, target, world, (Item)null);
        }
        for (Item hudSelected: hud)
        {
            update(input, target, world, hudSelected);
        }
    }

    private void update(UserInput input, Item target, Item world, Item hud)
    {
        interactions.update(input, target, world, hud);
    }

    private Collection<Item> getTargets(ItemRoot world, ItemRoot hud, UserInput input)
    {
        Collection<Item> hudTargets = getHudTargets(hud, input);

        if (!hudTargets.isEmpty())
        {
            return hudTargets;
        }
        return getWorldTargets(world, input);
    }

    private Collection<Item> getHudTargets(ItemRoot hud, UserInput userInput)
    {
        Vector2 position = userInput.getPosition();

        Item hudElement = hud.hit(position, false);

        if (hudElement != null)
        {
            return Arrays.asList(hudElement);
        }
        return Collections.emptyList();
    }

    private Collection<Item> getWorldTargets(ItemRoot world, UserInput userInput)
    {
        Collection<Item> result = new ArrayList<Item>();

        Item cameraTarget = getCameraTarget(world);

        if (cameraTarget != null)
        {
            result.add(cameraTarget);
        }
        Item worldTarget = getWorldTarget(world, userInput);

        if (worldTarget != null)
        {
            result.add(worldTarget);
        }
        return result;
    }

    private Item getCameraTarget(ItemRoot world)
    {
        return world.find(itemWithId(new Identifier("Camera")));
    }

    private Item getWorldTarget(ItemRoot world, UserInput userInput)
    {
        Vector2 position = userInput.getPosition();

        Vector2 worldPosition = world.unproject(position);

        return world.hit(worldPosition, false);
    }
}
