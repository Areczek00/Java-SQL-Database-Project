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

public class BorrowingController {
    @FXML
    private TextArea resultText;
    @FXML
    private TextField id_user;
    @FXML
    private TextField id_pos;
    @FXML
    private TextField data_poz;
    @FXML
    private TextField id_ksiazka_full;
    @FXML
    private TextField id_ksiazka_poz;
    @FXML
    private TextField id_ksiazka_poz_dos;
    @FXML
    private Button Borrowing;
    @FXML
    private Button CheckUserInfo;

    @FXML
    protected void fullInfoOKsiazce() {
        try {
            int id = Integer.parseInt(id_ksiazka_full.getText());
            PreparedStatement pst = WelcomeController.c.prepareStatement("select * from ksiazka_info_aut_kat where ksiazka_id = ? order by kategoria, autor");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            StringBuilder k = new StringBuilder();
            k.append("id, tytuł, autor/autorzy, kategorie\n");
            if (rs.next()) {
                String a1 = rs.getString(1);
                String a2 = rs.getString(3);
                String a3 = rs.getString(2);
                String a4 = rs.getString(4);
                k.append(a1).append(", ").append(a2).append(", ").append(a3).append(", ");
                String b3 = null;
                boolean flag = true;
                while (rs.next()) { //najpierw w kolejnosci wypiszą się wszyscy autorzy...potem kategorie
                    if (!rs.getString(2).equals(a3)) {
                        if (flag) {
                            b3 = rs.getString(2);
                            k.append(b3).append(", ");
                        }
                        a4 = rs.getString(4);
                    } else {
                        flag = false;
                        k.append(a4).append(", ");
                        a4 = rs.getString(4);
                    }
                }
                k.append(a4);
                resultText.setText(k.toString());
            } else resultText.setText("Brak takiego id.");
        } catch (SQLException | NumberFormatException e) {
            resultText.setText("Błąd przy wprowadzaniu danych.");
            e.printStackTrace();
        }
    }

    @FXML
    protected void pozycjeKsiazki() {
        try {
            int id = Integer.parseInt(id_ksiazka_poz.getText());
            PreparedStatement pst = WelcomeController.c.prepareStatement(
                    "select p.pozycja_id, k.nazwa, w2.wyd_nazwa, p.rok_wydania, p.isborrowed from ((biblioteka.ksiazka k join biblioteka.pozycja p using (ksiazka_id)) join biblioteka.wydawnictwo w2 using (wydawnictwo_id)) where ksiazka_id = ? order by pozycja_id ;\n");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            StringBuilder k = new StringBuilder();
            k.append("Pozycja_id, tytuł, nazwa wydawnictwa, rok wydania, czy pozyczona\n");
            boolean isEmpty = true;
            while (rs.next()) {
                isEmpty = false;
                String a1 = rs.getString(1);
                String a2 = rs.getString(2);
                String a3 = rs.getString(3);
                String a4 = rs.getString(4);
                boolean d5 = rs.getBoolean(5);
                String a5 = "Pozyczona";
                if (!d5)
                    a5 = "Dostępna";
                k.append(a1).append(", ").append(a2).append(", ").append(a3).append(", ").append(a4).append(", ").append(a5).append("\n");
            }
            if (isEmpty)
                resultText.setText("Brak takiego id.");
            else
                resultText.setText(k.toString());
        } catch (SQLException | NumberFormatException e) {
            resultText.setText("Błąd przy wprowadzaniu danych.");
            e.printStackTrace();
        }
    }

    @FXML
    protected void dostepnePozycjeKsiazki() {
        try {
            int id = Integer.parseInt(id_ksiazka_poz_dos.getText());
            PreparedStatement pst = WelcomeController.c.prepareStatement(
                    "SELECT p.pozycja_id, k.nazwa, w2.wyd_nazwa, p.rok_wydania FROM ((biblioteka.ksiazka k JOIN biblioteka.pozycja p USING (ksiazka_id)) JOIN biblioteka.wydawnictwo w2 USING (wydawnictwo_id)) WHERE ksiazka_id = ? and p.isborrowed = false ORDER BY pozycja_id ;\n");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            StringBuilder k = new StringBuilder();
            k.append("Pozycja_id, tytuł, nazwa wydawnictwa, rok wydania\n");
            boolean isEmpty = true;
            while (rs.next()) {
                isEmpty = false;
                String a1 = rs.getString(1);
                String a2 = rs.getString(2);
                String a3 = rs.getString(3);
                String a4 = rs.getString(4);
                k.append(a1).append(", ").append(a2).append(", ").append(a3).append(", ").append(a4).append("\n");
            }
            if (isEmpty)
                resultText.setText("Brak takiego id lub wszystkie pozycje zostały wypożyczone.");
            else
                resultText.setText(k.toString());
        } catch (SQLException | NumberFormatException e) {
            resultText.setText("Błąd przy wprowadzaniu danych.");
            e.printStackTrace();
        }
    }

    @FXML
    protected void pozyczanie() {
        try {
            int userID = Integer.parseInt(id_user.getText());
            int positionID = Integer.parseInt(id_pos.getText());
            String borrowDate = data_poz.getText();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            dateFormat.setLenient(false);
            try {
                dateFormat.parse(borrowDate);
            } catch (ParseException pe) {
                resultText.setText("Błąd we wprowadzaniu daty!");
                pe.printStackTrace();
                throw pe;
            }

            PreparedStatement pst = WelcomeController.c.prepareStatement("INSERT INTO biblioteka.wypozyczenie (czytelnik_id, pozycja_id, data_wypozyczenia, wymagana_data_zwrotu    ) " +
                    "VALUES (?,?, TO_DATE('" + borrowDate + "', 'DD-MM-YYYY'), TO_DATE('" + borrowDate + "', 'DD-MM-YYYY') + 30)");
            pst.setInt(1, userID);
            pst.setInt(2, positionID);
            StringBuilder k = new StringBuilder();
            String d = pst.toString();
            //resultText.setText(d); //Z RACJI NA UŻYCIE GROUP BY, NIC SIĘ NIE STANIE, JEŚLI NP DWA RAZY KLIKNIEMY TO SAMO WYPOZYCZENIE
            int l = pst.executeUpdate();
            SQLWarning warn = pst.getWarnings();
            if (warn != null)
                k.append(warn.getMessage()).append("\n");
            PreparedStatement kst = WelcomeController.c.prepareStatement("SELECT w.wymagana_data_zwrotu FROM biblioteka.wypozyczenie w where czytelnik_id = ? and pozycja_id = ? and data_wypozyczenia = TO_DATE('" + borrowDate + "', 'DD-MM-YYYY') and w.data_zwrocenia is null");
            kst.setInt(1, userID);
            kst.setInt(2, positionID);

            ResultSet rs = kst.executeQuery();

            if (rs.next()) {
                k.append("Pomyślnie wypożyczono pozycję " + positionID + " dla uzytkownika " + userID + ".\nData oddania: " + rs.getString(1));
            }
            else if (l == 0){
                k.append("Nie wypożyczono pomyślnie pozycji.");
            }
            resultText.setText(k.toString());
        } catch (SQLException | NumberFormatException e) {
            resultText.setText("Błąd przy wprowadzaniu danych!");
            e.printStackTrace();
        } catch (ParseException pe) {
        }
    }
    @FXML
    protected void userInfo(){
        try {
            PreparedStatement pst = WelcomeController.c.prepareStatement("select * from czytelnik_info order by nazwisko;");
            ResultSet rs = pst.executeQuery();
            StringBuilder k = new StringBuilder();
            k.append("Czytelnik_id, imię, nazwisko, liczba obecnie wypożycznonych pozycji\n");
            while (rs.next()){
                String a1 = rs.getString(1);
                String a2 = rs.getString(2);
                String a3 = rs.getString(3);
                String a4 = rs.getString(4);
                k.append(a1).append(", ").append(a2).append(", ").append(a3).append(", ").append(a4).append("\n");

            }
            rs.close();
            //resultText.setText(align.toString());
            resultText.setText(k.toString());
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}


