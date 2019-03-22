/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.menu;

import com.evilbird.engine.action.common.ActionTarget;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.lang.Identifier;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.inject.Inject;

/**
 * Instances of this class aid navigation through user interface menus.
 *
 * @author Blair Butterworth
 */
public class MenuNavigateAction extends BasicAction
{
    private ActionTarget source;

    @Inject
    public MenuNavigateAction() {
    }

    public void setSource(ActionTarget source) {
        this.source = source;
    }

    @Override
    public boolean act(float delta) {
        MenuProvider menuProvider = getMenuProvider();
        menuProvider.showMenu(getMenuIdentifier());
        return true;
    }

    private MenuProvider getMenuProvider() {
        switch (source) {
            case Item: return (MenuProvider)getItem();
            case Target: return (MenuProvider)getTarget();
            case Parent: return (MenuProvider)getItem().getParent();
            default: throw new UnsupportedOperationException();
        }
    }

    private Identifier getMenuIdentifier() {
        MenuActions menuAction = (MenuActions)getIdentifier();
        return menuAction.getMenuIdentifier();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;

        MenuNavigateAction that = (MenuNavigateAction)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(source, that.source)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(source)
            .toHashCode();
    }
}