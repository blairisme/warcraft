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
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.item.specialized.GridItem;
import com.evilbird.warcraft.item.ui.display.control.status.details.DetailsPaneStyle;
import com.evilbird.warcraft.item.unit.building.Building;

import static com.evilbird.engine.common.lang.Alignment.Bottom;

/**
 * Instances of this user interface are shown when a building is under
 * construction.
 *
 * @author Blair Butterworth
 */
public class ConstructionDetailsPane extends GridItem
{
    private Building building;
    private ProgressBar progress;

    public ConstructionDetailsPane(Skin skin) {
        super(1, 1);
        setSkin(skin);
        setSize(160, 100);
        setCellSpacing(8);
        setAlignment(Bottom);
        addProgress(skin);
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    @Override
    public void update(float delta) {
        progress.setValue(building.getConstructionProgress());
        super.update(delta);
    }

    private void addProgress(Skin skin) {
        Stack container = new Stack();
        add(container);

        progress = new ProgressBar(0, 1, 0.005f, false, skin, "building-progress");
        container.add(progress);

        DetailsPaneStyle style = skin.get(DetailsPaneStyle.class);
        Label label = new Label(style.strings.getProgress(), skin);
        label.setAlignment(Align.center);
        container.add(label);
    }
}
