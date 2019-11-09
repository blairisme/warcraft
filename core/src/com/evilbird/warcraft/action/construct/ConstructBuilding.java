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
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.item.common.production.ProductionCosts;
import com.evilbird.warcraft.item.common.resource.ResourceSet;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.selector.SelectorType;
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
import static com.evilbird.warcraft.item.unit.UnitSound.Placement;

/**
 * An {@link Action} that replaces a selector with the
 * {@link Building} it represents.
 *
 * @author Blair Butterworth
 */
public class ConstructBuilding extends BasicAction
{
    private ItemFactory factory;
    private ResourceTransfer resources;
    private RemoveEvents removeEvents;
    private CreateEvents createEvents;
    private Consumer<Item> recipient;
    private ProductionCosts production;
    private WarcraftPreferences preferences;

    @Inject
    public ConstructBuilding(
        ItemFactory factory,
        ResourceTransfer resources,
        RemoveEvents removeEvents,
        CreateEvents createEvents,
        ProductionCosts production,
        WarcraftPreferences preferences)
    {
        this.factory = factory;
        this.resources = resources;
        this.removeEvents = removeEvents;
        this.createEvents = createEvents;
        this.production = production;
        this.preferences = preferences;
    }

    public void setRecipient(Consumer<Item> recipient) {
        this.recipient = recipient;
    }

    @Override
    public boolean act(float delta) {
        Gatherer builder = (Gatherer)getItem();
        Item selector = getTarget();
        UnitType building = getBuilding(selector);
        Player player = getPlayer(builder);
        return construct(builder, selector, building, player);
    }

    private boolean construct(Gatherer builder, Item selector, UnitType building, Player player) {
        purchase(building, player);
        create(building, player, selector, builder);
        removeOccluding(selector);
        removePlaceholder(selector);
        return ActionComplete;
    }

    private void purchase(UnitType building, Player player) {
        ResourceSet cost = new ResourceSet(production.costOf(building));
        resources.setResources(player, cost.negate());
    }

    private void create(UnitType type, Player player, Item selector, Gatherer builder) {
        Building building = (Building)factory.get(type);
        setAttributes(building, selector);
        setAssociations(builder, building);
        setPlacementSound(building);
        player.addItem(building);
        notifyCreationObservers(building);
    }

    private void setAttributes(Building building, Item selector) {
        building.setConstructionProgress(0);
        building.setAnimation(BuildingSite);
        building.setPosition(selector.getPosition());
        building.setVisible(true);
    }

    private void setAssociations(Gatherer builder, Building building) {
        builder.setAssociatedItem(building);
        building.setAssociatedItem(builder);
    }

    private void setPlacementSound(Building building) {
        if (preferences.isBuildingSoundsEnabled()) {
            building.setSound(Placement, preferences.getEffectsVolume());
        }
    }

    private void notifyCreationObservers(Building building) {
        createEvents.notifyCreate(building);
        recipient.accept(building);
    }

    private void removePlaceholder(Item selector) {
        removeItem(selector);
    }

    private void removeOccluding(Item selector) {
        ItemRoot root = selector.getRoot();
        Rectangle bounds = selector.getBounds();
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

    private UnitType getBuilding(Item selector) {
        SelectorType type = (SelectorType)selector.getType();
        return type.getBuilding();
    }
}
