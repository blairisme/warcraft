/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.display.control.actions.buttons.orc;

import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.display.control.actions.buttons.BasicButtonController;
import com.evilbird.warcraft.item.unit.combatant.SpellCaster;

import java.util.ArrayList;
import java.util.List;

import static com.evilbird.warcraft.item.common.spell.Spell.DeathAndDecay;
import static com.evilbird.warcraft.item.common.spell.Spell.Whirlwind;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.AttackButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.CancelButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.DeathAndDecayButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.MoveButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.StopButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.WhirlwindButton;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/**
 * Controls the buttons shown when an Orcish Death Knight is selected.
 *
 * @author Blair Butterworth
 */
public class DeathKnightButtons extends BasicButtonController
{
    private static final List<ActionButtonType> BASIC_BUTTONS =
        asList(MoveButton, StopButton, AttackButton, DeathAndDecayButton, WhirlwindButton);

    private static final List<ActionButtonType> CASTING_BUTTONS =
        singletonList(CancelButton);

    @Override
    public List<ActionButtonType> getButtons(Item item) {
        SpellCaster caster = (SpellCaster)item;
        if (caster.getCastingSpell() != null) {
            return CASTING_BUTTONS;
        }
        return getActionButtons(item);
    }

    private List<ActionButtonType> getActionButtons(Item item) {
        Player player = UnitOperations.getPlayer(item);
        List<ActionButtonType> buttons = new ArrayList<>(BASIC_BUTTONS);
//        addUpgradeButton(player, buttons, ExorcismButton, ExorcismUpgrade);
//        addUpgradeButton(player, buttons, HealButton, HealingUpgrade);
        return buttons;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, Item item) {
        SpellCaster caster = (SpellCaster)item;
        switch (button) {
            case DeathAndDecayButton: return hasMana(caster, DeathAndDecay);
            case WhirlwindButton: return hasMana(caster, Whirlwind);
            default: return true;
        }
    }
}
