/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.data.player;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.audio.LazyLoadedMusic;
import com.evilbird.engine.common.audio.MusicSequence;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.game.GameFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static com.evilbird.engine.common.file.FileType.MP3;
import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;

/**
 * Instances of this factory create {@link Player} objects.
 *
 * @author Blair Butterworth
 */
public class PlayerFactory implements GameFactory<Player>
{
    private static final String MUSIC_DIRECTORY = "data/music/";
    private static final int MUSIC_COUNT = 15;

    private AssetManager assets;

    @Inject
    public PlayerFactory(Device device) {
        this(device.getAssetStorage());
    }

    public PlayerFactory(AssetManager assets) {
        this.assets = assets;
    }

    @Override
    public void load(Identifier context) {
    }

    @Override
    public void unload(Identifier context) {
    }

    @Override
    public Player get(Identifier type) {
        Player player = new Player(getSkin());
        player.setType(type);
        player.setIdentifier(objectIdentifier("Player", player));
        player.setPosition(0, 0);
        player.setSize(Float.MAX_VALUE, Float.MAX_VALUE);
        return player;
    }

    private Skin getSkin() {
        Skin skin = new Skin();
        skin.add("default", getPlayerStyle());
        return skin;
    }

    private PlayerStyle getPlayerStyle() {
        PlayerStyle playerStyle = new PlayerStyle();
        playerStyle.music = new MusicSequence(getMusic());
        return playerStyle;
    }

    private List<Music> getMusic() {
        List<Music> music = new ArrayList<>(MUSIC_COUNT);
        FileHandleResolver resolver = assets.getFileHandleResolver();
        for (int i = 1; i <= MUSIC_COUNT; ++i) {
            String path = MUSIC_DIRECTORY + i + MP3.getFileExtension();
            music.add(new LazyLoadedMusic(resolver, path));
        }
        return music;
    }
}
