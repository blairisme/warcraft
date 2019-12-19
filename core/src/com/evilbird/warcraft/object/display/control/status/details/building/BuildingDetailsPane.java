/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.control.status.details.building;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.control.Table;
import com.evilbird.warcraft.data.resource.ResourceType;
import com.evilbird.warcraft.object.display.control.status.details.DetailsPaneElement;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.building.Building;

/**
 * Instances of this user interface show details about a building, such as
 * construction or training information.
 *
 * @author Blair Butterworth
 */
public class BuildingDetailsPane extends Table implements DetailsPaneElement
{
    private ConstructionDetailsPane constructionDetails;
    private ProductionDetailsPane productionDetails;
    private CommandCentreDetailsPane commandCentreDetails;
    private FoodProducerDetailsPane foodProducerDetails;

    public BuildingDetailsPane(Skin skin) {
        constructionDetails = new ConstructionDetailsPane(skin);
        productionDetails = new ProductionDetailsPane(skin);
        commandCentreDetails = new CommandCentreDetailsPane(skin);
        foodProducerDetails = new FoodProducerDetailsPane(skin);
    }

    public void setConstructing(Building building, boolean constructing) {
        if (constructing) {
            showConstructionDetails(building);
        } else {
            updateView(building);
        }
    }

    public void setProducing(Building building, boolean producing) {
        if (producing) {
            showProductionDetails(building);
        } else {
            updateView(building);
        }
    }

    public void setResource(ResourceType resource, float value) {
        if (contains(commandCentreDetails)) {
            commandCentreDetails.setResource(resource, value);
        }
        if (contains(foodProducerDetails)) {
            foodProducerDetails.setResource(resource, value);
        }
    }

    public void setItem(GameObject gameObject) {
        updateView((Building) gameObject);
    }

    private void updateView(Building building) {
        removeObjects();
        UnitType buildingType = (UnitType)building.getType();

        if (building.isConstructing()){
            showConstructionDetails(building);
        }
        else if (building.isProducing()){
            showProductionDetails(building);
        }
        else if (buildingType.isFoodProducer()){
            showFoodProducerDetails(building);
        }
        else if (buildingType.isCommandCentre()) {
            showCommandCentreDetails(building);
        }
    }

    private void showConstructionDetails(Building building) {
        constructionDetails.setBuilding(building);
        showView(constructionDetails);
    }

    private void showProductionDetails(Building building) {
        productionDetails.setBuilding(building);
        showView(productionDetails);
    }

    private void showFoodProducerDetails(Building building) {
        foodProducerDetails.setBuilding(building);
        showView(foodProducerDetails);
    }

    private void showCommandCentreDetails(Building building) {
        commandCentreDetails.setBuilding(building);
        showView(commandCentreDetails);
    }

    private void showView(GameObject view) {
        removeObjects();
        add(view);
    }
}
