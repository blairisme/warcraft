/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.actions;

import com.evilbird.engine.common.lang.Identifier;

/**
 * Defines options for specifying action button types.
 *
 * @author Blair Butterworth
 */
public enum ActionButtonType implements Identifier
{
    CancelButton,
    MoveButton,
    AttackButton,
    StopButton,

    BuildBasicButton,
    BuildAdvancedButton,

    BuildBarracksButton,
    BuildFarmButton,
    BuildTownHallButton,

    TrainFootmanButton,
    TrainPeasantButton

    
}
