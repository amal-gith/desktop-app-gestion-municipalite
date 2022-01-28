package sample1;
import HelpFunctions.HelpingFunctions;
import entity.projet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.awt.TextArea;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class projetContrl  implements Initializable {
    @FXML
    private TableView<projet> TV;

    @FXML
    private TableColumn<projet, Integer> TID;

    @FXML
    private TableColumn<projet, String> TNomP;

    @FXML
    private TableColumn<projet, Integer> TBudget;

    @FXML
    private TableColumn<projet, String> TDateD;

    @FXML
    private TableColumn<projet, String> TDescription;

    @FXML
    private Button btnInsert;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    private TextField id;

    @FXML
    private TextField nomp;

    @FXML
    private TextField dated;

    @FXML
    private TextField budget;


    @FXML
    private TextField description;



    @FXML
    void Click(ActionEvent event) {
        if (event.getSource() == btnInsert) {
            insertA();

        } else if (event.getSource() == btnUpdate) {
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
        } else if (event.getSource() == btnDelete) {


                    Delete();


        }

    }




    public void table() {
        ObservableList<projet> list = gestionProjetList();
        TID.setCellValueFactory(new PropertyValueFactory<projet, Integer>("id"));
        TNomP.setCellValueFactory(new PropertyValueFactory<projet, String>("NomP"));
        TBudget.setCellValueFactory(new PropertyValueFactory<projet, Integer>("Budget"));
        TDateD.setCellValueFactory(new PropertyValueFactory<projet, String>("DateD"));
        TDescription.setCellValueFactory(new PropertyValueFactory<projet, String>("Description"));
        TV.setItems(list);
    }

    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE", "PFA1", "PFA1");
            return conn;
        } catch (Exception ex) {
            System.out.println("Error:" + ex.getMessage());
            return null;
        }
    }

    public ObservableList<projet> gestionProjetList() {
        ObservableList<projet> gestionsProjet = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String Query = "SELECT * FROM GestionProjet";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(Query);
            projet gestionProjet;
            while ((rs.next())) {
                gestionProjet = new projet(rs.getInt("id"), rs.getString("NomP"), rs.getInt("Budget"), rs.getString("DateD"), rs.getString("Description"));
                gestionsProjet.add(gestionProjet);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return gestionsProjet;
    }

    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.execute(query);

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    public  boolean vérifier(int text){
        Connection conn= getConnection();
        String verify ="SELECT ID from GESTIONPROJET ";
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
        try{
        System.out.println(this.id.getText());
        int ID1=Integer.parseInt(this.id.getText());
        String Nom1=this.nomp.getText();
        int Budget1=Integer.parseInt(this.budget.getText());
        String description1=this.description.getText();
        String date=this.dated.getText();

        if(vérifier(ID1)) {
            HelpingFunctions.alert("warning", "existe déja");

        }

        else if ( (ID1<1) || (Budget1<1) ){
            HelpingFunctions.alert("warning", "should be positive change it!");
        }
        else if(TID.getText().equals("") || TNomP.getText().equals("") || TDateD.getText().equals("") || TBudget.getText().equals("") || description.getText().equals("")) {
            HelpingFunctions.alert("warning", "please enter all the information");

        }else
        {
            String query = "INSERT INTO GestionProjet  VALUES (" + id.getText() + ",\'" + nomp.getText() + "\'," + budget.getText() + ",\'" + dated.getText() +"\',\'"+description.getText()+"\')";
            System.out.println(query);
            executeQuery(query);
            table();

        }

        }catch (Exception e){
            HelpingFunctions.alert("warning", "please enter all the information");
        }

    }

    private void Update() {
        String query = " UPDATE GestionProjet  SET NomP='" + nomp.getText() + "',Budget='" + budget.getText() +"',DateD='" + dated.getText() + "',Description='" + description.getText() + "' WHERE id=" + id.getText() + " ";
        System.out.println(query);
        executeQuery(query);
        table();
    }

    private void Delete() {
        try{
            System.out.println(this.id.getText());
            int ID1=Integer.parseInt(this.id.getText());
            String Nom1=this.nomp.getText();
            int Budget1=Integer.parseInt(this.budget.getText());
            String description1=this.description.getText();
            String date=this.dated.getText();


            if (id.getText().equals("") || nomp.getText().equals("")|| budget.getText().equals("")|| description.getText().equals("")|| dated.getText().equals("")) {
                HelpingFunctions.alert("warning", "please enter all the information");

            }
            else {
                String query = "DELETE FROM GestionProjet WHERE id='" + id.getText() + "'";
                System.out.println(query);
                executeQuery(query);
                table();
                HelpingFunctions.alert("warning", "supprimer avec succés");
            }
        } catch (Exception e){
            HelpingFunctions.alert("warning", "please enter all the information");
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table();
    }

    public void handl(javafx.scene.input.MouseEvent mouseEvent) {
        projet projet = TV.getSelectionModel().getSelectedItem();
        id.setText("" + projet.getId());
        nomp.setText("" + projet.getNomP());
        dated.setText("" + projet.getDateD());
        budget.setText("" + projet.getBudget());
        description.setText("" + projet.getDescription());
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