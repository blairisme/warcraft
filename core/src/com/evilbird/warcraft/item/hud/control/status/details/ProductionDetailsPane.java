/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.status.details;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.item.specialized.GridItem;
import com.evilbird.warcraft.item.unit.building.Building;

/**
 * Instances of this user interface are shown when a building is producing
 * something, usually a unit or upgrade.
 *
 * @author Blair Butterworth
 */
public class ProductionDetailsPane extends GridItem
{
    private Building building;
    private Image productImage;
    private ProgressBar productionProgress;

    public ProductionDetailsPane(Skin skin) {
        super(1, 2);
        initialize(skin);
        productImage = addProductImage(skin);
        productionProgress = addProductionProgress(skin);
    }

    public void setBuilding(Building building) {
        this.building = building;
        this.productImage.setDrawable(building.getIcon()); //TODO: Wrong: need to show icon of thing being made
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        productionProgress.setValue(building.getProductionProgress());
    }

    private void initialize(Skin skin) {
        setSkin(skin);
        setSize(160, 100);
        setCellSpacing(8);
        setAlignment(Alignment.Bottom);
    }

    private Image addProductImage(Skin skin) {
        GridItem container = new GridItem(2, 1);
        add(container);

        Label label = new Label("Training: ", skin);
        Cell cell = container.add(label);
        cell.grow();

        Image image = new Image();
        container.add(image);
        return image;
    }

    private ProgressBar addProductionProgress(Skin skin) {
        Stack container = new Stack();
        add(container);

        ProgressBar progressBar = new ProgressBar(0, 1, 0.005f, false, skin, "building-progress");
        container.add(progressBar);

        Label label = new Label("% Complete", skin);
        label.setAlignment(Align.center);
        container.add(label);

        return progressBar;
    }
}
