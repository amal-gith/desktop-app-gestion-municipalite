package sample1;

import HelpFunctions.HelpingFunctions;
import entity.login;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class loginCntrl implements Initializable
{

    @FXML
    private TextField Username;
    @FXML
    private PasswordField Password;
    @FXML
    private Button Login;
    @FXML
    public void Login() throws IOException {

        if(Username.getText()== null==false&& Password.getText().isEmpty()==false){

           /*validateLogin();*/

        }else{
            HelpingFunctions.alert("err" +
                    "error","please enter user name and password");


        }

    }
    public  Connection getConnection(){
        Connection conn;
        try{
            conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE", "PFA1", "PFA1");
            return  conn;
        }catch (Exception e){
            System.out.println("Error:"+e.getMessage());
            return null;
        }
    }
    public  boolean vérifier(String Password , String Username){
       // DatabaseConnection connection= new DatabaseConnection();
        Connection conn= getConnection();
        String verifyLogin ="SELECT * from login ";
        boolean test=false;
        try{
            Statement statement= conn.createStatement();
            ResultSet querryResult= statement.executeQuery(verifyLogin);
            if(querryResult.next()){
                test= true;
            }
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return test;
    }

    public void validateLogin(ActionEvent event)throws IOException {


        if(vérifier(Password.getText(),Username.getText())){


            if(Password.getText().equals("nouha123")){
                Stage stage = (Stage) Login.getScene().getWindow();
                // do what you have to do

                Parent root = FXMLLoader.load(getClass().getResource("employe.fxml"));
                Scene scene =new Scene(root);
                Stage primarStage = new Stage();
                primarStage.setScene(scene);
                primarStage.show();
                stage.close();
            }else if(Password.getText().equals("nawel123")){
                Stage stage = (Stage) Login.getScene().getWindow();
                // do what you have to do

                Parent root = FXMLLoader.load(getClass().getResource("reglage.fxml"));
                Scene scene =new Scene(root);
                Stage primarStage = new Stage();
                primarStage.setScene(scene);
                primarStage.show();
                stage.close();
            }else if(Password.getText().equals("eya123")){
                Stage stage = (Stage) Login.getScene().getWindow();
                // do what you have to do

                Parent root = FXMLLoader.load(getClass().getResource("choisi.fxml"));
                Scene scene =new Scene(root);
                Stage primarStage = new Stage();
                primarStage.setScene(scene);
                primarStage.show();
                stage.close();
            }else if(Password.getText().equals("amal123")){


                Stage stage = (Stage) Login.getScene().getWindow();
                // do what you have to do

                Parent root = FXMLLoader.load(getClass().getResource("guich.fxml"));
                Scene scene =new Scene(root);
                Stage primarStage = new Stage();
                primarStage.setScene(scene);
                primarStage.show();
                stage.close();
            }
        }else{
            System.out.println("error");

            HelpingFunctions.alert("error","invalid login");
            HelpingFunctions.alert("Failed","Job_Name or password incorrect please retray");
        }



    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }


}
