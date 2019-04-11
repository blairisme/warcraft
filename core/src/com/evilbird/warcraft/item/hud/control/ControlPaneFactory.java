/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.graphics.Fonts;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.item.hud.control.actions.ActionButtonStyle;
import com.evilbird.warcraft.item.hud.control.actions.ActionButtonType;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

import static com.evilbird.engine.common.graphics.TextureUtils.getDrawable;

/**
 * Instances of this factory create {@link ControlPane ControlPanes}, loading
 * the appropriate assets for their correct display.
 *
 * @author Blair Butterworth.
 */
//TODO: Replace string ids with constants
public class ControlPaneFactory implements AssetProvider<ControlPane>
{
    private static final String ACTION_BUTTON = "data/textures/neutral/perennial/action.png";
    private static final String ACTION_ICONS = "data/textures/neutral/perennial/icons.png";
    private static final String ACTION_ICONS_DISABLED = "data/textures/neutral/perennial/icons_disabled.png";
    private static final String ACTION_PANEL = "data/textures/human/hud/action_panel.png";
    private static final String DETAILS_PANEL = "data/textures/human/hud/details_panel.png";
    private static final String MENU_PANEL = "data/textures/human/hud/menu_panel.png";
    private static final String MINIMAP_PANEL = "data/textures/human/hud/minimap_panel.png";
    private static final String SELECTION_PANEL = "data/textures/human/hud/selection_panel.png";
    private static final String UNIT_PANEL = "data/textures/neutral/perennial/selection.png";
    private static final String BUTTON_ENABLED = "data/textures/human/menu/button-thin-medium-normal.png";
    private static final String BUTTON_SELECTED = "data/textures/human/menu/button-thin-medium-pressed.png";
    private static final String BUTTON_DISABLED = "data/textures/human/menu/button-thin-medium-grayed.png";
    private static final String BUILDING_PROGRESS_FILL = "data/textures/neutral/perennial/building_progress_bar.png";
    private static final String BUILDING_PROGRESS_BACKGROUND = "data/textures/neutral/perennial/building_progress_background.png";
    private static final String HEALTH_PROGRESS_HIGH = "data/textures/neutral/perennial/health_bar_high.png";
    private static final String HEALTH_PROGRESS_MEDIUM = "data/textures/neutral/perennial/health_bar_medium.png";
    private static final String HEALTH_PROGRESS_LOW = "data/textures/neutral/perennial/health_bar_low.png";

    private AssetManager assets;

    @Inject
    public ControlPaneFactory(Device device) {
        this.assets = device.getAssetStorage();
    }

    @Override
    public void load() {
        assets.load(ACTION_BUTTON, Texture.class);
        assets.load(ACTION_ICONS, Texture.class);
        assets.load(ACTION_ICONS_DISABLED, Texture.class);
        assets.load(ACTION_PANEL, Texture.class);
        assets.load(DETAILS_PANEL, Texture.class);
        assets.load(MENU_PANEL, Texture.class);
        assets.load(MINIMAP_PANEL, Texture.class);
        assets.load(SELECTION_PANEL, Texture.class);
        assets.load(UNIT_PANEL, Texture.class);
        assets.load(BUTTON_ENABLED, Texture.class);
        assets.load(BUTTON_SELECTED, Texture.class);
        assets.load(BUTTON_DISABLED, Texture.class);
        assets.load(BUILDING_PROGRESS_FILL, Texture.class);
        assets.load(BUILDING_PROGRESS_BACKGROUND, Texture.class);
        assets.load(HEALTH_PROGRESS_HIGH, Texture.class);
        assets.load(HEALTH_PROGRESS_MEDIUM, Texture.class);
        assets.load(HEALTH_PROGRESS_LOW, Texture.class);
    }

    @Override
    public ControlPane get() {
        return new ControlPane(getSkin());
    }

    private Skin getSkin() {
        Skin skin = new Skin();
        skin.add("default", getDefaultFont());
        skin.add("button-thin-medium", getButtonStyle());
        skin.add("building-progress", getBuildingProgressStyle());
        skin.add("health-progress-high", getHealthProgressHigh());
        skin.add("action-button", getActionButtonStyle());
        skin.add("action-panel", getDrawable(assets, ACTION_PANEL), Drawable.class);
        skin.add("details-panel", getDrawable(assets, DETAILS_PANEL), Drawable.class);
        skin.add("menu-panel", getDrawable(assets, MENU_PANEL), Drawable.class);
        skin.add("minimap-panel", getDrawable(assets, MINIMAP_PANEL), Drawable.class);
        skin.add("selection-panel", getDrawable(assets, SELECTION_PANEL), Drawable.class);
        skin.add("unit-panel", getDrawable(assets, UNIT_PANEL), Drawable.class);
        return skin;
    }

    private LabelStyle getDefaultFont() {
        LabelStyle style = new LabelStyle();
        style.font = Fonts.ARIAL;
        style.fontColor = Color.WHITE;
        return style;
    }

    private TextButtonStyle getButtonStyle() {
        Drawable enabled = getDrawable(assets, BUTTON_ENABLED);
        Drawable disabled = getDrawable(assets, BUTTON_DISABLED);
        Drawable selected = getDrawable(assets, BUTTON_SELECTED);

        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = Fonts.ARIAL;
        textButtonStyle.fontColor = Color.WHITE;
        textButtonStyle.up = enabled;
        textButtonStyle.over = enabled;
        textButtonStyle.checked = enabled;
        textButtonStyle.checkedOver = enabled;
        textButtonStyle.disabled = disabled;
        textButtonStyle.down = selected;

        //menu.setButtonSound(assets.get(CLICK));
        return textButtonStyle;
    }

    private ProgressBarStyle getBuildingProgressStyle() {
        ProgressBarStyle style = new ProgressBarStyle();
        style.background = getDrawable(assets, BUILDING_PROGRESS_BACKGROUND);
        style.knob = getDrawable(assets, BUILDING_PROGRESS_FILL);
        style.knobBefore = style.knob;
        return style;
    }

    private ProgressBarStyle getHealthProgressHigh() {
        ProgressBarStyle style = new ProgressBarStyle();
        style.knob = getDrawable(assets, HEALTH_PROGRESS_HIGH);
        style.knobBefore = style.knob;
        return style;
    }

    private ActionButtonStyle getActionButtonStyle() {
        ActionButtonStyle style = new ActionButtonStyle();
        style.icons = getActionIcons();
        style.disabledIcons = getActionDisabledIcons();
        style.background = getDrawable(assets, ACTION_BUTTON);
        return style;
    }

    private Map<ActionButtonType, Drawable> getActionIcons() {
        Map<ActionButtonType, Drawable> icons = new HashMap<>();
        for (ActionButtonType action: ActionButtonType.values()) {
            icons.put(action, getActionIcon(action));
        }
        return icons;
    }
    
    private Drawable getActionIcon(ActionButtonType type) {
        switch (type) {
            case CancelButton:
            case BuildCancelButton: return getDrawable(assets, ACTION_ICONS, 46, 684, 46, 38);
            case MoveButton: return getDrawable(assets, ACTION_ICONS, 138, 608, 46, 38);
            case StopButton: return getDrawable(assets, ACTION_ICONS, 184, 1216, 46, 38);
            case AttackButton: return getDrawable(assets, ACTION_ICONS, 46, 874, 46, 38);
            case DefendButton: return getDrawable(assets, ACTION_ICONS, 0, 1368, 46, 38);
            case PatrolButton: return getDrawable(assets, ACTION_ICONS, 138, 1330, 46, 38);
            case RepairButton: return getDrawable(assets, ACTION_ICONS, 0, 646, 46, 38);
            case GatherButton: return getDrawable(assets, ACTION_ICONS, 46, 646, 46, 38);
            case BuildSimpleButton: return getDrawable(assets, ACTION_ICONS, 92, 646, 46, 38);
            case BuildAdvancedButton: return getDrawable(assets, ACTION_ICONS, 138, 646, 46, 38);
            case BuildBarracksButton: return getDrawable(assets, ACTION_ICONS, 92, 304, 46, 38);
            case BuildFarmButton: return getDrawable(assets, ACTION_ICONS, 138, 266, 46, 38);
            case BuildTownHallButton: return getDrawable(assets, ACTION_ICONS, 0, 304, 46, 38);
            case BuildStablesButton: return getDrawable(assets, ACTION_ICONS, 46, 418, 46, 38);
            case TrainFootmanButton: return getDrawable(assets, ACTION_ICONS, 92, 0, 46, 38);
            case TrainPeasantButton: return getDrawable(assets, ACTION_ICONS, 0, 0, 46, 38);
            default: throw new UnsupportedOperationException();
        }
    }

    private Map<ActionButtonType, Drawable> getActionDisabledIcons() {
        Map<ActionButtonType, Drawable> icons = new HashMap<>();
        for (ActionButtonType action: ActionButtonType.values()) {
            icons.put(action, getActionDisabledIcon(action));
        }
        return icons;
    }
    
    private Drawable getActionDisabledIcon(ActionButtonType type) {
        switch (type) {
            case CancelButton:
            case BuildCancelButton: getDrawable(assets, ACTION_ICONS_DISABLED, 46, 684, 46, 38);
            case MoveButton: return getDrawable(assets, ACTION_ICONS_DISABLED, 138, 608, 46, 38);
            case StopButton: return getDrawable(assets, ACTION_ICONS_DISABLED, 184, 1216, 46, 38);
            case AttackButton: return getDrawable(assets, ACTION_ICONS_DISABLED, 46, 874, 46, 38);
            case DefendButton: return getDrawable(assets, ACTION_ICONS_DISABLED, 0, 1368, 46, 38);
            case PatrolButton: return getDrawable(assets, ACTION_ICONS_DISABLED, 138, 1330, 46, 38);
            case RepairButton: return getDrawable(assets, ACTION_ICONS_DISABLED, 0, 646, 46, 38);
            case GatherButton: return getDrawable(assets, ACTION_ICONS_DISABLED, 46, 646, 46, 38);
            case BuildSimpleButton: return getDrawable(assets, ACTION_ICONS_DISABLED, 92, 646, 46, 38);
            case BuildAdvancedButton: return getDrawable(assets, ACTION_ICONS_DISABLED, 138, 646, 46, 38);
            case BuildBarracksButton: return getDrawable(assets, ACTION_ICONS_DISABLED, 92, 304, 46, 38);
            case BuildFarmButton: return getDrawable(assets, ACTION_ICONS_DISABLED, 138, 266, 46, 38);
            case BuildTownHallButton: return getDrawable(assets, ACTION_ICONS_DISABLED, 0, 304, 46, 38);
            case BuildStablesButton: return getDrawable(assets, ACTION_ICONS_DISABLED, 46, 418, 46, 38);
            case TrainFootmanButton: return getDrawable(assets, ACTION_ICONS_DISABLED, 92, 0, 46, 38);
            case TrainPeasantButton: return getDrawable(assets, ACTION_ICONS_DISABLED, 0, 0, 46, 38);
            default: throw new UnsupportedOperationException();
        }
    }
}
