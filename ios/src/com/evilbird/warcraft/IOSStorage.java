package com.evilbird.warcraft;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.warcraft.device.DeviceStorage;

import java.io.IOException;
import java.util.Collection;

public class IOSStorage implements DeviceStorage
{
    @Override
    public boolean delete(String path) throws IOException
    {
        return false;
    }

    @Override
    public boolean exists(String path) throws IOException
    {
        return false;
    }

    @Override
    public Collection<String> list() throws IOException
    {
        return null;
    }

    @Override
    public <T> T read(String path) throws IOException
    {
        return null;
    }

    @Override
    public <T> void write(T object, String path) throws IOException
    {
    }

    @Override
    public AssetManager getAssets()
    {
        return null;
    }
}
