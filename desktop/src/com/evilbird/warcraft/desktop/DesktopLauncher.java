package com.evilbird.warcraft.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.evilbird.warcraft.desktop.DaggerDesktopInjector.Builder;
public class DesktopLauncher
{
	public static void main (String[] arg)
    {
		ApplicationListener engine = getEngine();
        LwjglApplicationConfiguration configuration = getConfiguration();
		new LwjglApplication(engine, configuration);
	}

    private static DesktopInjector getInjector()
    {
        Builder builder = DaggerDesktopInjector.builder();
        //builder.desktopModule(new DesktopModule());
        return builder.build();
    }

    private static ApplicationListener getEngine()
    {
        DesktopInjector injector = getInjector();
        return injector.newGameEngine();
    }

	private static LwjglApplicationConfiguration getConfiguration()
    {
        LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
        configuration.height = 768;
        configuration.width = 1024;
        configuration.vSyncEnabled = true;
        return configuration;
    }
}
