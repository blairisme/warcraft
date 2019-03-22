/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.select;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.common.AudibleAction;
import com.evilbird.engine.action.common.SelectAction;
import com.evilbird.engine.action.framework.DelegateAction;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.UnitSound;

import javax.inject.Inject;

/**
 * Instances of this {@link Action} invert the selected status of a given
 * {@link Item}.
 *
 * @author Blair Butterworth
 */
public class SelectToggle extends DelegateAction
{
    private SelectObserver observer;
    private SelectAction select;
    private AudibleAction audio;

    @Inject
    public SelectToggle() {
        select = new SelectAction();
        audio = new AudibleAction();
        delegate = new ParallelAction(select, audio);

//      scenario(SelectItem).givenItem(isAlive()).whenItem(isUnselected()).then(select(), play(Selected));
//      scenario(DeselectItem).givenItem(isAlive()).whenItem(isSelected()).then(deselect());
    }

    @Override
    public boolean act(float delta) {
        notifyOnSelect();
        return delegate.act(delta);
    }

    @Override
    public void setItem(Item item) {
        super.setItem(item);
        boolean selected = item != null && item.getSelectable() && !item.getSelected();
        select.setSelected(selected);
        audio.setSound(selected ? UnitSound.Selected : null);
    }

    public void setObserver(SelectObserver observer) {
        this.observer = observer;
    }

    private void notifyOnSelect() {
        if (observer != null) {
            observer.onSelect(select.getItem(), select.getSelected());
        }
    }
}
