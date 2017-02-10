package com.evilbird.warcraft.unit;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.utility.Identifier;

import java.util.Objects;

public class Camera extends Item
{
    private static final Identifier POSITION_PROPERTY = new Identifier("Position");
    private static final Identifier ZOOM_PROPERTY = new Identifier("Zoom");

    private OrthographicCamera camera;

    public Camera(OrthographicCamera camera)
    {
        this.camera = camera;
    }

    @Override
    public Object getProperty(Identifier property)
    {
        if (Objects.equals(property, POSITION_PROPERTY))
        {
            return new Vector2(camera.position.x, camera.position.y);
        }
        else if (Objects.equals(property, ZOOM_PROPERTY))
        {
            return camera.zoom;
        }
        else
        {
            return super.getProperty(property);
        }
    }

    @Override
    public void setProperty(Identifier property, Object value)
    {
        if (Objects.equals(property, POSITION_PROPERTY))
        {
            Vector2 position = (Vector2)value;
            camera.position.x = position.x;
            camera.position.y = position.y;
        }
        else if (Objects.equals(property, ZOOM_PROPERTY))
        {
            camera.zoom = (Float)value;
        }
        else
        {
            super.setProperty(property, value);
        }
    }
}
