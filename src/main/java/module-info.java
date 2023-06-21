module com.example.loginform {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.loginform to javafx.fxml;
    exports com.example.loginform;
}