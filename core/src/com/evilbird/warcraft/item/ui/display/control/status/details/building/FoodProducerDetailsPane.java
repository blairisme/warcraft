/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.status.details.building;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.common.control.LabelProperty;
import com.evilbird.engine.item.specialized.Grid;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.data.player.PlayerStatistic;
import com.evilbird.warcraft.item.ui.display.control.status.details.DetailsPaneStyle;
import com.evilbird.warcraft.item.unit.building.Building;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Instances of this user interface show details about a farm.
 *
 * @author Blair Butterworth
 */
public class FoodProducerDetailsPane extends Grid
{
    private Player player;
    private LabelProperty grown;
    private LabelProperty used;
    private DetailsPaneStyle style;

    public FoodProducerDetailsPane(Skin skin) {
        super(1, 3);

        setSkin(skin);
        setSize(160, 100);
        setCellSpacing(4);
        setCellWidth(160);
        setCellHeight(12);

        addLabel(style.strings.getFoodUsage(), skin);
        grown = addLabel(skin, this::getGrown, this::getGrownLabel);
        used = addLabel(skin, this::getUsed, this::getUsedLabel);
    }

    public void setBuilding(Building building) {
        this.player = (Player)building.getParent();
    }

    @Override
    public void setSkin(Skin skin) {
        super.setSkin(skin);
        this.style = skin.get(DetailsPaneStyle.class);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        grown.evaluate();
        used.evaluate();
    }

    private LabelProperty addLabel(Skin skin, Supplier<Float> value, Function<Float, String> text) {
        Label label = addLabel("", skin);
        return new LabelProperty(label, value, text);
    }

    private Label addLabel(String text, Skin skin) {
        Label result = new Label(text, skin);
        result.setSize(160, 12);
        result.setAlignment(Align.center);
        add(result);
        return result;
    }

    private Float getGrown() {
        return (float)player.getStatistic(PlayerStatistic.Population);
    }

    private String getGrownLabel(Float value) {
        return style.strings.getFoodGrown(value);
    }

    private Float getUsed() {
        float total = player.getStatistic(PlayerStatistic.Population);
        float remaining = player.getResource(ResourceType.Food);
        return total - remaining;
    }

    private String getUsedLabel(Float value) {
        return style.strings.getFoodUsed(value);
    }
}
