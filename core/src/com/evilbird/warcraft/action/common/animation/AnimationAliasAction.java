/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.animation;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.AnimatedItem;

/**
 * Instances of this class assigned an alias to a given animation identifier.
 *
 * @author Blair Butterworth
 */
public class AnimationAliasAction extends BasicAction
{
    private Identifier id;
    private Identifier alias;

    public AnimationAliasAction(Identifier id, Identifier alias) {
        this.id = id;
        this.alias = alias;
    }

    @Override
    public boolean act(float delta) {
        AnimatedItem item = (AnimatedItem)getItem();
        item.setAnimationAlias(alias, id);
        return true;
    }
}
