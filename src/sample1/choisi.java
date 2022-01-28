package sample1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class choisi implements Initializable {
    @FXML
    private Button profit;

    @FXML
    private Button gp;

    @FXML
    private Button gain;

    public void clickme(ActionEvent event) throws IOException {
        Stage stage = null;
        Parent root =null;
        if(event.getSource()==profit){
            stage = (Stage) profit.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../sample1/profit.fxml"));
        }
        else if (event.getSource()==gp){
            stage = (Stage) gp.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../sample1/projet.fxml"));
        }
        else if (event.getSource()==gain){
            stage = (Stage) gain.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../sample1/GaineA.fxml"));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void retour(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        stage.close();
        int pred = 0;
        if (pred == 0) {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../sample1/navigation.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
