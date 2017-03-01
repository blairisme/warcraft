package com.evilbird.warcraft.behaviour.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.utility.Identifier;
import com.evilbird.warcraft.action.Actions;
import com.evilbird.warcraft.item.unit.common.AnimatedItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static com.evilbird.engine.item.ItemPredicates.itemWithId;
import static com.evilbird.engine.item.ItemPredicates.itemWithProperty;

public class InteractionAnalyzer implements Behaviour
{
    private ActionFactory actionFactory;
    private List<Interaction> interactions;

    public InteractionAnalyzer(ActionFactory actionFactory)
    {
        this.actionFactory = actionFactory;
        this.interactions = new ArrayList<Interaction>();

        this.interactions.add(new Interaction(UserInputType.Zoom, "Camera", null, Actions.Zoom));
        this.interactions.add(new Interaction(UserInputType.Pan, "Camera", null, Actions.Pan));

        this.interactions.add(new Interaction(UserInputType.Action, "Footman", null, Actions.Select));
        this.interactions.add(new Interaction(UserInputType.Action, "Peasant", null, Actions.Select));
        this.interactions.add(new Interaction(UserInputType.Action, "Grunt", null, Actions.Select));
        this.interactions.add(new Interaction(UserInputType.Action, "GoldMine", null, Actions.Select));
        this.interactions.add(new Interaction(UserInputType.Action, "TownHall", null, Actions.Select));
        this.interactions.add(new Interaction(UserInputType.Action, "Barracks", null, Actions.Select));
        this.interactions.add(new Interaction(UserInputType.Action, "Farm", null, Actions.Select));
        this.interactions.add(new Interaction(UserInputType.Action, "Wood", null, Actions.Select));

        this.interactions.add(new Interaction(UserInputType.Action, "Map", "Footman", Actions.Move));
        this.interactions.add(new Interaction(UserInputType.Action, "Map", "Peasant", Actions.Move));
        this.interactions.add(new Interaction(UserInputType.Action, "Map", "Grunt", Actions.Move));

        //this.interactions.add(new Interaction(UserInputType.Action, "Map", "Peasant", ActionType.BuildFarm));
        //this.interactions.add(new Interaction(UserInputType.Action, "Map", "Peasant", ActionType.BuildBarracks));

        this.interactions.add(new Interaction(UserInputType.Action, "GoldMine", "Peasant", Actions.GatherGold));
        this.interactions.add(new Interaction(UserInputType.Action, "Wood", "Peasant", Actions.GatherWood));

        this.interactions.add(new Interaction(UserInputType.Action, "Grunt", "Footman", Actions.Attack));
    }

    @Override
    public void update(ItemRoot world, ItemRoot hud, List<UserInput> input)
    {
        update(world, input); //TODO
    }

    public void update(ItemRoot world, List<UserInput> inputs)
    {
        if (! inputs.isEmpty())
        {
            Collection<Item> selection = world.findAll(itemWithProperty(new Identifier("Selected"), true));

            for (UserInput input : inputs)
            {
                Collection<Item> targets = getTargets(world, input);

                updateActions(input, targets, selection);
            }
        }
    }

    private void updateActions(UserInput input, Collection<Item> targets, Collection<Item> selection)
    {
        for (Item target: targets)
        {
            if (selection.isEmpty())
            {
                updateActions(input, target, null);
            }
            for (Item selected: selection)
            {
                updateActions(input, target, selected);
            }
        }
    }

    private void updateActions(UserInput input, Item target, Item selected)
    {
        for (Interaction interaction: interactions)
        {
            if (interactionApplicable(interaction, input, target, selected))
            {
                addAction(interaction, input, target, selected);
            }
        }
    }

    //TODO: Refactor into common action creation
    private void addAction(Interaction interaction, UserInput input, Item target, Item selected)
    {
        if (interaction.getCommandType() == Actions.Move)
        {
            //Vector2 meh = selected.getStage().screenToStageCoordinates(input.getPosition());
            Vector2 position = input.getPosition(); //screenToStageCoordinates in getTargets modified input position
            Action action = actionFactory.newAction(interaction.getCommandType(), selected, position);

            selected.clearActions();
            selected.addAction(action);
        }
        else if (interaction.getCommandType() == Actions.Select)
        {
            AnimatedItem animatedItem = (AnimatedItem)target;
            boolean itemSelected = (Boolean)animatedItem.getProperty(new Identifier("Selected"));
            Action action = actionFactory.newAction(interaction.getCommandType(), target, !itemSelected);
            target.addAction(action);
        }
        else if (interaction.getCommandType() == Actions.Zoom)
        {
            Action action = actionFactory.newAction(interaction.getCommandType(), target, input);
            target.addAction(action);
        }
        else if (interaction.getCommandType() == Actions.Pan)
        {
            Action action = actionFactory.newAction(interaction.getCommandType(), target, input.getDelta());
            target.addAction(action);
        }
        else if (interaction.getCommandType() == Actions.GatherGold)
        {
            Action action = actionFactory.newAction(interaction.getCommandType(), selected, target);
            selected.addAction(action);
        }
        else if (interaction.getCommandType() == Actions.GatherWood)
        {
            Action action = actionFactory.newAction(interaction.getCommandType(), selected, target);
            selected.addAction(action);
        }
        else if (interaction.getCommandType() == Actions.BuildFarm)
        {
            Action action = actionFactory.newAction(interaction.getCommandType(), selected, input.getPosition());
            selected.addAction(action);
        }
        else if (interaction.getCommandType() == Actions.BuildBarracks)
        {
            Action action = actionFactory.newAction(interaction.getCommandType(), selected, input.getPosition());
            selected.addAction(action);
        }
        else if (interaction.getCommandType() == Actions.Attack)
        {
            Action action = actionFactory.newAction(interaction.getCommandType(), selected, target);
            selected.addAction(action);
        }
    }

    private Collection<Item> getTargets(ItemRoot root, UserInput userInput)
    {
        Vector2 inputPosition = userInput.getPosition();

        Vector2 worldPosition = root.unproject(inputPosition);

        Item target = root.hit(worldPosition, false);

        Item camera = root.find(itemWithId(new Identifier("Camera")));

        return Arrays.asList(target, camera);
    }

    private boolean interactionApplicable(Interaction interaction, UserInput input, Item target, Item selected)
    {
        if (!Objects.equals(interaction.getInputType(), input.getType()))
        {
            return false;
        }
        //if (!Objects.equals(interaction.getTargetType(), target.getType()))
        if (!Objects.equals(interaction.getTargetType(), getType(target)))
        {
            return false;
        }
        if (interaction.getSelectedType() != null)
        {
            //if (!Objects.equals(interaction.getSelectedType(), selected.getType()))
            if (!Objects.equals(interaction.getSelectedType(), getType(selected)))
            {
                return false;
            }
        }
        return true;
    }

    //TODO: Investigate why type is missing
    private String getType(Item item)
    {
        if (item == null) return "Unknown";
        Identifier type = (Identifier)item.getProperty(new Identifier("Type"));
        return type != null ? type.toString() : "Unknown";
    }
}
