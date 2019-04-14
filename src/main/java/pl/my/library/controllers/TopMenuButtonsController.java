package pl.my.library.controllers;

/*
kontroller przycisków w górnym menu
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class TopMenuButtonsController {

    public static final String LIBRARY_FXML = "/fxml/Library.fxml";
    public static final String LIST_BOOK_FXML = "/fxml/ListBook.fxml";
    public static final String STATS_FXML = "/fxml/Stats.fxml";
    public static final String ADD_BOOK_FXML = "/fxml/AddBook.fxml";
    public static final String ADD_CATEGORY_FXML = "/fxml/AddCategory.fxml";
    public static final String ADD_AUTHOR_FXML = "/fxml/AddAuthor.fxml";
    private MainController mainController;

    @FXML
    private ToggleGroup toggleButtons;

    @FXML
    public void openLibrary() {
        mainController.setCenter(LIBRARY_FXML);
    }

    @FXML
    public void openListBook() {
        mainController.setCenter(LIST_BOOK_FXML);
    }

    @FXML
    public void openStats() {
        mainController.setCenter(STATS_FXML);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void addBook() {
        resetToggleButtons();
        mainController.setCenter(ADD_BOOK_FXML);
    }

    @FXML
    public void addCategory(ActionEvent actionEvent) {
        resetToggleButtons();
        mainController.setCenter(ADD_CATEGORY_FXML);
    }

    @FXML
    public void addAuthor(ActionEvent actionEvent) {
        resetToggleButtons();
        mainController.setCenter(ADD_AUTHOR_FXML);
    }

    @FXML
    private void resetToggleButtons() {
        if (toggleButtons.getSelectedToggle() != null) {
            toggleButtons.getSelectedToggle().setSelected(false);
        }
    }
}
