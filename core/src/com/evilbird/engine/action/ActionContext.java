package com.evilbird.engine.action;

import com.evilbird.engine.item.Item;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ActionContext
{
    private ActionIdentifier action;
    private Item target;
    private Object data;

    public ActionContext(ActionIdentifier action, Item target, Object data)
    {
        this.action = action;
        this.target = target;
        this.data = data;
    }

    public ActionIdentifier getAction()
    {
        return action;
    }

    public Item getTarget()
    {
        return target;
    }

    public Object getData()
    {
        return data;
    }
}
