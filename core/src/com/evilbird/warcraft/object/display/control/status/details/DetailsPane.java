/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.control.status.details;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.control.Grid;
import com.evilbird.warcraft.object.common.resource.ResourceType;
import com.evilbird.warcraft.object.display.control.status.details.building.BuildingDetailsPane;
import com.evilbird.warcraft.object.display.control.status.details.combatant.CombatantDetailsPane;
import com.evilbird.warcraft.object.display.control.status.details.combatant.CombatantTitlePane;
import com.evilbird.warcraft.object.display.control.status.details.combatant.SpellCasterDetailsPane;
import com.evilbird.warcraft.object.display.control.status.details.common.UnitTitlePane;
import com.evilbird.warcraft.object.display.control.status.details.resource.ResourceDetailsPane;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.building.Building;
import com.evilbird.warcraft.object.unit.building.OilPlatform;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import com.evilbird.warcraft.object.unit.combatant.spellcaster.SpellCaster;
import com.evilbird.warcraft.object.unit.resource.Resource;

import static com.evilbird.warcraft.object.common.query.UnitOperations.isCorporeal;
import static com.evilbird.warcraft.object.common.query.UnitOperations.isResource;

/**
 * Represents a user interface control that displays the icon and name of an
 * {@link GameObject}. For some Items a selection of pertinent details is also
 * shown. For example the gold remaining in a gold mine or the attack strength
 * of a footman.
 *
 * @author Blair Butterworth
 */
public class DetailsPane extends Grid
{
    private UnitTitlePane generalTitle;
    private CombatantTitlePane combatantTitle;
    private BuildingDetailsPane buildingDetails;
    private CombatantDetailsPane combatantDetails;
    private SpellCasterDetailsPane spellCasterDetails;
    private ResourceDetailsPane resourceDetails;

    public DetailsPane(Skin skin) {
        super(1, 2);

        generalTitle = new UnitTitlePane(skin);
        combatantTitle = new CombatantTitlePane(skin);
        buildingDetails = new BuildingDetailsPane(skin);
        combatantDetails = new CombatantDetailsPane(skin);
        spellCasterDetails = new SpellCasterDetailsPane(skin);
        resourceDetails = new ResourceDetailsPane(skin);

        setSkin(skin);
        setSize(176, 176);
        setCellPadding(8);
        setTouchable(Touchable.disabled);
    }

    @Override
    public void setSkin(Skin skin) {
        super.setSkin(skin);
        DetailsPaneStyle style = skin.get(DetailsPaneStyle.class);
        setBackground(style.background);
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

    public void setResource(ResourceType resource, float value) {
        if (isShown(buildingDetails)) {
            buildingDetails.setResource(resource, value);
        }
    }

    public void setResource(GameObject gameObject, ResourceType resource, float value) {
        if (isShown(resourceDetails)) {
            resourceDetails.setResource(gameObject, resource, value);
        }
    }

    public void setItem(GameObject gameObject) {
        clearObjects();
        showDetails(gameObject);
    }

    private void showDetails(GameObject gameObject) {
        setTitle(gameObject);
        if (isCorporeal(gameObject) || isResource(gameObject)) {
            setDetails(gameObject);
        }
    }

    private void setTitle(GameObject gameObject) {
        if (gameObject instanceof Combatant) {
            setTitle(combatantTitle, gameObject);
        } else if (gameObject instanceof Unit) {
            setTitle(generalTitle, gameObject);
        }
    }

    private void setTitle(DetailsPaneElement view, GameObject gameObject) {
        view.setItem(gameObject);
        Cell cell = add(view);
        cell.expandX();
        cell.fillX();
    }

    private void setDetails(GameObject gameObject) {
        if (gameObject instanceof Resource || gameObject instanceof OilPlatform) {
            setDetails(resourceDetails, gameObject);
        } else if (gameObject instanceof Building) {
            setDetails(buildingDetails, gameObject);
        } else if (gameObject instanceof SpellCaster) {
            setDetails(spellCasterDetails, gameObject);
        } else if (gameObject instanceof Combatant) {
            setDetails(combatantDetails, gameObject);
        }
    }

    private void setDetails(DetailsPaneElement view, GameObject gameObject) {
        view.setItem(gameObject);
        view.setSize(160, 100);
        add(view);
    }
}
