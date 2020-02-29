/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.menu;

import com.evilbird.engine.action.ActionResult;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectGroup;

import javax.inject.Inject;

/**
 * Instances of this class aid navigation through user interface menus.
 *
 * @author Blair Butterworth
 */
public class MenuNavigateAction extends BasicAction
{
    @Inject
    public MenuNavigateAction() {
    }

    @Override
    public ActionResult act(float delta) {
        GameObject subject = getSubject();
        GameObjectGroup parent = subject.getParent();
        MenuProvider menuProvider = (MenuProvider)parent;
        menuProvider.showMenu(getMenuIdentifier());
        return ActionResult.Complete;
    }

    private Identifier getMenuIdentifier() {
        MenuActions menuAction = (MenuActions)getIdentifier();
        return menuAction.getMenuIdentifier();
    }
}