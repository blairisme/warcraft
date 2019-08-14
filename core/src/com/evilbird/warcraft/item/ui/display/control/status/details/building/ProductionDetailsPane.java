/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.status.details.building;

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
import com.evilbird.warcraft.action.produce.ProduceUnitActions;
import com.evilbird.warcraft.action.produce.ProduceUpgradeActions;
import com.evilbird.warcraft.item.ui.display.control.status.details.DetailsPaneStyle;
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
    private Label productName;
    private Image productImage;
    private ProgressBar productProgress;
    private DetailsPaneStyle style;

    public ProductionDetailsPane(Skin skin) {
        super(1, 2);
        setSkin(skin);
        setSize(160, 100);
        setCellSpacing(8);
        setAlignment(Alignment.Bottom);
        addProductDetails(skin);
        addProductionProgress(skin);
    }

    public void setBuilding(Building building) {
        this.building = building;
        this.productName.setText(style.strings.getTraining(building));
        this.productImage.setDrawable(getProductImage(building));
    }

    @Override
    public void setSkin(Skin skin) {
        super.setSkin(skin);
        this.style = skin.get(DetailsPaneStyle.class);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        productProgress.setValue(building.getProductionProgress());
    }

    private void addProductDetails(Skin skin) {
        GridItem container = new GridItem(2, 1);
        add(container);

        addProductLabel(skin, container);
        addProductImage(skin, container);
    }

    private void addProductLabel(Skin skin, GridItem container) {
        productName = new Label("", skin);
        Cell cell = container.add(productName);
        cell.grow();
    }

    private void addProductImage(Skin skin, GridItem container) {
        DetailsPaneStyle style = skin.get(DetailsPaneStyle.class);

        Image background = new Image();
        background.setDrawable(style.productionBackground);

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
            if (identifier instanceof ProduceUnitActions) {
                return getProductImage((ProduceUnitActions)identifier);
            }
            if (identifier instanceof ProduceUpgradeActions) {
                return getProductImage((ProduceUpgradeActions)identifier);
            }
        }
        return null;
    }

    private Drawable getProductImage(ProduceUnitActions action) {
        DetailsPaneStyle style = getSkin().get(DetailsPaneStyle.class);
        return style.icons.get(action.getProduct());
    }

    private Drawable getProductImage(ProduceUpgradeActions action) {
        DetailsPaneStyle style = getSkin().get(DetailsPaneStyle.class);
        return style.icons.get(action.getProduct(), building);
    }

    private void addProductionProgress(Skin skin) {
        Stack container = new Stack();
        add(container);

        productProgress = new ProgressBar(0, 1, 0.005f, false, skin, "building-progress");
        container.add(productProgress);

        Label label = new Label(style.strings.getProgress(), skin);
        label.setAlignment(Align.center);
        container.add(label);
    }
}
