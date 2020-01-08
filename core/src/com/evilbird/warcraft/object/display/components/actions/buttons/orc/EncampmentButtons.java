/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.components.actions.buttons.orc;

import com.evilbird.engine.common.collection.Lists;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.display.components.actions.ActionButtonType;
import com.evilbird.warcraft.object.display.components.actions.buttons.BasicButtonController;

import java.util.ArrayList;
import java.util.List;

import static com.evilbird.warcraft.data.upgrade.Upgrade.MeleeType1;
import static com.evilbird.warcraft.data.upgrade.Upgrade.RangedType1;
import static com.evilbird.warcraft.object.common.query.UnitOperations.hasUnit;
import static com.evilbird.warcraft.object.common.query.UnitOperations.hasUnits;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.CatapultButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.GruntButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.OgreButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.OgreMageButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.TrollAxethrowerButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.TrollBerserkerButton;
import static com.evilbird.warcraft.object.unit.UnitType.Catapult;
import static com.evilbird.warcraft.object.unit.UnitType.Forge;
import static com.evilbird.warcraft.object.unit.UnitType.Grunt;
import static com.evilbird.warcraft.object.unit.UnitType.Ogre;
import static com.evilbird.warcraft.object.unit.UnitType.OgreMage;
import static com.evilbird.warcraft.object.unit.UnitType.OgreMound;
import static com.evilbird.warcraft.object.unit.UnitType.TrollAxethrower;
import static com.evilbird.warcraft.object.unit.UnitType.TrollBerserker;
import static com.evilbird.warcraft.object.unit.UnitType.TrollLumberMill;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/**
 * Controls the buttons shown when an Orc Encampment (Barracks) is selected.
 *
 * @author Blair Butterworth
 */
public class EncampmentButtons extends BasicButtonController
{
    private static final List<ActionButtonType> BASIC_BUTTONS =
            singletonList(GruntButton);

    private static final List<ActionButtonType> IMPROVED_BUTTONS =
            asList(GruntButton, TrollAxethrowerButton);

    private static final List<ActionButtonType> ADVANCED_BUTTONS =
            asList(GruntButton, TrollAxethrowerButton, CatapultButton);

    private static final List<ActionButtonType> ALL_BUTTONS =
            asList(GruntButton, TrollAxethrowerButton, CatapultButton, OgreButton);

    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        List<ActionButtonType> buttons = new ArrayList<>(getButtons(player.getLevel()));

        if (player.hasUpgrade(RangedType1)) {
            Lists.replace(buttons, TrollAxethrowerButton, TrollBerserkerButton);
        }
        if (player.hasUpgrade(MeleeType1)) {
            Lists.replace(buttons, OgreButton, OgreMageButton);
        }
        return buttons;
    }

    public List<ActionButtonType> getButtons(int level) {
        if (level <= 2) {
            return BASIC_BUTTONS;
        }
        else if (level <= 4) {
            return IMPROVED_BUTTONS;
        }
        else if (level <= 5) {
            return ADVANCED_BUTTONS;
        }
        return ALL_BUTTONS;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        switch (button) {
            case GruntButton: return hasResources(player, Grunt);
            case TrollAxethrowerButton: return hasResources(player, TrollAxethrower) && hasUnit(player,TrollLumberMill);
            case TrollBerserkerButton: return hasResources(player, TrollBerserker) && hasUnit(player, TrollLumberMill);
            case OgreButton: return hasResources(player, Ogre) && hasUnits(player, TrollLumberMill, OgreMound);
            case OgreMageButton: return hasResources(player, OgreMage) && hasUnits(player, TrollLumberMill, OgreMound);
            case CatapultButton: return hasResources(player, Catapult) && hasUnits(player, TrollLumberMill, Forge);
            default: return false;
        }
    }
}
