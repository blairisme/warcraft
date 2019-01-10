/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.user;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.item.Item;

/**
 * Instances of this class define operations common to all interactions.
 *
 * @author Blair Butterworth
 */
public abstract class AbstractInteraction implements Interaction
{
    protected Identifier targetType;
    protected Identifier hudType;
    protected Identifier selectedType;
    protected UserInputType inputType;

    public AbstractInteraction()
    {
        this.inputType = null;
        this.targetType = null;
        this.selectedType = null;
        this.hudType = null;
    }

    public void setInputType(UserInputType inputType)
    {
        this.inputType = inputType;
    }

    public void setTargetType(Identifier targetType)
    {
        this.targetType = targetType;
    }

    public void setSelectedType(Identifier selectedType)
    {
        this.selectedType = selectedType;
    }

    public void setHudType(Identifier hudType)
    {
        this.hudType = hudType;
    }
//
//    @Override
//    public void update(UserInput input, Item target, Item worldSelection, Item hudSelection)
//    {
//        apply(input, target, worldSelection);
//    }

    @Override
    public boolean applies(UserInput input, Item target, Item worldSelection, Item hudSelection)
    {
        if (inputType != null && !Objects.equals(inputType, input.getType())){
            return false;
        }
        if (targetType != null && !Objects.equals(targetType, getType(target))){
            return false;
        }
        if (selectedType != null && !Objects.equals(selectedType, getType(worldSelection))){
            return false;
        }
        if (hudType != null && !Objects.equals(hudType, getType(hudSelection))){
            return false;
        }
        return true;
    }

    //protected abstract void apply(UserInput input, Item target, Item selected);

    private Identifier getType(Item item)
    {
        return item != null ? item.getType() : null;
    }
}
