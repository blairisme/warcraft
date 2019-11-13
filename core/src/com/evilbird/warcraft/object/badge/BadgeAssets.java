/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.badge;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.assets.AssetBundle;

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
