/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.menu.main;

import com.evilbird.engine.menu.MenuIdentifier;

/**
 * Defines options for main menu varieties: those menus shown when the user
 * enters the application.
 *
 * @author Blair Butterworth
 */
public enum MainMenuType implements MenuIdentifier
{
    Home,
    Campaign,
    CampaignNew,
    CampaignLoad,
}
