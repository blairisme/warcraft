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
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.item.control.GridPane;
import com.evilbird.engine.item.control.Image;
import com.evilbird.warcraft.item.hud.HudControls;
import com.evilbird.warcraft.item.unit.Unit;

/**
 * Instances of this user interface control display an tile containing a units
 * icon.
 *
 * @author Blair Butterworth
 */
public class UnitPane extends GridPane
{
    private Image icon;
    private HealthBar healthBar;
    private Unit unit;
    private float unitHealth;

    public UnitPane(HealthBarProvider healthBarProvider) {
        super(1, 2);

        icon = new Image();
        icon.setSize(50, 42);
        icon.setPadding(2);

        healthBar = healthBarProvider.get();
        healthBar.setProgress(1);
        healthBar.setSize(46, 5);

        setSize(54, 53);
        setCellPadding(2);
        setCellSpacing(2);
        setCell(icon, 0, 0);
        setCell(healthBar, 0, 1);
        setType(HudControls.UnitPane);
        setTouchable(Touchable.enabled);
    }

    public void setItem(Unit unit) {
        this.unit = unit;
        this.unitHealth = 0;
        updateImage();
        updateHealth();
    }

    private void updateImage() {
        Drawable image = unit.getIcon();
        icon.setImage(image);
    }

    private void updateHealth() {
        float currentHealth = unit.getHealth();
        if (currentHealth != unitHealth) {
            unitHealth = currentHealth;
            float healthMaximum = unit.getHealthMaximum();
            float healthPercent = currentHealth != 0 && healthMaximum != 0 ? currentHealth / healthMaximum : 0f;
            healthBar.setProgress(healthPercent);
        }
    }

    @Override
    public void update(float delta) {
        updateHealth();
        super.update(delta);
    }
}
