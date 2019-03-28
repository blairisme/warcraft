/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.status.details;

import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.item.specialized.GridItem;
import com.evilbird.warcraft.item.unit.building.Building;

/**
 * Instances of this user interface are shown when a building is under
 * construction.
 *
 * @author Blair Butterworth
 */
public class ConstructionDetailsPane extends GridItem
{
    private Building building;
    private ProgressBar progressBar;

    public ConstructionDetailsPane(Skin skin) {
        super(1, 1);
        progressBar = new ProgressBar(0, 100, 1, false, skin, "building-progress");

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
}
