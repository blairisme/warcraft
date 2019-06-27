/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Fort extends Building
{
    private float attackSpeed;
    private int damageMinimum;
    private int damageMaximum;
    private int range;

    public Fort(Skin skin) {
        super(skin);
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    public int getDamageMinimum() {
        return damageMinimum;
    }

    public int getDamageMaximum() {
        return damageMaximum;
    }

    public int getRange() {
        return range;
    }

    public void setAttackSpeed(float attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public void setDamageMinimum(int damageMinimum) {
        this.damageMinimum = damageMinimum;
    }

    public void setDamageMaximum(int damageMaximum) {
        this.damageMaximum = damageMaximum;
    }

    public void setRange(int range) {
        this.range = range;
    }
}
