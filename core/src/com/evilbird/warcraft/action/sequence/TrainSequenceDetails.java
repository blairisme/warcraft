//package com.evilbird.warcraft.action.sequence;
//
//import com.evilbird.engine.common.collection.Maps;
//import com.evilbird.warcraft.action.ActionType;
//import com.evilbird.warcraft.item.common.capability.ResourceIdentifier;
//import com.evilbird.warcraft.item.unit.UnitType;
//
//import java.util.Map;
//
//import static com.evilbird.warcraft.item.unit.UnitType.Footman;
//import static com.evilbird.warcraft.item.unit.UnitType.Peasant;
//import static com.evilbird.warcraft.item.unit.resource.ResourceType.Gold;
//
//public class TrainSequenceDetails
//{
//    private float trainTime;
//    private float unitWidth;
//    private UnitType unitType;
//    private Map<ResourceIdentifier, Float> unitCost;
//
//    private TrainSequenceDetails(float trainTime, float unitWidth, UnitType unitType, Map<ResourceIdentifier, Float> unitCost) {
//        this.trainTime = trainTime;
//        this.unitWidth = unitWidth;
//        this.unitType = unitType;
//        this.unitCost = unitCost;
//    }
//
//    public static TrainSequenceDetails forActionType(ActionType actionType) {
//        switch (actionType) {
//            case TrainFootman: return new TrainSequenceDetails(20f, 32f, Footman, Maps.of(Gold, 100f));
//            case TrainPeasant: return new TrainSequenceDetails(20f, 32f, Peasant, Maps.of(Gold, 100f));
//            default : throw new UnsupportedOperationException();
//        }
//    }
//
//    public float getTrainTime() {
//        return trainTime;
//    }
//
//    public float getUnitWidth() {
//        return unitWidth;
//    }
//
//    public UnitType getUnitType() {
//        return unitType;
//    }
//
//    public Map<ResourceIdentifier, Float> getUnitCost() {
//        return unitCost;
//    }
//}
