/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.actions;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;

import javax.inject.Inject;

import static com.evilbird.warcraft.item.common.texture.TextureUtils.getDrawable;

//TODO: Move texture coordinates into type enum
public class ActionButtonProvider implements AssetProvider<ActionButton>
{
    private static final String BACKGROUND = "data/textures/neutral/perennial/action.png";
    private static final String ICONS = "data/textures/neutral/perennial/icons.png";
    private static final String ICONS_DISABLED = "data/textures/neutral/perennial/icons_disabled.png";
    
    private AssetManager assets;
    private Drawable background;

    @Inject
    public ActionButtonProvider(Device device) {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load() {
        assets.load(BACKGROUND, Texture.class);
        assets.load(ICONS, Texture.class);
        assets.load(ICONS_DISABLED, Texture.class);
    }

    @Override
    public ActionButton get() {
        ActionButton result = new ActionButton();
        result.setBackground(getBackground());
        return result;
    }

    public ActionButton get(ActionButtonType type) {
        ActionButton result = get();
        result.setPadding(4);
        result.setSize(54, 46);
        result.setId(type);
        result.setType(type);
        result.setTouchable(Touchable.enabled);
        result.setImage(getNormalIcon(type));
        result.setDisabledImage(getDisabledIcon(type));
        return result;
    }

    private Drawable getBackground() {
        if (background == null){
            background = getDrawable(assets, BACKGROUND);
        }
        return background;
    }

    private Drawable getNormalIcon(ActionButtonType type) {
        switch (type) {
            case CancelButton: return getDrawable(assets, ICONS, 46, 684, 46, 38);
            case MoveButton: return getDrawable(assets, ICONS, 138, 608, 46, 38);
            case StopButton: return getDrawable(assets, ICONS, 184, 1216, 46, 38);
            case AttackButton: return getDrawable(assets, ICONS, 46, 874, 46, 38);
            case DefendButton: return getDrawable(assets, ICONS, 0, 1368, 46, 38);
            case PatrolButton: return getDrawable(assets, ICONS, 138, 1330, 46, 38);
            case RepairButton: return getDrawable(assets, ICONS, 0, 646, 46, 38);
            case GatherButton: return getDrawable(assets, ICONS, 46, 646, 46, 38);
            case BuildSimpleButton: return getDrawable(assets, ICONS, 92, 646, 46, 38);
            case BuildAdvancedButton: return getDrawable(assets, ICONS, 138, 646, 46, 38);
            case BuildCancelButton: return getDrawable(assets, ICONS, 46, 684, 46, 38);
            case BuildBarracksButton: return getDrawable(assets, ICONS, 92, 304, 46, 38);
            case BuildFarmButton: return getDrawable(assets, ICONS, 138, 266, 46, 38);
            case BuildTownHallButton: return getDrawable(assets, ICONS, 0, 304, 46, 38);
            case BuildStablesButton: return getDrawable(assets, ICONS, 46, 418, 46, 38);
            case TrainFootmanButton: return getDrawable(assets, ICONS, 92, 0, 46, 38);
            case TrainPeasantButton: return getDrawable(assets, ICONS, 0, 0, 46, 38);
            default: throw new UnsupportedOperationException();
        }
    }

    private Drawable getDisabledIcon(ActionButtonType type) {
        switch (type) {
            case CancelButton: getDrawable(assets, ICONS_DISABLED, 46, 684, 46, 38);
            case MoveButton: return getDrawable(assets, ICONS_DISABLED, 138, 608, 46, 38);
            case StopButton: return getDrawable(assets, ICONS_DISABLED, 184, 1216, 46, 38);
            case AttackButton: return getDrawable(assets, ICONS_DISABLED, 46, 874, 46, 38);
            case DefendButton: return getDrawable(assets, ICONS_DISABLED, 0, 1368, 46, 38);
            case PatrolButton: return getDrawable(assets, ICONS_DISABLED, 138, 1330, 46, 38);
            case RepairButton: return getDrawable(assets, ICONS_DISABLED, 0, 646, 46, 38);
            case GatherButton: return getDrawable(assets, ICONS_DISABLED, 46, 646, 46, 38);
            case BuildSimpleButton: return getDrawable(assets, ICONS_DISABLED, 92, 646, 46, 38);
            case BuildAdvancedButton: return getDrawable(assets, ICONS_DISABLED, 138, 646, 46, 38);
            case BuildCancelButton: return getDrawable(assets, ICONS_DISABLED, 46, 684, 46, 38);
            case BuildBarracksButton: return getDrawable(assets, ICONS_DISABLED, 92, 304, 46, 38);
            case BuildFarmButton: return getDrawable(assets, ICONS_DISABLED, 138, 266, 46, 38);
            case BuildTownHallButton: return getDrawable(assets, ICONS_DISABLED, 0, 304, 46, 38);
            case BuildStablesButton: return getDrawable(assets, ICONS_DISABLED, 46, 418, 46, 38);
            case TrainFootmanButton: return getDrawable(assets, ICONS_DISABLED, 92, 0, 46, 38);
            case TrainPeasantButton: return getDrawable(assets, ICONS_DISABLED, 0, 0, 46, 38);
            default: throw new UnsupportedOperationException();
        }
    }
}

