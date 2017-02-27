package com.evilbird.engine.action;

import com.evilbird.engine.item.Item;
import com.evilbird.engine.utility.Identifier;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ActionContext
{
    private Identifier action;
    private Item target;
    private Object data;

    public ActionContext(Identifier action, Item target, Object data)
    {
        this.action = action;
        this.target = target;
        this.data = data;
    }

    public Identifier getAction()
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
