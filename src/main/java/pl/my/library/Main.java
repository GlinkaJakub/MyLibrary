package pl.my.library;

/*
Główna klasa aplikacji, otwiera okno ustaiwia boarderpane i takie tam
 */


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pl.my.library.database.dbuitls.DbManager;
import pl.my.library.utils.FillDatabase;
import pl.my.library.utils.FxmlUtils;

import java.util.Locale;

public class Main extends Application {

    public static final String BOARDER_PANE_MAIN_FXML = "/fxml/BoarderPaneMain.fxml";

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        Locale.setDefault(new Locale("pl"));

        Pane borderPane = FxmlUtils.fxmlLoader(BOARDER_PANE_MAIN_FXML);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle(FxmlUtils.getResourceBundle().getString("tittle.application"));
        primaryStage.show();

        //tworzenie bazy danych
        DbManager.initDatabse();
        FillDatabase.fillDatabase();
    }
}
