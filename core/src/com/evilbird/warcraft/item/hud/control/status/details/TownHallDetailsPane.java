/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.status.details;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.control.LabelProperty;
import com.evilbird.engine.item.specialized.GridItem;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.unit.building.Building;
import com.evilbird.warcraft.item.unit.resource.ResourceType;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Instances of this user interface show details about a Town Hall, including
 * the resources that the owning player has accumulated.
 *
 * @author Blair Butterworth
 */
public class TownHallDetailsPane extends GridItem
{
    private Player player;
    private LabelProperty gold;
    private LabelProperty lumber;
    private LabelProperty oil;

    public TownHallDetailsPane(Skin skin) {
        super(1, 4);

        setSize(160, 100);
        setCellSpacing(4);
        setCellWidth(160);
        setCellHeight(12);

        addLabel("Production", skin);
        gold = addLabel(skin, this::getGoldValue, this::getGoldLabel);
        lumber = addLabel(skin, this::getLumberValue, this::getLumberLabel);
        oil = addLabel(skin, this::getOilValue, this::getOilLabel);
    }

    public void setBuilding(Building building) {
        this.player = (Player)building.getParent();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        gold.evaluate();
        lumber.evaluate();
        oil.evaluate();
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

    private Float getGoldValue() {
        return player.getResource(ResourceType.Gold);
    }

    private String getGoldLabel(Float value) {
        return "Gold: " + Math.round(value);
    }

    private Float getLumberValue() {
        return player.getResource(ResourceType.Wood);
    }

    private String getLumberLabel(Float value) {
        return "Lumber: " + Math.round(value);
    }

    private Float getOilValue() {
        return player.getResource(ResourceType.Oil);
    }

    private String getOilLabel(Float value) {
        return "Oil: " + Math.round(value);
    }
}
