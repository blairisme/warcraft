/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.select;

import com.evilbird.engine.action.common.AudibleAction;
import com.evilbird.engine.action.common.SelectAction;
import com.evilbird.engine.action.framework.DelegateAction;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.animated.Audible;
import com.evilbird.warcraft.item.unit.UnitSound;

import javax.inject.Inject;

public class SelectToggleAction extends DelegateAction
{
    private boolean notified;
    private SelectObserver observer;
    private SelectAction select;
    private AudibleAction audio;

    @Inject
    public SelectToggleAction() {
        select = new SelectAction();
        audio = new AudibleAction();
        delegate = new ParallelAction(select, audio);
    }

    @Override
    public boolean act(float delta) {
        notifySelected();
        return delegate.act(delta);
    }

    @Override
    public void setItem(Item item) {
        super.setItem(item);
        boolean selected = item != null && !item.getSelected();
        select.setSelected(selected);
        audio.setSound(selected ? UnitSound.Selected : null);
    }

    public void setObserver(SelectObserver observer) {
        this.observer = observer;
    }

    @Override
    public void reset() {
        super.reset();
        notified = false;
    }

    private void notifySelected() {
        if (!notified && observer != null) {
            notified = true;
            observer.onSelect(select.getItem(), select.getSelected());
        }
    }
}
