package com.projekt_biblioteka;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RankingController {
    @FXML
    private TextArea resultText;
    @FXML
    private Button CheckBook;
    @FXML
    private Button CheckAuthor;
    @FXML
    private Button CheckCathegory;

    @FXML
    protected void checkbooks(){
        try {
            StringBuilder k = new StringBuilder();
            PreparedStatement pst = WelcomeController.c.prepareStatement("with cte as(select count(*) as liczba_wypozyczen, k.nazwa as Tytul from ((biblioteka.wypozyczenie w join biblioteka.pozycja p using (pozycja_id)) join biblioteka.ksiazka k using (ksiazka_id))\n" +
                    "group by k.nazwa)\n" +
                    "select rank() over (order by liczba_wypozyczen) as Miejsce, Tytul from cte;");
            ResultSet rs = pst.executeQuery();
            k.append("Miejsce, tytuł\n");
            while (rs.next()){
                String a1 = rs.getString(1);
                String a2 = rs.getString(2);
                k.append(a1).append(", ").append(a2).append("\n");
            }
            resultText.setText(k.toString());
    }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void checkauthors(){
        try {
            StringBuilder k = new StringBuilder();
            PreparedStatement pst = WelcomeController.c.prepareStatement("with cte as(select count(*) as liczba_wypozyczen, a.nazwa as Autor from ((((biblioteka.wypozyczenie w join biblioteka.pozycja p using (pozycja_id)) join biblioteka.ksiazka k using (ksiazka_id)) join biblioteka.ksiazka_autor ka using (ksiazka_id)) join biblioteka.autor a using (autor_id))\n" +
                    "group by a.nazwa)\n" +
                    "select rank() over (order by liczba_wypozyczen) as Miejsce, Autor from cte;");
            ResultSet rs = pst.executeQuery();
            k.append("Miejsce, autor\n");
            while (rs.next()){
                String a1 = rs.getString(1);
                String a2 = rs.getString(2);
                k.append(a1).append(", ").append(a2).append("\n");
            }
            resultText.setText(k.toString());
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void checkcathegories(){
        try {
            StringBuilder k = new StringBuilder();
            PreparedStatement pst = WelcomeController.c.prepareStatement("with cte as(select count(*) as liczba_wypozyczen, k2.kat_nazwa as Kategoria from ((((biblioteka.wypozyczenie w join biblioteka.pozycja p using (pozycja_id)) join biblioteka.ksiazka k using (ksiazka_id)) join biblioteka.ksiazka_kategoria kk using (ksiazka_id)) join biblioteka.kategoria k2 using (kategoria_id))\n" +
                    "group by Kategoria)\n" +
                    "select rank() over (order by liczba_wypozyczen desc) as Miejsce, Kategoria from cte;");
            ResultSet rs = pst.executeQuery();
            k.append("Miejsce, kategoria\n");
            while (rs.next()){
                String a1 = rs.getString(1);
                String a2 = rs.getString(2);
                k.append(a1).append(", ").append(a2).append("\n");
            }
            resultText.setText(k.toString());
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
