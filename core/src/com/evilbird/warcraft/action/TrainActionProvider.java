package com.evilbird.warcraft.action;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.CreateAction;
import com.evilbird.engine.action.ModifyAction;
import com.evilbird.engine.action.SequenceAction;
import com.evilbird.engine.action.duration.ActionDuration;
import com.evilbird.engine.action.duration.InstantDuration;
import com.evilbird.engine.action.duration.TimeDuration;
import com.evilbird.engine.action.modifier.ActionModifier;
import com.evilbird.engine.action.modifier.ConstantModifier;
import com.evilbird.engine.action.modifier.DeltaModifier;
import com.evilbird.engine.action.value.ActionValue;
import com.evilbird.engine.action.value.ItemValue;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;

import static com.evilbird.engine.action.modifier.DeltaType.PerSecond;
import static com.evilbird.engine.item.ItemProperties.Position;
import static com.evilbird.engine.item.ItemProperties.Visible;
import static com.evilbird.warcraft.item.unit.building.BuildingProperties.Producing;
import static com.evilbird.warcraft.item.unit.building.BuildingProperties.Progress;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class TrainActionProvider implements ActionProvider
{
    private ItemFactory itemFactory;

    @Inject
    public TrainActionProvider(ItemFactory itemFactory)
    {
        this.itemFactory = itemFactory;
    }

    @Override
    public Action get(ActionType action, Item item, Item target, UserInput input)
    {
        Action startProducing = setProducing((Building)item, true);
        Action incrementProgress = new ProgressAction((Building)item);
        Action endProducing = setProducing((Building)item, false);
        Action addProduct = addProduct(item, action);
        return new SequenceAction(startProducing, incrementProgress, endProducing, addProduct);
    }

    /*
    private Action incrementProgress(Item building)
    {
        ActionValue value = new ItemValue(building, Progress);
        Action reset = new ModifyAction(value, new ConstantModifier(0f), new InstantDuration());
        Action increment = new ModifyAction(value, new DeltaModifier(0.05f, PerSecond, 0f, 1f), new TimeDuration(20f));
        return new SequenceAction(reset, increment);
    }

    private Action setProducing(Item building, boolean producing)
    {
        ActionValue value = new ItemValue(building, Producing);
        ActionModifier modifier = new ConstantModifier(producing);
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(value, modifier, duration);
    }
    */

    //TODO: Remove
    private Action setProducing(Building building, boolean producing)
    {
        return new Action() {
            @Override
            public boolean act(float delta) {
                building.setProducing(producing);
                return false;
            }
        };
    }

    private Action addProduct(Item building, ActionType action)
    {
        ItemComposite player = building.getParent();
        UnitType type = getUnitType(action);
        Identifier identifier = new NamedIdentifier();
        Vector2 position = getProductLocation(building);
        return new CreateAction(player, type, itemFactory, identifier, position, false);
    }

    private Vector2 getProductLocation(Item building)
    {
        Vector2 location = building.getPosition();
        return new Vector2(location.x - 96, location.y);
    }

    private UnitType getUnitType(ActionType actionType)
    {
        switch (actionType){
            case TrainFootman: return UnitType.Footman;
            case TrainPeasant: return UnitType.Peasant;
            default: throw new UnsupportedOperationException();
        }
    }
}
