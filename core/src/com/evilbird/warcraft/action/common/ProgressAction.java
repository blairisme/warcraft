package com.evilbird.warcraft.action.common;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.item.Reference;
import com.evilbird.warcraft.item.unit.building.Building;

/**
 * Created by blair on 19/09/2017.
 */
public class ProgressAction extends Action
{
    private Building building;
    private Reference<Building> reference;

    public ProgressAction(Building building)
    {
        this.building = building;
    }

    public ProgressAction(Reference<Building> reference)
    {
        this.reference = reference;
    }

    @Override
    public boolean act(float delta)
    {


        /*
        ActionValue value = new ItemValue(building, Progress);
        Action reset = new ModifyAction(value, new ConstantModifier(0f), new InstantDuration());
        Action increment = new ModifyAction(value, new DeltaModifier(0.05f, PerSecond, 0f, 1f), new TimeDuration(20f));
        return new SequenceAction(reset, increment);
         */

        /*
        ActionValue value = new ItemReferenceValue(player, building, Progress);
        Action reset = new ModifyAction(value, new ConstantModifier(0f), new InstantDuration());
        Action increment = new ModifyAction(value, new DeltaModifier(0.05f, PerSecond, 0f, 1f), new TimeDuration(20f));
        return new SequenceAction(reset, increment);
         */
        return false;
    }
}
