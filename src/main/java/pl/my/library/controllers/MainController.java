package pl.my.library.controllers;

/*
kontroller górnego menu
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pl.my.library.utils.DialogsUtils;
import pl.my.library.utils.FxmlUtils;

import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController {

    @FXML
    public BorderPane borderPane;

    @FXML
    private TopMenuButtonsController topMenuButtonsController;

    @FXML
    public void initialize() {
        topMenuButtonsController.setMainController(this);
    }

    //dodaje metode

    public void setCenter(String fxmlPath) {
        borderPane.setCenter(FxmlUtils.fxmlLoader((fxmlPath)));
    }

    //metoda zamykająca aplikacje
    public void closeApplication(ActionEvent actionEvent) {
        Optional<ButtonType> result = DialogsUtils.confirmationDialog();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
            System.exit(0);
        }
    }

    //zmiana motywu na Caspian
    public void setCaspian(ActionEvent actionEvent) {
        Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
    }

    //zmiana motywu na Modena
    public void setModena(ActionEvent actionEvent) {
        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
    }

    //wyświetlanie informacji o aplikacji
    public void about(ActionEvent actionEvent) {
        DialogsUtils.dialogAboutApplication();
    }

    //dzięki tej metodzie nasz program zawsze bedzie z przodu, nie można go ukryc za innymi aplikacjami
    public void setAlwaysOnTop(ActionEvent actionEvent) {
        Stage stage = (Stage) borderPane.getScene().getWindow();
        boolean value = ((CheckMenuItem) actionEvent.getSource()).selectedProperty().get();
        stage.setAlwaysOnTop(value);
    }
}
