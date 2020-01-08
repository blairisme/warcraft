/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.action;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.serialization.AbstractAdapter;
import com.evilbird.engine.game.GameService;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Instances of this class serialize and deserialize {@link Action Actions}.
 *
 * @author Blair Butterworth
 */
public class ActionAdapter extends AbstractAdapter<Action>
{
    private static final String IDENTIFIER = "identifier";
    private ActionFactory actionFactory;
    private List<Field> actionFields;

    public ActionAdapter() {
        GameService service = GameService.getInstance();
        this.actionFactory = service.getActionFactory();
        this.actionFields = Arrays.asList(BasicAction.class.getDeclaredFields());
    }

    public ActionAdapter(ActionFactory actionFactory) {
        this.actionFactory = actionFactory;
        this.actionFields = Arrays.asList(BasicAction.class.getDeclaredFields());
    }

    @Override
    protected JsonObject getSerializedType(Action target, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add(IDENTIFIER, context.serialize(target.getIdentifier(), Identifier.class));
        return result;
    }

    @Override
    protected boolean isSerializedField(Action target, Field field) {
        return actionFields.contains(field) && !Objects.equals(field.getName(), IDENTIFIER);
    }

    @Override
    protected Class<?> getDeserializedType(JsonObject json, JsonDeserializationContext context) {
        return Action.class;
    }

    @Override
    protected Action getDeserializedInstance(JsonObject json, JsonDeserializationContext context) {
        JsonElement element = json.get(IDENTIFIER);
        ActionIdentifier identifier = context.deserialize(element, Identifier.class);
        return actionFactory.get(identifier);
    }

    @Override
    protected boolean isDeserializedField(String name, JsonElement value) {
        return true;
    }
}