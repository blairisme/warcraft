/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.components.status.details.building;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.object.control.Grid;
import com.evilbird.warcraft.data.resource.ResourceType;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.display.components.status.details.DetailsPaneStyle;
import com.evilbird.warcraft.object.unit.building.Building;

/**
 * Instances of this user interface show details about a Town Hall, including
 * the resources that the owning player has accumulated.
 *
 * @author Blair Butterworth
 */
public class CommandCentreDetailsPane extends Grid
{
    private Player player;
    private Label title;
    private Label gold;
    private Label lumber;
    private Label oil;
    private Skin skin;
    private DetailsPaneStyle style;

    public CommandCentreDetailsPane(Skin skin) {
        super(1, 4);
        setSkin(skin);
        setSize(160, 100);
        setCellSpacing(4);
        setCellWidth(160);
        setCellHeight(12);
    }

    public void setBuilding(Building building) {
        player = (Player)building.getParent();
        createView(player);
    }

    public void setResource(ResourceType resource, float value) {
        if (resource == ResourceType.Gold) {
            gold.setText(style.strings.getGold(value));
        }
        else if (resource == ResourceType.Wood) {
            lumber.setText(style.strings.getWood(value));
        }
        else if (resource == ResourceType.Oil) {
            oil.setText(style.strings.getOil(value));
        }
    }

    @Override
    public void setSkin(Skin skin) {
        super.setSkin(skin);
        this.skin = skin;
        this.style = skin.get(DetailsPaneStyle.class);
    }

    private void createView(Player player) {
        removeObjects();
        title = addLabel(getProduction(), skin);
        gold = addLabel(getGold(), skin);
        lumber = addLabel(getLumber(), skin);
        oil = addLabel(getOil(), skin);
    }

    private Label addLabel(String text, Skin skin) {
        Label result = new Label(text, skin);
        result.setSize(160, 12);
        result.setAlignment(Align.center);
        add(result);
        return result;
    }

    private String getProduction() {
        return style.strings.getProduction();
    }

    private String getGold() {
        float value = player.getResource(ResourceType.Gold);
        return style.strings.getGold(value);
    }

    private String getLumber() {
        float value = player.getResource(ResourceType.Wood);
        return style.strings.getWood(value);
    }

    private String getOil() {
        float value = player.getResource(ResourceType.Oil);
        return style.strings.getOil(value);
    }
}
