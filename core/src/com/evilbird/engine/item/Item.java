package com.evilbird.engine.item;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.utility.Identifier;
import com.evilbird.engine.utility.PropertySet;

import java.util.HashMap;
import java.util.Map;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class Item implements PropertySet<Identifier,Object>, ActorObserver
{
    Actor delegate;
    private ItemRoot root;
    private ItemGroup parent;
    private Identifier id;
    protected Map<Identifier, Object> properties; //TODO: Remove

    public Item()
    {
        this.delegate = initializeDelegate();
        this.delegate.setUserObject(this);
        this.properties = new HashMap<Identifier, Object>();
    }

    protected Actor initializeDelegate()
    {
        return new ActorExtension(this);
    }

    public Identifier getId()
    {
        return id;
    }

    public Vector2 getSize()
    {
        return new Vector2(delegate.getWidth(), delegate.getHeight());
    }

    public float getWidth()
    {
        return delegate.getWidth();
    }

    public float getHeight()
    {
        return delegate.getHeight();
    }

    public Vector2 getPosition()
    {
        return new Vector2(delegate.getX(), delegate.getY());
    }

    public float getX()
    {
        return delegate.getX();
    }

    public float getY()
    {
        return delegate.getY();
    }

    public ItemGroup getParent()
    {
        return parent;
    }

    public ItemRoot getRoot()
    {
        return root;
    }

    public void setId(Identifier id)
    {
        this.id = id;
    }

    public void setSize(float width, float height)
    {
        delegate.setSize(width, height);
    }

    public void setSize(Vector2 size)
    {
        delegate.setSize(size.x, size.y);
    }

    public void setPosition(float x, float y)
    {
        delegate.setPosition(x, y);
    }

    public void setPosition(Vector2 position)
    {
        delegate.setPosition(position.x, position.y);
    }

    public void setParent(ItemGroup parent)
    {
        this.parent = parent;
    }

    public void setRoot(ItemRoot root)
    {
        this.root = root;
    }

    public void setTouchable(Touchable touchable)
    {
        delegate.setTouchable(touchable);
    }

    public void setVisible(boolean visible)
    {
        delegate.setVisible(visible);
    }

    public void draw(Batch batch, float alpha)
    {
    }

    public void update(float delta)
    {
    }

    public void addAction(Action action)
    {
        delegate.addAction(action);
    }

    public boolean hasActions()
    {
        return delegate.hasActions();
    }

    public void clearActions()
    {
        delegate.clearActions();
    }

    public void positionChanged()
    {
    }

    public void sizeChanged()
    {
    }

    @Override
    public Object getProperty(Identifier key)
    {
        if (ItemProperties.Id.equals(key)){
            return getId();
        }
        else if (ItemProperties.Position.equals(key)){
            return getPosition();
        }
        else if (ItemProperties.Size.equals(key)){
            return getSize();
        }
        return properties.get(key);
        //throw new IllegalArgumentException();
    }

    @Override
    public void setProperty(Identifier key, Object value)
    {
        if (ItemProperties.Id.equals(key)){
            setId((Identifier)value);
        }
        else if (ItemProperties.Position.equals(key)){
            setPosition((Vector2)value);
        }
        else if (ItemProperties.Size.equals(key)){
            setSize((Vector2)value);
        }
        properties.put(key, value);
        //throw new IllegalArgumentException();
    }
}
