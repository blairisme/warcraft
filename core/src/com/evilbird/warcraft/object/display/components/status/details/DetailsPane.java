/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.components.status.details;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.control.Grid;
import com.evilbird.warcraft.data.resource.ResourceType;
import com.evilbird.warcraft.object.display.components.status.details.building.BuildingDetailsPane;
import com.evilbird.warcraft.object.display.components.status.details.combatant.CombatantDetailsPane;
import com.evilbird.warcraft.object.display.components.status.details.combatant.CombatantTitlePane;
import com.evilbird.warcraft.object.display.components.status.details.combatant.SpellCasterDetailsPane;
import com.evilbird.warcraft.object.display.components.status.details.common.UnitTitlePane;
import com.evilbird.warcraft.object.display.components.status.details.resource.ResourceDetailsPane;
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
        setCellSpacing(6);
        setHorizontalCellPadding(10);
        setVerticalCellPadding(0);
        setTouchable(Touchable.disabled);
    }

    @Override
    public void setSkin(Skin skin) {
        super.setSkin(skin);
        DetailsPaneStyle style = skin.get(DetailsPaneStyle.class);
        setBackground(style.background);
    }

    public void setConstructing(Building building, boolean constructing) {
        if (contains(buildingDetails)) {
            buildingDetails.setConstructing(building, constructing);
        }
    }

    public void setProducing(Building building, boolean producing) {
        if (contains(buildingDetails)) {
            buildingDetails.setProducing(building, producing);
        }
    }

    public void setResource(ResourceType resource, float value) {
        if (contains(buildingDetails)) {
            buildingDetails.setResource(resource, value);
        }
    }

    public void setResource(GameObject gameObject, ResourceType resource, float value) {
        if (contains(resourceDetails)) {
            resourceDetails.setResource(gameObject, resource, value);
        }
    }

    public void setItem(GameObject gameObject) {
        removeObjects();
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
        Cell<Actor> cell = add(view);
        cell.expandX();
        cell.fillX();
        cell.padTop(10);
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
