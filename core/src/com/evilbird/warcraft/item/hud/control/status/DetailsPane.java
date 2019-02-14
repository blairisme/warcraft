/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.status;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.control.GridPane;
import com.evilbird.engine.item.control.TextLabel;
import com.evilbird.engine.item.control.TextLabelAlignment;
import com.evilbird.warcraft.item.hud.HudControl;
import com.evilbird.warcraft.item.hud.common.UnitPane;
import com.evilbird.warcraft.item.hud.control.status.building.BuildingDetailsPane;
import com.evilbird.warcraft.item.hud.control.status.building.BuildingProgressProvider;
import com.evilbird.warcraft.item.hud.control.status.combatant.CombatantDetailsPane;
import com.evilbird.warcraft.item.hud.control.status.resource.ResourceDetailsPane;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.building.Building;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.resource.Resource;

import javax.inject.Provider;

//TODO: Localize
//TODO: Scale flexibly
public class DetailsPane extends GridPane
{
    private Provider<UnitPane> unitPaneProvider;
    private BuildingProgressProvider buildingProgressProvider;

    public DetailsPane(
        Provider<UnitPane> unitPaneProvider,
        BuildingProgressProvider buildingProgressProvider)
    {
        super(1, 2);
        this.unitPaneProvider = unitPaneProvider;
        this.buildingProgressProvider = buildingProgressProvider;
        initialize();
    }

    private void initialize() {
        setSize(176, 176);
        setCellPadding(5);
        setCellSpacing(15);
        setCellWidthMinimum(170);
        setCellHeightMinimum(50);
        setId(HudControl.DetailsPane);
        setType(HudControl.DetailsPane);
        setTouchable(Touchable.disabled);
    }

    public void setItem(Item item) {
        clearCells();
        setTitle(item);
        setDetails(item);
    }

    private void setTitle(Item item) {
        if (item instanceof Combatant) {
            setCombatantTitle((Combatant) item);
        } else if (item instanceof Unit) {
            setUnitTitle((Unit) item);
        }
    }

    private void setUnitTitle(Unit unit) {
        UnitPane unitPane = unitPaneProvider.get();
        unitPane.setItem(unit);
        unitPane.setSize(54, 53);

        TextLabel name = createLabel(unit.getName(), 100, 15);

        GridPane nameContainer = new GridPane(1, 1);
        nameContainer.setSize(110, 50);
        nameContainer.setCellPadding(4);
        nameContainer.setCellSpacing(4);
        nameContainer.setCell(name, 0, 0);

        GridPane titleContainer = new GridPane(2, 1);
        titleContainer.setSize(160, 50);
        titleContainer.setCellSpacing(0);
        titleContainer.setCell(unitPane, 0, 0);
        titleContainer.setCell(nameContainer, 1, 0);

        setCell(titleContainer, 0, 0);
    }

    private void setCombatantTitle(Combatant combatant) {
        UnitPane unitPane = unitPaneProvider.get();
        unitPane.setItem(combatant);
        unitPane.setSize(54, 53);

        String levelText = "Level " + String.valueOf(combatant.getLevel());
        TextLabel name = createLabel(combatant.getName(), 100, 15);
        TextLabel level = createLabel(levelText, 100, 15);

        GridPane nameContainer = new GridPane(1, 2);
        nameContainer.setSize(110, 50);
        nameContainer.setCellPadding(4);
        nameContainer.setCellSpacing(4);
        nameContainer.setCell(name, 0, 0);
        nameContainer.setCell(level, 0, 1);

        GridPane titleContainer = new GridPane(2, 1);
        titleContainer.setSize(160, 50);
        titleContainer.setCellSpacing(0);
        titleContainer.setCell(unitPane, 0, 0);
        titleContainer.setCell(nameContainer, 1, 0);

        setCell(titleContainer, 0, 0);
    }

    private void setDetails(Item item) {
        if (item instanceof Building) {
            setBuildingDetails((Building) item);
        } else if (item instanceof Combatant) {
            setCombatantDetails((Combatant) item);
        } else if (item instanceof Resource) {
            setResourceDetails((Resource) item);
        }
    }

    private void setBuildingDetails(Building building) {
        BuildingDetailsPane buildingDetailsPane = new BuildingDetailsPane(buildingProgressProvider);
        buildingDetailsPane.setBuilding(building);
        buildingDetailsPane.setSize(160, 100);
        setCell(buildingDetailsPane, 0, 1);
    }

    private void setCombatantDetails(Combatant combatant) {
        CombatantDetailsPane combatantDetailsPane = new CombatantDetailsPane();
        combatantDetailsPane.setCombatant(combatant);
        combatantDetailsPane.setSize(160, 100);
        setCell(combatantDetailsPane, 0, 1);
    }

    private void setResourceDetails(Resource resource) {
        ResourceDetailsPane resourceDetailsPane = new ResourceDetailsPane();
        resourceDetailsPane.setResource(resource);
        resourceDetailsPane.setSize(160, 100);
        setCell(resourceDetailsPane, 0, 1);
    }

    private TextLabel createLabel(String text, float width, float height)
    {
        TextLabel result = new TextLabel();
        result.setText(text);
        result.setSize(width, height);
        result.setAlignment(TextLabelAlignment.Center);
        return result;
    }
}
