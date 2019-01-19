/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.lang;

public interface Selectable
{
    public boolean getSelected();

    public boolean getSelectable();

    public void setSelected(boolean selected);

    public void setSelectable(boolean selectable);
}
