package sample1;

import HelpFunctions.HelpingFunctions;
import entity.véhicules;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class véhiculeCntrl  implements Initializable {
    @FXML
    private TextField Matricule;

    @FXML
    private TextField Nom;

    @FXML
    private TextField Type;

    @FXML
    private TextField DateAchat;

    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnSupprimer;

    @FXML
    private Button btnChercher;

    @FXML
    private TableView<véhicules> tab;
    @FXML
    private TextField Nombre;
    @FXML
    private TableColumn<véhicules,Integer> Tnombre;

    @FXML
    private TableColumn<véhicules, Integer> Tmatricule;

    @FXML
    private TableColumn<véhicules, String> Tnom;

    @FXML
    private TableColumn<véhicules, String> Ttype;

    @FXML
    private TableColumn<véhicules, String> Tdateachat;

    @FXML

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        show();
    }

    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE", "PFA1", "PFA1");
            return conn;
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
            return null;
        }
    }

    public ObservableList<véhicules> getvehiculeList() {
        ObservableList<véhicules> vehiculesList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String Query = "SELECT * FROM vehicules";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(Query);
            véhicules véhicules;
            while (rs.next()) {
                véhicules = new véhicules(rs.getInt("Matricule"), rs.getString("Nom"), rs.getString("Type"), rs.getString("DateAchat"),rs.getInt("Nombre"));
                vehiculesList.add(véhicules);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return vehiculesList;
    }

    public void show(){
        ObservableList<véhicules>list=getvehiculeList();
        Tmatricule.setCellValueFactory(new PropertyValueFactory<véhicules,Integer>("Matricule"));
        Tnom.setCellValueFactory(new PropertyValueFactory<véhicules,String>("Nom"));
        Ttype.setCellValueFactory(new PropertyValueFactory<véhicules,String>("Type"));
        Tdateachat.setCellValueFactory(new PropertyValueFactory<véhicules,String>("DateAchat"));
        Tnombre.setCellValueFactory(new PropertyValueFactory<véhicules,Integer>("Nombre"));
        tab.setItems(list);

    }
    public  boolean vérifier(int text){
        // DatabaseConnection connection= new DatabaseConnection();
        Connection conn= getConnection();
        String verify ="SELECT Matricule from VEHICULES ";
        boolean test=false;
        try{
            Statement statement= conn.createStatement();
            ResultSet querryResult= statement.executeQuery(verify);
            while(querryResult.next()){
                if(querryResult.getInt("Matricule")==(text))
                    test= true;
            }
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return test;
    }
    private void Ajouter(){
        try{
        int Matricule1=Integer.parseInt(this.Matricule.getText());
        String Nom1=this.Nom.getText();
        String Type1=this.Type.getText();
        String Dateachat1=this.DateAchat.getText();
        int Nombre1=Integer.parseInt(this.Nombre.getText());


        if(vérifier(Matricule1)) {
            HelpingFunctions.alert("warning", "existe déja");

        }

        else if ( (Matricule1<1) || (Nombre1<1) ){
            HelpingFunctions.alert("warning", "should be positive change it!");
        }
        else if(Matricule.getText().equals("")|| Nom.getText().equals("") || Type.getText().equals("") || DateAchat.getText().equals("")|| Nombre.getText().equals("")) {
            HelpingFunctions.alert("warning", "please enter all the information");

        }else
        {
            String Query="INSERT INTO vehicules VALUES (\'"+Nom.getText()+"\',\'"+Type.getText()+"\',"+Matricule.getText()+",\'"+DateAchat.getText() + "\',"+Nombre.getText()+")";
            System.out.println(Query);
            executeQuery(Query);
            show();

        }
    }catch (Exception e){
        HelpingFunctions.alert("warning", "please enter all the information");
    }
}
    private void Supprimer(){
        try{
            int Matricule1=Integer.parseInt(this.Matricule.getText());
            String Nom1=this.Nom.getText();
            String Type1=this.Type.getText();
            String Dateachat1=this.DateAchat.getText();
            int Nombre1=Integer.parseInt(this.Nombre.getText());


            if (Matricule.getText().equals("") || Nom.getText().equals("")|| Type.getText().equals("")|| DateAchat.getText().equals("")|| Nombre.getText().equals("")) {
                HelpingFunctions.alert("warning", "please enter all the information");

            }
            else {
                String Query="DELETE FROM vehicules where Matricule='"+Matricule.getText() + "'";
                executeQuery(Query);
                show();
                HelpingFunctions.alert("warning", "supprimer avec succés");
            }
        } catch (Exception e){
            HelpingFunctions.alert("warning", "please enter all the information");
        }

    }

    private void Chercher(){
        String Query=" UPDATE vehicules SET Nom='"+Nom.getText()+"',Type='"+Type.getText()+"',DateAchat='"+DateAchat.getText()+"',"+"Nombre='"+Nombre.getText()+"' WHERE Matricule="+Matricule.getText()+" ";
        System.out.println(Query);
        executeQuery(Query);
        show();
    }
    private void executeQuery(String Query) {
        Connection conn=getConnection();
        Statement st;
        try{
            st=conn.createStatement();
            st.execute(Query);

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void clickme(javafx.event.ActionEvent event) {
        if (event.getSource() == btnAjouter) {
            Ajouter();
        }else if (event.getSource()==btnSupprimer){

                    Supprimer();

        }else if (event.getSource()==btnChercher){
            Chercher();
        }
    }
    public void bundleMouse(javafx.scene.input.MouseEvent mouseEvent) {
        véhicules véhicules=tab.getSelectionModel().getSelectedItem();
        Matricule.setText(""+véhicules.getMatricule());
        Nom.setText(""+véhicules.getNom());
        Type.setText(""+véhicules.getType());
        DateAchat.setText(""+véhicules.getDateAchat());
        Nombre.setText(""+véhicules.getNombre());
    }
    public void retour(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        stage.close();
        int pred = 0;
        if (pred == 0) {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../sample1/reglage.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }
}