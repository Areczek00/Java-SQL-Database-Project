module com.projekt_biblioteka {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.projekt_biblioteka to javafx.fxml;
    exports com.projekt_biblioteka;
}