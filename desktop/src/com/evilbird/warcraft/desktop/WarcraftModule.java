/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.desktop;

import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.behaviour.BehaviourFactory;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.menu.MenuFactory;
import com.evilbird.engine.state.StateService;
import com.evilbird.warcraft.action.WarcraftActionFactory;
import com.evilbird.warcraft.behaviour.WarcraftBehaviourFactory;
import com.evilbird.warcraft.item.WarcraftItemFactory;
import com.evilbird.warcraft.menu.WarcraftMenuFactory;
import com.evilbird.warcraft.state.WarcraftStateService;
import dagger.Binds;
import dagger.Module;

import javax.inject.Singleton;

@Module
public abstract class WarcraftModule
{
    @Binds
    @Singleton
    public abstract ActionFactory bindActionFactory(WarcraftActionFactory warcraftActionFactory);

    @Binds
    @Singleton
    public abstract BehaviourFactory bindBehaviourFactory(WarcraftBehaviourFactory warcraftBehaviourFactory);

    @Binds
    @Singleton
    public abstract MenuFactory bindMenuFactory(WarcraftMenuFactory warcraftMenuFactory);

    @Binds
    @Singleton
    public abstract ItemFactory bindItemFactory(WarcraftItemFactory warcraftItemFactory);

    @Binds
    @Singleton
    public abstract StateService bindStateService(WarcraftStateService warcraftStateService);
}
