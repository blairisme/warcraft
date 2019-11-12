/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.display.control.common;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.object.control.Grid;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.UnitType;

/**
 * A user interface control that displays a tile containing the icon of a
 * {@link Unit} and a health displaying the units remaining health.
 *
 * @author Blair Butterworth
 */
public class UnitPane extends Grid
{
    private Unit unit;
    private Image icon;
    private HealthBar health;

    public UnitPane(Skin skin) {
        super(1, 2);
        initialize(skin);
        icon = addIcon(skin);
        health = addHealth(skin);
    }

    public void setItem(Unit unit) {
        this.unit = unit;

        this.health.setRange(0, unit.getHealthMaximum());
        this.health.setValue(unit.getHealth());

        setIcon((UnitType)unit.getType());
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        this.health.setValue(unit.getHealth());
    }

    private void initialize(Skin skin) {
        setSkin(skin);
        setSize(54, 52);
        setCellPadding(2);
        setCellSpacing(-2);
        setTouchable(Touchable.enabled);
        setBackground();
    }

    private Image addIcon(Skin skin) {
        Image icon = new Image();
        icon.setSize(50, 42);

        Cell cell = add(icon);
        cell.width(50);
        cell.height(42);

        return icon;
    }

    private HealthBar addHealth(Skin skin) {
        HealthBar health = new HealthBar(0, 1, skin);
        health.setSize(46, 5);

        Cell cell = add(health);
        cell.width(46);
        cell.height(5);

        return health;
    }

    private UnitPaneStyle getStyle() {
        Skin skin = getSkin();
        return skin.get("default", UnitPaneStyle.class);
    }

    private void setBackground() {
        UnitPaneStyle style = getStyle();
        setBackground(style.background);
    }

    private void setIcon(UnitType type) {
        UnitPaneStyle style = getStyle();
        if (style != null && style.icons != null) {
            this.icon.setDrawable(style.icons.get(type));
        }
    }
}
