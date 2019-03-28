/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.status.details;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.control.StyledLabel;
import com.evilbird.engine.item.specialized.GridItem;
import com.evilbird.warcraft.item.unit.building.Building;

/**
 * Instances of this user interface show details about a farm.
 *
 * @author Blair Butterworth
 */
public class FarmDetailsPane extends GridItem
{
    private Building building;
    private Label grown;
    private Label used;

    public FarmDetailsPane(Skin skin) {
        super(1, 3);

        setSize(160, 100);
        setCellSpacing(4);
        setCellWidth(160);
        setCellHeight(12);

        grown = createLabel("Grown");
        used = createLabel("Used");

        add(createLabel("Food Usage"));
        add(grown);
        add(used);
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    private String getText(String prefix, float suffix) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(prefix);
        stringBuilder.append(": ");
        stringBuilder.append(Math.round(suffix));
        return stringBuilder.toString();
    }

    private Label createLabel(String text) {
        StyledLabel result = new StyledLabel(text);
        result.setSize(160, 12);
        result.setAlignment(Align.center);
        result.setFontColour(Color.WHITE);
        return result;
    }
}
