/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.status.details.building;

import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.specialized.GridItem;
import com.evilbird.warcraft.action.train.TrainActions;
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
    private ProgressBar productProgress;

    public ProductionDetailsPane(Skin skin) {
        super(1, 2);
        initialize(skin);
        addProductDetails(skin);
        productProgress = addProductionProgress(skin);
    }

    public void setBuilding(Building building) {
        this.building = building;
        this.productImage.setDrawable(getProductImage(building));
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        productProgress.setValue(building.getProductionProgress());
    }

    private void initialize(Skin skin) {
        setSkin(skin);
        setSize(160, 100);
        setCellSpacing(8);
        setAlignment(Alignment.Bottom);
    }

    private void addProductDetails(Skin skin) {
        GridItem container = new GridItem(2, 1);
        add(container);

        addProductLabel(skin, container);
        addProductImage(skin, container);
    }

    private void addProductLabel(Skin skin, GridItem container) {
        Label label = new Label("Training: ", skin);
        Cell cell = container.add(label);
        cell.grow();
    }

    private void addProductImage(Skin skin, GridItem container) {
        ProductionDetailsStyle style = skin.get("default", ProductionDetailsStyle.class);

        Image background = new Image();
        background.setDrawable(style.trainBackground);

        productImage = new Image();
        productImage.setScaling(Scaling.none);

        Stack stack = new Stack();
        stack.add(background);
        stack.add(productImage);
        container.add(stack);
    }

    private Drawable getProductImage(Building building) {
        for (Action action: building.getActions()) {
            Identifier identifier = action.getIdentifier();
            if (identifier instanceof TrainActions) {
                return getProductImage((TrainActions)identifier);
            }
        }
        return null;
    }

    private Drawable getProductImage(TrainActions trainAction) {
        ProductionDetailsStyle style = getSkin().get(ProductionDetailsStyle.class);
        switch (trainAction) {
            case TrainFootman: return style.trainFootmanIcon;
            case TrainPeasant: return style.trainPeasantIcon;
            default: return null;
        }
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
