/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.spell;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionResult;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.combatant.spellcaster.SpellCaster;

import javax.inject.Inject;
import java.util.Collection;

import static com.evilbird.engine.action.ActionResult.Complete;

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
    public ActionResult act(float time) {
        removeCastingSpell();
        setUnhighlighted();
        return Complete;
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
