/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.status.details.building;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.TableItem;
import com.evilbird.warcraft.item.ui.display.control.status.details.DetailsPaneElement;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.Building;

/**
 * Instances of this user interface show details about a building, such as
 * construction or training information.
 *
 * @author Blair Butterworth
 */
public class BuildingDetailsPane extends TableItem implements DetailsPaneElement
{
    private ConstructionDetailsPane constructionDetails;
    private ProductionDetailsPane productionDetails;
    private CommandCentreDetailsPane townHallDetails;
    private FoodProducerDetailsPane farmDetails;

    public BuildingDetailsPane(Skin skin) {
        constructionDetails = new ConstructionDetailsPane(skin);
        productionDetails = new ProductionDetailsPane(skin);
        townHallDetails = new CommandCentreDetailsPane(skin);
        farmDetails = new FoodProducerDetailsPane(skin);
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

    public void setItem(Item item) {
        updateView((Building)item);
    }

    private void updateView(Building building) {
        clearItems();
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
        farmDetails.setBuilding(building);
        showView(farmDetails);
    }

    private void showCommandCentreDetails(Building building) {
        townHallDetails.setBuilding(building);
        showView(townHallDetails);
    }

    private void showView(Item view) {
        clearItems();
        add(view);
    }
}