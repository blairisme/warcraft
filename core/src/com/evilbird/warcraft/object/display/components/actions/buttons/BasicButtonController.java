/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.components.actions.buttons;

import com.evilbird.engine.game.GameService;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.data.resource.ResourceQuantity;
import com.evilbird.warcraft.data.resource.ResourceSet;
import com.evilbird.warcraft.data.spell.Spell;
import com.evilbird.warcraft.data.upgrade.Upgrade;
import com.evilbird.warcraft.data.upgrade.UpgradeProduction;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.display.components.actions.ActionButtonType;
import com.evilbird.warcraft.object.unit.UnitProduction;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.spellcaster.SpellCaster;

import java.util.List;

import static com.evilbird.warcraft.data.resource.ResourceSet.EmptyResourceSet;

/**
 * A basic {@link ButtonController} implementation containing common methods
 * for determining button enablement.
 *
 * @author Blair Butterworth
 */
public abstract class BasicButtonController implements ButtonController
{
    private WarcraftPreferences preferences;

    public BasicButtonController() {
        preferences = (WarcraftPreferences)GameService.getInstance().getPreferences();
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
        for (ResourceQuantity cost: getProductionCost(type)) {
            if (player.getResource(cost.getType()) < cost.getValue()){
                return false;
            }
        }
        return true;
    }

    protected boolean hasResources(Player player, Upgrade upgrade) {
        for (ResourceQuantity cost: getProductionCost(upgrade)) {
            if (player.getResource(cost.getType()) < cost.getValue()){
                return false;
            }
        }
        return true;
    }

    protected boolean hasMana(SpellCaster caster, Spell spell) {
        return caster.getMana() >= spell.getCastCost();
    }

    private ResourceSet getProductionCost(UnitType product) {
        if (!preferences.isBuildCostCheatEnabled()) {
            UnitProduction production = UnitProduction.forProduct(product);
            return production.getCost();
        }
        return EmptyResourceSet;
    }

    private ResourceSet getProductionCost(Upgrade product) {
        if (!preferences.isBuildCostCheatEnabled()) {
            UpgradeProduction production = UpgradeProduction.forProduct(product);
            return production.getCost();
        }
        return EmptyResourceSet;
    }
}
