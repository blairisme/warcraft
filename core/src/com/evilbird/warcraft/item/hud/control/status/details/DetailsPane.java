/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.status.details;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.GridItem;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.building.Building;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.resource.Resource;

/**
 * Represents a user interface control that displays the icon and name of an
 * {@link Item}. For some Items a selection of pertinent details is also
 * shown. For example the gold remaining in a gold mine or the attack strength
 * of a footman.
 *
 * @author Blair Butterworth
 */
public class DetailsPane extends GridItem
{
    public DetailsPane(Skin skin) {
        super(1, 2);
        setSkin(skin);
        setBackground("details-panel");
        setSize(176, 176);
        setCellPadding(8);
        setTouchable(Touchable.disabled);
    }

    public void setItem(Item item) {
        clearItems();
        setTitle(item);
        setDetails(item);
    }

    private void setTitle(Item item) {
        if (item instanceof Combatant) {
            setCombatantTitle((Combatant)item);
        } else if (item instanceof Unit) {
            setUnitTitle((Unit)item);
        }
    }

    private void setUnitTitle(Unit unit) {
        UnitTitlePane titlePane = new UnitTitlePane(getSkin());
        titlePane.setUnit(unit);

        Cell cell = add(titlePane);
        cell.expandX();
        cell.fillX();
    }

    private void setCombatantTitle(Combatant combatant) {
        CombatantTitlePane titlePane = new CombatantTitlePane(getSkin());
        titlePane.setCombatant(combatant);

        Cell cell = add(titlePane);
        cell.expandX();
        cell.fillX();
    }

    private void setDetails(Item item) {
        if (item instanceof Building) {
            setBuildingDetails((Building)item);
        } else if (item instanceof Combatant) {
            setCombatantDetails((Combatant)item);
        } else if (item instanceof Resource) {
            setResourceDetails((Resource)item);
        }
    }

    private void setBuildingDetails(Building building) {
        BuildingDetailsPane buildingDetailsPane = new BuildingDetailsPane(getSkin());
        buildingDetailsPane.setBuilding(building);
        buildingDetailsPane.setSize(160, 100);
        add(buildingDetailsPane);
    }

    private void setCombatantDetails(Combatant combatant) {
        CombatantDetailsPane combatantDetailsPane = new CombatantDetailsPane(getSkin());
        combatantDetailsPane.setCombatant(combatant);
        combatantDetailsPane.setSize(160, 100);
        add(combatantDetailsPane);
    }

    private void setResourceDetails(Resource resource) {
        ResourceDetailsPane resourceDetailsPane = new ResourceDetailsPane(getSkin());
        resourceDetailsPane.setResource(resource);
        resourceDetailsPane.setSize(160, 100);
        add(resourceDetailsPane);
    }
}
