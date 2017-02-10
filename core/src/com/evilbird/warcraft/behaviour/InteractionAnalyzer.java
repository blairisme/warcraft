package com.evilbird.warcraft.behaviour;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.hud.Hud;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemUtils;
import com.evilbird.engine.utility.Identifier;
import com.evilbird.warcraft.action.ActionFactory;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.unit.Unit;
import com.evilbird.warcraft.unit.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class InteractionAnalyzer implements com.evilbird.engine.behaviour.Behaviour
{
    private ActionFactory actionFactory;
    private List<Interaction> interactions;

    public InteractionAnalyzer(ActionFactory actionFactory)
    {
        this.actionFactory = actionFactory;
        this.interactions = new ArrayList<Interaction>();

        this.interactions.add(new Interaction(UserInputType.Zoom, "Camera", null, ActionType.Zoom));
        this.interactions.add(new Interaction(UserInputType.Pan, "Camera", null, ActionType.Pan));

        this.interactions.add(new Interaction(UserInputType.Action, "Footman", null, ActionType.Select));
        this.interactions.add(new Interaction(UserInputType.Action, "Peasant", null, ActionType.Select));
        this.interactions.add(new Interaction(UserInputType.Action, "Grunt", null, ActionType.Select));
        this.interactions.add(new Interaction(UserInputType.Action, "GoldMine", null, ActionType.Select));
        this.interactions.add(new Interaction(UserInputType.Action, "TownHall", null, ActionType.Select));
        this.interactions.add(new Interaction(UserInputType.Action, "Barracks", null, ActionType.Select));
        this.interactions.add(new Interaction(UserInputType.Action, "Farm", null, ActionType.Select));
        this.interactions.add(new Interaction(UserInputType.Action, "Wood", null, ActionType.Select));

        this.interactions.add(new Interaction(UserInputType.Action, "Map", "Footman", ActionType.Move));
        this.interactions.add(new Interaction(UserInputType.Action, "Map", "Peasant", ActionType.Move));
        this.interactions.add(new Interaction(UserInputType.Action, "Map", "Grunt", ActionType.Move));

        //this.interactions.add(new Interaction(UserInputType.Action, "Map", "Peasant", ActionType.BuildFarm));
        //this.interactions.add(new Interaction(UserInputType.Action, "Map", "Peasant", ActionType.BuildBarracks));

        this.interactions.add(new Interaction(UserInputType.Action, "GoldMine", "Peasant", ActionType.GatherGold));
        this.interactions.add(new Interaction(UserInputType.Action, "Wood", "Peasant", ActionType.GatherWood));

        this.interactions.add(new Interaction(UserInputType.Action, "Grunt", "Footman", ActionType.Attack));
    }

    @Override
    public void update(World world, Hud hud, List<UserInput> input)
    {
        update(world, input); //TODO
    }

    public void update(Stage stage, List<UserInput> inputs)
    {
        if (! inputs.isEmpty())
        {
            Collection<Actor> selection = getSelection(stage);

            for (UserInput input : inputs)
            {
                Collection<Actor> targets = getTargets(stage, input);

                updateActions(input, targets, selection);
            }
        }
    }

    private void updateActions(UserInput input, Collection<Actor> targets, Collection<Actor> selection)
    {
        for (Actor target: targets)
        {
            if (selection.isEmpty())
            {
                updateActions(input, target, null);
            }
            for (Actor selected: selection)
            {
                updateActions(input, target, selected);
            }
        }
    }

    private void updateActions(UserInput input, Actor target, Actor selected)
    {
        for (Interaction interaction: interactions)
        {
            if (interactionApplicable(interaction, input, target, selected))
            {
                addAction(interaction, input, target, selected);
            }
        }
    }

    //TODO - Messy/incomplete
    private void addAction(Interaction interaction, UserInput input, Actor target, Actor selected)
    {
        if (interaction.getCommandType() == ActionType.Move)
        {
            //Vector2 meh = selected.getStage().screenToStageCoordinates(input.getPosition());
            Vector2 position = input.getPosition(); //screenToStageCoordinates in getTargets modified input position
            Action action = actionFactory.newAction(new Identifier("Move"), selected, position);

            selected.clearActions();
            selected.addAction(action);
        }
        else if (interaction.getCommandType() == ActionType.Select)
        {
            Unit unit = (Unit)target;
            Action action = actionFactory.newAction(new Identifier("Select"), target, !unit.getSelected());
            target.addAction(action);
        }
        else if (interaction.getCommandType() == ActionType.Zoom)
        {
            Action action = actionFactory.newAction(new Identifier("Zoom"), target, input);
            target.addAction(action);
        }
        else if (interaction.getCommandType() == ActionType.Pan)
        {
            Action action = actionFactory.newAction(new Identifier("Pan"), target, input.getDelta());
            target.addAction(action);
        }
        else if (interaction.getCommandType() == ActionType.GatherGold)
        {
            Action action = actionFactory.newAction(new Identifier("GatherGold"), selected, target);
            selected.addAction(action);
        }
        else if (interaction.getCommandType() == ActionType.GatherWood)
        {
            Action action = actionFactory.newAction(new Identifier("GatherWood"), selected, target);
            selected.addAction(action);
        }
        else if (interaction.getCommandType() == ActionType.BuildFarm)
        {
            Action action = actionFactory.newAction(new Identifier("BuildFarm"), selected, input.getPosition());
            selected.addAction(action);
        }
        else if (interaction.getCommandType() == ActionType.BuildBarracks)
        {
            Action action = actionFactory.newAction(new Identifier("BuildBarracks"), selected, input.getPosition());
            selected.addAction(action);
        }
        else if (interaction.getCommandType() == ActionType.Attack)
        {
            Action action = actionFactory.newAction(new Identifier("Attack"), selected, target);
            selected.addAction(action);
        }
    }

    private Collection<Actor> getSelection(Stage stage)
    {
        Collection<Actor> result = new ArrayList<Actor>();

        for (Actor actor: stage.getActors())
        {
            if (getSelected(actor))
            {
                result.add(actor);
            }
        }
        return result;
    }

    private Collection<Actor> getTargets(Stage stage, UserInput userInput)
    {
        Vector2 inputPosition = userInput.getPosition();

        Vector2 worldPosition = stage.screenToStageCoordinates(inputPosition);

        Actor target = stage.hit(worldPosition.x, worldPosition.y, false);

        Actor camera = ItemUtils.findByType(stage, new Identifier("Camera"));

        return Arrays.asList(target, camera);
    }

    private boolean interactionApplicable(Interaction interaction, UserInput input, Actor target, Actor selected)
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

    //TODO: Use Items not Actors
    private String getType(Actor actor)
    {
        if (actor instanceof Item)
        {
            Item item = (Item)actor;
            Identifier type = (Identifier)item.getProperty(new Identifier("Type"));
            return type.toString();
        }
        return null;
    }

    //TODO: Do better
    private boolean getSelected(Actor actor)
    {
        if (actor instanceof Unit)
        {
            Unit unit = (Unit)actor;
            return unit.getSelected();
        }
        return false;
    }
}
