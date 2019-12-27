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
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.engine.object.GameObjectGroup;
import com.evilbird.warcraft.action.common.create.CreateEvents;
import com.evilbird.warcraft.action.common.transfer.ResourceTransfer;
import com.evilbird.warcraft.action.death.RemoveEvents;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.data.resource.ResourceSet;
import com.evilbird.warcraft.object.common.production.ProductionCosts;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.selector.SelectorType;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.building.Building;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;

import javax.inject.Inject;
import java.util.Collection;
import java.util.function.Consumer;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.common.function.Predicates.all;
import static com.evilbird.engine.object.utility.GameObjectPredicates.overlapping;
import static com.evilbird.warcraft.object.common.query.UnitOperations.getPlayer;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.isBuilding;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.isDead;
import static com.evilbird.warcraft.object.unit.UnitAnimation.BuildingSite;
import static com.evilbird.warcraft.object.unit.UnitSound.Placement;

/**
 * An {@link Action} that replaces a selector with the
 * {@link Building} it represents.
 *
 * @author Blair Butterworth
 */
public class ConstructBuilding extends BasicAction
{
    private GameObjectFactory factory;
    private ResourceTransfer resources;
    private RemoveEvents removeEvents;
    private CreateEvents createEvents;
    private Consumer<GameObject> recipient;
    private ProductionCosts production;
    private WarcraftPreferences preferences;

    @Inject
    public ConstructBuilding(
        GameObjectFactory factory,
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

    public void setRecipient(Consumer<GameObject> recipient) {
        this.recipient = recipient;
    }

    @Override
    public boolean act(float delta) {
        Gatherer builder = (Gatherer) getSubject();
        GameObject selector = getTarget();
        UnitType building = getBuilding(selector);
        Player player = getPlayer(builder);
        return construct(builder, selector, building, player);
    }

    private boolean construct(Gatherer builder, GameObject selector, UnitType building, Player player) {
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

    private void create(UnitType type, Player player, GameObject selector, Gatherer builder) {
        Building building = (Building)factory.get(type);
        setAttributes(building, selector);
        setAssociations(builder, building);
        setPlacementSound(building);
        player.addObject(building);
        notifyCreationObservers(building);
    }

    private void setAttributes(Building building, GameObject selector) {
        building.setConstructionProgress(0);
        building.setAnimation(BuildingSite);
        building.setPosition(selector.getPosition());
        building.setVisible(true);
    }

    private void setAssociations(Gatherer builder, Building building) {
        builder.setConstruction(building);
        building.setConstructor(builder);
    }

    private void setPlacementSound(Building building) {
        if (preferences.isBuildingSoundsEnabled()) {
            building.setSound(Placement);
        }
    }

    private void notifyCreationObservers(Building building) {
        createEvents.notifyCreate(building);
        recipient.accept(building);
    }

    private void removePlaceholder(GameObject selector) {
        removeItem(selector);
    }

    private void removeOccluding(GameObject selector) {
        GameObjectContainer root = selector.getRoot();
        Rectangle bounds = selector.getBounds();
        removeItems(root.findAll(all(isBuilding(), isDead(), overlapping(bounds))));
    }

    private void removeItems(Collection<GameObject> gameObjects) {
        for (GameObject gameObject : gameObjects) {
            removeItem(gameObject);
        }
    }

    private void removeItem(GameObject gameObject) {
        GameObjectGroup parent = gameObject.getParent();
        parent.removeObject(gameObject);
        removeEvents.objectRemoved(gameObject);
    }

    private UnitType getBuilding(GameObject selector) {
        SelectorType type = (SelectorType)selector.getType();
        return type.getBuilding();
    }
}
