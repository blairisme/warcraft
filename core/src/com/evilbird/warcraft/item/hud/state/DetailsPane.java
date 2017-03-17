package com.evilbird.warcraft.item.hud.state;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.control.GridPane;
import com.evilbird.engine.item.control.TextLabel;
import com.evilbird.engine.item.control.TextLabelAlignment;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.hud.common.UnitPane;
import com.evilbird.warcraft.item.unit.ResourceType;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.building.Building;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.resource.Resource;

import javax.inject.Provider;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
//TODO: Localize
public class DetailsPane extends GridPane
{
    private Provider<UnitPane> unitPaneProvider;
    private Provider<BuildingBar> buildingBarProvider;

    public DetailsPane(
        Provider<UnitPane> unitPaneProvider,
        Provider<BuildingBar> buildingBarProvider)
    {
        super(1, 2);
        this.unitPaneProvider = unitPaneProvider;
        this.buildingBarProvider = buildingBarProvider;
        initialize();
    }

    private void initialize()
    {
        setSize(176, 176);
        setCellPadding(5);
        setCellSpacing(15);
        setCellWidthMinimum(170);
        setCellHeightMinimum(50);
        setId(new NamedIdentifier("DetailsPane"));
        setType(new NamedIdentifier("DetailsPane"));
        setTouchable(Touchable.disabled);
    }

    public void setItem(Item item)
    {
        clearCells();
        setTitle(item);
        setDetails(item);
    }

    private void setTitle(Item item)
    {
        if (item instanceof Combatant){
            setCombatantTitle((Combatant)item);
        }
        else if (item instanceof Unit){
            setUnitTitle((Unit)item);
        }
    }

    private void setUnitTitle(Unit unit)
    {
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

    private void setCombatantTitle(Combatant combatant)
    {
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

    private void setDetails(Item item)
    {
        if (item instanceof Building){
            setBuildingDetails((Building)item);
        }
        else if (item instanceof Combatant){
            setCombatantDetails((Combatant)item);
        }
        else if (item instanceof Resource){
            setResourceDetails((Resource)item);
        }
    }

    private void setBuildingDetails(Building building)
    {
        if (building.isConstructing()){
            setConstructionDetails(building);
        }
        else if (building.isProducing()){
            setProductionDetails(building);
        }
        else if (Objects.equals(new NamedIdentifier("Farm"), building.getType())){
            setFarmDetails(building);
        }
        else if (Objects.equals(new NamedIdentifier("TownHall"), building.getType())){
            setTownHallDetails(building);
        }
    }

    private void setConstructionDetails(Building building)
    {
        BuildingBar buildingBar = buildingBarProvider.get();
        buildingBar.setBuilding(building);

        GridPane detailsContainer = new GridPane(1, 1);
        detailsContainer.setSize(160, 100);
        detailsContainer.setHorizontalCellPadding(4);
        detailsContainer.setVerticalCellPadding(80);
        detailsContainer.setCell(buildingBar, 0, 0);

        setCell(detailsContainer, 0, 1);
    }

    private void setProductionDetails(Building building)
    {

    }

    private void setFarmDetails(Building building)
    {
        TextLabel title = createLabel("Food Usage:", 160, 12);
        TextLabel grown = createLabel("    Grown: XXX", 160, 12);
        TextLabel used = createLabel("    Used: XXX", 160, 12);

        GridPane detailsContainer = new GridPane(1, 3);
        detailsContainer.setSize(160, 100);
        detailsContainer.setCellSpacing(4);
        detailsContainer.setCell(title, 0, 0);
        detailsContainer.setCell(grown, 0, 1);
        detailsContainer.setCell(used, 0, 2);
        detailsContainer.setCellWidthMinimum(160);
        detailsContainer.setCellHeightMinimum(12);

        setCell(detailsContainer, 0, 1);
    }

    private void setTownHallDetails(Building building)
    {
        Player player = (Player)building.getParent();

        TextLabel production = createLabel("Production");
        TextLabel gold = createDetailsLabel("Gold", player.getResource(ResourceType.Gold));
        TextLabel lumber = createDetailsLabel("Lumber", player.getResource(ResourceType.Wood));
        TextLabel oil = createDetailsLabel("Oil", player.getResource(ResourceType.Oil));

        GridPane detailsContainer = new GridPane(1, 4);
        detailsContainer.setSize(160, 100);
        detailsContainer.setCellSpacing(4);
        detailsContainer.setCell(production, 0, 0);
        detailsContainer.setCell(gold, 0, 1);
        detailsContainer.setCell(lumber, 0, 2);
        detailsContainer.setCell(oil, 0, 3);
        detailsContainer.setCellWidthMinimum(160);
        detailsContainer.setCellHeightMinimum(12);

        setCell(detailsContainer, 0, 1);
    }

    private void setCombatantDetails(Combatant combatant)
    {
        TextLabel armour = createDetailsLabel("Armour", combatant.getArmour());
        TextLabel damage = createDetailsLabel("Damage", combatant.getDamageMinimum(), combatant.getDamageMaximum());
        TextLabel range = createDetailsLabel("Range", combatant.getRange());
        TextLabel sight = createDetailsLabel("Sight", combatant.getSight());
        TextLabel speed = createDetailsLabel("Speed", combatant.getSpeed());

        GridPane detailsContainer = new GridPane(1, 5);
        detailsContainer.setSize(160, 100);
        detailsContainer.setCellSpacing(4);
        detailsContainer.setCell(armour, 0, 0);
        detailsContainer.setCell(damage, 0, 1);
        detailsContainer.setCell(range, 0, 2);
        detailsContainer.setCell(sight, 0, 3);
        detailsContainer.setCell(speed, 0, 4);
        detailsContainer.setCellWidthMinimum(160);
        detailsContainer.setCellHeightMinimum(12);

        setCell(detailsContainer, 0, 1);
    }

    private void setResourceDetails(Resource resource)
    {
        if (Objects.equals(new NamedIdentifier("GoldMine"), resource.getType())){
            setGoldMineDetails(resource);
        }
    }

    private void setGoldMineDetails(Resource resource)
    {
        TextLabel title = createDetailsLabel("Gold Left", resource.getResource(ResourceType.Gold));

        GridPane detailsContainer = new GridPane(1, 1);
        detailsContainer.setSize(160, 100);
        detailsContainer.setCellSpacing(4);
        detailsContainer.setCell(title, 0, 0);
        detailsContainer.setCellWidthMinimum(160);
        detailsContainer.setCellHeightMinimum(12);

        setCell(detailsContainer, 0, 1);
    }

    private TextLabel createDetailsLabel(String prefix, float suffix)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(prefix);
        stringBuilder.append(": ");
        stringBuilder.append(Math.round(suffix));
        return createLabel(stringBuilder.toString());
    }

    private TextLabel createDetailsLabel(String prefix, float suffixMin, float suffixMax)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(prefix);
        stringBuilder.append(": ");
        stringBuilder.append(Math.round(suffixMin));
        stringBuilder.append("-");
        stringBuilder.append(Math.round(suffixMax));
        return createLabel(stringBuilder.toString());
    }

    private TextLabel createLabel(String text)
    {
        return createLabel(text, 160, 12);
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
