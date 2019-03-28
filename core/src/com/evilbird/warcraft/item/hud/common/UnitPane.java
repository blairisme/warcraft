/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.common;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.item.specialized.GridItem;
import com.evilbird.warcraft.item.hud.HudControl;
import com.evilbird.warcraft.item.unit.Unit;

/**
 * A user interface control that displays a tile containing the icon of a
 * {@link Unit} and a health displaying the units remaining health.
 *
 * @author Blair Butterworth
 */
public class UnitPane extends GridItem
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
        this.icon.setDrawable(unit.getIcon());
        this.health.setRange(0, unit.getHealthMaximum());
        this.health.setValue(unit.getHealth());
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
        setBackground("unit-panel");
        setType(HudControl.UnitPane);
        setTouchable(Touchable.enabled);
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
}
