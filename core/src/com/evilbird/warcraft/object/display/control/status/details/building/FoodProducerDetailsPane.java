/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.control.status.details.building;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.object.control.Grid;
import com.evilbird.warcraft.data.resource.ResourceType;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.data.player.PlayerStatistic;
import com.evilbird.warcraft.object.display.control.status.details.DetailsPaneStyle;
import com.evilbird.warcraft.object.unit.building.Building;

/**
 * Instances of this user interface show details about a farm.
 *
 * @author Blair Butterworth
 */
public class FoodProducerDetailsPane extends Grid
{
    private Player player;
    private Label title;
    private Label grown;
    private Label used;
    private Skin skin;
    private DetailsPaneStyle style;

    public FoodProducerDetailsPane(Skin skin) {
        super(1, 3);
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
        if (resource == ResourceType.Food) {
            updateView(player);
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
        title = addLabel(getFoodUsage(), skin);
        grown = addLabel(getFoodGrown(player), skin);
        used = addLabel(getFoodUsed(player), skin);
    }

    private void updateView(Player player) {
        grown.setText(getFoodGrown(player));
        used.setText(getFoodUsed(player));
    }

    private Label addLabel(String text, Skin skin) {
        Label result = new Label(text, skin);
        result.setSize(160, 12);
        result.setAlignment(Align.center);
        add(result);
        return result;
    }

    private String getFoodUsage() {
        return style.strings.getFoodUsage();
    }

    private String getFoodGrown(Player player) {
        int grown = player.getStatistic(PlayerStatistic.Population);
        return style.strings.getFoodGrown(grown);
    }

    private String getFoodUsed(Player player) {
        int total = player.getStatistic(PlayerStatistic.Population);
        int remaining = (int)player.getResource(ResourceType.Food);
        int used =  total - remaining;
        return style.strings.getFoodUsed(used);
    }
}
