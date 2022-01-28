package sample1;


import HelpFunctions.HelpingFunctions;
import entity.matériaux;
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

public class matériauxCntrl  implements Initializable {
    @FXML
    private TextField ID;

    @FXML
    private TextField Type;

    @FXML
    private TextField Dateachat;

    @FXML
    private Button btnAjouter;

    @FXML
    private Button bntSupprimer;

    @FXML
    private Button btnChercher;

    @FXML
    private TableView<matériaux> tab;

    @FXML
    private TableColumn<matériaux, Integer> Tid;

    @FXML
    private TableColumn<matériaux,String> Ttype;

    @FXML
    private TableColumn<matériaux,String> Tdateachat;
    @FXML
    private Button Retour;
    @FXML
    private TableColumn<matériaux, Integer> Tnombre;


    @FXML
    private TextField Nombre;

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
    public ObservableList<matériaux> getmatériauxList(){
        ObservableList<matériaux>matériauxList= FXCollections.observableArrayList();
        Connection conn=getConnection();
        String Query="SELECT * FROM materiaux";
        Statement st;
        ResultSet rs;
        try {
            st=conn.createStatement();
            rs=st.executeQuery(Query);
            matériaux matériaux;
            while (rs.next()){
                matériaux=new matériaux(rs.getInt("ID"),rs.getString("Type"),rs.getString("DateAchat"),rs.getInt("Nombre"));
                matériauxList.add(matériaux);
            }


        }catch (Exception e){
            e.printStackTrace();

        }
        return matériauxList;
    }
    public void show(){
        ObservableList<matériaux>list=getmatériauxList();
        Tid.setCellValueFactory(new PropertyValueFactory<matériaux,Integer>("ID"));
        Ttype.setCellValueFactory(new PropertyValueFactory<matériaux,String>("Type"));
        Tdateachat.setCellValueFactory(new PropertyValueFactory<matériaux,String>("DateAchat"));
        Tnombre.setCellValueFactory(new PropertyValueFactory<matériaux,Integer>("Nombre"));
        tab.setItems(list);

    }

    public  boolean vérifier(int text){

        Connection conn= getConnection();
        String verify ="SELECT ID from MATERIAUX ";
        boolean test=false;
        try{
            Statement statement= conn.createStatement();
            ResultSet querryResult= statement.executeQuery(verify);
            while(querryResult.next()){
                if(querryResult.getInt("ID")==(text))
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

        int ID1=Integer.parseInt(this.ID.getText());
        String Type1=this.Type.getText();
        int Nombre1=Integer.parseInt(this.Nombre.getText());
        String Dateachat1=this.Dateachat.getText();

        if(vérifier(ID1)) {
            HelpingFunctions.alert("warning", "existe déja");

        }

            else if ( (ID1<1) || (Nombre1<1) ){
            HelpingFunctions.alert("warning", "should be positive change it!");
        }
        else if(ID.getText().equals("") || Type.getText().equals("") || Dateachat.getText().equals("") || Nombre.getText().equals("")) {
            HelpingFunctions.alert("warning", "please enter all the information");

        }else {

            String Query = "INSERT INTO MATERIAUX VALUES (" + ID.getText() + ",\'" + Type.getText() + "\',\'" + Dateachat.getText() + "\'," + Nombre.getText() + ")";
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
            int ID1=Integer.parseInt(this.ID.getText());
            String Type1=this.Type.getText();
            int Nombre1=Integer.parseInt(this.Nombre.getText());
            String Dateachat1=this.Dateachat.getText();


            if (ID.getText().equals("") || Type.getText().equals("")|| Nombre.getText().equals("")|| Dateachat.getText().equals("")) {
                HelpingFunctions.alert("warning", "please enter all the information");

            }
            else {
                String Query="DELETE FROM materiaux WHERE ID='" +ID.getText()+ "'";
                executeQuery(Query);
                show();
                HelpingFunctions.alert("warning", "supprimer avec succés");
            }
        } catch (Exception e){
            HelpingFunctions.alert("warning", "please enter all the information");
        }

    }

    private void Update(){
        String Query=" UPDATE materiaux SET Type='"+Type.getText()+"',Dateachat='"+Dateachat.getText()+"',"+"nombre='"+Nombre.getText()+"' WHERE ID="+ID.getText()+" ";
        System.out.println(Query);
        executeQuery(Query);
        show();
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

    public void clickme(ActionEvent event) throws IOException {
        if (event.getSource() == btnAjouter) {
            Ajouter();
        } else if (event.getSource() == bntSupprimer) {

                    Supprimer();




        } else if (event.getSource() == btnChercher) {
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

        public void bundleMouse (MouseEvent mouseEvent){
            matériaux matériaux = tab.getSelectionModel().getSelectedItem();
            ID.setText("" + matériaux.getID());
            Type.setText("" + matériaux.getType());
            Dateachat.setText("" + matériaux.getDateAchat());
            Nombre.setText("" + matériaux.getNombre());


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
