/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.test.data.item;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.item.placeholder.Placeholder;
import com.evilbird.warcraft.item.placeholder.PlaceholderStyle;
import com.evilbird.warcraft.item.placeholder.PlaceholderType;

import static com.evilbird.test.data.item.TestItemRoots.newTestRoot;
import static com.evilbird.test.data.item.TestPlayers.newTestPlayer;

public class TestPlaceholders
{
    private TestPlaceholders() {
    }

    public static Placeholder newTestPlaceholder(String id) {
        return newTestPlaceholder(new TextIdentifier(id), PlaceholderType.FarmPlaceholder);
    }

    public static Placeholder newTestPlaceholder(Identifier identifier, Identifier type) {
        return newTestPlaceholder(identifier, type, newTestRoot("root"), newTestPlayer("parent"));
    }

    public static Placeholder newTestPlaceholder(Identifier identifier, Identifier type, ItemRoot root, ItemGroup parent) {
        Placeholder item = new Placeholder(getSkin());
        item.setIdentifier(identifier);
        item.setType(type);
        item.setRoot(root);
        item.setParent(parent);
        item.setPosition(128, 128);
        item.setSize(64, 64);
        return item;
    }

    private static Skin getSkin() {
        Skin skin = new Skin();
        skin.add("default", new PlaceholderStyle(), PlaceholderStyle.class);
        return skin;
    }
}
