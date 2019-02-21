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
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.io.Writer;

/**
 * Instances of this class serialize objects into their equivalent JSON
 * representation. Methods are provided to convert Java objects into JSON
 * and JSON into Java objects.
 *
 * @author Blair Butterworth
 */
public class JsonSerializer implements Serializer
{
    private Gson gson;

    public JsonSerializer(AssetManager assetManager, ItemFactory itemFactory) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.serializeSpecialFloatingPointValues();
//        gsonBuilder.registerTypeAdapter(Texture.class, new TextureAdapter(assetManager));
//        gsonBuilder.registerTypeAdapter(SoundEffect.class, new SoundAdapter(assetManager));


        gsonBuilder.registerTypeAdapter(Item.class, new ItemAdapter(itemFactory)); //TODO use annoation


        //gsonBuilder.registerTypeAdapter(TextureRegion.class, new TextureRegionAdapter());
        //gsonBuilder.registerTypeAdapter(Identifier.class, new IdentifierAdapter());

        gsonBuilder.registerTypeAdapterFactory(new SerializedAdapterFactory());
        gsonBuilder.registerTypeAdapterFactory(new IdentifierAdapterFactory());
        gson = gsonBuilder.create();
    }

    @Override
    public <T> String serialize(T value, Class<T> type) throws SerializationException {
        try {
            return gson.toJson(value, type);
        }
        catch (Throwable error) {
            throw new SerializationException(error);
        }
    }

    @Override
    public <T> void serialize(T value, Class<T> type, Writer writer) throws SerializationException {
        try {
            gson.toJson(value, type, writer);
        }
        catch (Throwable error) {
            throw new SerializationException(error);
        }
    }

    @Override
    public <T> T deserialize(String value, Class<T> type) throws SerializationException {
        try {
            return gson.fromJson(value, type);
        }
        catch (Throwable error) {
            throw new SerializationException(error);
        }
    }

    @Override
    public <T> T deserialize(Reader reader, Class<T> type) throws SerializationException {
        try {
            return gson.fromJson(reader, type);
        }
        catch (Throwable error) {
            throw new SerializationException(error);
        }
    }
}
