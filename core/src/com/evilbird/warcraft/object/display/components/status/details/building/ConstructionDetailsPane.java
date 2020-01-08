/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.components.status.details.building;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.object.control.Grid;
import com.evilbird.warcraft.object.display.components.status.details.DetailsPaneStyle;
import com.evilbird.warcraft.object.unit.building.Building;

import static com.evilbird.engine.common.lang.Alignment.Bottom;

/**
 * Instances of this user interface are shown when a building is under
 * construction.
 *
 * @author Blair Butterworth
 */
public class ConstructionDetailsPane extends Grid
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
