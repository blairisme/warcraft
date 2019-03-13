/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.data.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemBasic;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.item.common.capability.Zoomable;
import com.evilbird.warcraft.item.data.DataType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.inject.Inject;

import static com.badlogic.gdx.Gdx.graphics;

public class Camera extends ItemBasic implements Zoomable
{
    private float originalZoom;
    private float currentZoom;
    private transient OrthographicCamera camera;

    @Inject
    public Camera() {
        camera = new OrthographicCamera(graphics.getWidth(), graphics.getHeight());
        camera.setToOrtho(false, 30, 20);
        originalZoom = 1;
        camera.zoom = originalZoom;
        currentZoom = originalZoom;

        setIdentifier(DataType.Camera);
        setType(DataType.Camera);
        setPosition(camera.position.x, camera.position.y);
        setTouchable(Touchable.disabled);
        setVisible(false);
    }

    @Override
    public void setRoot(ItemRoot root) {
        root.getViewport().setCamera(camera);
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
    public Item hit(Vector2 position, boolean touchable) {
        return null;
    }

    @Override
    public void positionChanged() {
        camera.position.x = getX();
        camera.position.y = getY();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;

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
}
