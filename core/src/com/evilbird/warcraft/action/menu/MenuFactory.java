/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.menu;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.common.ActionRecipient;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.warcraft.action.ActionProvider;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * Instances of this class aid navigation through HUD menus.
 *
 * @author Blair Butterworth
 */
public class MenuFactory implements ActionProvider
{
    private InjectedPool<MenuNavigateAction> navigatePool;
    private InjectedPool<MenuOverlayAction> overlayPool;
    private InjectedPool<MapNavigate> mapNavigatePool;

    @Inject
    public MenuFactory(
        InjectedPool<MapNavigate> mapNavigatePool,
        InjectedPool<MenuNavigateAction> navigatePool,
        InjectedPool<MenuOverlayAction> overlayPool)
    {
        this.overlayPool = overlayPool;
        this.navigatePool = navigatePool;
        this.mapNavigatePool = mapNavigatePool;
    }

    @Override
    public Action get(ActionIdentifier identifier) {
        Validate.isInstanceOf(MenuActions.class, identifier);
        MenuActions menuAction = (MenuActions)identifier;

        switch (menuAction) {
            case IngameMenu:
            case FailureMenu:
            case VictoryMenu: return getOverlayAction(menuAction);
            case ActionsMenu:
            case BuildSimpleMenu:
            case BuildAdvancedMenu: return getNavigateAction(menuAction);
            case MapNavigate: return mapNavigatePool.obtain();
            default: throw new UnsupportedOperationException();
        }
    }

    private Action getOverlayAction(MenuActions action) {
        MenuOverlayAction result = overlayPool.obtain();
        result.setIdentifier(action);
        return result;
    }

    private Action getNavigateAction(MenuActions action) {
        MenuNavigateAction result = navigatePool.obtain();
        result.setSource(ActionRecipient.Parent);
        result.setIdentifier(action);
        return result;
    }
}
