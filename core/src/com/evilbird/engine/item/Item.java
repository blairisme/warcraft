/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.action.framework.Action;
import com.evilbird.engine.common.lang.*;
import com.evilbird.engine.item.framework.ActorExtension;
import com.evilbird.engine.item.framework.ActorObserver;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Instances of this class represent the basic entity in the game.
 *
 * @author Blair Butterworth
 */
// TODO: Cache size, position and bounds
public class Item implements ActorObserver, Identifiable, Categorizable, Positionable, Selectable, Disablable, Visible
{
    Actor delegate;
    private ItemRoot root;
    private ItemGroup parent;
    private Identifier id;
    private Identifier type;
    private boolean selected;
    private boolean selectable;

    public Item() {
        this.delegate = initializeDelegate();
        this.delegate.setUserObject(this);

        setId(new NamedIdentifier());//TODO: replace with enum
        setType(new NamedIdentifier("Unknown")); //TODO: replace with enum
        setSelected(false);
        setSelectable(true);
        setTouchable(Touchable.enabled);
    }

    protected Actor initializeDelegate() {
        return new ActorExtension(this);
    }

    @Override
    public Identifier getIdentifier() {
        return id;
    }

    @Override
    public Identifier getType() {
        return type;
    }

    public ItemGroup getParent() {
        return parent;
    }

    public ItemRoot getRoot() {
        return root;
    }

    public boolean getSelected() {
        return selected;
    }

    public boolean getSelectable() {
        return selectable;
    }

    public boolean getTouchable() {
        return delegate.isTouchable();
    }

    public boolean getVisible() {
        return delegate.isVisible();
    }

    public Rectangle getBounds() {
        return new Rectangle(delegate.getX(), delegate.getY(), delegate.getWidth(), delegate.getHeight());
    }

    public Vector2 getSize() {
        return new Vector2(delegate.getWidth(), delegate.getHeight());
    }

    public float getWidth() {
        return delegate.getWidth();
    }

    public float getHeight() {
        return delegate.getHeight();
    }

    public Vector2 getPosition() {
        return new Vector2(delegate.getX(), delegate.getY());
    }

    public Vector2 getPosition(Alignment alignment) {
        int align = alignment.toGdxAlign();
        return new Vector2(delegate.getX(align), delegate.getY(align));
    }

    public float getX() {
        return delegate.getX();
    }

    public float getY() {
        return delegate.getY();
    }

    public void setId(Identifier id) {
        this.id = id;
    }

    public void setType(Identifier type) {
        this.type = type;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    public void setTouchable(Touchable touchable) {
        delegate.setTouchable(touchable);
    }

    public void setVisible(boolean visible) {
        delegate.setVisible(visible);
    }

    /**
     * Sets the spatial dimensions of the item.
     *
     * @param width  the new width of the item.
     * @param height the new height of the item.
     */
    public void setSize(float width, float height) {
        delegate.setSize(width, height);
    }

    public void setSize(Vector2 size) {
        delegate.setSize(size.x, size.y);
    }

    public void setPosition(float x, float y) {
        delegate.setPosition(x, y);
    }

    public void setPosition(Vector2 position) {
        delegate.setPosition(position.x, position.y);
    }

    public void setParent(ItemGroup parent) {
        this.parent = parent;
    }

    public void setRoot(ItemRoot root) {
        this.root = root;
    }




    private Collection<Action> actions = new ArrayList<>();

    public void addAction(Action action) {
        if (action instanceof ItemAction) {
            delegate.addAction(((ItemAction)action).delegate);
        }
        actions.add(action);
    }

    public Collection<Action> getActions() {
        return actions;
    }

    public boolean hasActions() {
        return delegate.hasActions();
    }

    public void clearActions() {
        delegate.clearActions();
    }






    @Override
    public void draw(Batch batch, float alpha) {
    }

    @Override
    public void update(float delta) {
    }

    /**
     * Called when the item's position has been changed.
     */
    @Override
    public void positionChanged() {
    }

    /**
     * Called when the item's size has been changed.
     */
    @Override
    public void sizeChanged() {
    }

    /**
     * Returns the {@link Item} at the specified location in the items local coordinate system (0,0
     * is the bottom left of the actor and width,height is the upper right).
     *
     * @param position  the world coordinates to test.
     * @param touchable specifies if hit detection will respect the items touchability.
     * @return the item at the specified location or null if no item is located there.
     */
    public Item hit(Vector2 position, boolean touchable) {
        if (touchable && delegate.getTouchable() != Touchable.enabled) return null;
        return position.x >= 0 && position.x < delegate.getWidth() &&
                position.y >= 0 && position.y < delegate.getHeight() ? this : null;
    }

    /**
     * Converts the coordinates given in the parent's coordinate system to this items's coordinate
     * system.
     *
     * @param coordinates given in the parent's coordinate system.
     * @return coordinates given in the items's coordinate system.
     */
    public Vector2 parentToLocalCoordinates(Vector2 coordinates) {
        Vector2 result = new Vector2(coordinates);
        return delegate.parentToLocalCoordinates(result);
    }
}
