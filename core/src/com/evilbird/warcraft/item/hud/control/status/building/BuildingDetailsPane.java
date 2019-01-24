/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.status.building;

import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.control.BorderPane;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;

/**
 * Instances of this user interface show details about a building, such as
 * construction or training information.
 *
 * @author Blair Butterworth
 */
public class BuildingDetailsPane extends BorderPane
{
    private Building building;
    private BuildingProgressProvider buildingProgressProvider;

    @Inject
    public BuildingDetailsPane(BuildingProgressProvider buildingProgressProvider) {
        this.buildingProgressProvider = buildingProgressProvider;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    @Override
    public void update(float delta) {
        if (building.isConstructing()){
            if (!isConstructionDetailsShown()){
                showConstructionDetails();
            }
        }
        else if (building.isProducing()){
            if (!isProductionDetailsShown()) {
                showProductionDetails();
            }
        }
        else if (Objects.equals(UnitType.Farm, building.getType())){
            if (!isFarmDetailsShown()){
                showFarmDetails();
            }
        }
        else if (Objects.equals(UnitType.TownHall, building.getType())){
            if (!isTownHallDetailsShown()){
                showTownHallDetails();
            }
        }
        else if (! isNothingShown()) {
            showNothing();
        }
        super.update(delta);
    }

    private boolean isConstructionDetailsShown() {
        Item current = getCenter();
        return current instanceof ConstructionDetailsPane;
    }

    private boolean isProductionDetailsShown() {
        Item current = getCenter();
        return current instanceof ProductionDetailsPane;
    }

    private boolean isFarmDetailsShown() {
        Item current = getCenter();
        return current instanceof FarmDetailsPane;
    }

    private boolean isTownHallDetailsShown() {
        Item current = getCenter();
        return current instanceof TownHallDetailsPane;
    }

    private boolean isNothingShown() {
        Item current = getCenter();
        return current == null;
    }

    private void showConstructionDetails() {
        ConstructionDetailsPane detailsPane = new ConstructionDetailsPane(buildingProgressProvider);
        detailsPane.setBuilding(building);
        setCenter(detailsPane);
    }

    private void showProductionDetails() {
        ProductionDetailsPane detailsPane = new ProductionDetailsPane(buildingProgressProvider);
        detailsPane.setBuilding(building);
        setCenter(detailsPane);
    }

    private void showFarmDetails() {
        FarmDetailsPane detailsPane = new FarmDetailsPane();
        detailsPane.setBuilding(building);
        setCenter(detailsPane);
    }

    private void showTownHallDetails() {
        TownHallDetailsPane detailsPane = new TownHallDetailsPane();
        detailsPane.setBuilding(building);
        setCenter(detailsPane);
    }

    private void showNothing() {
        clearItems();
    }
}