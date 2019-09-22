/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.badlogic.gdx.math.Rectangle;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.action.common.create.CreateEvents;
import com.evilbird.warcraft.action.common.remove.RemoveEvents;
import com.evilbird.warcraft.action.common.transfer.ResourceTransfer;
import com.evilbird.warcraft.item.common.resource.ResourceSet;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.ui.placement.Placeholder;
import com.evilbird.warcraft.item.ui.placement.PlaceholderType;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.Building;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;

import javax.inject.Inject;
import java.util.Collection;
import java.util.function.Consumer;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.common.function.Predicates.all;
import static com.evilbird.engine.item.utility.ItemPredicates.overlapping;
import static com.evilbird.warcraft.item.common.query.UnitOperations.getPlayer;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isBuilding;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isDead;
import static com.evilbird.warcraft.item.unit.UnitAnimation.BuildingSite;
import static com.evilbird.warcraft.item.unit.UnitCosts.cost;

/**
 * An {@link Action} that replaces a {@link Placeholder} with the
 * {@link Building} it represents.
 *
 * @author Blair Butterworth
 */
public class ConstructPlaceholder extends BasicAction
{
    private ItemFactory factory;
    private ResourceTransfer resources;
    private RemoveEvents removeEvents;
    private CreateEvents createEvents;
    private Consumer<Item> recipient;

    @Inject
    public ConstructPlaceholder(
        ItemFactory factory,
        ResourceTransfer resources,
        RemoveEvents removeEvents,
        CreateEvents createEvents)
    {
        this.factory = factory;
        this.resources = resources;
        this.removeEvents = removeEvents;
        this.createEvents = createEvents;
    }

    public void setRecipient(Consumer<Item> recipient) {
        this.recipient = recipient;
    }

    @Override
    public boolean act(float delta) {
        Gatherer builder = (Gatherer)getItem();
        Placeholder placeholder = (Placeholder)getTarget();
        UnitType building = getBuilding(placeholder);
        Player player = getPlayer(builder);
        return construct(builder, placeholder, building, player);
    }

    private boolean construct(Gatherer builder, Placeholder placeholder, UnitType building, Player player) {
        purchase(building, player);
        create(building, player, placeholder, builder);
        removeOccluding(placeholder);
        removePlaceholder(placeholder);
        return ActionComplete;
    }

    private void purchase(UnitType building, Player player) {
        ResourceSet cost = new ResourceSet(cost(building));
        resources.setResources(player, cost.negate());
    }

    private void create(UnitType type, Player player, Placeholder placeholder, Gatherer builder) {
        Building building = (Building)factory.get(type);
        building.setConstructionProgress(0);
        building.setAnimation(BuildingSite);
        building.setPosition(placeholder.getPosition());
        building.setVisible(true);
        builder.setAssociatedItem(building);
        building.setAssociatedItem(builder);
        player.addItem(building);
        createEvents.notifyCreate(building);
        recipient.accept(building);
    }

    private void removePlaceholder(Placeholder placeholder) {
        removeItem(placeholder);
    }

    private void removeOccluding(Placeholder placeholder) {
        ItemRoot root = placeholder.getRoot();
        Rectangle bounds = placeholder.getBounds();
        removeItems(root.findAll(all(isBuilding(), isDead(), overlapping(bounds))));
    }

    private void removeItems(Collection<Item> items) {
        for (Item item: items) {
            removeItem(item);
        }
    }

    private void removeItem(Item item) {
        ItemGroup parent = item.getParent();
        parent.removeItem(item);
        removeEvents.notifyRemove(item);
    }

    private UnitType getBuilding(Placeholder placeholder) {
        PlaceholderType type = (PlaceholderType)placeholder.getType();
        return type.getBuilding();
    }
}
