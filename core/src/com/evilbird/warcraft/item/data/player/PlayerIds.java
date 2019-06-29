/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.data.player;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.TextIdentifier;

/**
 * Defines constants for commonly used player identifiers.
 *
 * @author Blair Butterworth
 */
public class PlayerIds
{
    public static final Identifier Player1 = new TextIdentifier("Player1");
    public static final Identifier Player2 = new TextIdentifier("Player2");
    public static final Identifier Player3 = new TextIdentifier("Player3");
    public static final Identifier Player4 = new TextIdentifier("Player4");
    public static final Identifier Neutral = new TextIdentifier("Neutral");

    private PlayerIds() {
    }
}
