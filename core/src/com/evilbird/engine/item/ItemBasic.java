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
import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.collection.EmptyIterator;
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.serialization.SerializedInitializer;
import com.evilbird.engine.item.interop.ActorDecorator;
import com.google.gson.annotations.JsonAdapter;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import static com.badlogic.gdx.scenes.scene2d.Touchable.enabled;
import static com.evilbird.engine.common.lang.GenericIdentifier.Unknown;

/**
 * Instances of this class represent the basic entity in the game.
 *
 * @author Blair Butterworth
 */
@JsonAdapter(ItemBasicAdapter.class)
public class ItemBasic implements Item
{
    transient Actor delegate;
    transient ItemRoot root;
    transient ItemGroup parent;
    private Identifier id;
    private Identifier type;
    private boolean visible;
    private Touchable touchable;
    private Vector2 size;
    private Vector2 position;
    private List<Action> actions;
    private transient int index;
    private transient ListIterator<Action> iterator;

    public ItemBasic() {
        id = Unknown;
        type = Unknown;
        touchable = enabled;
        size = new Vector2(1, 1);
        position = new Vector2(0, 0);
        actions = new ArrayList<>();
        delegate = getDelegate();
        index = Integer.MAX_VALUE;
    }

    @Override
    public void addAction(Action action) {
        Validate.notNull(action);
        action.setItem(this);
        if (iterator != null) {
            iterator.add(action);
        } else {
            actions.add(action);
        }
    }

    @Override
    public void clearActions() {
        if (iterator != null) {
            iterator = new EmptyIterator<>();
        }
        actions.clear();
    }

    @Override
    public Collection<Action> getActions() {
        return Collections.unmodifiableList(actions);
    }

    @Override
    public boolean hasActions() {
        return !actions.isEmpty();
    }

    @Override
    public Identifier getIdentifier() {
        return id;
    }

    @Override
    public Identifier getType() {
        return type;
    }

    @Override
    public ItemGroup getParent() {
        return parent;
    }

    @Override
    public ItemRoot getRoot() {
        return root;
    }

    @Override
    public boolean getTouchable() {
        return touchable == Touchable.enabled;
    }

    @Override
    public boolean getVisible() {
        return visible;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(position.x, position.y, size.x, size.y);
    }

    @Override
    public Vector2 getSize() {
        return size.cpy();
    }

    @Override
    public float getWidth() {
        return size.x;
    }

    @Override
    public float getHeight() {
        return size.y;
    }

    @Override
    public Vector2 getPosition() {
        return position.cpy();
    }

    @Override
    public Vector2 getPosition(Alignment alignment) {
        Validate.notNull(alignment);
        int align = alignment.toGdxAlign();
        return new Vector2(delegate.getX(align), delegate.getY(align));
    }

    @Override
    public float getX() {
        return position.x;
    }

    @Override
    public float getY() {
        return position.y;
    }

    @Override
    public int getZIndex() {
        return index;
    }

    @Override
    public void setIdentifier(Identifier identifier) {
        Validate.notNull(id);
        this.id = identifier;
    }

    @Override
    public void setType(Identifier type) {
        Validate.notNull(type);
        this.type = type;
    }

    @Override
    public void setParent(ItemGroup parent) {
        Validate.notNull(parent);
        this.parent = parent;
    }

    @Override
    public void setRoot(ItemRoot root) {
        this.root = root;
        actions.forEach(action -> action.setRoot(root));
    }

    @Override
    public void setTouchable(Touchable touchable) {
        this.touchable = touchable;
        this.delegate.setTouchable(touchable);
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
        this.delegate.setVisible(visible);
    }

    @Override
    public void setSize(float width, float height) {
        this.size.x = width;
        this.size.y = height;
        this.delegate.setSize(width, height);
    }

    @Override
    public void setSize(Vector2 size) {
        Validate.notNull(size);
        this.size = size;
        this.delegate.setSize(size.x, size.y);
    }

    @Override
    public void setPosition(float x, float y) {
        this.position.x = x;
        this.position.y = y;
        this.delegate.setPosition(x, y);
    }

    @Override
    public void setPosition(Vector2 position) {
        Validate.notNull(position);
        this.position = position;
        this.delegate.setPosition(position.x, position.y);
    }

    @Override
    public void setZIndex(int index) {
        this.index = index;
        this.delegate.setZIndex(index);
    }

    @Override
    public void draw(Batch batch, float alpha) {
    }

    @Override
    public void update(float delta) {
        iterator = actions.listIterator();
        while (iterator.hasNext()) {
            Action action = iterator.next();
            if (action.act(delta)) {
                iterator.remove();
            }
        }
        iterator = null;
    }

    @Override
    public void positionChanged() {
    }

    @Override
    public void sizeChanged() {
    }

    @Override
    public Item hit(Vector2 position, boolean touchable) {
        if (touchable && delegate.getTouchable() != enabled) {
            return null;
        }
        return position.x >= 0 && position.x < delegate.getWidth()
            && position.y >= 0 && position.y < delegate.getHeight() ? this : null;
    }

    @Override
    public Vector2 parentToLocalCoordinates(Vector2 coordinates) {
        Vector2 result = new Vector2(coordinates);
        return delegate.parentToLocalCoordinates(result);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", id)
            .append("type", type)
            .append("visible", visible)
            .append("touchable", touchable)
            .append("size", size)
            .append("position", position)
            .append("actions", actions)
            .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        ItemBasic item = (ItemBasic)obj;
        return new EqualsBuilder()
            .append(id, item.id)
            .append(type, item.type)

            .append(visible, item.visible)
            .append(touchable, item.touchable)
            .append(size, item.size)
            .append(position, item.position)
            .append(actions, item.actions)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(id)
            .append(type)
            .append(visible)
            .append(touchable)
            .append(size)
            .append(position)
            .append(actions)
            .toHashCode();
    }

    @Override
    public Actor toActor() {
        return delegate;
    }

    protected Actor getDelegate() {
        Actor result = newDelegate();
        result.setUserObject(this);
        return result;
    }

    protected Actor newDelegate() {
        return new ActorDecorator(this);
    }

    @SerializedInitializer
    protected void updateDelegate() {
        delegate.setVisible(visible);
        delegate.setTouchable(touchable);
        delegate.setSize(size.x, size.y);
        delegate.setPosition(position.x, position.y);
    }
}
