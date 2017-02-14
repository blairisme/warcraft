package com.evilbird.warcraft.desktop;

import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.behaviour.BehaviourFactory;
import com.evilbird.engine.hud.HudFactory;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.level.Level;
import com.evilbird.engine.menu.MenuFactory;
import com.evilbird.engine.world.WorldFactory;
import com.evilbird.warcraft.action.WarcraftActionFactory;
import com.evilbird.warcraft.behaviour.WarcraftBehaviourFactory;
import com.evilbird.warcraft.hud.WarcraftHudFactory;
import com.evilbird.warcraft.item.WarcraftItemFactory;
import com.evilbird.warcraft.menu.WarcraftMenuFactory;
import com.evilbird.warcraft.world.WarcraftWorldFactory;

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
    public static MenuFactory provideMenuFactory(Provider<Level> levelProvider)
    {
        return new WarcraftMenuFactory(levelProvider);
    }

    @Provides
    @Singleton
    public static HudFactory provideHudFactory()
    {
        return new WarcraftHudFactory();
    }

    @Provides
    @Singleton
    public static ItemFactory provideItemFactory()
    {
        return new WarcraftItemFactory();
    }

    @Provides
    @Singleton
    public static WorldFactory provideWorldFactory(ItemFactory itemFactory)
    {
        return new WarcraftWorldFactory(itemFactory);
    }
}
