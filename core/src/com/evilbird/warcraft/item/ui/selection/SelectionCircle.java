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
import com.badlogic.gdx.utils.Disposable;
import com.evilbird.engine.common.graphics.TextureUtils;
import com.evilbird.engine.item.ItemBasic;

import static com.evilbird.engine.common.graphics.Colours.FOREST_GREEN;

/**
 * @author Blair Butterworth
 */
public class SelectionCircle extends ItemBasic implements Disposable
{
    private Texture texture;

    public SelectionCircle() {
        setIdentifier(SelectionType.SelectionBox);
        setType(SelectionType.SelectionBox);
        setTouchable(Touchable.enabled);
        setVisible(true);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(getTexture(), getX(), getY(), getWidth(), getHeight());
    }

    private Texture getTexture() {
        if (texture == null) {
            texture = TextureUtils.getCircle((int)getWidth(), FOREST_GREEN);
        }
        return texture;
    }

    @Override
    public void dispose() {
        if (texture != null) {
            texture.dispose();
            texture = null;
        }
    }

    @Override
    public void sizeChanged() {
        dispose();
    }
}
