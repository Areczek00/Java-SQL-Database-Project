package com.projekt_biblioteka;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.SQLException;

class BladAutoryzacji extends Exception{}

public class UserController {
    @FXML
    private TextArea resultText;
    @FXML
    private TextField imie;
    @FXML
    private TextField nazwisko;
    @FXML
    private TextField id_user_zadl;
    @FXML
    private TextField haslo;
    @FXML
    private TextField id_user_del;
    @FXML
    private TextField id_user_pass;
    @FXML
    private Button dodawanie;
    @FXML
    private Button zerowanie;
    @FXML
    private Button usuwanie;

    @FXML
    protected void addUser(){
        try{
            String fname = imie.getText();
            String lname = nazwisko.getText();
            PreparedStatement pst = WelcomeController.c.prepareStatement("INSERT INTO biblioteka.czytelnik (imie, nazwisko) values (?,?)");
            pst.setString(1,fname);
            pst.setString(2,lname);
            int l = pst.executeUpdate();
            if (l > 0){
                resultText.setText("Wprowadzono nowego czytelnika do bazy: " + fname + " " + lname + ".");
            }
            else if (l == 0)
                resultText.setText("Nie udało się wprowadzić nowego czytelnika do bazy.");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void cancelDebt(){
        try{
            String password = haslo.getText();
            if(!password.equals("maslo")){
                throw new BladAutoryzacji();
            }
            int userID = Integer.parseInt(id_user_zadl.getText());
            PreparedStatement pst = WelcomeController.c.prepareStatement("UPDATE biblioteka.czytelnik c set zadluzenie = NULL where c.czytelnik_id = ?");
            pst.setInt(1, userID);
            int l = pst.executeUpdate();
            if(l>0){
                resultText.setText("Wyzerowano zadłużenie użytkownikowi o id = " + userID +".");
            }
            if (l == 0){
                resultText.setText("Nie udało się wyzerować zadłużenia.");
            }

        }
        catch(SQLException | NumberFormatException e){
            e.printStackTrace();
            resultText.setText("Błąd wprowadzenia danych!");
        }
        catch(BladAutoryzacji e){
            resultText.setText("Błąd dostępu! Złe hasło lub id.");
        }
    }
    @FXML
    protected void deleteUser(){
        try{
            String password = id_user_pass.getText();
            if(!password.equals("maslo")){
                throw new BladAutoryzacji();
            }
            int userID = Integer.parseInt(id_user_del.getText());
            PreparedStatement pst = WelcomeController.c.prepareStatement("DELETE FROM biblioteka.czytelnik c where c.czytelnik_id = ?");
            pst.setInt(1, userID);
            int l = pst.executeUpdate();
            if(l>0){
                resultText.setText("Usunięto użytkownika o id = " + userID +".");
            }
            if (l == 0){
                resultText.setText("Nie udało się usunąć użytkownika.");
            }

        }
        catch(SQLException | NumberFormatException e){
            e.printStackTrace();
            resultText.setText("Błąd wprowadzenia danych!");
        }
        catch(BladAutoryzacji e){
            resultText.setText("Błąd dostępu! Złe hasło lub id.");
        }
    }
}
