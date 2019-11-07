/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.status.details;

import com.badlogic.gdx.utils.I18NBundle;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.UnitType;

/**
 * A language specific detailsBundle of strings used for a units details pane.
 *
 * @author Blair Butterworth
 */
public class DetailsPaneStrings
{
    private I18NBundle detailsBundle;
    private I18NBundle namesBundle;

    public DetailsPaneStrings(I18NBundle detailsBundle, I18NBundle namesBundle) {
        this.detailsBundle = detailsBundle;
        this.namesBundle = namesBundle;
    }

    public String getName(Unit unit) {
        return getName((UnitType)unit.getType());
    }

    public String getName(UnitType type) {
        return namesBundle.format(type.name());
    }

    public String getGold(float gold) {
        return detailsBundle.format("gold", Math.round(gold));
    }

    public String getGoldRemaining(float gold) {
        return detailsBundle.format("gold_remaining", Math.round(gold));
    }

    public String getOil(float oil) {
        return detailsBundle.format("oil", Math.round(oil));
    }

    public String getOilRemaining(float oil) {
        return detailsBundle.format("oil_remaining", Math.round(oil));
    }

    public String getWood(float wood) {
        return detailsBundle.format("wood", Math.round(wood));
    }

    public String getFoodUsage() {
        return detailsBundle.format("food_usage");
    }

    public String getFoodGrown(float food) {
        return detailsBundle.format("food_grown", Math.round(food));
    }

    public String getFoodUsed(float food) {
        return detailsBundle.format("food_used", Math.round(food));
    }

    public String getProduction() {
        return detailsBundle.format("production");
    }

    public String getTraining() {
        return detailsBundle.get("training");
    }

    public String getUpgrading() {
        return detailsBundle.get("upgrading");
    }

    public String getProgress() {
        return detailsBundle.format("progress");
    }

    public String getLevel(int level) {
        return detailsBundle.format("level", level);
    }

    public String getArmour(int armour, int armourUpgrade) {
        if (armourUpgrade == 0) {
            return detailsBundle.format("armour", armour);
        } else {
            return detailsBundle.format("armour-upgraded", armour, armourUpgrade);
        }
    }

    public String getDamage(int damageMin, int damageMax, int damageUpgrade) {
        if (damageUpgrade == 0) {
            return detailsBundle.format("damage", damageMin, damageMax);
        } else {
            return detailsBundle.format("damage-upgraded", damageMin, damageMax, damageUpgrade);
        }
    }

    public String getRange(int range) {
        return detailsBundle.format("range", range);
    }

    public String getSight(int sight) {
        return detailsBundle.format("sight", sight);
    }

    public String getSpeed(int speed) {
        return detailsBundle.format("speed", speed);
    }

    public String getMagic() {
        return detailsBundle.format("magic");
    }
}