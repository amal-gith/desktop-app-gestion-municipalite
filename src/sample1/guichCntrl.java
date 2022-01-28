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

public class guichCntrl implements Initializable {
    @FXML
    private Button Autorisation;

    @FXML
    private Button Reclamation;


    @FXML
    public void clickme(javafx.event.ActionEvent event) throws  Exception {


        Stage stage = null;
        Parent root =null;


        if(event.getSource()==Autorisation){
            stage = (Stage) Autorisation.getScene().getWindow();
            try {
                root = FXMLLoader.load(getClass().getResource("../sample1/autorisation.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (event.getSource()==Reclamation){
            stage = (Stage) Reclamation.getScene().getWindow();
            try {
                root = FXMLLoader.load(getClass().getResource("../sample1/Reclamation.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


        
}
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

   public void retourn(ActionEvent event) throws IOException {
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
}