package HelpFunctions;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class HelpingFunctions {
    public static void alert(String title, String contenue){
        Alert alerte = new Alert(Alert.AlertType.INFORMATION);
        alerte.initStyle(StageStyle.UTILITY);
        alerte.setTitle(title);
        alerte.setHeaderText(null);
        alerte.setContentText(contenue);
        alerte.showAndWait();
    }
}