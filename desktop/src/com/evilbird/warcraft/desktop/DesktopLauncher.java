package com.evilbird.warcraft.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.evilbird.engine.GameEngine;
import com.evilbird.engine.device.Device;

public class DesktopLauncher
{
	public static void main (String[] arg)
    {
		Device device = new DesktopDevice();
		ApplicationListener delegate = new GameEngine(device);
		LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
		configuration.height = 768;
		configuration.width = 1024;
		configuration.vSyncEnabled = true;
		new LwjglApplication(delegate, configuration);
	}
}
