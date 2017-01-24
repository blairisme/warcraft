package com.evilbird.warcraft.utility;

import com.badlogic.gdx.assets.AssetManager;

import java.util.Map;
import java.util.Map.Entry;

public class AssetManagerUtils
{
    public static void load(AssetManager manager, Map<String, Class<?>> assets)
    {
        for (Entry<String, Class<?>> asset: assets.entrySet())
        {
            manager.load(asset.getKey(), asset.getValue());
        }
    }
}
