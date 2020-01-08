/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.selector.selection;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Disposable;
import com.evilbird.engine.common.graphics.TextureUtils;
import com.evilbird.engine.object.BasicGameObject;
import com.evilbird.warcraft.object.selector.SelectorType;

import static com.evilbird.engine.common.graphics.Colours.FOREST_GREEN;

/**
 * Instances of this class represent a selection box, a user defined area
 * denoting those items that should be selected.
 *
 * @author Blair Butterworth
 */
public class AreaSelector extends BasicGameObject implements Disposable
{
    private Texture texture;

    public AreaSelector() {
        setIdentifier(SelectorType.AreaSelector);
        setType(SelectorType.AreaSelector);
        setTouchable(Touchable.disabled);
        setVisible(true);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(getTexture(), getX(), getY(), getWidth(), getHeight());
    }

    private Texture getTexture() {
        if (texture == null) {
            texture = TextureUtils.getRectangle((int)getWidth(), (int)getHeight(), FOREST_GREEN);
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
