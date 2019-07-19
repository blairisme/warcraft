/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.intro;

import com.evilbird.engine.common.function.ParameterizedSupplier;
import com.evilbird.engine.menu.MenuIdentifier;

/**
 * Defines types of introduction menus, one for each level in the Human and Orc
 * campaigns.
 *
 * @author Blair Butterworth
 */
public enum IntroMenuType implements MenuIdentifier, ParameterizedSupplier<String, String>
{
    Human1,
    Human2,
    Human3,
    Human4,
    Human5,
    Human6,
    Human7,
    Human8,
    Human9,
    Human10,
    Human11,
    Human12,
    Human13,
    Human14,

    Orc1,
    Orc2,
    Orc3,
    Orc4,
    Orc5,
    Orc6,
    Orc7,
    Orc8,
    Orc9,
    Orc10,
    Orc11,
    Orc12,
    Orc13,
    Orc14;

    public int getIndex() {
        return ordinal() >= Orc1.ordinal() ? ordinal() - Orc1.ordinal() + 1 : ordinal() + 1;
    }

    public boolean isHuman() {
        return !isOrc();
    }

    public boolean isOrc() {
        return this.ordinal() >= Orc1.ordinal();
    }

    @Override
    public String get(String key) {
        if (key.equals("faction")) {
            return isHuman() ? "human" : "orc";
        }
        if (key.equals("name")) {
            return "intro" + getIndex();
        }
        return null;
    }
}
