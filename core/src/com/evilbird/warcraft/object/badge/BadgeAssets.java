/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.badge;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.assets.AssetBundle;

/**
 * Provides access to the assets that are required to display badge game
 * objects.
 *
 * @author Blair Butterworth
 */
public class BadgeAssets extends AssetBundle
{
    public BadgeAssets(AssetManager assetManager) {
        super(assetManager);
        register("data/textures/common/menu/badge_icon.png");
    }

    public Drawable getBloodlustIcon() {
        return getDrawable("badge_icon.png", 0, 0, 16, 16);
    }

    public Drawable getHasteIcon() {
        return getDrawable("badge_icon.png", 0, 16, 16, 16);
    }

    public Drawable getInvisibilityIcon() {
        return getDrawable("badge_icon.png", 0, 48, 16, 16);
    }

    public Drawable getSlowIcon() {
        return getDrawable("badge_icon.png", 0, 64, 16, 16);
    }

    public Drawable getIcon(BadgeType type) {
        switch (type) {
            case BloodlustBadge: return getBloodlustIcon();
            case HasteBadge: return getHasteIcon();
            case InvisibilityBadge: return getInvisibilityIcon();
            case SlowBadge: return getSlowIcon();
            default: throw new UnsupportedOperationException();
        }
    }
}
