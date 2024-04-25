module com.mycompany.loginprokon {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.mycompany.loginprokon to javafx.fxml;
    exports com.mycompany.loginprokon;
    exports com.mycompany.loginprokon.data;
}
