package sample1;

import HelpFunctions.HelpingFunctions;
import entity.autorisation;
import entity.reclamation;
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

public class reclamationCntrl implements Initializable {
    @FXML
    private TextField CIN;

    @FXML
    private TextField Nom;

    @FXML
    private TextField Adresse;

    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnSupprimer;

    @FXML
    private Button btnChercher;


    @FXML
    private TableView<reclamation> tab;

    @FXML
    private TableColumn<reclamation, Integer> TCIN;

    @FXML
    private TableColumn<reclamation, String> Tnom;

    @FXML
    private TableColumn<reclamation, String> Tadresse;

    @FXML
    private TableColumn<reclamation, String> Tchoix;
    @FXML
    private TextArea combo;

    @FXML
    private Button Update;

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
    public ObservableList<reclamation>getreclamationList(){
        ObservableList<reclamation>reclamationsList=FXCollections.observableArrayList();
        Connection conn=getConnection();
        String Query="SELECT * FROM reclamation";
        Statement st;
        ResultSet rs;
        try {
            st=conn.createStatement();
            rs=st.executeQuery(Query);
            reclamation reclamation;
            while (rs.next()){
                reclamation=new reclamation(rs.getInt("CIN"),rs.getString("Nom"),rs.getString("Adresse"),rs.getString("choixrec"));
                reclamationsList.add(reclamation);
            }


        }catch (Exception e){
            e.printStackTrace();

        }
        return reclamationsList;
    }
    public void show(){
        ObservableList<reclamation>list=getreclamationList();
        TCIN.setCellValueFactory(new PropertyValueFactory<reclamation,Integer>("CIN"));
        Tnom.setCellValueFactory(new PropertyValueFactory<reclamation,String>("Nom"));
        Tadresse.setCellValueFactory(new PropertyValueFactory<reclamation,String>("Adresse"));
        Tchoix.setCellValueFactory(new PropertyValueFactory<reclamation,String>("choixrec"));
        tab.setItems(list);


    }
    public  boolean vérifier(int text){
        Connection conn= getConnection();
        String verify ="SELECT CIN from RECLAMATION ";
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
        String Adresse1=this.Adresse.getText();
        String combo1=this.combo.getText();

        if(vérifier(CIN1)) {
            HelpingFunctions.alert("warning", "existe déja");

        }
        else if ( (CIN1<1) ){
            HelpingFunctions.alert("warning", "should be positive change it!");

        } else if (CIN.getText().equals("") || Nom.getText().equals("")|| Adresse.getText().equals("")|| combo.getText().equals("")) {
            HelpingFunctions.alert("warning", "please enter all the information");

        } else
        {
            String Query="INSERT INTO reclamation VALUES (" +CIN.getText()+",\'"+Nom.getText()+"\',\'"+Adresse.getText()+"\',\'"+combo.getText()+"\')";
            System.out.println(Query);
            executeQuery(Query);
            show();

        }}
    catch (Exception e){
        HelpingFunctions.alert("warning", "please enter all the information");
    }

    }

    private void Supprimer(){
        try{
            String Nom1=this.Nom.getText();
            String Adresse1=this.Adresse.getText();
            String combo1=this.combo.getText();
        int CIN1=Integer.parseInt(this.CIN.getText());


        if (Nom.getText().equals("") || Adresse.getText().equals("")|| combo.getText().equals("")|| CIN.getText().equals("")) {
            HelpingFunctions.alert("warning", "please enter all the information");

        }
        else {
        String Query="DELETE FROM reclamation WHERE CIN= '" +CIN.getText()+ "'";
        executeQuery(Query);
        show();
            HelpingFunctions.alert("warning", "supprimer avec succés");
    }
    } catch (Exception e){
        HelpingFunctions.alert("warning", "please enter all the information");
        }
    }
    private void Chercher(){

        String Query=" SELECT * FROM reclamation WHERE CIN ='" +CIN.getText()+ "'";
        executeQuery(Query);
        show();
    }
    private void Update(){
        String Query=" UPDATE reclamation SET Nom='"+Nom.getText()+"',Adresse='"+Adresse.getText()+"',choixrec='"+combo.getText()+"' WHERE CIN="+CIN.getText()+"";
        executeQuery(Query);
        show ();
    }




    private void executeQuery(String Query) {
        Connection conn=getConnection();
        Statement st;
        try{
            st=conn.createStatement();
            st.executeUpdate(Query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void clickme(javafx.event.ActionEvent event) {
        if (event.getSource() == btnAjouter) {
            Ajouter();
        }   else if (event.getSource()==btnSupprimer) {

                    Supprimer();


        }else if (event.getSource()==btnChercher){
        Chercher();
    }else if (event.getSource()==Update){
            Update();
        }

    }

    public void bandle(MouseEvent mouseEvent) {
        reclamation reclamation=tab.getSelectionModel().getSelectedItem();
        CIN.setText(""+reclamation.getCIN());
        Nom.setText(""+reclamation.getNom());
        Adresse.setText(""+reclamation.getAdresse());
        combo.setText(""+reclamation.getChoixrec());

    }

    public void retour(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        stage.close();
        int pred = 0;
        if (pred == 0) {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../sample1/guich.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }
}