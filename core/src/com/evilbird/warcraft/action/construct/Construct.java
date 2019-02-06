/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.ActionContext;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.common.*;
import com.evilbird.engine.action.framework.Action;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.action.framework.duration.TimeDuration;
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.item.*;
import com.evilbird.engine.item.specialized.animated.Animated;
import com.evilbird.engine.item.specialized.animated.Audible;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.common.AnimatedMoveAction;
import com.evilbird.warcraft.action.common.ProgressAction;
import com.evilbird.warcraft.action.common.ResourceTransferAction;
import com.evilbird.warcraft.item.common.capability.ResourceContainer;
import com.evilbird.warcraft.item.common.capability.ResourceIdentifier;
import com.evilbird.warcraft.item.common.resource.ResourceUtils;
import com.evilbird.warcraft.item.data.DataType;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;
import java.util.Map;

import static com.evilbird.engine.common.function.Suppliers.constantValue;
import static com.evilbird.engine.item.ItemOperations.findAncestorByType;

/**
 * Instances of this class construct a building.
 *
 * @author Blair Butterworth
 */
public class Construct implements ActionProvider
{
    private ItemFactory itemFactory;

    @Inject
    public Construct(ItemFactory itemFactory) {
        this.itemFactory = itemFactory;
    }

    @Override
    public Action get(ActionIdentifier action, ActionContext context) {
        return buildBuilding((ConstructActions)action, context.getItem(), context.getTarget());
    }

    private Action buildBuilding(ConstructActions action, Item builder, Item site) {
        Action removeSite = new RemoveAction(site);
        Action purchaseBuilding = purchaseBuilding(action, builder);
        Action constructBuilding = constructBuilding(action, builder, site);
        return new ParallelAction(removeSite, purchaseBuilding, constructBuilding);
    }

    private Action purchaseBuilding(ConstructActions type, Item builder) {
        ResourceContainer player = (ResourceContainer)findAncestorByType(builder, DataType.Player);
        Map<ResourceIdentifier, Float> requirements = type.getResourceRequirements();
        Map<ResourceIdentifier, Float> resources = ResourceUtils.negate(requirements);
        return new ResourceTransferAction(player, resources);
    }

    private Action constructBuilding(ConstructActions type, Item builder, Item site) {
        Vector2 location = site.getPosition();
        Identifier identifier = new NamedIdentifier();
        Reference<Building> building = new Reference<>(builder.getParent(), identifier);

        Action repositionBuilder = repositionBuilder(builder, location);
        Action hideBuilder = hideBuilder(builder);
        Action createBuilding = createBuilding(builder.getParent(), type.getBuildType(), identifier, location);
        Action constructBuilding = constructBuilding(type, builder, building);
        Action showBuilder = showBuilder(builder, building);

        return new SequenceAction(repositionBuilder, hideBuilder, createBuilding, constructBuilding, showBuilder);
    }

    private Action repositionBuilder(Item builder, Vector2 location) {
        Action acknowledge = new AudibleAction((Audible)builder, UnitSound.Acknowledge);
        Action moveToSite = new AnimatedMoveAction(builder, location);
        return new SequenceAction(acknowledge, moveToSite);
    }

    private Action hideBuilder(Item builder) {
        Action deselect = new SelectAction(builder, false);
        Action hide = new VisibleAction(builder, false);
        return new ParallelAction(deselect, hide);
    }

    private Action constructBuilding(ConstructActions type, Item builder, Reference<Building> building) {
        Action preConstruction = preConstructionAudioVisual(builder, building);
        Action constructionProgress = constructionProgress(builder, building, type.getBuildDuration());
        Action postConstruction = postConstructionAudioVisual(builder, building);
        return new SequenceAction(preConstruction, constructionProgress, postConstruction);
    }

    private Action createBuilding(ItemGroup player, ItemType type, Identifier identifier, Vector2 location) {
        return new CreateAction(player, type, itemFactory, identifier, location, true);
    }

    private Action preConstructionAudioVisual(Item builder, Reference<Building> building) {
        Action soundBefore = new AudibleAction((Audible)builder, UnitSound.Construct);
        Action animateBuilderBefore = new AnimateAction((Animated)builder, UnitAnimation.Build);
        Action animateBuildingBefore = new AnimateAction(building, UnitAnimation.Construct);
        return new ParallelAction(animateBuilderBefore, animateBuildingBefore, soundBefore);
    }

    private Action constructionProgress(Item builder, Reference<Building> building, TimeDuration duration) {
        Action constructing = new ConstructAction(building, constantValue(builder), true);
        Action progress = new ProgressAction(building, duration);
        Action idle = new ConstructAction(building, constantValue((Item)null), false);
        return new SequenceAction(constructing, progress, idle);
    }

    private Action postConstructionAudioVisual(Item builder, Reference<Building> building) {
        Action soundAfter = new AudibleAction((Audible)builder, UnitSound.Complete);
        Action animateBuilderAfter = new AnimateAction((Animated)builder, UnitAnimation.Idle);
        Action animateBuildingAfter = new AnimateAction(building, UnitAnimation.Idle);
        return new ParallelAction(animateBuilderAfter, animateBuildingAfter, soundAfter);
    }

    private Action showBuilder(Item builder, Reference<Building> building) {
        Action reposition = new AlignAction(constantValue(builder), building, Alignment.BottomLeft);
        Action show = new VisibleAction(builder, true);
        return new ParallelAction(reposition, show);
    }
}
