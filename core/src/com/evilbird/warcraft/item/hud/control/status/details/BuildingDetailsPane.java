/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.status.details;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.TableItem;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.Building;

import java.util.Collection;
import java.util.Objects;

/**
 * Instances of this user interface show details about a building, such as
 * construction or training information.
 *
 * @author Blair Butterworth
 */
public class BuildingDetailsPane extends TableItem
{
    private Building building;

    public BuildingDetailsPane(Skin skin) {
        setSkin(skin);
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    @Override
    public void update(float delta) {
        if (showProgressDetails()) {
            return;
        }
        else if (showBuildingDetails()) {
            return;
        }
        else if (! isNothingShown()) {
            showNothing();
        }
        super.update(delta);
    }

    private boolean showProgressDetails() {
        if (building.isConstructing()){
            if (!isConstructionDetailsShown()){
                showConstructionDetails();
            }
            return true;
        }
        else if (building.isProducing()){
            if (!isProductionDetailsShown()) {
                showProductionDetails();
            }
            return true;
        }
        return false;
    }

    private boolean showBuildingDetails() {
        if (Objects.equals(UnitType.Farm, building.getType())){
            if (!isFarmDetailsShown()){
                showFarmDetails();
            }
            return true;
        }
        else if (Objects.equals(UnitType.TownHall, building.getType())){
            if (!isTownHallDetailsShown()){
                showTownHallDetails();
            }
            return true;
        }
        return false;
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
        ConstructionDetailsPane detailsPane = new ConstructionDetailsPane(getSkin());
        detailsPane.setBuilding(building);
        add(detailsPane);
    }

    private void showProductionDetails() {
        ProductionDetailsPane detailsPane = new ProductionDetailsPane(getSkin());
        detailsPane.setBuilding(building);
        add(detailsPane);
    }

    private void showFarmDetails() {
        FarmDetailsPane detailsPane = new FarmDetailsPane(getSkin());
        detailsPane.setBuilding(building);
        add(detailsPane);
    }

    private void showTownHallDetails() {
        TownHallDetailsPane detailsPane = new TownHallDetailsPane(getSkin());
        detailsPane.setBuilding(building);
        add(detailsPane);
    }

    private void showNothing() {
        clearItems();
    }

    private Item getCenter() {
        Collection<Item> items = getItems();
        return items.isEmpty() ? null : items.iterator().next();
    }
}
