package sample1;

import HelpFunctions.HelpingFunctions;
import entity.autorisation;
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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class autorisationCntrl implements Initializable {
    @FXML
    private TextField CIN;

    @FXML
    private TextField Nom;

    @FXML
    private TextField Prenom;

    @FXML
    private TextField Date;

    @FXML
    private TableView<autorisation> tab;

    @FXML
    private TableColumn<autorisation, Integer> TCIN;

    @FXML
    private TableColumn<autorisation, String> TNom;

    @FXML
    private TableColumn<autorisation, String> TPrenom;

    @FXML
    private TableColumn<autorisation, String> Tdate;

    @FXML
    private TableColumn<autorisation, String> Tauto;

    @FXML
    private Button Ajouter;

    @FXML
    private Button Supprimer;

    @FXML
    private Button Update;

    @FXML
    private TextArea auto;


    @FXML
    public void bandle(MouseEvent mouseEvent) {
        autorisation autorisation=tab.getSelectionModel().getSelectedItem();
        CIN.setText(""+autorisation.getCIN());
        Nom.setText(""+autorisation.getNom());
        Prenom.setText(""+autorisation.getPrenom());
        Date.setText(""+autorisation.getDateautorisation());
        auto.setText(""+autorisation.getAutorisation());
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
    public ObservableList<autorisation> getautorisationList(){
        ObservableList<autorisation>autorisationsList= FXCollections.observableArrayList();
        Connection conn=getConnection();
        String Query="SELECT * FROM autorisation";
        Statement st;
        ResultSet rs;
        try {
            st=conn.createStatement();
            rs=st.executeQuery(Query);
            autorisation autorisation;
            while (rs.next()){
                autorisation=new autorisation(rs.getInt("CIN"),rs.getString("Nom"),rs.getString("Prenom"),rs.getString("dateautorisation"),rs.getString("autorisation"));
                autorisationsList.add(autorisation);
            }
        }catch (Exception e){
            e.printStackTrace();

        }
        return autorisationsList;
    }
    public void show(){
        ObservableList<autorisation>list=getautorisationList();
        TCIN.setCellValueFactory(new PropertyValueFactory<autorisation,Integer>("CIN"));
        TNom.setCellValueFactory(new PropertyValueFactory<autorisation,String>("Nom"));
        TPrenom.setCellValueFactory(new PropertyValueFactory<autorisation,String>("Prenom"));
        Tdate.setCellValueFactory(new PropertyValueFactory<autorisation,String>("Dateautorisation"));
        Tauto.setCellValueFactory(new PropertyValueFactory<autorisation,String>("autorisation"));
        tab.setItems(list);

    }
    public  boolean vérifier(int text){

        Connection conn= getConnection();
        String verify ="SELECT CIN from autorisation ";
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
           System.out.println(CIN1);
           String Nom1=this.Nom.getText();
           String Prenom1=this.Prenom.getText();
           String Date1=this.Date.getText();
           String Auto1=this.auto.getText();

           if(vérifier(CIN1)) {
               HelpingFunctions.alert("warning", "existe déja");

           }
           else if ( (CIN1<1)){
               HelpingFunctions.alert("warning", "should be positive change it!");
           }
           else if(CIN.getText().equals("")  || Nom.getText().equals("") || Prenom.getText().equals("")  || Date.getText().equals("")  || auto.getText().equals("") ) {
               HelpingFunctions.alert("warning", "please enter all the information");

           }else
           {

               String Query="INSERT INTO autorisation VALUES (" +CIN.getText()+",\'"+Nom.getText()+"\',\'"+Prenom.getText()+"\',\'"+Date.getText()+"\',\'"+auto.getText()+"\')";
               System.out.println(Query);
               executeQuery(Query);
               show();

           }

       }
     catch (Exception e){
      HelpingFunctions.alert("warning", "please enter all the information");
}
    }


    private void Supprimer(){
        try{
            int CIN1=Integer.parseInt(this.CIN.getText());

            String Nom1=this.Nom.getText();
            String Prenom1=this.Prenom.getText();
            String Date1=this.Date.getText();
            String Auto1=this.auto.getText();



            if (Nom.getText().equals("") || Prenom.getText().equals("")|| Date.getText().equals("")|| auto.getText().equals("")) {
                HelpingFunctions.alert("warning", "please enter all the information");

            }
            else {
                String Query="DELETE FROM autorisation WHERE CIN= '" +CIN.getText()+ "'";
                executeQuery(Query);
                show();
                HelpingFunctions.alert("warning", "supprimer avec succés");
            }
        } catch (Exception e){
            HelpingFunctions.alert("warning", "please enter all the information");
        }


    }

    private void Update(){
        String Query=" UPDATE autorisation SET Nom='"+Nom.getText()+"',Prenom='"+Prenom.getText()+"',Dateautorisation='"+Date.getText()+"',autorisation='"+auto.getText()+"' WHERE CIN="+CIN.getText()+"";
        executeQuery(Query);
        show ();
    }
    @FXML
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

    public void clickme(ActionEvent event) {
        if (event.getSource()==Ajouter){
            Ajouter();
        } else if (event.getSource()==Supprimer) {

                    Supprimer();


        }else if (event.getSource()==Update){
            Update();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        show();
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
