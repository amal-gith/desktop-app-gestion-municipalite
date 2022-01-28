package sample1;
import HelpFunctions.HelpingFunctions;
import entity.profit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class profitCntrl implements Initializable {
        @FXML
        private TextField ID;

        @FXML
        private TextField Nom;

        @FXML
        private TextField Date;

        @FXML
        private TextField Prix;

        @FXML
        private Button btnInsert;

        @FXML
        private Button btnUpdate;

        @FXML
        private Button btnDelete;

        @FXML
        private TableView<profit> TV;

        @FXML
        private TableColumn<profit, Integer> TID;

        @FXML
        private TableColumn<profit, String> TNom;

        @FXML
        private TableColumn<profit, String> TDate;

        @FXML
        private TableColumn<profit, Integer> TPrix;

        @FXML
        void Click(javafx.event.ActionEvent event) {
            if (event.getSource()==btnInsert){
                insertA();

            }else if (event.getSource()==btnUpdate){
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
            }else if(event.getSource()==btnDelete){

                        Delete();
                    }

        }



       @FXML
        public void show() {
            ObservableList<profit> list=getProfitlist();
            TID.setCellValueFactory(new PropertyValueFactory<profit,Integer>("id"));
            TNom.setCellValueFactory(new PropertyValueFactory<profit,String>("Nom"));
            TDate.setCellValueFactory(new PropertyValueFactory<profit,String>("DateP"));
            TPrix.setCellValueFactory(new PropertyValueFactory<profit,Integer>("Prix"));
            TV.setItems(list);

        }

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            show();

        }
    public  boolean vérifier(int text){

        Connection conn= getConnection();
        String verify ="SELECT ID from profit ";
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
        private void insertA() {
            try {
                int ID1 = Integer.parseInt(this.ID.getText());
                String Nom1 = this.Nom.getText();
                int Prix1 = Integer.parseInt(this.Prix.getText());
                String Date1 = this.Date.getText();

                if (vérifier(ID1)) {
                    HelpingFunctions.alert("warning", "existe déja");

                }

                else if ((ID1 < 1) || (Prix1 < 1)) {
                    HelpingFunctions.alert("warning", "should be positive change it!");
                } else if (ID.getText().equals("") || Nom.getText().equals("")|| Prix.getText().equals("")|| Date.getText().equals("")) {
                    HelpingFunctions.alert("warning", "please enter all the information");

                } else {
                    String Query = "INSERT INTO profit VALUES (" + ID.getText() + ",\'" + Nom.getText() + "\',\'" + Date.getText() + "\'," + Prix.getText() + ")";
                    executeQuery(Query);
                    show();

                }

            }catch (Exception e){
                HelpingFunctions.alert("warning", "please enter all the information");
            }
        }

        private void executeQuery(String Query) {
            java.sql.Connection conn=getConnection();
            Statement st;
            try{
                st=conn.createStatement();
                st.executeUpdate(Query);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        private void Update(){
            String Query=" UPDATE profit SET Nom='"+Nom.getText()+"',DateP='"+Date.getText()+"',Prix="+Prix.getText()+" WHERE id="+ID.getText()+"";
            executeQuery(Query);
            show();
        }
        private void Delete(){
            try{
                int ID1 = Integer.parseInt(this.ID.getText());
                String Nom1 = this.Nom.getText();
                int Prix1 = Integer.parseInt(this.Prix.getText());
                String Date1 = this.Date.getText();


                if (ID.getText().equals("") || Nom.getText().equals("")|| Prix.getText().equals("")|| Date.getText().equals("")) {
                    HelpingFunctions.alert("warning", "please enter all the information");

                }
                else {
                    String query="DELETE FROM profit WHERE ID="+ID.getText()+"";
                    executeQuery(query);
                    show();
                    HelpingFunctions.alert("warning", "supprimer avec succés");
                }
            } catch (Exception e){
                HelpingFunctions.alert("warning", "please enter all the information");
            }

        }



        public ObservableList<profit> getProfitlist(){
            ObservableList<profit>profitList= FXCollections.observableArrayList();
            Connection conn=getConnection();
            String Query="SELECT * FROM profit";
            Statement st;
            ResultSet rs;
            try{
                st=conn.createStatement();
                rs=st.executeQuery(Query);
                profit profit;
                while ((rs.next())){
                    profit=new profit(rs.getInt("ID"),rs.getString("Nom"),rs.getString("DateP"),rs.getInt("Prix"));
                    profitList.add(profit);
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
            return  profitList;
        }

    private Connection getConnection() {
        java.sql.Connection conn;
        try{
            conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE", "PFA1", "PFA1");
            return  conn;
        }catch (Exception e){
            System.out.println("Error:"+e.getMessage());
            return null;
        }
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

    public void handl(javafx.scene.input.MouseEvent mouseEvent) {
        profit profit=  TV.getSelectionModel().getSelectedItem();
        ID.setText(""+profit.getId());
        Nom.setText(""+profit.getNom());
        Date.setText(""+profit.getDateP());
        Prix.setText(""+profit.getPrix());
    }


}




