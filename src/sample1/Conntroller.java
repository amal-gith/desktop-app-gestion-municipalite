package sample1;
import HelpFunctions.HelpingFunctions;
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
import java.sql.*;
import java.util.ResourceBundle;

public class Conntroller implements Initializable {


    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnInsert;

    @FXML
    private Button btnDelets;

    @FXML
    private TableView<GaineA> tv;

    @FXML
    private TableColumn<GaineA, String> TNom;

    @FXML
    private TableColumn<GaineA, String> TDate;

    @FXML
    private TableColumn<GaineA, Integer> TPrix;
    @FXML
    private TableColumn<GaineA, String> TDescription;

    @FXML
    private TextField Nomid;

    @FXML
    private TextField DateGid;

    @FXML
    private TextField Budgetid;
    @FXML
    private TextArea Descriptionid;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showA();

    }

    public Connection  getConnection(){
        Connection conn;
        try{
            conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE", "PFA1", "PFA1");
            return  conn;
        }catch (Exception ex){
            System.out.println("Error:"+ex.getMessage());
            return null;
        }
    }
    public ObservableList<GaineA>getGaineAList(){
        ObservableList<GaineA>gainesList=FXCollections.observableArrayList();
        Connection conn=getConnection();
        String Query="SELECT * FROM GaineA";
        Statement st;
        ResultSet rs;
        try{
            st=conn.createStatement();
            rs=st.executeQuery(Query);
            GaineA gaineA;
            while ((rs.next())){
                gaineA=new GaineA(rs.getString("Nom"),rs.getString("DateG"),rs.getInt("Budget"),rs.getString("Description"));
                gainesList.add(gaineA);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return  gainesList;
    }
    public void showA(){
        ObservableList<GaineA>list=getGaineAList();
        TNom.setCellValueFactory(new PropertyValueFactory<GaineA,String>("Nom"));
        TDate.setCellValueFactory(new PropertyValueFactory<GaineA,String>("DateG"));
        TPrix.setCellValueFactory(new PropertyValueFactory<GaineA,Integer>("Budget"));
        TDescription.setCellValueFactory(new PropertyValueFactory<GaineA,String>("Description"));
        tv.setItems(list);
    }
    public  boolean vérifier(String text){
        // DatabaseConnection connection= new DatabaseConnection();
        Connection conn= getConnection();
        String verify ="SELECT Nom from GaineA ";
        boolean test=false;
        try{
            Statement statement= conn.createStatement();
            ResultSet querryResult= statement.executeQuery(verify);
            while(querryResult.next()){
                if(querryResult.getString("Nom").equals(text))
                    test= true;
            }
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return test;
    }


    private void insertA(){
        try{
        int Budget1=Integer.parseInt(this.Budgetid.getText());
        String Nom1=this.Nomid.getText();
        String DateG1=this.DateGid.getText();
        String Description1=this.Descriptionid.getText();
        if(vérifier(Nom1)) {
            HelpingFunctions.alert("warning", "existe déja");

        }
        else if ( (Budget1<1) ){
            HelpingFunctions.alert("warning", "should be positive change it!");
        }
        else if(Budgetid.getText().equals("")  || Nomid.getText().equals("")  || DateGid.getText().equals("")  || Descriptionid.getText().equals("") ) {
            HelpingFunctions.alert("warning", "please enter all the information");

        }else {

            String Query = "INSERT INTO GaineA VALUES (\'" + Nomid.getText() + "\',\'" + DateGid.getText() + "\',\'" + Budgetid.getText() + "\',\'" + Descriptionid.getText() + "\')";
            System.out.println(Query);
            executeQuery(Query);
            showA();
        }
        } catch (Exception e){
        HelpingFunctions.alert("warning", "please enter all the information");
    }

    }

    private void executeQuery(String query) {
        Connection conn=getConnection();
        Statement st;
        try{
            st=conn.createStatement();
            st.execute(query);

        }catch (Exception ex){
            ex.printStackTrace();
        }


    }

    private void Update(){
        String Query=" UPDATE gaineA SET DateG='"+DateGid.getText()+"',Budget='"+Budgetid.getText()+"',Description='"+Descriptionid.getText()+"' WHERE Nom='"+Nomid.getText()+"'";
        executeQuery(Query);
        showA();
    }
    private void Delete(){
        try{

            int Budget1=Integer.parseInt(this.Budgetid.getText());
            String Nom1=this.Nomid.getText();
            String DateG1=this.DateGid.getText();
            String Description1=this.Descriptionid.getText();


            if (Budgetid.getText().equals("") || Nomid.getText().equals("")|| DateGid.getText().equals("")|| Descriptionid.getText().equals("")) {
                HelpingFunctions.alert("warning", "please enter all the information");

            }
            else {
                String query="DELete FROM gaineA WHERE Nom='"+Nomid.getText()+"'";
                executeQuery(query);
                showA();
                HelpingFunctions.alert("warning", "supprimer avec succés");
            }
        } catch (Exception e){
            HelpingFunctions.alert("warning", "please enter all the information");
        }

    }
    @FXML
    public void ClickA(javafx.event.ActionEvent event) {
        if (event.getSource()==btnInsert){
            insertA();

        }else if (event.getSource()==btnUpdate){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UNDECORATED);
            ButtonType ok = new ButtonType("Yes",ButtonBar.ButtonData.YES);
            ButtonType no = new ButtonType("No",ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(ok,no);
            alert.setContentText("Etes vous sur ????");
            alert.showAndWait().ifPresent(type ->{
                Update();
            });

        }else if(event.getSource()==btnDelets){

                    Delete();


        }
    }
    @FXML
    public void handle(javafx.scene.input.MouseEvent mouseEvent) {
        GaineA gaineA=  tv.getSelectionModel().getSelectedItem();
        Nomid.setText(""+gaineA.getNom());
        DateGid.setText(""+gaineA.getDateG());
        Budgetid.setText(""+gaineA.getBudget());
        Descriptionid.setText(""+gaineA.getDescription());
    }
    public void retour(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        stage.close();
        int pred = 0;
        if (pred == 0) {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../sample1/choisi.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

}

