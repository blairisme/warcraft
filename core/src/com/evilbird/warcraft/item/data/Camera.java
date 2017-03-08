package com.evilbird.warcraft.item.data;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.item.Item;

public class Camera extends Item
{
    private static final Identifier ZOOM_PROPERTY = new Identifier("Zoom");
    private static final Identifier ORIGINAL_ZOOM_PROPERTY = new Identifier("Zoom");

    private float originalZoom;
    private OrthographicCamera camera;

    public Camera(OrthographicCamera camera)
    {
        this.camera = camera;
        this.originalZoom = 0f; //TODO: 1.0f?

        setId(new Identifier("Camera"));
        setType(new Identifier("Camera"));
        setPosition(camera.position.x, camera.position.y);
        setTouchable(Touchable.disabled);
        setVisible(false);
    }

    public float getZoom()
    {
        return camera.zoom;
    }

    public float getOriginalZoom()
    {
        return originalZoom;
    }

    public void setZoom(float zoom)
    {
        camera.zoom = zoom;
    }

    public void setOriginalZoom(float originalZoom)
    {
        this.originalZoom = originalZoom;
    }

    @Override
    public void positionChanged()
    {
        camera.position.x = getX();
        camera.position.y = getY();
    }

    @Override
    public Object getProperty(Identifier property)
    {
        if (Objects.equals(property, ZOOM_PROPERTY)){
            return getZoom();
        }
        else if (Objects.equals(property, ORIGINAL_ZOOM_PROPERTY)){
            return getOriginalZoom();
        }
        return super.getProperty(property);
    }

    @Override
    public void setProperty(Identifier property, Object value)
    {
        if (Objects.equals(property, ZOOM_PROPERTY)){
            setZoom((Float) value);
        }
        else if (Objects.equals(property, ORIGINAL_ZOOM_PROPERTY)){
            setOriginalZoom((Float) value);
        }
        else{
            super.setProperty(property, value);
        }
    }
}
