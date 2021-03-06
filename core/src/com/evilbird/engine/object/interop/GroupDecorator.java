/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.object.interop;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.evilbird.engine.object.GameObjectGroup;

/**
 * Instances of this class decorate the LibGDX {@link Group} class with an
 * {@link GameObjectGroup}, allowing the ItemGroup to receive events from the Group
 * without inheriting from it.
 *
 * @author Blair Butterworth
 */
public class GroupDecorator extends Group
{
    private GameObjectGroup objectGroup;

    public GroupDecorator(GameObjectGroup objectGroup) {
        this.objectGroup = objectGroup;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        objectGroup.update(delta);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        objectGroup.draw(batch, alpha);
        super.draw(batch, alpha);
    }

    @Override
    public void positionChanged() {
        objectGroup.positionChanged();
    }

    @Override
    public void sizeChanged() {
        objectGroup.sizeChanged();
    }
}
