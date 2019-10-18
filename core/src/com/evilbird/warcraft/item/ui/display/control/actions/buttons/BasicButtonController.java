/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.actions.buttons;

import com.evilbird.warcraft.item.common.production.ProductionCosts;
import com.evilbird.warcraft.item.common.resource.ResourceQuantity;
import com.evilbird.warcraft.item.common.upgrade.Upgrade;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.unit.UnitType;

import java.util.List;

import static com.evilbird.warcraft.item.common.query.UnitOperations.hasUpgrade;

/**
 * A basic {@link ButtonController} implementation containing common methods
 * for determining button enablement.
 *
 * @author Blair Butterworth
 */
public abstract class BasicButtonController implements ButtonController
{
    private ProductionCosts productionCosts;

    public BasicButtonController() {
        productionCosts = new ProductionCosts();
    }

    protected void addUpgradeButton(
        Player player,
        List<ActionButtonType> buttons,
        ActionButtonType button,
        Upgrade upgrade)
    {
        if (!hasUpgrade(player, upgrade)) {
            buttons.add(button);
        }
    }

    protected void addUpgradeButton(
        Player player,
        List<ActionButtonType> buttons,
        ActionButtonType button,
        Upgrade basicUpgrade,
        Upgrade advancedUpgrade)
    {
        if (hasUpgrade(player, basicUpgrade) && !hasUpgrade(player, advancedUpgrade)) {
            buttons.add(button);
        }
    }

    protected boolean hasResources(Player player, UnitType type) {
        for (ResourceQuantity cost: productionCosts.costOf(type)) {
            if (player.getResource(cost.getType()) < cost.getValue()){
                return false;
            }
        }
        return true;
    }

    protected boolean hasResources(Player player, Upgrade upgrade) {
        for (ResourceQuantity cost: productionCosts.costOf(upgrade)) {
            if (player.getResource(cost.getType()) < cost.getValue()){
                return false;
            }
        }
        return true;
    }
}
