/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.highlight;

import com.evilbird.engine.action.ActionIdentifier;

/**
 * Defines options of specifying highlight action varieties.
 *
 * @author Blair Butterworth
 */
public enum HighlightActions implements ActionIdentifier
{
    HighlightCancel,
    HighlightEnemyCombatant,
    HighlightPlayerBuilding,
    HighlightPlayerCombatant
}
