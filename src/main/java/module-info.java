module com.mycompany.loginprokon {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.loginprokon to javafx.fxml;
    exports com.mycompany.loginprokon;
}
