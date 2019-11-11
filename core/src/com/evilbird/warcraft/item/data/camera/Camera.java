/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.data.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.evilbird.engine.common.lang.Zoomable;
import com.evilbird.engine.common.serialization.SerializedInitializer;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.BasicGameObject;
import com.evilbird.engine.object.GameObjectContainer;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.inject.Inject;

import static com.badlogic.gdx.Gdx.graphics;

/**
 * Instances of this class represent a camera, a positionable entity that
 * dictates what game content is presented to the user.
 *
 * @author Blair Butterworth
 */
public class Camera extends BasicGameObject implements Zoomable
{
    private float originalZoom;
    private float currentZoom;
    private transient OrthographicCamera camera;

    @Inject
    public Camera(Device device) {
        this(device.getDeviceDisplay());
    }

    public Camera(DeviceDisplay display) {
        camera = new OrthographicCamera(graphics.getWidth(), graphics.getHeight());
        camera.setToOrtho(false, 30, 20);
        originalZoom = 1 / display.getScaleFactor();
        camera.zoom = originalZoom;
        currentZoom = originalZoom;

        setIdentifier(CameraType.Camera);
        setType(CameraType.Camera);
        setPosition(camera.position.x, camera.position.y);
        setTouchable(Touchable.disabled);
        setVisible(false);
    }

    @Override
    public void setRoot(GameObjectContainer root) {
        super.setRoot(root);
        if (root != null) {
            Viewport viewport = root.getViewport();
            viewport.setCamera(camera);
        }
    }

    public float getZoom() {
        return currentZoom;
    }

    public float getOriginalZoom() {
        return originalZoom;
    }

    public void setZoom(float zoom) {
        currentZoom = zoom;
        camera.zoom = currentZoom;
    }

    public void setOriginalZoom(float originalZoom) {
        this.originalZoom = originalZoom;
    }

    @Override
    public GameObject hit(Vector2 position, boolean touchable) {
        return null;
    }

    @Override
    public void positionChanged() {
        camera.position.x = getX();
        camera.position.y = getY();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        Camera camera1 = (Camera)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(originalZoom, camera1.originalZoom)
            .append(currentZoom, camera1.currentZoom)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(originalZoom)
            .append(currentZoom)
            .toHashCode();
    }

    @SerializedInitializer
    protected void updateDelegate() {
        camera.zoom = currentZoom;
        camera.position.x = getX();
        camera.position.y = getY();
    }
}
