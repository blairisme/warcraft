///*
// * Blair Butterworth (c) 2019
// *
// * This work is licensed under the MIT License. To view a copy of this
// * license, visit
// *
// *      https://opensource.org/licenses/MIT
// */
//
//package com.evilbird.warcraft.item.hud;
//
//import com.evilbird.engine.common.inject.IdentifiedAssetProvider;
//import com.evilbird.engine.common.lang.Identifier;
//import com.evilbird.engine.item.ItemRoot;
//import org.apache.commons.lang3.Validate;
//
//import javax.inject.Inject;
//
//public class HudStateFactory implements IdentifiedAssetProvider<ItemRoot>
//{
//    private HudControlFactory controlFactory;
//
//    @Inject
//    public HudStateFactory(HudControlFactory controlFactory) {
//        this.controlFactory = controlFactory;
//    }
//
//    @Override
//    public ItemRoot get(Identifier identifier) {
//        Validate.isInstanceOf(HudType.class, identifier);
//        return get((HudType)identifier);
//    }
//
//    private ItemRoot get(HudType hudType) {
//        ItemRoot hud = new ItemRoot();
//        //hud.setViewport(viewport);
//
//        for (HudControl hudControl: hudType.getControls()) {
//            hud.addItem(controlFactory.get(hudControl));
//        }
//        return hud;
//    }
//
//    @Override
//    public void load() {
//        controlFactory.load();
//    }
//}
