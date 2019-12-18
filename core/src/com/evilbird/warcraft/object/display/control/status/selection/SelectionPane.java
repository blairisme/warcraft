/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.control.status.selection;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.control.Grid;
import com.evilbird.warcraft.object.display.UserInterfaceControl;
import com.evilbird.warcraft.object.unit.Unit;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Instances of this class display the selected units.
 *
 * @author Blair Butterworth
 */
public class SelectionPane extends Grid
{
    private static final int SELECTION_MAX = 9;

    public SelectionPane(Skin skin) {
        super(3, 3);
        setSkin(skin);
        setBackground("selection-panel");
        setSize(176, 176);
        setCellPadding(3);
        setCellWidth(54);
        setCellHeight(53);
        setIdentifier(UserInterfaceControl.SelectionPane);
        setType(UserInterfaceControl.SelectionPane);
        setTouchable(Touchable.childrenOnly);
    }

    public void setItems(Collection<GameObject> selection) {
        removeObjects();
        addObjects(getUnitPanes(selection));
    }

    private Collection<GameObject> getUnitPanes(Collection<GameObject> gameObjects) {
        Collection<GameObject> result = new ArrayList<>(gameObjects.size());
        for (GameObject gameObject : gameObjects) {
            if (gameObject instanceof Unit) {
                result.add(getSelectionButton(gameObject));
            }
            if (result.size() == SELECTION_MAX) {
                break;
            }
        }
        return result;
    }

    private SelectionButton getSelectionButton(GameObject gameObject) {
        SelectionButton result = new SelectionButton(getSkin());
        result.set(gameObject);
        return result;
    }
}
