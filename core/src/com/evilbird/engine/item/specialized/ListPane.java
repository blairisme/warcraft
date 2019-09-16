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
 *  the height of items to be controlled.
 * </p>
 * 
 * @author Blair Butterworth
 */
public class ListPane<T> extends List<T>
{
    private static final Logger logger = LoggerFactory.getLogger(ScrollBarPane.class);

    private Float itemHeight;
    private Field itemHeightField;

    public ListPane(Skin skin) {
        super(skin);
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
