package com.evilbird.warcraft.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.CreateAction;
import com.evilbird.engine.action.ParallelAction;
import com.evilbird.engine.action.RemoveAction;
import com.evilbird.engine.action.SequenceAction;
import com.evilbird.engine.action.replacement.AnimateAction;
import com.evilbird.engine.action.replacement.AudibleAction;
import com.evilbird.engine.action.replacement.PositionAction;
import com.evilbird.engine.action.replacement.SelectAction;
import com.evilbird.engine.action.replacement.VisibleAction;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.item.ItemType;
import com.evilbird.engine.item.Reference;
import com.evilbird.engine.item.specialized.animated.Animated;
import com.evilbird.engine.item.specialized.animated.Audible;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;

import static com.evilbird.engine.item.ItemPredicates.itemWithId;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class BuildActionProvider implements ActionProvider
{
    private ItemFactory itemFactory;
    private MoveActionProvider moveActionProvider;

    @Inject
    public BuildActionProvider(
        ItemFactory itemFactory,
        MoveActionProvider moveActionProvider)
    {
        this.itemFactory = itemFactory;
        this.moveActionProvider = moveActionProvider;
    }

    @Override
    public Action get(ActionType action, Item builder, Item buildingSite, UserInput input)
    {
        Action removeBuildSite = new RemoveAction(buildingSite);
        Action constructBuilding = construct(action, builder, buildingSite);
        return new SequenceAction(removeBuildSite, constructBuilding);
    }

    private Action construct(ActionType action, Item builder, Item buildingSite)
    {
        ItemType type = getBuildingType(action);
        Vector2 location = buildingSite.getPosition();

        Action prepareBuilder = prepareBuilder(builder, location);
        Action constructBuilding = constructBuilding(builder, type, location);
        Action resetBuilder = resetBuilder(builder, location);

        return new SequenceAction(prepareBuilder, constructBuilding, resetBuilder);
    }

    private UnitType getBuildingType(ActionType actionType)
    {
        switch (actionType){
            case BuildBarracks: return UnitType.Barracks;
            case BuildFarm: return UnitType.Farm;
            case BuildTownHall: return UnitType.TownHall;
            default: throw new UnsupportedOperationException();
        }
    }

    private Action prepareBuilder(Item builder, Vector2 location)
    {
        Action acknowledge = new AudibleAction((Audible)builder,  UnitSound.Acknowledge);
        Action moveToSite = new MoveAction((Movable)builder, location);
        Action deselectBuilder = new SelectAction(builder, false);
        Action hideBuilder = new VisibleAction(builder, false);
        return new SequenceAction(acknowledge, moveToSite, deselectBuilder, hideBuilder);
    }

    private Action constructBuilding(Item builder, ItemType type, Vector2 location)
    {
        ItemGroup player = builder.getParent();
        NamedIdentifier building = new NamedIdentifier();
        Action create = new CreateAction(player, type, itemFactory, building, location, true);
        Reference<Animated> reference = new Reference<>(player, building);

        Action soundBefore = new AudibleAction((Audible)builder, UnitSound.Construct);
        Action animateBuilderBefore = new AnimateAction((Animated)builder, UnitAnimation.Build);
        Action animateBuildingBefore = new AnimateAction(reference, UnitAnimation.Construct);
        Action before = new ParallelAction(animateBuilderBefore, animateBuildingBefore, soundBefore);

        Action constructing = setConstructing(player, building, true);
        Action progress = new ProgressAction(new Reference<>(player, building));
        Action idle = setConstructing(player, building, false);
        Action construct = new SequenceAction(constructing, progress, idle);

        Action soundAfter = new AudibleAction((Audible)builder, UnitSound.Complete);
        Action animateBuilderAfter = new AnimateAction((Animated)builder, UnitAnimation.Idle);
        Action animateBuildingAfter = new AnimateAction(reference, UnitAnimation.Idle);
        Action after = new ParallelAction(animateBuilderAfter, animateBuildingAfter, soundAfter);

        return new SequenceAction(create, before, construct, after);
    }

    private Action resetBuilder(Item builder, Vector2 location)
    {
        Vector2 resetLocation = new Vector2(location.x - builder.getWidth(), location.y);
        Action repositionBuilder = new PositionAction(builder, resetLocation);
        Action showBuilder = new VisibleAction(builder, true);
        return new SequenceAction(repositionBuilder, showBuilder);
    }

    /*
    private Action setProgress(ItemComposite player, Identifier building)
    {
        ActionValue value = new ItemReferenceValue(player, building, Progress);
        Action reset = new ModifyAction(value, new ConstantModifier(0f), new InstantDuration());
        Action increment = new ModifyAction(value, new DeltaModifier(0.05f, PerSecond, 0f, 1f), new TimeDuration(20f));
        return new SequenceAction(reset, increment);
    }

    private Action setConstructing(ItemComposite player, Identifier building, boolean constructing)
    {
        ActionValue value = new ItemReferenceValue(player, building, BuildingProperties.Constructing);
        ActionModifier modifier = new ConstantModifier(constructing);
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(value, modifier, duration);
    }
    */

    //TODO: Remove
    private Action setConstructing(ItemComposite player, Identifier id, boolean constructing)
    {
        return new Action() {
            @Override
            public boolean act(float delta) {
                Building building = (Building)player.find(itemWithId(id));
                building.setConstructing(constructing);
                return false;
            }
        };
    }
}
