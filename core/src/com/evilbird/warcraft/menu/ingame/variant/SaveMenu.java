/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.menu.ingame.variant;

import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.evilbird.engine.common.collection.Lists;
import com.evilbird.engine.common.control.LabelButton;
import com.evilbird.engine.common.control.ListPane;
import com.evilbird.engine.common.control.ScrollBarPane;
import com.evilbird.engine.common.control.TextInput;
import com.evilbird.engine.common.file.FileNameValidator;
import com.evilbird.engine.state.StateIdentifier;
import com.evilbird.engine.state.StateService;
import com.evilbird.warcraft.menu.ingame.IngameMenu;
import com.evilbird.warcraft.menu.ingame.IngameMenuStrings;
import com.evilbird.warcraft.state.WarcraftSave;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;

import static com.evilbird.warcraft.menu.ingame.IngameMenuDimensions.Wide;
import static com.evilbird.warcraft.state.WarcraftStateType.UserState;

/**
 * Represents a user interface control that allows the user to save their game.
 *
 * @author Blair Butterworth
 */
public class SaveMenu extends IngameMenu
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SaveMenu.class);

    private StateService states;
    private IngameMenuStrings strings;
    private FileNameValidator nameValidator;

    private Cell<Label> nameError;
    private Cell<Label> listError;
    private TextInput nameField;
    private ScrollBarPane saveContainer;
    private ListPane<StateIdentifier> saveList;
    private LabelButton saveButton;
    private LabelButton deleteButton;

    public SaveMenu(IngameMenu menu, IngameMenuStrings strings, StateService states) {
        super(menu);
        this.strings = strings;
        this.states = states;
        this.nameValidator = new FileNameValidator();

        initializeMenu();
        initializeTextField();
        initializeList();
        initializeButtons();

        loadSaves();
    }

    private void initializeMenu() {
        setLayout(Wide);
        addTitle(strings.getSaveTitle());
    }

    private void initializeTextField() {
        nameField = addTextField();
        nameError = addErrorLabel();
        stage.setKeyboardFocus(nameField);
    }

    private void initializeList() {
        saveList = addList();
        saveList.addSelectionListener(this::saveSelected);
        saveContainer = (ScrollBarPane)saveList.getParent();
        listError = addErrorLabel();
    }

    private void initializeButtons() {
        List<LabelButton> buttons = addButtonRow(
            Pair.of(strings.getSaveButtonText(), this::createSave),
            Pair.of(strings.getDeleteButtonText(), this::deleteState),
            Pair.of(strings.getCancelButtonText(), this::showRootMenu));
        saveButton = buttons.get(0);
        deleteButton = buttons.get(1);
    }

    private void saveSelected(StateIdentifier save) {
        nameField.setText(save.toString());
        nameField.setCursorPosition(Integer.MAX_VALUE);
    }

    private void loadSaves() {
        try {
            List<StateIdentifier> saves = states.list(UserState);
            if (! saves.isEmpty()) {
                Lists.sort(saves, Comparator.comparing(Object::toString));
                StateIdentifier[] items = saves.toArray(new StateIdentifier[0]);

                saveList.setItems(items);
                saveList.setSelected(items[0]);
            }
        }
        catch (Throwable error) {
            LOGGER.error("Failed to list states", error);
            showListError(strings.getListSavesFailed());
        }
    }

    private void createSave() {
        try {
            String name = nameField.getText();
            if (name.isEmpty()) {
                showNameError(strings.getSaveMissingNameError());
            } else if (!nameValidator.isValidName(name)) {
                showNameError(strings.getSaveInvalidNameError());
            } else {
                states.set(new WarcraftSave(name), controller.getState());
                showState();
            }
        }
        catch (Throwable error) {
            LOGGER.error("Failed to save state", error);
            showSaveError(strings.getNewSaveFailed());
        }
    }

    private void deleteState() {
        try {
            StateIdentifier save = saveList.getSelected();
            if (save == null) {
                showListError(strings.getSaveNotSelectedError());
            } else {
                states.remove(save);
                saveList.removeItem(save);
            }
        }
        catch (Throwable error) {
            LOGGER.error("Failed to remove state", error);
            showDeleteError(strings.getRemoveSaveFailed());
        }
    }

    private void showNameError(String text) {
        hideError(listError);
        showError(nameError, text);

        resetErrorStyles();
        saveContainer.setStyle("default");
    }

    private void showListError(String text) {
        hideError(nameError);
        showError(listError, text);

        resetErrorStyles();
        saveContainer.setStyle("error");
    }

    private void showSaveError(String text) {
        hideError(nameError);
        showError(listError, text);

        resetErrorStyles();
        saveButton.setStyle("error");
    }

    private void showDeleteError(String text) {
        hideError(nameError);
        showError(listError, text);

        resetErrorStyles();
        deleteButton.setStyle("error");
    }

    private void showError(Cell<Label> label, String text) {
        label.getActor().setText(text);
        label.height(20);
        label.padBottom(6);
    }

    private void hideError(Cell<Label> label) {
        label.getActor().setText("");
        label.height(0);
        label.padBottom(0);
    }

    private void resetErrorStyles() {
        nameField.setStyle("default");
        saveContainer.setStyle("default");
        saveButton.setStyle("default");
        deleteButton.setStyle("default");
    }
}
