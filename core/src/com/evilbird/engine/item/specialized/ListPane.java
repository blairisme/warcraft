/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.specialized;

import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

import static org.apache.commons.lang3.reflect.FieldUtils.getDeclaredField;

/**
 * <p>
 * Represents a user interface control that displays a list of elements.
 * </p>
 * <p>
 *  This control also overrides the default {@link List} behaviour, allowing
 *  the height of items to be controlled as well as the colour of the controls
 *  border.
 * </p>
 *
 * @author Blair Butterworth
 */
public class ListPane<T> extends List<T>
{
    private static final Logger logger = LoggerFactory.getLogger(ListPane.class);

    private Skin skin;
    private Float itemHeight;
    private Field itemHeightField;

    public ListPane(Skin skin) {
        super(skin);
        this.skin = skin;
    }

    public void removeItem(T element) {
        Array<T> items = getItems();
        items.removeValue(element, true);
        setItems(items);
    }

    public void addSelectionListener(ListSelectionListener<T> listener) {
        addListener(new ListSelectionAdapter<T>(listener));
    }

    public void setStyle(String name) {
        setStyle(skin.get(name, ListStyle.class));
    }

    @Override
    public void layout() {
        super.layout();
        updateItemHeight();
    }

    public void setItemHeight(Float itemHeight) {
        this.itemHeight = itemHeight;
    }

    private void updateItemHeight() {
        try {
            if (itemHeightField == null) {
                itemHeightField = getDeclaredField(List.class, "itemHeight", true);
            }
            if (itemHeight != null) {
                itemHeightField.set(this, itemHeight);
            }
        }
        catch (IllegalArgumentException | IllegalAccessException e) {
            logger.error("Unable to access itemHeight", e);
        }
    }
}
