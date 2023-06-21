package com.example.loginform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.sql.Connection;
import javax.swing.*;

public class HelloController implements Initializable {
    @FXML
    private Hyperlink create_acc;

    @FXML
    private Button login_btn;

    @FXML
    private Label niceDay;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label welcome;

    private Connection connect;
    private PreparedStatement statement;
    private ResultSet result;

    public Connection connectDb(){
        try{
            connect = DriverManager.getConnection("jdbc:mysql://localhost/admin" , "root", "");
            return connect;
        }catch (Exception e){e.printStackTrace();
        return null;}
    }

    public void login(ActionEvent event){

        connect = connectDb();

        try{
            String sql = "SELECT * FROM data WHERE username = ? AND password = ?";
            statement = connect.prepareStatement(sql);
            statement.setString(1, username.getText());
            statement.setString(2, password.getText());
            result = statement.executeQuery();

            if(result.next()){
                JOptionPane.showMessageDialog(null, "Successfully Login", "Message", JOptionPane.INFORMATION_MESSAGE);

                login_btn.getScene().getWindow().hide();
               Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
               Scene scene = new Scene(root);
                Stage stage = new Stage();

                stage.setScene(scene);
                stage.setTitle("Simple Dashboard");
                stage.show();
            }else {
                JOptionPane.showMessageDialog(null, "Wrong Username/Password", "Message", JOptionPane.ERROR_MESSAGE);
            }

        }catch (Exception e){e.printStackTrace();}
    }



    public void exit() {
        System.exit(0);
    }


    public void textfield(MouseEvent event){
        if(event.getSource() == username){
            username.setStyle("-fx-background-color:#fff;" + "-fx-border-width:3px;");
            password.setStyle("-fx-background-color:#eef3ff;" + "-fx-border-width:1px;");
        }else if(event.getSource() == password){
            username.setStyle("-fx-background-color: #eef3ff;" + "-fx-border-width: 1px;");
            password.setStyle("-fx-background-color: #fff;" + "-fx-border-width:3px;");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcome.setStyle("-fx-background-color:#fff;" + "-fx-border-width:3px;");
        DropShadow original = new DropShadow(20, Color.valueOf("#6a9ae7"));

        welcome.setEffect(original);
        niceDay.setEffect(original);

        welcome.setOnMouseEntered((MouseEvent event) -> {
            DropShadow shadow = new DropShadow(50, Color.valueOf("#6a9ae7"));

            welcome.setStyle("-fx-text-fill: #fff");
            welcome.setEffect(shadow);
            niceDay.setEffect(shadow);
        });

        welcome.setOnMouseExited((MouseEvent event) -> {
            DropShadow shadow = new DropShadow(20, Color.valueOf("#6a9ae7"));
            welcome.setStyle("-fx-text-fill:#6a9ae7");
            welcome.setEffect(shadow);
            niceDay.setEffect(shadow);
        });


        niceDay.setOnMouseEntered((MouseEvent event) -> {
            DropShadow shadow = new DropShadow(50, Color.valueOf("#6a9ae7"));

            welcome.setStyle("-fx-text-fill: #fff");
            welcome.setEffect(shadow);
            niceDay.setEffect(shadow);
        });

        niceDay.setOnMouseExited((MouseEvent event) -> {
            DropShadow shadow = new DropShadow(20, Color.valueOf("#6a9ae7"));
            welcome.setStyle("-fx-text-fill:#6a9ae7");
            welcome.setEffect(shadow);
            niceDay.setEffect(shadow);
        });

    }
}