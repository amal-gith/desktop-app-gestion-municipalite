package sample1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class navigationCntrl implements Initializable {
    @FXML
    private Button admin;

    @FXML
    private Button resp;

    @FXML
    private Button financier;

    @FXML
    private Button guichet;

    @FXML
     public void clickme(ActionEvent event) throws Exception{
        Stage stage = null;
        Parent root =null;
        if(event.getSource()==admin){
            stage = (Stage) admin.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../sample1/employe.fxml"));
        }
        else if (event.getSource()==resp){
                stage = (Stage) resp.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("../sample1/reglage.fxml"));
        }
        else if (event.getSource()==financier){
            stage = (Stage) financier.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../sample1/choisi.fxml"));
        }
        else if (event.getSource()==guichet) {
            stage = (Stage) guichet.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../sample1/guich.fxml"));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
