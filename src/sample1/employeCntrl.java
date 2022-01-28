package sample1;
import HelpFunctions.HelpingFunctions;
import entity.employe;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class employeCntrl implements Initializable {

    @FXML
    private TextField Nom;

    @FXML
    private TextField Prenom;

    @FXML
    private TextField Adresse;

    @FXML
    private TableView<employe> tab;

    @FXML
    private TableColumn<employe, String > TNom;

    @FXML
    private TableColumn<employe, String> TPrenom;

    @FXML
    private TableColumn<employe, String> TAdresse;

    @FXML
    private TableColumn<employe,Integer> TCIN;

    @FXML
    private TextField CIN;

    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnSupprimer;

    @FXML
    private Button btnRechercher;


    @FXML
    private ImageView user;


    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        show();
    }
    public Connection getConnection(){
        Connection conn;
        try{
            conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE", "PFA1", "PFA1");
            return  conn;
        }catch (Exception e){
            System.out.println("Error:"+e.getMessage());
            return null;
        }
    }
    public ObservableList<employe> getemployeList(){
        ObservableList<employe>employesList= FXCollections.observableArrayList();
        Connection conn=getConnection();
        String Query="SELECT * FROM EMPLOYÉ";
        Statement st;
        ResultSet rs;
        try {
            st=conn.createStatement();
            rs=st.executeQuery(Query);
            employe employe;
            while (rs.next()){
                employe=new employe(rs.getInt("CIN"),rs.getString("Nom"),rs.getString("Prenom"),rs.getString("Adresse"));
                employesList.add(employe);
            }
        }catch (Exception e){
            e.printStackTrace();

        }
        return employesList;
    }
    public void show(){
        ObservableList<employe>list=getemployeList();
        TCIN.setCellValueFactory(new PropertyValueFactory<employe,Integer>("CIN"));
        TNom.setCellValueFactory(new PropertyValueFactory<employe,String>("Nom"));
        TPrenom.setCellValueFactory(new PropertyValueFactory<employe,String>("Prenom"));
        TAdresse.setCellValueFactory(new PropertyValueFactory<employe,String>("Adresse"));
        tab.setItems(list);

    }

    public  boolean vérifier(int text){
        Connection conn= getConnection();
        String verify ="SELECT CIN from EMPLOYÉ ";
        boolean test=false;
        try{
            Statement statement= conn.createStatement();
            ResultSet querryResult= statement.executeQuery(verify);
            while(querryResult.next()){
                if(querryResult.getInt("CIN")==(text))
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
        int CIN1=Integer.parseInt(this.CIN.getText());
        String Nom1=this.Nom.getText();
        String Prenom1=this.Prenom.getText();
        String Adresse1=this.Adresse.getText();



        if(vérifier(CIN1)) {
            HelpingFunctions.alert("warning", "existe déja");

        }

        else if ( (CIN1<1)){
            HelpingFunctions.alert("warning", "should be positive change it!");
        }
        else if(CIN.getText().isEmpty() || Nom.getText().isEmpty() || Prenom.getText().isEmpty() || Adresse.getText().isEmpty()) {
            HelpingFunctions.alert("warning", "please enter all the information");

        }else
        {
            String Query="INSERT INTO employé VALUES (" +CIN.getText()+",\'"+Nom.getText()+"\',\'"+Prenom.getText()+"\',\'"+Adresse.getText()+"\')";
            System.out.println(Query);
            executeQuery(Query);
            show();}

        }catch (Exception e){
        HelpingFunctions.alert("warning", "please enter all the information");
    }
        }


    private void Supprimer(){
        try{
            int CIN1=Integer.parseInt(this.CIN.getText());
            String Nom1=this.Nom.getText();
            String Prenom1=this.Prenom.getText();
            String Adresse1=this.Adresse.getText();


            if (CIN.getText().equals("") || Nom.getText().equals("")|| Prenom.getText().equals("")|| Adresse.getText().equals("")) {
                HelpingFunctions.alert("warning", "please enter all the information");

            }
            else {
                String Query="DELETE FROM employé WHERE CIN= '" +CIN.getText()+ "'";
                executeQuery(Query);
                show();
                HelpingFunctions.alert("warning", "supprimer avec succés");
            }
        } catch (Exception e){
            HelpingFunctions.alert("warning", "please enter all the information");
        }

    }
    private void Update(){
        String Query=" UPDATE employé SET Nom='"+Nom.getText()+"',Prenom='"+Prenom.getText()+"',Adresse='"+Adresse.getText()+"' WHERE CIN="+CIN.getText()+"";
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
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void clickme(ActionEvent event) {
        if (event.getSource()==btnAjouter){
            Ajouter();
        } else if (event.getSource()==btnSupprimer) {

                    Supprimer();

        }else if (event.getSource()==btnRechercher){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UNDECORATED);
            ButtonType okb = new ButtonType("Yes",ButtonBar.ButtonData.YES);
            ButtonType nob = new ButtonType("No",ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(okb,nob);
            alert.setContentText("Etes vous sur ????");
            alert.showAndWait().ifPresent(type -> {
                if (type == okb){
                    Update();
                }
            });

        }


    }

    public void handle(MouseEvent mouseEvent) {
     employe employe=tab.getSelectionModel().getSelectedItem();
     Nom.setText(""+employe.getNom());
     Prenom.setText(""+employe.getPrenom());
     CIN.setText(""+employe.getCIN());
     Adresse.setText(""+employe.getAdresse());

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
}

