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
import com.evilbird.engine.common.file.FileNameValidator;
import com.evilbird.engine.item.specialized.ListPane;
import com.evilbird.engine.item.specialized.ScrollBarPane;
import com.evilbird.engine.item.specialized.TextInput;
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
        addButtonRow(
            Pair.of(strings.getSaveButtonText(), this::createSave),
            Pair.of(strings.getDeleteButtonText(), this::deleteState),
            Pair.of(strings.getCancelButtonText(), this::showRootMenu));
    }

    private void saveSelected(StateIdentifier save) {
        nameField.setText(save.toString());
        nameField.setCursorPosition(Integer.MAX_VALUE);
    }

    private void loadSaves() {
        try {
            List<StateIdentifier> saves = states.list(UserState);
            if (! saves.isEmpty()) {
                saves.sort(Comparator.comparing(Object::toString));
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
                saveState(new WarcraftSave(name));
            }
        }
        catch (Throwable error) {
            LOGGER.error("Failed to save state", error);
            showListError(strings.getNewSaveFailed());
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
            showListError(strings.getRemoveSaveFailed());
        }
    }

    private void showNameError(String text) {
        hideError(listError);
        showError(nameError, text);

        nameField.setStyle("error");
        saveContainer.setStyle("default");
    }

    private void showListError(String text) {
        hideError(nameError);
        showError(listError, text);

        nameField.setStyle("default");
        saveContainer.setStyle("error");
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
}
