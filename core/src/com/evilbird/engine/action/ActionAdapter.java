/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.common.serialization.AbstractAdapter;
import com.evilbird.engine.game.GameService;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

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
        result.add(IDENTIFIER, context.serialize(target.getIdentifier(), ActionIdentifier.class));
        return result;
    }

    @Override
    protected boolean isSerializedField(Action target, Field field) {
        return actionFields.contains(field) && !Objects.equals(field.getName(), IDENTIFIER);
    }

    @Override
    protected Action getDeserializedInstance(JsonObject json, JsonDeserializationContext context) {
        JsonElement element = json.get(IDENTIFIER);
        ActionIdentifier identifier = context.deserialize(element, ActionIdentifier.class);
        return actionFactory.newAction(identifier);
    }

    @Override
    protected boolean isDeserializedField(String name, JsonElement value) {
        return true;
    }
}