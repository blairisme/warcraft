/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.selection;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.common.graphics.Colours;
import com.evilbird.engine.common.graphics.TextureUtils;
import com.evilbird.engine.item.ItemBasic;

/**
 * Instances of this class represent a selection box, a user defined area
 * denoting those items that should be selected.
 *
 * @author Blair Butterworth
 */
public class SelectionBox extends ItemBasic
{
    public SelectionBox() {
        setIdentifier(SelectionType.SelectionBox);
        setType(SelectionType.SelectionBox);
        setTouchable(Touchable.disabled);
        setVisible(true);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        int width = (int)getWidth();
        int height = (int)getHeight();

        Texture texture = TextureUtils.getTexture(width, height, Colours.FOREST_GREEN);
        batch.draw(texture, getX(), getY(), width, height);
    }
}
