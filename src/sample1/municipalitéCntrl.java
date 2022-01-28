package sample1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class municipalitéCntrl implements Initializable {
    @FXML
    private Button btnn;



    @FXML
    void clickme(ActionEvent event) throws IOException {
        Stage stage = null;
        Parent root =null;
        if(event.getSource()==btnn){
            stage = (Stage) btnn.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../sample1/login.fxml"));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
