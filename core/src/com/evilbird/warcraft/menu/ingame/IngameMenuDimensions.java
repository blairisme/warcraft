/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.menu.ingame;

/**
 * Defines options for specifing the dimension of an {@link IngameMenu}.
 *
 * @author Blair Butterworth
 */
public enum IngameMenuDimensions
{
    Normal("menu-background-normal", 256, 288),
    Wide("menu-background-wide", 384, 256),
    Small("menu-background-small", 288, 128);

    private String background;
    private int width;
    private int height;

    IngameMenuDimensions(String background, int width, int height) {
        this.background = background;
        this.width = width;
        this.height = height;
    }

    public String getBackground() {
        return background;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}