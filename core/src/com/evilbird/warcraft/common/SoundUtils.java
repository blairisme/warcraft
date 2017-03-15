package com.evilbird.warcraft.common;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.evilbird.engine.common.audio.BasicSoundEffect;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.audio.SoundEffectSet;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class SoundUtils
{
    public static SoundEffect newSoundEffect(AssetManager assets, String path)
    {
        Sound sound = assets.get(path, Sound.class);
        return new BasicSoundEffect(sound);
    }

    public static SoundEffect newSoundEffect(AssetManager assets, String ... paths)
    {
        Collection<SoundEffect> effects = new ArrayList<SoundEffect>(paths.length);
        for (String path: paths){
            effects.add(newSoundEffect(assets, path));
        }
        return new SoundEffectSet(effects);
    }

    public static SoundEffect newSoundEffect(AssetManager assets, Collection<String> paths)
    {
        Collection<SoundEffect> effects = new ArrayList<SoundEffect>(paths.size());
        for (String path: paths){
            effects.add(newSoundEffect(assets, path));
        }
        return new SoundEffectSet(effects);
    }

    public static SoundEffect newSoundEffect(AssetManager assets, String prefix, String suffix, int start, int end)
    {
        Collection<String> paths = new ArrayList<String>();
        for (int i = start; i <= end; i++){
            paths.add(prefix + String.valueOf(i) + suffix);
        }
        return newSoundEffect(assets, paths);
    }
}
