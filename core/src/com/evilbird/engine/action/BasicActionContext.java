/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action;

import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;

public class BasicActionContext implements ActionContext
{
    private Item item;
    private Item target;
    private UserInput input;
    private boolean feedback;

    public BasicActionContext(Item item, Item target, UserInput input) {
        this(item, target, input, input != null);
    }

    public BasicActionContext(Item item, Item target, UserInput input, boolean feedback) {
        this.item = item;
        this.target = target;
        this.input = input;
        this.feedback = feedback;
    }

    @Override
    public Item getItem() {
        return item;
    }

    @Override
    public Item getTarget() {
        return target;
    }

    @Override
    public UserInput getInput() {
        return input;
    }

    @Override
    public boolean showFeedback() {
        return feedback;
    }
}
