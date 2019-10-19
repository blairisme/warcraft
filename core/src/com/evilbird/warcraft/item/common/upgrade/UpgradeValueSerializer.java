/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.upgrade;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import javax.inject.Inject;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class UpgradeValueSerializer implements JsonSerializer<UpgradeValue>, JsonDeserializer<UpgradeValue>
{
    @Inject
    public UpgradeValueSerializer() {
    }

    @Override
    public JsonElement serialize(UpgradeValue source, Type type, JsonSerializationContext context) {
        if (source instanceof UpgradeCombination) {
            return serializeCombination((UpgradeCombination)source);
        }
        if (source instanceof UpgradeSequence) {
            return serializeSequence((UpgradeSequence)source);
        }
        throw new UnsupportedOperationException();
    }

    private JsonElement serializeCombination(UpgradeCombination source) {
        JsonObject json = new JsonObject();
        json.addProperty(Upgrade.None.name(), source.base);
        for (Entry<Upgrade, Integer> entry: source.upgrades.entrySet()) {
            json.addProperty(entry.getKey().name(), entry.getValue());
        }
        return json;
    }

    private JsonElement serializeSequence(UpgradeSequence source) {
        JsonObject json = new JsonObject();
        json.addProperty("basic", source.basic);
        json.addProperty("improved", source.improved);
        json.addProperty("advanced", source.advanced);
        json.addProperty("series", source.series.name());
        return json;
    }

    @Override
    public UpgradeValue deserialize(JsonElement source, Type type, JsonDeserializationContext context) {
        JsonObject json = source.getAsJsonObject();
        if (json.has("none")) {
            return deserializeCombination(json);
        }
        if (json.has("series")) {
            return deserializeSequence(json);
        }
        throw new UnsupportedOperationException();
    }

    private UpgradeCombination deserializeCombination(JsonObject json) {
        Map<Upgrade, Integer> upgrades = new HashMap<>();
        for (String key: json.keySet()) {
            JsonElement element = json.get(key);
            Upgrade upgrade = Upgrade.valueOf(key);
            Integer value = element.getAsInt();
            upgrades.put(upgrade, value);
        }
        return new UpgradeCombination(upgrades.remove(Upgrade.None), upgrades);
    }

    private UpgradeSequence deserializeSequence(JsonObject json) {
        int basic = json.get("basic").getAsInt();
        int improved = json.get("improved").getAsInt();
        int advanced = json.get("advanced").getAsInt();
        UpgradeSeries series = UpgradeSeries.valueOf(json.get("series").getAsString());
        return new UpgradeSequence(series, basic, improved, advanced);
    }
}
