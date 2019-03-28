/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.specialized;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.item.ItemGroupObserver;
import com.evilbird.engine.item.interop.TableDecorator;

import java.util.Objects;

/**
 * An item which arranges its children using a table pattern.
 *
 * @author Blair Butterworth
 */
public class TableItem extends ItemGroup
{
    protected transient Table control;
    
    public TableItem() {
        super();
    }

    /**
     * Adds a new cell to the table containing the given {@link Actor}. A
     * {@link Cell} reference will be returned, allowing the layout of the new
     * <code>cell</code> to be customised.
     *
     * @param actor  an <code>Actor</code> instance. This parameter cannot be
     *              <code>null</code>.
     * @return      a reference to the new <code>cell</code>. This method will
     *              not return <code>null</code>.
     */
    public Cell<Actor> add(Actor actor) {
        Objects.requireNonNull(actor);
        return control.add(actor);
    }

    /**
     * Adds a new cell to the table containing the given {@link Item}. A
     * {@link Cell} reference will be returned, allowing the layout of the new
     * <code>cell</code> to be customised.
     *
     * @param item  an <code>Item</code> instance. This parameter cannot be
     *              <code>null</code>.
     * @return      a reference to the new <code>cell</code>. This method will
     *              not return <code>null</code>.
     */
    public Cell<Actor> add(Item item) {
        Objects.requireNonNull(item);
        item.setParent(this);
        item.setRoot(getRoot());
        items.add(item);
        observers.forEach(it -> it.itemAdded(item));
        return control.add(item.toActor());
    }

    /**
     * Adds a new cell to the table containing the given {@link Item}.
     *
     * @param item  an <code>Item</code> instance. This parameter cannot be
     *              <code>null</code>.
     */
    @Override
    public void addItem(Item item) {
        add(item);
    }

    /**
     * Removes a cell from the Table containing the given {@link Item}.
     *
     * @param item  an <code>Item</code> instance. This parameter cannot be
     *              <code>null</code>.
     */
    @Override
    public void removeItem(Item item) {
        Objects.requireNonNull(item);
        items.remove(item);
        control.removeActor(item.toActor());
        observers.forEach(it -> it.itemRemoved(item));
    }

    /**
     * Removes all cells from the Table.
     */
    @Override
    public void clearItems() {
        items.clear();
        control.clear();
        observers.forEach(ItemGroupObserver::itemsCleared);
    }

    public void debug() {
        control.debug();
    }

    public Skin getSkin() {
        return control.getSkin();
    }

    public void setSkin(Skin skin) {
        control.setSkin(skin);
    }

    public void setAlignment(Alignment alignment) {
        control.align(alignment.toGdxAlign());
    }

    public void setBackground(Drawable background) {
        control.setBackground(background);
    }

    public void setBackground(String background) {
        control.setBackground(background);
    }

    public void setCentered() {
        control.center();
    }

    public void setFillParent(boolean fill) {
        control.setFillParent(fill);
    }

    @Override
    protected Actor newDelegate() {
        control = new TableDecorator(this);
        return control;
    }
}
