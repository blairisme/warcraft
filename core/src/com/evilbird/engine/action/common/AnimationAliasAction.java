/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.common;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.specialized.Viewable;

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
        Viewable item = (Viewable)getItem();
        item.setAnimationAlias(alias, id);
        return true;
    }

    public static AnimationAliasAction setAnimation(Identifier id, Identifier alias) {
        return new AnimationAliasAction(id, alias);
    }
}
