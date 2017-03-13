package com.evilbird.warcraft.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.CreateAction;
import com.evilbird.engine.action.ModifyAction;
import com.evilbird.engine.action.ParallelAction;
import com.evilbird.engine.action.RemoveAction;
import com.evilbird.engine.action.SequenceAction;
import com.evilbird.engine.action.duration.ActionDuration;
import com.evilbird.engine.action.duration.TimeDuration;
import com.evilbird.engine.action.modifier.ActionModifier;
import com.evilbird.engine.action.modifier.DeltaModifier;
import com.evilbird.engine.action.modifier.DeltaType;
import com.evilbird.engine.action.value.ActionValue;
import com.evilbird.engine.action.value.ItemReferenceValue;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.ItemType;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.BuildingProperties;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class BuildActionProvider implements ActionProvider
{
    private ItemFactory itemFactory;
    private AudioActionProvider audioActionProvider;
    private AnimateActionProvider animateActionProvider;
    private MoveActionProvider moveActionProvider;
    private SelectionActionProvider selectionActionProvider;

    @Inject
    public BuildActionProvider(
        ItemFactory itemFactory,
        AudioActionProvider audioActionProvider,
        AnimateActionProvider animateActionProvider,
        MoveActionProvider moveActionProvider,
        SelectionActionProvider selectionActionProvider)
    {
        this.itemFactory = itemFactory;
        this.audioActionProvider = audioActionProvider;
        this.animateActionProvider = animateActionProvider;
        this.moveActionProvider = moveActionProvider;
        this.selectionActionProvider = selectionActionProvider;
    }

    @Override
    public Action get(Item item, Item target, UserInput input)
    {
        return get(target, item);
    }

    public Action get(Item prototype, Item builder)
    {
        if (Objects.equals(prototype.getType(), new Identifier("BarracksBuildingSite"))){ //TODO: Obtain mapping from prototype
            return build(prototype, builder, UnitType.Barracks);
        }
        throw new UnsupportedOperationException();
    }

    private Action build(Item prototype, Item builder, ItemType buildingType)
    {
        Action remove = new RemoveAction(prototype);
        Action build = build(builder, buildingType, prototype.getPosition());
        return new SequenceAction(remove, build);
    }

    private Action build(Item builder, ItemType type, Vector2 location)
    {
        ItemRoot itemRoot = builder.getRoot();
        Identifier building = new Identifier();

        Action acknowledge = audioActionProvider.get(builder, new Identifier("Acknowledge"));
        Action moveToSite = moveActionProvider.get(builder, location);
        Action deselectBuilder = selectionActionProvider.get(builder, false);
        Action createFarm = newCreateAction(itemRoot, type, building, location);
        Action buildFarm = build(builder, building, itemRoot);
        Action selectBuilder = selectionActionProvider.get(builder, true);

        return new SequenceAction(acknowledge, moveToSite, deselectBuilder, createFarm, buildFarm, selectBuilder);
    }

    private Action build(Item builder, Identifier building, ItemRoot stage)
    {
        Action soundBefore = audioActionProvider.get(builder, new Identifier("Construct"));
        Action animateBuilderBefore = animateActionProvider.get(builder, new Identifier("Build"));
        Action animateBuildingBefore = animateActionProvider.get(stage, building, new Identifier("Construct"));
        Action before = new ParallelAction(animateBuilderBefore, animateBuildingBefore, soundBefore);

        ActionValue value = new ItemReferenceValue(stage, building, BuildingProperties.Progress);
        ActionModifier modifier = new DeltaModifier(10f, DeltaType.PerSecond);
        ActionDuration duration = new TimeDuration(10f);
        Action build = new ModifyAction(value, modifier, duration);

        Action soundAfter = audioActionProvider.get(builder, new Identifier("Complete"));
        Action animateBuilderAfter = animateActionProvider.get(builder, new Identifier("Idle"));
        Action animateBuildingAfter = animateActionProvider.get(stage, building, new Identifier("Idle"));
        Action after = new ParallelAction(animateBuilderAfter, animateBuildingAfter, soundAfter);

        return new SequenceAction(before, build, after);
    }

    private Action newCreateAction(ItemRoot root, ItemType type, Identifier id, Vector2 position)
    {
        return new CreateAction(root, type, itemFactory, id, position, true);
    }
}
