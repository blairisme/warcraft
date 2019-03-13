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
//import com.evilbird.engine.common.lang.Identifier;
//
//import java.util.Arrays;
//import java.util.Collection;
//
//public enum HudType implements Identifier
//{
//    Human (HudControl.ResourcePane, HudControl.ControlPane),
//    Orc   ();
//
//    private Collection<HudControl> controls;
//
//    HudType(HudControl ... controls) {
//        this(Arrays.asList(controls));
//    }
//
//    HudType(Collection<HudControl> controls) {
//        this.controls = controls;
//    }
//
//    public Collection<HudControl> getControls() {
//        return controls;
//    }
//}
