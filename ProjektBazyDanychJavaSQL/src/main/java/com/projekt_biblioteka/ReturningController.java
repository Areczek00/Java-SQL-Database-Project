package com.projekt_biblioteka;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ReturningController {
    @FXML
    private TextArea resultText;
    @FXML
    private TextField id_user;
    @FXML
    private TextField id_pos;
    @FXML
    private TextField id_user_ex;
    @FXML
    private TextField id_pos_ex;
    @FXML
    private TextField id_zadl;
    @FXML
    private TextField data_oddania;
    @FXML
    private Button Returning;
    @FXML
    private Button Extending;
    @FXML
    private Button CheckUserDebt;
    @FXML
    protected void oddawanie() {
        try {
            int userID = Integer.parseInt(id_user.getText());
            int positionID = Integer.parseInt(id_pos.getText());
            String returnDate = data_oddania.getText();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            dateFormat.setLenient(false);
            try {
                dateFormat.parse(returnDate);
            } catch (ParseException pe) {
                resultText.setText("Błąd we wprowadzaniu daty!");
                pe.printStackTrace();
                throw pe;
            }
            StringBuilder k = new StringBuilder();
            PreparedStatement pst = WelcomeController.c.prepareStatement("update biblioteka.wypozyczenie set data_zwrocenia = TO_DATE('"+ returnDate +"', 'DD.MM.YYYY') " +
                    "where czytelnik_id = ? and pozycja_id = ? and data_zwrocenia is null");
            pst.setInt(1,userID);
            pst.setInt(2,positionID);
            int l = pst.executeUpdate();
            SQLWarning warn = pst.getWarnings();
            if (warn != null) {
                k.append(warn.getMessage()).append("\n");
            }
            if (l > 0)
                k.append("Przez użytkownika ").append(userID).append(" zwrócono pozycję ").append(positionID).append(".");
            if (l == 0)
                k.append("Coś poszło nie tak, czyżbyś usiłował/a oddać niewypożyczoną pozycję od nieistniejącego użytkownika?");
            resultText.setText(k.toString());
        }
        catch(SQLException | NumberFormatException e){
            resultText.setText("Wystąpił błąd, sprawdź poprawność danych!");
            e.printStackTrace();
        }
        catch(ParseException pe){
        }
    }
    @FXML
    protected void przedluzenie() {
        try {
            int userID = Integer.parseInt(id_user_ex.getText());
            int positionID = Integer.parseInt(id_pos_ex.getText());
            StringBuilder k = new StringBuilder();
            PreparedStatement pst = WelcomeController.c.prepareStatement("update biblioteka.wypozyczenie set wymagana_data_zwrotu = wymagana_data_zwrotu + 30 " +
                    "where czytelnik_id = ? and pozycja_id = ? and data_zwrocenia is null");
            pst.setInt(1,userID);
            pst.setInt(2,positionID);
            int l = pst.executeUpdate();
            SQLWarning warn = pst.getWarnings();
            if (warn != null) {
                k.append(warn.getMessage()).append("\n");
            }
            if (l > 0)
                k.append("Przez użytkownika ").append(userID).append(" przedłużono pozycję nr ").append(positionID).append(" o 30 dni.");
            if (l == 0)
                k.append("Coś poszło nie tak.");
            resultText.setText(k.toString());
        }
        catch(SQLException | NumberFormatException e){
            resultText.setText("Wystąpił błąd, sprawdź poprawność danych!");
            e.printStackTrace();
        }
    }
    @FXML
    protected void zadluzenie(){
        try {
            int userID = Integer.parseInt(id_zadl.getText());
            StringBuilder k = new StringBuilder();
            PreparedStatement pst = WelcomeController.c.prepareStatement("select * from biblioteka.czytelnik where czytelnik_id = ?");
            pst.setInt(1, userID);
            ResultSet rs = pst.executeQuery();
            k.append("Imię, nazwisko, zadłużenie\n");
            while(rs.next()){
                String a2 = rs.getString(2);
                String a3 = rs.getString(3);
                String a4 = rs.getString(4);
                if(a4 == null)
                    a4 = "brak zadłużenia";
                k.append(a2).append(", ").append(a3).append(", ").append(a4);
                resultText.setText(k.toString());
            }



        }
        catch (SQLException | NumberFormatException e){
            resultText.setText("Błąd we wprowadzonych danych!");
            e.printStackTrace();
        }
    }
    @FXML
    protected void czytelnicy(){
        try {
            StringBuilder k = new StringBuilder();
            PreparedStatement pst = WelcomeController.c.prepareStatement("select * from uzytkownik order by nazwisko");
            ResultSet rs = pst.executeQuery();
            k.append("ID użytkownika, Imię, nazwisko, ID pozycji, tytuł, data zwrotu \n");
            while(rs.next()){
                String a1 = rs.getString(1);
                String a2 = rs.getString(2);
                String a3 = rs.getString(3);
                String a4 = rs.getString(4);
                String a5 = rs.getString(5);
                String a6 = rs.getString(6);
                k.append(a1).append(", ").append(a2).append(", ").append(a3).append(", ").append(a4).append(", ").append(a5).append(", ").append(a6).append("\n");
            }
            resultText.setText(k.toString());

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}