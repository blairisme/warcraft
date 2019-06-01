/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
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
