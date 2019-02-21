/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.serialization;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.audio.BasicSoundEffect;
import com.evilbird.engine.common.audio.SilentSoundEffect;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.audio.SoundEffectSet;
import com.google.gson.*;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SoundAdapter implements JsonSerializer<SoundEffect>, JsonDeserializer<SoundEffect>
{
    private static final String FILE_PATH = "file";
    private AssetManager assetManager;

    public SoundAdapter(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    @Override
    public JsonElement serialize(SoundEffect source, Type type, JsonSerializationContext context) {
        if (source instanceof BasicSoundEffect) {
            return serializeBasic((BasicSoundEffect)source);
        }
        if (source instanceof SoundEffectSet) {
            return serializeSet((SoundEffectSet)source, context);
        }
        if (source instanceof SilentSoundEffect) {
            return serializeSilent();
        }
        throw new UnsupportedOperationException();
    }

    private JsonElement serializeBasic(BasicSoundEffect soundEffect) {
        JsonObject result = new JsonObject();
        result.addProperty("type", "basic");
        result.addProperty(FILE_PATH, soundEffect.getPath());
        return result;
    }

    private JsonElement serializeSet(SoundEffectSet soundEffectSet, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.addProperty("type", "set");

        JsonArray array = new JsonArray();
        for (SoundEffect soundEffect: soundEffectSet.getSounds()) {
            array.add(context.serialize(soundEffect, SoundEffect.class));
        }
        result.add("sounds", array);
        return result;
    }

    private JsonElement serializeSilent() {
        JsonObject result = new JsonObject();
        result.addProperty("type", "empty");
        return result;
    }

    @Override
    public SoundEffect deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String soundType = jsonObject.get("type").getAsString();

        if (Objects.equals(soundType, "basic")) {
            return deserializeBasic(jsonObject);
        }
        if (Objects.equals(soundType, "set")) {
            return deserializeSet(jsonObject, context);
        }
        if (Objects.equals(soundType, "empty")) {
            return deserializeSilent();
        }
        throw new UnsupportedOperationException();
    }

    private SoundEffect deserializeBasic(JsonObject jsonObject) {
        String path = jsonObject.get(FILE_PATH).getAsString();
        Sound sound = load(path);
        return new BasicSoundEffect(sound, path);
    }

    private SoundEffect deserializeSet(JsonObject jsonObject, JsonDeserializationContext context) {
        List<SoundEffect> sounds = new ArrayList<>();
        JsonArray array = jsonObject.get("sounds").getAsJsonArray();
        for (JsonElement element: array) {
            sounds.add(context.deserialize(element, SoundEffect.class));
        }
        return new SoundEffectSet(sounds);
    }

    private SoundEffect deserializeSilent() {
        return new SilentSoundEffect();
    }

    private Sound load(String path) {
        assetManager.load(path, Texture.class);
        assetManager.finishLoadingAsset(path);
        return assetManager.get(path, Sound.class);
    }
}
