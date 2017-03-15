package com.evilbird.warcraft.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ModifyAction;
import com.evilbird.engine.action.duration.ActionDuration;
import com.evilbird.engine.action.duration.InstantDuration;
import com.evilbird.engine.action.modifier.ActionModifier;
import com.evilbird.engine.action.modifier.DeltaModifier;
import com.evilbird.engine.action.modifier.DeltaType;
import com.evilbird.engine.action.value.ActionValue;
import com.evilbird.engine.action.value.ItemValue;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemProperties;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class PanActionProvider implements ActionProvider
{
    @Inject
    public PanActionProvider()
    {
    }

    @Override
    public Action get(Item item, Item target, UserInput input)
    {
        return get(item, input);
    }

    public Action get(Item item, UserInput input)
    {
        ActionValue value = new ItemValue(item, ItemProperties.Position);
        ActionModifier modifier = getPanModifier(item, input.getDelta());
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(value, modifier, duration);
    }

    private ActionModifier getPanModifier(Item item, Vector2 delta)
    {
        //OrthographicCamera camera = (OrthographicCamera)item.getStage().getCamera();

        //float mapWidth = 1024; //TODO
        //float mapHeight = 1024; //TODO

        //float viewportWidth = camera.viewportWidth * camera.zoom;
        //float viewportHeight = camera.viewportHeight * camera.zoom;

        Vector2 lowerBound = null;
        Vector2 upperBound = null;
/*
        if (mapWidth >= viewportWidth && mapHeight >= viewportHeight)
        {
            float viewportHalfWidth = viewportWidth * .5f;
            float viewportHalfHeight = viewportHeight * .5f;

            lowerBound = new Vector2(viewportHalfWidth, viewportHalfHeight);
            upperBound = new Vector2(mapWidth - viewportHalfWidth, mapHeight - viewportHalfHeight);
        }
*/
        return new DeltaModifier(delta, DeltaType.PerUpdate, lowerBound, upperBound);
    }
}
