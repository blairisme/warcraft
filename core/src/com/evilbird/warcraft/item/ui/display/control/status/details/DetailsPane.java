/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.status.details;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.GridItem;
import com.evilbird.warcraft.item.ui.display.control.status.details.building.BuildingDetailsPane;
import com.evilbird.warcraft.item.ui.display.control.status.details.combatant.CombatantDetailsPane;
import com.evilbird.warcraft.item.ui.display.control.status.details.combatant.CombatantTitlePane;
import com.evilbird.warcraft.item.ui.display.control.status.details.common.UnitTitlePane;
import com.evilbird.warcraft.item.ui.display.control.status.details.resource.ResourceDetailsPane;
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
    private UnitTitlePane generalTitle;
    private CombatantTitlePane combatantTitle;
    private BuildingDetailsPane buildingDetails;
    private CombatantDetailsPane combatantDetails;
    private ResourceDetailsPane resourceDetails;

    public DetailsPane(Skin skin) {
        super(1, 2);

        generalTitle = new UnitTitlePane(skin);
        combatantTitle = new CombatantTitlePane(skin);
        buildingDetails = new BuildingDetailsPane(skin);
        combatantDetails = new CombatantDetailsPane(skin);
        resourceDetails = new ResourceDetailsPane(skin);

        setSkin(skin);
        setBackground("details-panel");
        setSize(176, 176);
        setCellPadding(8);
        setTouchable(Touchable.disabled);
    }

    public void setConstructing(Building building, boolean constructing) {
        if (isShown(buildingDetails)) {
            buildingDetails.setConstructing(building, constructing);
        }
    }

    public void setProducing(Building building, boolean producing) {
        if (isShown(buildingDetails)) {
            buildingDetails.setProducing(building, producing);
        }
    }

    public void setItem(Item item) {
        clearItems();
        setTitle(item);
        setDetails(item);
    }

    private void setTitle(Item item) {
        if (item instanceof Combatant) {
            setTitle(combatantTitle, item);
        } else if (item instanceof Unit) {
            setTitle(generalTitle, item);
        }
    }

    private void setTitle(DetailsPaneElement view, Item item) {
        view.setItem(item);
        Cell cell = add(view);
        cell.expandX();
        cell.fillX();
    }

    private void setDetails(Item item) {
        if (item instanceof Building) {
            setDetails(buildingDetails, item);
        } else if (item instanceof Combatant) {
            setDetails(combatantDetails, item);
        } else if (item instanceof Resource) {
            setDetails(resourceDetails, item);
        }
    }

    private void setDetails(DetailsPaneElement view, Item item) {
        view.setItem(item);
        view.setSize(160, 100);
        add(view);
    }
}
