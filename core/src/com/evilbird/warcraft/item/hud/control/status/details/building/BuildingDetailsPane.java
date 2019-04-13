/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.status.details.building;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.TableItem;
import com.evilbird.warcraft.item.hud.control.status.details.DetailsPaneElement;
import com.evilbird.warcraft.item.unit.building.Building;

import static com.evilbird.warcraft.item.unit.UnitType.Farm;
import static com.evilbird.warcraft.item.unit.UnitType.TownHall;

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
    private TownHallDetailsPane townHallDetails;
    private FarmDetailsPane farmDetails;

    public BuildingDetailsPane(Skin skin) {
        constructionDetails = new ConstructionDetailsPane(skin);
        productionDetails = new ProductionDetailsPane(skin);
        townHallDetails = new TownHallDetailsPane(skin);
        farmDetails = new FarmDetailsPane(skin);
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
        if (building.isConstructing()){
            showConstructionDetails(building);
        }
        else if (building.isProducing()){
            showProductionDetails(building);
        }
        else if (building.getType().equals(Farm)){
            showFarmDetails(building);
        }
        else if (building.getType().equals(TownHall)){
            showTownHallDetails(building);
        }
    }

    private void showConstructionDetails(Building building) {
        constructionDetails.setBuilding(building);
        add(constructionDetails);
    }

    private void showProductionDetails(Building building) {
        productionDetails.setBuilding(building);
        add(productionDetails);
    }

    private void showFarmDetails(Building building) {
        farmDetails.setBuilding(building);
        add(farmDetails);
    }

    private void showTownHallDetails(Building building) {
        townHallDetails.setBuilding(building);
        add(townHallDetails);
    }
}
