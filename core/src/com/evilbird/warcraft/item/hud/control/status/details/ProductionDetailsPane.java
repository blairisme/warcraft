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
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.control.StyledLabel;
import com.evilbird.engine.item.specialized.GridItem;
import com.evilbird.warcraft.item.unit.building.Building;

/**
 * Instances of this user interface are shown when a building is producing
 * something, usually a unit or upgrade.
 *
 * @author Blair Butterworth
 */
//TODO: Show tile with icon and name of thing being produced
public class ProductionDetailsPane extends GridItem
{
    private Building building;
    private ProgressBar progressBar;

    public ProductionDetailsPane(Skin skin) {
        super(1, 1);
        progressBar = new ProgressBar(0, 100, 1, false, skin, "building-progress");

        Label training = createLabel("Training");

        setSize(160, 100);
        setHorizontalCellPadding(4);
        setVerticalCellPadding(80);
        add(progressBar);
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    @Override
    public void update(float delta) {
        progressBar.setValue(building.getProgress());
        super.update(delta);
    }

    private Label createLabel(String text) {
        Label result = new StyledLabel(text);
        result.setSize(160, 12);
        result.setAlignment(Align.center);
        result.setColor(Color.WHITE);
        return result;
    }
}
