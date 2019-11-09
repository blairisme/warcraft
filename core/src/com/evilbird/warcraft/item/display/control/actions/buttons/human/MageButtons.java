/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.display.control.actions.buttons.human;

import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.display.control.actions.buttons.BasicButtonController;
import com.evilbird.warcraft.item.unit.combatant.SpellCaster;

import java.util.ArrayList;
import java.util.List;

import static com.evilbird.warcraft.item.common.spell.Spell.Blizzard;
import static com.evilbird.warcraft.item.common.spell.Spell.FlameShield;
import static com.evilbird.warcraft.item.common.spell.Spell.Invisibility;
import static com.evilbird.warcraft.item.common.spell.Spell.Polymorph;
import static com.evilbird.warcraft.item.common.spell.Spell.Slow;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.AttackButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.BlizzardButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.CancelButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.FireballButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.FlameShieldButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.InvisibilityButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.MoveButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.PolymorphButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.SlowButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.StopButton;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/**
 * Controls the buttons shown when a Human Mage is selected.
 *
 * @author Blair Butterworth
 */
public class MageButtons extends BasicButtonController
{
//    private static final List<ActionButtonType> BASIC_BUTTONS =
//            asList(MoveButton, StopButton, AttackButton, FireballButton);

    private static final List<ActionButtonType> BASIC_BUTTONS =
        asList(MoveButton, StopButton, AttackButton,
                FireballButton, SlowButton, FlameShieldButton,
                InvisibilityButton, PolymorphButton, BlizzardButton);

    private static final List<ActionButtonType> CASTING_BUTTONS =
        singletonList(CancelButton);

    @Override
    public List<ActionButtonType> getButtons(Item item) {
        SpellCaster mage = (SpellCaster)item;
        if (mage.getCastingSpell() != null) {
            return CASTING_BUTTONS;
        }
        return getActionButtons(item);
    }

    private List<ActionButtonType> getActionButtons(Item item) {
        Player player = UnitOperations.getPlayer(item);
        List<ActionButtonType> buttons = new ArrayList<>(BASIC_BUTTONS);
//        addUpgradeButton(player, buttons, SlowButton, SlowUpgrade);
//        addUpgradeButton(player, buttons, FlameShieldButton, FlameShieldUpgrade);
//        addUpgradeButton(player, buttons, InvisibilityButton, InvisibilityUpgrade);
//        addUpgradeButton(player, buttons, PolymorphButton, PolymorphUpgrade);
//        addUpgradeButton(player, buttons, BlizzardButton, BlizzardUpgrade);
        return buttons;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, Item item) {
        SpellCaster mage = (SpellCaster)item;
        switch (button) {
            case BlizzardButton: return hasMana(mage, Blizzard);
//            case FireballButton: return hasMana(mage, Fireball);
            case FlameShieldButton: return hasMana(mage, FlameShield);
            case InvisibilityButton: return hasMana(mage, Invisibility);
            case PolymorphButton: return hasMana(mage, Polymorph);
            case SlowButton: return hasMana(mage, Slow);
            default: return true;
        }
    }
}
