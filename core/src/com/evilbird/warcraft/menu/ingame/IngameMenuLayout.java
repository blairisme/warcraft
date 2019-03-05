/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.ingame;

public enum IngameMenuLayout
{
    Normal  ("menu-background-normal", 256, 288),
    Wide    ("menu-background-wide", 384, 256),
    Small   ("menu-background-small", 288, 128);

    private String background;
    private int width;
    private int height;

    IngameMenuLayout(String background, int width, int height) {
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