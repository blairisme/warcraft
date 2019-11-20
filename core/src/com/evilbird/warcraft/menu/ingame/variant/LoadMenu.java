/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.ingame.variant;

import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.evilbird.engine.common.collection.Lists;
import com.evilbird.engine.common.control.ListPane;
import com.evilbird.engine.common.control.ScrollBarPane;
import com.evilbird.engine.state.StateIdentifier;
import com.evilbird.engine.state.StateService;
import com.evilbird.warcraft.menu.ingame.IngameMenu;
import com.evilbird.warcraft.menu.ingame.IngameMenuStrings;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;

import static com.evilbird.warcraft.menu.ingame.IngameMenuDimensions.Wide;
import static com.evilbird.warcraft.state.WarcraftStateType.UserState;

/**
 * Represents a user interface control that allows the user to load a
 * previously saved game.
 *
 * @author Blair Butterworth
 */
public class LoadMenu extends IngameMenu
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LoadMenu.class);

    private StateService states;
    private IngameMenuStrings strings;

    private Cell<Label> listError;
    private ScrollBarPane listContainer;
    private ListPane<StateIdentifier> list;

    public LoadMenu(IngameMenu menu, IngameMenuStrings strings, StateService states) {
        super(menu);
        this.strings = strings;
        this.states = states;

        initializeMenu();
        initializeList();
        initializeButtons();

        loadSaves();
    }

    private void initializeMenu() {
        setLayout(Wide);
        addTitle(strings.getLoadTitle());
    }

    private void initializeList() {
        list = addList();
        listContainer = (ScrollBarPane)list.getParent();
        listError = addErrorLabel();
    }

    private void initializeButtons() {
        addButtonRow(
            Pair.of(strings.getLoadButtonText(), this::loadSave),
            Pair.of(strings.getCancelButtonText(), this::showRootMenu));
    }

    private void loadSaves() {
        try {
            List<StateIdentifier> saves = states.list(UserState);
            if (! saves.isEmpty()) {
                Lists.sort(saves, Comparator.comparing(Object::toString));
                StateIdentifier[] items = saves.toArray(new StateIdentifier[0]);

                list.setItems(items);
                list.setSelected(items[0]);
            }
        }
        catch (Throwable error) {
            LOGGER.error("Failed to list states", error);
            showError(strings.getListSavesFailed());
        }
    }

    private void loadSave() {
        try {
            showState(list.getSelected());
        }
        catch (Throwable error) {
            LOGGER.error("Failed to load state", error);
            showError(strings.getLoadSaveFailed());
        }
    }

    private void showError(String text) {
        listError.getActor().setText(text);
        listError.height(20);
        listError.padBottom(6);
        listContainer.setStyle("error");
    }
}
