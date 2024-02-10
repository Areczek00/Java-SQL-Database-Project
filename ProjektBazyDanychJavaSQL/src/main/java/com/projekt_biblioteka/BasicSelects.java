package com.projekt_biblioteka;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BasicSelects {
    @FXML
    private TextArea resultText;
    @FXML
    private Button czytelnicyTable;
    @FXML
    private Button autorzyTable;
    @FXML
    private Button kategoriaTable;
    @FXML
    private Button ksiazkiTable;
    @FXML
    private Button wydawnictwaTable;
    @FXML
    private Button wypozyczeniaTable;
    @FXML
    private Button pozycjaTable;
    @FXML
    private MenuItem wypozyczPozycje;
    @FXML
    private MenuItem oddajPozycje;
    @FXML
    private MenuItem edycjaUzytkownikow;
    @FXML
    private MenuItem ranks;
    @FXML
    private Button resetButton;

    @FXML
    protected void onAutorzyClick(){
        try {

            PreparedStatement pst = WelcomeController.c.prepareStatement("SELECT * from biblioteka.autor");
            ResultSet rs = pst.executeQuery();
            StringBuilder k = new StringBuilder();
            k.append("Autor_id, imię i nazwisko, data urodzenia\n");
            while (rs.next()){
                String a1 = rs.getString(1);
                String a2 = rs.getString(2);
                String a3 = rs.getString(3);
                //fieldContent.append(a1).append("\t\t\t").append(a2).append("\t\t\t").append(a3).append("\n");
                k.append(a1).append(", ").append(a2).append(", ").append(a3).append("\n");
            }
            rs.close();
            resultText.setText(k.toString());
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    @FXML
    protected void onCzytelnicyClick(){
        try {

            PreparedStatement pst = WelcomeController.c.prepareStatement("SELECT * from biblioteka.czytelnik");
            ResultSet rs = pst.executeQuery();
            //Align align = new Align();
            StringBuilder k = new StringBuilder();
            k.append("Czytelnik_id, imie, nazwisko, zadłużenie\n");
            //align.addLine("Czytelnik_id","imie","nazwisko","zadłużenie");
            while (rs.next()){
                String a1 = rs.getString(1);
                String a2 = rs.getString(2);
                String a3 = rs.getString(3);
                String a4 = rs.getString(4);
                if(a4 == null)
                    a4 = "null";
                //align.addLine(a1,a2,a3,a4);
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
    @FXML
    protected void onKsiazkiClick(){
        try {
            PreparedStatement pst = WelcomeController.c.prepareStatement("SELECT * from biblioteka.ksiazka order by nazwa");
            ResultSet rs = pst.executeQuery();
            StringBuilder k = new StringBuilder();
            k.append("Książka_id, Tytuł\n");
            while (rs.next()){
                String a1 = rs.getString(1);
                String a2 = rs.getString(2);
                k.append(a1).append(", ").append(a2).append("\n");
            }
            rs.close();
            resultText.setText(k.toString());
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void onWydawnictwaClick(){
        try {

            PreparedStatement pst = WelcomeController.c.prepareStatement("SELECT * from biblioteka.wydawnictwo");
            ResultSet rs = pst.executeQuery();
            //Align align = new Align();
            StringBuilder k = new StringBuilder();
            k.append("Wydawnictwo_id, nazwa wydawnictwa\n");
            //align.addLine("Czytelnik_id","imie","nazwisko","zadłużenie");
            while (rs.next()){
                String a1 = rs.getString(1);
                String a2 = rs.getString(2);
                //align.addLine(a1,a2,a3,a4);
                k.append(a1).append(", ").append(a2).append("\n");
            }
            rs.close();
            //resultText.setText(align.toString());
            resultText.setText(k.toString());
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void onKategoriaClick(){
        try {

            PreparedStatement pst = WelcomeController.c.prepareStatement("SELECT * FROM biblioteka.kategoria");
            ResultSet rs = pst.executeQuery();
            //Align align = new Align();
            StringBuilder k = new StringBuilder();
            k.append("Kategoria_id, nazwa kategorii\n");
            //align.addLine("Czytelnik_id","imie","nazwisko","zadłużenie");
            while (rs.next()){
                String a1 = rs.getString(1);
                String a2 = rs.getString(2);
                //align.addLine(a1,a2,a3,a4);
                k.append(a1).append(", ").append(a2).append("\n");
            }
            rs.close();
            //resultText.setText(align.toString());
            resultText.setText(k.toString());
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void onWypozyczeniaClick(){
        try {

            PreparedStatement pst = WelcomeController.c.prepareStatement("SELECT * from biblioteka.wypozyczenie");
            ResultSet rs = pst.executeQuery();
            //Align align = new Align();
            StringBuilder k = new StringBuilder();
            k.append("Wypozyczenie_id, czytelnik_id, pozycja_id, data_wypozyczenia, wymagana_data_zwrotu, data_zwrocenia\n");
            //align.addLine("Czytelnik_id","imie","nazwisko","zadłużenie");
            while (rs.next()){
                String a1 = rs.getString(1);
                String a2 = rs.getString(2);
                String a3 = rs.getString(3);
                String a4 = rs.getString(4);
                String a5 = rs.getString(5);
                String a6 = rs.getString(6);
                if (a6 == null)
                    a6 = "null";
                //align.addLine(a1,a2,a3,a4);
                k.append(a1).append(", ").append(a2).append(", ").append(a3).append(", ").append(a4).append(", ").append(a5).append(", ").append(a6).append("\n");
            }
            rs.close();
            //resultText.setText(align.toString());
            resultText.setText(k.toString());
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void onPozycjaClick(){
        try {

            PreparedStatement pst = WelcomeController.c.prepareStatement("SELECT * from biblioteka.pozycja order by pozycja_id");
            ResultSet rs = pst.executeQuery();
            //Align align = new Align();
            StringBuilder k = new StringBuilder();
            k.append("pozycja_id, ksiazka_id, wydawnictwo_id, rok_wydania, czy pozyczona\n");
            //align.addLine("Czytelnik_id","imie","nazwisko","zadłużenie");
            while (rs.next()){
                String a1 = rs.getString(1);
                String a2 = rs.getString(2);
                String a3 = rs.getString(3);
                String a4 = rs.getString(4);
                boolean d5 = rs.getBoolean(5);
                String a5 = "Pozyczona";
                if (!d5)
                    a5 = "Dostępna";
                //align.addLine(a1,a2,a3,a4);
                k.append(a1).append(", ").append(a2).append(", ").append(a3).append(", ").append(a4).append(", ").append(a5).append("\n");
            }
            rs.close();
            //resultText.setText(align.toString());
            resultText.setText(k.toString());
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void wypozyczanieMenu(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("borrowing.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 640, 682);
            Stage stage = new Stage();
            stage.setTitle("Baza danych - biblioteka, pożyczanie");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    protected void oddawanieMenu(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("returning.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 640, 682);
            Stage stage = new Stage();
            stage.setTitle("Baza danych - biblioteka, oddawanie");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void uzytkownicyMenu(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("uzytkownicy.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 640, 682);
            Stage stage = new Stage();
            stage.setTitle("Baza danych - biblioteka, użytkownicy");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void reset(){
        try{
            Statement st = WelcomeController.c.createStatement();
            String g = "drop schema biblioteka cascade;\n" +
                    "create schema biblioteka;\n" +
                    "\n" +
                    "create table biblioteka.ksiazka(\n" +
                    "\tksiazka_id SERIAL primary key,\n" +
                    "\tnazwa VARCHAR(128) not null,\n" +
                    "\tliczba_egzemplarzy int\n" +
                    ");\n" +
                    "\n" +
                    "create table biblioteka.autor(\n" +
                    "\tautor_id SERIAL primary key,\n" +
                    "\tnazwa VARCHAR(64) not null,\n" +
                    "\tdata_urodzenia DATE not null\n" +
                    ");\n" +
                    "\n" +
                    "--encja asocjacyjna autorów (m:n)\n" +
                    "\n" +
                    "create table biblioteka.ksiazka_autor(\n" +
                    "\tksiazka_id int not null,\n" +
                    "\tautor_id int not null,\n" +
                    "\tCONSTRAINT ksiazka_autor_pk primary key(ksiazka_id, autor_id),\n" +
                    "\tCONSTRAINT ksiazka_autor_autor_id_fk foreign key (autor_id) references biblioteka.autor(autor_id),\n" +
                    "\tCONSTRAINT ksiazka_autor_ksiazka_id_fk foreign key (ksiazka_id) references biblioteka.ksiazka(ksiazka_id)\n" +
                    ");\n" +
                    "\n" +
                    "create table biblioteka.kategoria(\n" +
                    "\tkategoria_id SERIAL primary key,\n" +
                    "\tkat_nazwa varchar(32) not null\n" +
                    ");\n" +
                    "--encja asocyjacyjna kategorii (m:n)\n" +
                    "create table biblioteka.ksiazka_kategoria(\n" +
                    "\tksiazka_id int not null,\n" +
                    "\tkategoria_id int not null,\n" +
                    "\tconstraint ksiazka_kategoria_pk primary key (ksiazka_id, kategoria_id),\n" +
                    "\tconstraint ksiazka_kategoria_ksiazka_id_fk foreign key(ksiazka_id) references biblioteka.ksiazka(ksiazka_id),\n" +
                    "\tconstraint ksiazka_kategoria_kategoria_id_fk foreign key(kategoria_id) references biblioteka.kategoria(kategoria_id)\n" +
                    ");\n" +
                    "\n" +
                    "create table biblioteka.wydawnictwo(\n" +
                    "\twydawnictwo_id serial primary key,\n" +
                    "\twyd_nazwa varchar(32) not null\n" +
                    ");\n" +
                    "\n" +
                    "create table biblioteka.pozycja(\n" +
                    "\tpozycja_id SERIAL primary key,\n" +
                    "\tksiazka_id int not null,\n" +
                    "\twydawnictwo_id int not null,\n" +
                    "\trok_wydania int not null,\n" +
                    "\tisBorrowed boolean,\n" +
                    "\tconstraint pozycja_ksiazka_id_fk foreign key (ksiazka_id) references biblioteka.ksiazka(ksiazka_id),\n" +
                    "\tconstraint pozycja_wydawnictwtwo_id_fk foreign key (wydawnictwo_id) references biblioteka.wydawnictwo(wydawnictwo_id)\n" +
                    ");\n" +
                    "\n" +
                    "create table biblioteka.czytelnik(\n" +
                    "\tczytelnik_id serial primary key,\n" +
                    "\timie varchar(32) not null,\n" +
                    "\tnazwisko varchar(32) not null,\n" +
                    "\tzadluzenie int\n" +
                    ");\n" +
                    "\n" +
                    "\n" +
                    "--encja asocjacyjna -> wypozyczenie\n" +
                    "\n" +
                    "create table biblioteka.wypozyczenie(\n" +
                    "\twypozyczenie_id SERIAL primary key,\n" +
                    "\tczytelnik_id int,\n" +
                    "\tpozycja_id int not null,\n" +
                    "\tdata_wypozyczenia date not null,\n" +
                    "\twymagana_data_zwrotu date not null,\n" +
                    "\tdata_zwrocenia date,\n" +
                    "\tconstraint wypozyczenie_czytelnik_id_fk foreign key(czytelnik_id) references biblioteka.czytelnik(czytelnik_id) on delete set null,\n" +
                    "\tconstraint wypozyczenie_pozycja_id_fk foreign key(pozycja_id) references biblioteka.pozycja(pozycja_id) on delete set null\n" +
                    ");\n" +
                    "\n" +
                    "\n" +
                    "insert into biblioteka.czytelnik (imie, nazwisko) values (\n" +
                    "\t 'Arkadiusz', 'Korzeniak'\n" +
                    ");\n" +
                    "insert into biblioteka.czytelnik (imie, nazwisko) values (\n" +
                    "\t 'Arkadiusz', 'Dworzeniak'\n" +
                    ");\n" +
                    "insert into biblioteka.czytelnik (imie, nazwisko) values (\n" +
                    "\t 'Mariusz', 'Corzeniak'\n" +
                    ");\n" +
                    "\n" +
                    "insert into biblioteka.wydawnictwo ( wyd_nazwa) values (\n" +
                    "\t 'Grek'\n" +
                    ");\n" +
                    "\n" +
                    "insert into biblioteka.wydawnictwo ( wyd_nazwa) values (\n" +
                    "\t 'Roma'\n" +
                    ");\n" +
                    "\n" +
                    "\n" +
                    "insert into biblioteka.kategoria ( kat_nazwa) values(\n" +
                    "\t'kryminal'\n" +
                    ");\n" +
                    "insert into biblioteka.kategoria ( kat_nazwa) values(\n" +
                    "\t'fantasy'\n" +
                    ");\n" +
                    "insert into biblioteka.kategoria (kat_nazwa) values(\n" +
                    "\t'romans'\n" +
                    ");\n" +
                    "insert into biblioteka.kategoria ( kat_nazwa) values(\n" +
                    "\t'powiesc przygodowa'\n" +
                    ");\n" +
                    "insert into biblioteka.kategoria ( kat_nazwa) values(\n" +
                    "\t'powiesc historyczna'\n" +
                    ");\n" +
                    "\n" +
                    "insert into biblioteka.autor (nazwa, data_urodzenia) values(\n" +
                    "\t 'J.R.R. Tolkien', TO_DATE('03.01.1852','DD.MM.YYYY')\n" +
                    ");\n" +
                    "\n" +
                    "\n" +
                    "insert into biblioteka.ksiazka ( nazwa, liczba_egzemplarzy) values(\n" +
                    "\t'Druzyna Pierscienia', 2\n" +
                    ");\n" +
                    "insert into biblioteka.ksiazka_autor values (1, 1);\n" +
                    "insert into biblioteka.ksiazka_kategoria values (1,2);\n" +
                    "insert into biblioteka.ksiazka_kategoria values (1,4);\n" +
                    "\n" +
                    "insert into biblioteka.ksiazka (nazwa, liczba_egzemplarzy) values(\n" +
                    "\t 'Dwie Wieze', 2\n" +
                    ");\n" +
                    "insert into biblioteka.ksiazka_autor values (2, 1);\n" +
                    "insert into biblioteka.ksiazka_kategoria values (2,2);\n" +
                    "insert into biblioteka.ksiazka_kategoria values (2,4);\n" +
                    "\n" +
                    "insert into biblioteka.ksiazka ( nazwa, liczba_egzemplarzy) values(\n" +
                    "\t 'Powrot Krola', 2\n" +
                    ");\n" +
                    "insert into biblioteka.ksiazka_autor values (3, 1);\n" +
                    "insert into biblioteka.ksiazka_kategoria values (3,2);\n" +
                    "insert into biblioteka.ksiazka_kategoria values (3,4);\n" +
                    "\n" +
                    "insert into biblioteka.ksiazka( nazwa, liczba_egzemplarzy) values(\n" +
                    "\t'Romeo i Julia', 5\n" +
                    ");\n" +
                    "insert into biblioteka.autor ( nazwa, data_urodzenia) values(\n" +
                    "\t 'William Shakespeare', TO_DATE('01.04.1564', 'DD.MM.YYYY')\n" +
                    ");\n" +
                    "insert into biblioteka.ksiazka_autor values (4,2);\n" +
                    "insert into biblioteka.ksiazka_kategoria values (4,3);\n" +
                    "\n" +
                    "insert into biblioteka.ksiazka (nazwa, liczba_egzemplarzy)values (\n" +
                    "\t'Potop', 1\n" +
                    ");\n" +
                    "insert into biblioteka.autor (nazwa, data_urodzenia )values('Henryk Sienkiewicz', TO_DATE('05.05.1846', 'DD.MM.YYYY'));\n" +
                    "insert into biblioteka.ksiazka_autor values (5,3);\n" +
                    "insert into biblioteka.ksiazka_autor values (5,1);\n" +
                    "insert into biblioteka.ksiazka_kategoria values(5,3);\n" +
                    "insert into biblioteka.ksiazka_kategoria values(5,4);\n" +
                    "insert into biblioteka.ksiazka_kategoria values(5,5);\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "insert into biblioteka.pozycja ( ksiazka_id, wydawnictwo_id, rok_wydania, isborrowed) values (\n" +
                    "\t1, 1, 2002, false\n" +
                    ");\n" +
                    "insert into biblioteka.pozycja (ksiazka_id, wydawnictwo_id, rok_wydania, isborrowed) values (\n" +
                    "\t1, 1, 2003, false\n" +
                    ");\n" +
                    "insert into biblioteka.pozycja (ksiazka_id, wydawnictwo_id, rok_wydania, isborrowed) values (\n" +
                    "\t1, 2, 2002, false\n" +
                    ");\n" +
                    "insert into biblioteka.pozycja (ksiazka_id, wydawnictwo_id, rok_wydania, isborrowed) values (\n" +
                    "\t1, 1, 2012, false\n" +
                    ");\n" +
                    "\n" +
                    "insert into biblioteka.pozycja (ksiazka_id, wydawnictwo_id, rok_wydania, isborrowed) values (\n" +
                    "\t2, 1, 2002, false\n" +
                    ");\n" +
                    "insert into biblioteka.pozycja (ksiazka_id, wydawnictwo_id, rok_wydania, isborrowed) values (\n" +
                    "\t2, 1, 2002, false\n" +
                    ");\n" +
                    "insert into biblioteka.pozycja (ksiazka_id, wydawnictwo_id, rok_wydania, isborrowed) values (\n" +
                    "\t2, 2, 2002, false\n" +
                    ");\n" +
                    "insert into biblioteka.pozycja (ksiazka_id, wydawnictwo_id, rok_wydania, isborrowed) values (\n" +
                    "\t2, 2, 2002, false\n" +
                    ");\n" +
                    "insert into biblioteka.pozycja (ksiazka_id, wydawnictwo_id, rok_wydania, isborrowed) values (\n" +
                    "\t3, 1, 2005, false\n" +
                    ");\n" +
                    "\n" +
                    "insert into biblioteka.pozycja (ksiazka_id, wydawnictwo_id, rok_wydania, isborrowed) values (\n" +
                    "\t3, 1, 2004, false\n" +
                    ");\n" +
                    "\n" +
                    "insert into biblioteka.pozycja (ksiazka_id, wydawnictwo_id, rok_wydania, isborrowed) values (\n" +
                    "\t4, 2, 2013, false\n" +
                    ");\n" +
                    "insert into biblioteka.pozycja (ksiazka_id, wydawnictwo_id, rok_wydania, isborrowed) values (\n" +
                    "\t 4, 2, 2013, false\n" +
                    ");\n" +
                    "insert into biblioteka.pozycja ( ksiazka_id, wydawnictwo_id, rok_wydania, isborrowed) values (\n" +
                    " 4, 2, 2004, false\n" +
                    ");\n" +
                    "\n" +
                    "insert into biblioteka.pozycja ( ksiazka_id, wydawnictwo_id, rok_wydania, isborrowed) values (\n" +
                    "\t 5, 2, 1994, false\n" +
                    ");\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "create or replace function liczba_ksiazek() returns void as \n" +
                    "$$\n" +
                    "\tdeclare \n" +
                    "\t\tcounter int := 0;\n" +
                    "\t\tvar_r int;\n" +
                    "\t\tvar_p int;\n" +
                    "\tbegin\n" +
                    "\t\tfor var_r in (select s.ksiazka_id from biblioteka.ksiazka s group by s.ksiazka_id) \n" +
                    "\t\tloop\n" +
                    "\t\t\tfor var_p in (select p.pozycja_id from biblioteka.pozycja p where p.isborrowed = false and var_r = p.ksiazka_id group by p.pozycja_id)\n" +
                    "\t\t\tloop \n" +
                    "\t\t\t\tcounter = counter + 1;\n" +
                    "\t\t\tend loop;\n" +
                    "\t\tupdate biblioteka.ksiazka s set liczba_egzemplarzy = counter where s.ksiazka_id = var_r;\n" +
                    "\t\tcounter = 0;\n" +
                    "\t\tend loop;\n" +
                    "\tend;\n" +
                    "$$ language plpgsql;\n" +
                    "\n" +
                    "create or replace function wypozyczenie(integer) returns text as \n" +
                    "$$\n" +
                    "\tdeclare \n" +
                    "\t\tid_p alias for $1;\n" +
                    "\t\tid_k int;\n" +
                    "\t\tborrowed_check boolean := (select p.isborrowed from biblioteka.pozycja p where p.pozycja_id = id_p);\n" +
                    "\t\tzwrot text;\n" +
                    "\tbegin\n" +
                    "\t\tzwrot := 'Niepowodzenie.';\n" +
                    "\t\tif borrowed_check = false then\n" +
                    "\t\tid_k = (select p.ksiazka_id from biblioteka.pozycja p where p.pozycja_id = id_p group by p.ksiazka_id);\n" +
                    "\t\tupdate biblioteka.pozycja p set isborrowed = true where p.pozycja_id = id_p;\n" +
                    "\t\tupdate biblioteka.ksiazka s set liczba_egzemplarzy = s.liczba_egzemplarzy - 1 where s.ksiazka_id = id_k;\n" +
                    "\t\tzwrot := 'Operacja wypozyczenia udana.';\n" +
                    "\t\tend if;\n" +
                    "\t\treturn zwrot;\n" +
                    "\tend;\n" +
                    "$$ language plpgsql;\n" +
                    "\n" +
                    "\n" +
                    "create or replace function oddanie(integer) returns text as \n" +
                    "$$\n" +
                    "\tdeclare \n" +
                    "\t\tid_p alias for $1;\n" +
                    "\t\tid_k int;\n" +
                    "\t\tborrowed_check boolean := (select p.isborrowed from biblioteka.pozycja p where p.pozycja_id = id_p);\n" +
                    "\t\tzwrot text;\n" +
                    "\tbegin\n" +
                    "\t\tzwrot = 'Niepowodzenie.';\n" +
                    "\t\tif borrowed_check = true then\n" +
                    "\t\t\tid_k = (select p.ksiazka_id from biblioteka.pozycja p where p.pozycja_id = id_p group by p.ksiazka_id);\n" +
                    "\t\t\tupdate biblioteka.pozycja p set isborrowed = false where p.pozycja_id = id_p;\n" +
                    "\t\t\tupdate biblioteka.ksiazka s set liczba_egzemplarzy = s.liczba_egzemplarzy + 1 where s.ksiazka_id = id_k;\n" +
                    "\t\t\tzwrot = 'Operacja oddania udana.';\t\n" +
                    "\t\tend if;\n" +
                    "\treturn zwrot;\n" +
                    "\tend;\n" +
                    "$$ language plpgsql;\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "create or replace function wypozyczenie_check() returns trigger as \n" +
                    "$$\n" +
                    "\tdeclare \n" +
                    "\t\ttekst text;\n" +
                    "\tbegin \n" +
                    "\t\tif (select c.zadluzenie from biblioteka.czytelnik c where czytelnik_id = NEW.czytelnik_id) is null OR (select c.zadluzenie from biblioteka.czytelnik c where czytelnik_id = NEW.czytelnik_id) < 30 then \n" +
                    "\t\t\tif (select count(*) as liczba_pozycji from biblioteka.wypozyczenie w group by w.czytelnik_id, w.data_zwrocenia\n" +
                    "\t\t\t\thaving w.czytelnik_id = new.czytelnik_id and w.data_zwrocenia is NULL) is null or (select count(*) as liczba_pozycji from biblioteka.wypozyczenie w group by w.czytelnik_id, w.data_zwrocenia\n" +
                    "\t\t\t\thaving w.czytelnik_id = new.czytelnik_id and w.data_zwrocenia is NULL) < 3 then\n" +
                    "\t\t\t\t\ttekst = (select wypozyczenie(new.pozycja_id));\n" +
                    "\t\t\t\t\tif tekst = 'Niepowodzenie.' then\n" +
                    "\t\t\t\t\t\traise warning 'Nie mozna wypozyczyc juz wypozyczonej pozycji!';\n" +
                    "\t\t\t\t\t\treturn null;\n" +
                    "\t\t\t\t\tend if;\n" +
                    "\t\t\t\t\traise warning 'Wypozyczono pozycje przez czytelnika %.', new.czytelnik_id;\n" +
                    "\t\t\t\t\tperform liczba_ksiazek();\n" +
                    "\t\t\t\t\treturn new;\n" +
                    "\t\t\tend if;\n" +
                    "\t\t\traise warning 'Osiagnieto maksymalna liczbe pozycji!';\n" +
                    "\t\t\tperform liczba_ksiazek();\n" +
                    "\t\t\treturn null;\n" +
                    "\t\tend if;\n" +
                    "\t\traise warning 'Zadluzenie przekracza dozwolona wartosc!';\n" +
                    "\t\treturn null;\n" +
                    "\tend;\n" +
                    "$$ language plpgsql;\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "create trigger new_wypozyczenie before insert on biblioteka.wypozyczenie \n" +
                    "for each row execute procedure wypozyczenie_check();\n" +
                    "\n" +
                    "\n" +
                    "create or replace function oddanie_check() returns trigger as \n" +
                    "$$\n" +
                    "declare \n" +
                    "\ttekst text;\n" +
                    "\tzadluzenie_val int;\n" +
                    "begin\n" +
                    "\tif old.data_zwrocenia is null and new.data_zwrocenia is not null then \n" +
                    "\t\ttekst := (select oddanie(new.pozycja_id));\n" +
                    "\t\tif tekst = 'Niepowodzenie.' then\n" +
                    "\t\t\traise warning 'Nie mozna oddac niewypozyczonej pozycji!';\n" +
                    "\t\t\treturn NULL;\n" +
                    "\t\tend if;\n" +
                    "\t\t-- miejsce na wyliczenie zadluzenia\n" +
                    "\t\tif new.data_zwrocenia - new.wymagana_data_zwrotu > 1 then\n" +
                    "\t\t\tzadluzenie_val = ( new.data_zwrocenia - new.wymagana_data_zwrotu )/2 * 1;\n" +
                    "\t\t\tupdate biblioteka.czytelnik c set zadluzenie = zadluzenie_val where c.czytelnik_id = new.czytelnik_id;\n" +
                    "\t\tend if;\n" +
                    "\t\traise warning 'Pomyslnie oddano.';\n" +
                    "\t\tperform liczba_ksiazek();\n" +
                    "\t\treturn new;\n" +
                    "\tend if;\n" +
                    "\n" +
                    "\tif new.wymagana_data_zwrotu - new.data_wypozyczenia > 120 then \n" +
                    "\t\traise warning 'Nie mozna przedluzyc pozycji.';\n" +
                    "\t\treturn NULL;\n" +
                    "\tend if;\n" +
                    "\tif new.wymagana_data_zwrotu <> old.wymagana_data_zwrotu then\n" +
                    "\t\traise warning 'Przedluzono pozycje.';\n" +
                    "\tend if;\n" +
                    "\treturn new;\n" +
                    "end;\n" +
                    "$$ language plpgsql;\n" +
                    "\n" +
                    "\n" +
                    "create trigger oddanie_lub_przedluzenie before update on biblioteka.wypozyczenie \n" +
                    "for each row execute procedure oddanie_check();\n" +
                    "\n" +
                    "\n" +
                    "--test funkcjonalnosci\n" +
                    "insert into biblioteka.wypozyczenie (czytelnik_id, pozycja_id, data_wypozyczenia, wymagana_data_zwrotu)\n" +
                    "values (\n" +
                    "\t1, 7, TO_DATE('20.11.2002', 'DD.MM.YYYY'), TO_DATE('20.12.2002', 'DD.MM.YYYY')\n" +
                    ");\n" +
                    "\n" +
                    "update biblioteka.wypozyczenie set data_zwrocenia = TO_DATE('20.12.2002', 'DD.MM.YYYY') \n" +
                    "where czytelnik_id = 1 and data_zwrocenia is null;\n" +
                    "\n" +
                    "insert into biblioteka.wypozyczenie (czytelnik_id, pozycja_id, data_wypozyczenia, wymagana_data_zwrotu)\n" +
                    "values (\n" +
                    "\t1, 2, TO_DATE('20.11.2002', 'DD.MM.YYYY'), TO_DATE('20.12.2002', 'DD.MM.YYYY')\n" +
                    ");\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "create view uzytkownik as\n" +
                    "select c.czytelnik_id, c.imie, c.nazwisko, p.pozycja_id, k.nazwa, w.wymagana_data_zwrotu from ((((biblioteka.czytelnik c join biblioteka.wypozyczenie w using (czytelnik_id)) join biblioteka.pozycja p using (pozycja_id))) join biblioteka.ksiazka k using (ksiazka_id))\n" +
                    "where w.data_zwrocenia is null;\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "create view ksiazka_info as\n" +
                    "select k.ksiazka_id, a.nazwa as Autor, k.nazwa as Tytul, k.liczba_egzemplarzy from ((biblioteka.ksiazka k join biblioteka.ksiazka_autor ka using (ksiazka_id)) join biblioteka.autor a using (autor_id))\n" +
                    "group by ksiazka_id, Autor, Tytul, liczba_egzemplarzy;\n" +
                    "\n" +
                    "create view ksiazka_kat_info as\n" +
                    "select k.nazwa as tytul, k2.kat_nazwa as Kategoria from ((biblioteka.ksiazka k join biblioteka.ksiazka_kategoria kk using (ksiazka_id)) join biblioteka.kategoria k2 using (kategoria_id))\n" +
                    "group by tytul, kategoria;\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "create view ksiazka_info_aut_kat as\n" +
                    "select k.ksiazka_id, k.autor, k.tytul, ki.kategoria from ksiazka_info k join ksiazka_kat_info ki using (tytul)\n" +
                    "group by ksiazka_id, k.autor, k.tytul, ki.kategoria;\n" +
                    "\n" +
                    "create view czytelnik_info as\n" +
                    "with cte as (select w.czytelnik_id, w.data_zwrocenia, sum(case when w.data_zwrocenia is null then 1 else 0 end) as liczba_pozycji from biblioteka.wypozyczenie w group by w.czytelnik_id, w.data_zwrocenia \n" +
                    "\t\t\t\t)\n" +
                    "select c.czytelnik_id, c.imie, c.nazwisko, sum(cte.liczba_pozycji) from biblioteka.czytelnik c join cte using(czytelnik_id)\n" +
                    "group by c.czytelnik_id, c.imie, c.nazwisko ;";
            st.execute(g);
            resultText.setText("RESET się wykonał.");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void rankMenu(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ranking.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 640, 682);
            Stage stage = new Stage();
            stage.setTitle("Baza danych - rankingi");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    }

