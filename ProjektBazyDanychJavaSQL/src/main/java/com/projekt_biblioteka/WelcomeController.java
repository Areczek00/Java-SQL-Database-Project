package com.projekt_biblioteka;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.EventObject;

public class WelcomeController {
    public static Connection c;
    @FXML
    private Label welcomeLabel;
    @FXML
    private Button buttonWelcome;

    @FXML
    protected void onButtonWelcomeClick() {
        welcomeLabel.setText("Łączenie...");
        ConnectionDB conn = new ConnectionDB();
        try{
            c = conn.getConnection();
            if (c != null){
                welcomeLabel.setText("Pomyślnie połączono.");
                buttonWelcome.setText("Dziękuję!");
                buttonWelcome.setOnMouseClicked(null);
            //sql script
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("basic_selects.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 640, 682);
                    Stage stage = new Stage();
                    stage.setTitle("Baza danych - biblioteka");
                    stage.setScene(scene);
                    stage.show();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            else
                welcomeLabel.setText("Połączenie nieudane!");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}