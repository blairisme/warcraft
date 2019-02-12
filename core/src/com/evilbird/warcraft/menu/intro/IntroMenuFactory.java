/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.intro;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.menu.Menu;

import javax.inject.Inject;

public class IntroMenuFactory extends IdentifiedAssetProviderSet<Menu>
{
    @Inject
    public IntroMenuFactory(
        HumanIntroOneFactory humanIntroOneFactory)
    {
        addProvider(IntroMenuType.HumanLevel1, humanIntroOneFactory);
    }
}
