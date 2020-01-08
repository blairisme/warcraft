/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.data.player;

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
    public static final Identifier Player5 = new TextIdentifier("Player5");
    public static final Identifier Player6 = new TextIdentifier("Player6");
    public static final Identifier Neutral = new TextIdentifier("Neutral");

    private PlayerIds() {
    }
}
