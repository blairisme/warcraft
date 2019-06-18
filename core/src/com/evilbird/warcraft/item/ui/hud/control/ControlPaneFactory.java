/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.hud.control;

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
import com.evilbird.engine.device.DeviceControls;
import com.evilbird.warcraft.item.ui.hud.common.HealthBarStyle;
import com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonStyle;
import com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.ui.hud.control.status.details.building.ProductionDetailsStyle;
import com.evilbird.warcraft.item.ui.hud.control.status.selection.SelectionButtonStyle;

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
public class ControlPaneFactory implements AssetProvider<ControlPane>
{
    private static final String ICONS = "data/textures/neutral/perennial/icons.png";
    private static final String ICONS_DISABLED = "data/textures/neutral/perennial/icons_disabled.png";
    private static final String ACTION_BUTTON = "data/textures/neutral/perennial/action.png";
    private static final String UNSELECT_BUTTON = "data/textures/neutral/perennial/unselect.png";
    private static final String ACTION_PANEL = "data/textures/human/hud/action_panel.png";
    private static final String DETAILS_PANEL = "data/textures/human/hud/details_panel.png";
    private static final String MENU_PANEL = "data/textures/human/hud/menu_panel.png";
    private static final String MINIMAP_PANEL = "data/textures/human/hud/minimap_panel.png";
    private static final String SELECTION_PANEL = "data/textures/human/hud/selection_panel.png";
    private static final String UNIT_PANEL = "data/textures/neutral/perennial/selection.png";
    private static final String BUTTON_ENABLED = "data/textures/human/menu/button-thin-medium-normal.png";
    private static final String BUTTON_SELECTED = "data/textures/human/menu/button-thin-medium-pressed.png";
    private static final String BUTTON_DISABLED = "data/textures/human/menu/button-thin-medium-grayed.png";
    private static final String BUILDING_FILL = "data/textures/neutral/perennial/building_progress_bar.png";
    private static final String BUILDING_BACKGROUND="data/textures/neutral/perennial/building_progress_background.png";
    private static final String HEALTH_PROGRESS_HIGH = "data/textures/neutral/perennial/health_bar_high.png";
    private static final String HEALTH_PROGRESS_MEDIUM = "data/textures/neutral/perennial/health_bar_medium.png";
    private static final String HEALTH_PROGRESS_LOW = "data/textures/neutral/perennial/health_bar_low.png";

    private AssetManager assets;
    private DeviceControls controls;

    @Inject
    public ControlPaneFactory(Device device) {
        this(device.getAssetStorage(), device.getDeviceControls());
    }

    public ControlPaneFactory(AssetManager assets, DeviceControls controls) {
        this.assets = assets;
        this.controls = controls;
    }

    @Override
    public void load() {
        loadActionAssets();
        loadPanelAssets();
        loadButtonAssets();
        loadBuildingAssets();
        loadHealthAssets();
    }

    private void loadActionAssets() {
        assets.load(ACTION_BUTTON, Texture.class);
        assets.load(ICONS, Texture.class);
        assets.load(ICONS_DISABLED, Texture.class);
        assets.load(ACTION_PANEL, Texture.class);
    }

    private void loadPanelAssets() {
        assets.load(DETAILS_PANEL, Texture.class);
        assets.load(MENU_PANEL, Texture.class);
        assets.load(MINIMAP_PANEL, Texture.class);
        assets.load(SELECTION_PANEL, Texture.class);
        assets.load(UNIT_PANEL, Texture.class);
    }

    private void loadBuildingAssets() {
        assets.load(BUILDING_FILL, Texture.class);
        assets.load(BUILDING_BACKGROUND, Texture.class);
    }

    private void loadHealthAssets() {
        assets.load(HEALTH_PROGRESS_HIGH, Texture.class);
        assets.load(HEALTH_PROGRESS_MEDIUM, Texture.class);
        assets.load(HEALTH_PROGRESS_LOW, Texture.class);
    }

    private void loadButtonAssets() {
        assets.load(UNSELECT_BUTTON, Texture.class);
        assets.load(BUTTON_ENABLED, Texture.class);
        assets.load(BUTTON_SELECTED, Texture.class);
        assets.load(BUTTON_DISABLED, Texture.class);
    }

    @Override
    public ControlPane get() {
        return new ControlPane(getSkin());
    }

    private Skin getSkin() {
        Skin skin = new Skin();
        skin.add("default", getFontStyle());
        skin.add("default", getHealthBarStyle());
        skin.add("default", getSelectionButtonStyle());
        skin.add("default", getProductionDetailsStyle());
        skin.add("default", getControlPaneStyle());
        skin.add("button-thin-medium", getButtonStyle());
        skin.add("building-progress", getBuildingProgressStyle());
        skin.add("action-button", getActionButtonStyle());
        skin.add("action-panel", getDrawable(assets, ACTION_PANEL), Drawable.class);
        skin.add("details-panel", getDrawable(assets, DETAILS_PANEL), Drawable.class);
        skin.add("menu-panel", getDrawable(assets, MENU_PANEL), Drawable.class);
        skin.add("minimap-panel", getDrawable(assets, MINIMAP_PANEL), Drawable.class);
        skin.add("selection-panel", getDrawable(assets, SELECTION_PANEL), Drawable.class);
        skin.add("unit-panel", getDrawable(assets, UNIT_PANEL), Drawable.class);
        return skin;
    }

    private ControlPaneStyle getControlPaneStyle() {
        ControlPaneStyle style = new ControlPaneStyle();
        style.showActions = true;
        style.showStatus = true;
        style.showMenuButton = !controls.hasMenuButton();
        style.showMiniMap = style.showMenuButton;
        return style;
    }

    private LabelStyle getFontStyle() {
        LabelStyle style = new LabelStyle();
        style.font = Fonts.ARIAL;
        style.fontColor = Color.WHITE;
        return style;
    }

    private TextButtonStyle getButtonStyle() {
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = Fonts.ARIAL;
        textButtonStyle.fontColor = Color.WHITE;
        textButtonStyle.up = getDrawable(assets, BUTTON_ENABLED);
        textButtonStyle.over = textButtonStyle.up;
        textButtonStyle.checked = textButtonStyle.up;
        textButtonStyle.checkedOver = textButtonStyle.up;
        textButtonStyle.disabled = getDrawable(assets, BUTTON_DISABLED);
        textButtonStyle.down = getDrawable(assets, BUTTON_SELECTED);
        return textButtonStyle;
    }

    private ProgressBarStyle getBuildingProgressStyle() {
        ProgressBarStyle style = new ProgressBarStyle();
        style.background = getDrawable(assets, BUILDING_BACKGROUND);
        style.knob = getDrawable(assets, BUILDING_FILL);
        style.knobBefore = style.knob;
        return style;
    }

    private ProductionDetailsStyle getProductionDetailsStyle() {
        ProductionDetailsStyle style = new ProductionDetailsStyle();
        style.trainBackground = getDrawable(assets, ACTION_BUTTON);
        style.trainPeasantIcon = getDrawable(assets, ICONS, 0, 0, 46, 38);
        style.trainFootmanIcon = getDrawable(assets, ICONS, 92, 0, 46, 38);
        style.upgradeArrowDamageIcon = getDrawable(assets, ICONS, 184, 912, 46, 38);
        return style;
    }

    private HealthBarStyle getHealthBarStyle() {
        HealthBarStyle style = new HealthBarStyle();
        style.highBar = getDrawable(assets, HEALTH_PROGRESS_HIGH);
        style.mediumBar = getDrawable(assets, HEALTH_PROGRESS_MEDIUM);
        style.lowBar = getDrawable(assets, HEALTH_PROGRESS_LOW);
        return style;
    }

    private SelectionButtonStyle getSelectionButtonStyle() {
        SelectionButtonStyle style =  new SelectionButtonStyle();
        style.closeButtonEnabled = getDrawable(assets, UNSELECT_BUTTON);
        style.closeButtonDisabled = getDrawable(assets, UNSELECT_BUTTON);
        style.closeButtonSelected = getDrawable(assets, UNSELECT_BUTTON);
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
            case BuildCancelButton: return getDrawable(assets, ICONS, 46, 684, 46, 38);
            case MoveButton: return getDrawable(assets, ICONS, 138, 608, 46, 38);
            case StopButton: return getDrawable(assets, ICONS, 184, 1216, 46, 38);
            case AttackButton: return getDrawable(assets, ICONS, 46, 874, 46, 38);
            case DefendButton: return getDrawable(assets, ICONS, 0, 1368, 46, 38);
            case PatrolButton: return getDrawable(assets, ICONS, 138, 1330, 46, 38);
            case RepairButton: return getDrawable(assets, ICONS, 0, 646, 46, 38);
            case GatherButton: return getDrawable(assets, ICONS, 46, 646, 46, 38);
            case BuildSimpleButton: return getDrawable(assets, ICONS, 92, 646, 46, 38);
            case BuildAdvancedButton: return getDrawable(assets, ICONS, 138, 646, 46, 38);
            case BuildBarracksButton: return getDrawable(assets, ICONS, 92, 304, 46, 38);
            case BuildFarmButton: return getDrawable(assets, ICONS, 138, 266, 46, 38);
            case BuildLumberMillButton: return getDrawable(assets, ICONS, 184, 304, 46, 38);
            case BuildTownHallButton: return getDrawable(assets, ICONS, 0, 304, 46, 38);
            case BuildStablesButton: return getDrawable(assets, ICONS, 46, 418, 46, 38);
            case TrainFootmanButton: return getDrawable(assets, ICONS, 92, 0, 46, 38);
            case TrainPeasantButton: return getDrawable(assets, ICONS, 0, 0, 46, 38);
            case UpgradeArrowDamageButton: return getDrawable(assets, ICONS, 184, 912, 46, 38);
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
            case BuildCancelButton: return getDrawable(assets, ICONS_DISABLED, 46, 684, 46, 38);
            case MoveButton: return getDrawable(assets, ICONS_DISABLED, 138, 608, 46, 38);
            case StopButton: return getDrawable(assets, ICONS_DISABLED, 184, 1216, 46, 38);
            case AttackButton: return getDrawable(assets, ICONS_DISABLED, 46, 874, 46, 38);
            case DefendButton: return getDrawable(assets, ICONS_DISABLED, 0, 1368, 46, 38);
            case PatrolButton: return getDrawable(assets, ICONS_DISABLED, 138, 1330, 46, 38);
            case RepairButton: return getDrawable(assets, ICONS_DISABLED, 0, 646, 46, 38);
            case GatherButton: return getDrawable(assets, ICONS_DISABLED, 46, 646, 46, 38);
            case BuildSimpleButton: return getDrawable(assets, ICONS_DISABLED, 92, 646, 46, 38);
            case BuildAdvancedButton: return getDrawable(assets, ICONS_DISABLED, 138, 646, 46, 38);
            case BuildBarracksButton: return getDrawable(assets, ICONS_DISABLED, 92, 304, 46, 38);
            case BuildFarmButton: return getDrawable(assets, ICONS_DISABLED, 138, 266, 46, 38);
            case BuildLumberMillButton: return getDrawable(assets, ICONS_DISABLED, 184, 304, 46, 38);
            case BuildTownHallButton: return getDrawable(assets, ICONS_DISABLED, 0, 304, 46, 38);
            case BuildStablesButton: return getDrawable(assets, ICONS_DISABLED, 46, 418, 46, 38);
            case TrainFootmanButton: return getDrawable(assets, ICONS_DISABLED, 92, 0, 46, 38);
            case TrainPeasantButton: return getDrawable(assets, ICONS_DISABLED, 0, 0, 46, 38);
            case UpgradeArrowDamageButton: return getDrawable(assets, ICONS_DISABLED, 184, 912, 46, 38);
            default: throw new UnsupportedOperationException();
        }
    }
}
