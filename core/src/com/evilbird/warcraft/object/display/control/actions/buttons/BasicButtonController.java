/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.control.actions.buttons;

import com.evilbird.warcraft.data.resource.ResourceQuantity;
import com.evilbird.warcraft.data.spell.Spell;
import com.evilbird.warcraft.data.upgrade.Upgrade;
import com.evilbird.warcraft.object.common.production.ProductionCosts;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.spellcaster.SpellCaster;

import java.util.List;

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
        if (!player.hasUpgrade(upgrade)) {
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
        if (player.hasUpgrade(basicUpgrade) && !player.hasUpgrade(advancedUpgrade)) {
            buttons.add(button);
        }
    }

    protected void addUpgradeDependentButton(
        Player player,
        List<ActionButtonType> buttons,
        ActionButtonType button,
        Upgrade dependentUpgrade)
    {
        if (player.hasUpgrade(dependentUpgrade)) {
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

    protected boolean hasMana(SpellCaster caster, Spell spell) {
        return caster.getMana() >= spell.getCastCost();
    }
}
