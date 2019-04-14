package pl.my.library.utils;

/*
klasa przechowująca okna dialogowe
okna informujące o aplikacji lub o błędzie
 */

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;
import java.util.ResourceBundle;

public class DialogsUtils {

    static ResourceBundle bundle = FxmlUtils.getResourceBundle();

    public static void dialogAboutApplication() {
        Alert informationsAlert = new Alert(Alert.AlertType.INFORMATION);
        informationsAlert.setTitle(bundle.getString("about.title"));
        informationsAlert.setHeaderText(bundle.getString("about.header"));
        informationsAlert.setContentText(bundle.getString("about.content"));
        informationsAlert.showAndWait();
    }

    public static Optional<ButtonType> confirmationDialog() {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle(bundle.getString("exit.title"));
        confirmationAlert.setHeaderText(bundle.getString("exit.header"));
        Optional<ButtonType> result = confirmationAlert.showAndWait();
        return result;
    }

    public static void errorDialog(String error) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(bundle.getString("error.title"));
        errorAlert.setHeaderText(bundle.getString("error.header"));

        TextArea textArea = new TextArea(error);
        errorAlert.getDialogPane().setContent(textArea);

        errorAlert.showAndWait();
    }

    public static String editDialog(String value) {
        TextInputDialog dialog = new TextInputDialog(value);
        dialog.setTitle(bundle.getString("edit.title"));
        dialog.setHeaderText(bundle.getString("edit.header"));
        dialog.setContentText(bundle.getString("edit.content"));
        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }
/*
    public static void addDialog(String error){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Błąd dodania kategorii!");
        errorAlert.setHeaderText("Nie można dodać takiej ketegorii. Już istnieje kategoria o takiej nazwie.");

        TextArea textArea = new TextArea("Taka kategoria już istnieje.");
        errorAlert.getDialogPane().setContent(textArea);

        errorAlert.showAndWait();
    }
*/
}
