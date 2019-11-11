/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.combatant.SpellCaster;

import javax.inject.Inject;
import java.util.Collection;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;

/**
 * An {@link Action} that unhighlights highlighted units.
 *
 * @author Blair Butterworth
 */
public class SpellDeselect extends BasicAction
{
    @Inject
    public SpellDeselect() {
    }

    @Override
    public boolean act(float time) {
        removeCastingSpell();
        setUnhighlighted();
        return ActionComplete;
    }

    private void removeCastingSpell() {
        SpellCaster spellCaster = (SpellCaster) getSubject();
        spellCaster.setCastingSpell(null);
    }

    private void setUnhighlighted() {
        for (GameObject target: getTargets()) {
            setUnhighlighted(target);
        }
    }

    private void setUnhighlighted(GameObject target) {
        Unit unit = (Unit)target;
        unit.setHighlighted(false);
    }

    private Collection<GameObject> getTargets() {
        GameObject gameObject = getSubject();
        GameObjectContainer root = gameObject.getRoot();
        return root.findAll(UnitOperations::isHighlighted);
    }
}
