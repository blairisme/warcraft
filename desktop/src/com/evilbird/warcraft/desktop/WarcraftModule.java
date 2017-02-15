package com.evilbird.warcraft.desktop;

import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.behaviour.BehaviourFactory;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.level.Level;
import com.evilbird.engine.menu.MenuFactory;
import com.evilbird.warcraft.action.WarcraftActionFactory;
import com.evilbird.warcraft.behaviour.WarcraftBehaviourFactory;
import com.evilbird.warcraft.item.WarcraftItemFactory;
import com.evilbird.warcraft.menu.WarcraftMenuFactory;

import javax.inject.Provider;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class WarcraftModule
{
    @Provides
    @Singleton
    public static ActionFactory provideActionFactory(ItemFactory itemFactory)
    {
        return new WarcraftActionFactory(itemFactory);
    }

    @Provides
    @Singleton
    public static BehaviourFactory provideBehaviourFactory(ActionFactory actionFactory)
    {
        return new WarcraftBehaviourFactory(actionFactory);
    }

    @Provides
    @Singleton
    public static MenuFactory provideMenuFactory(Device device, Provider<Level> levelProvider)
    {
        return new WarcraftMenuFactory(device, levelProvider);
    }

    @Provides
    @Singleton
    public static ItemFactory provideItemFactory(Device device)
    {
        return new WarcraftItemFactory(device);
    }
}
