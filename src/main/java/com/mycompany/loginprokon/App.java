package com.mycompany.loginprokon;

import com.mycompany.loginprokon.data.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        try {
            scene = new Scene(loadFXML("primary"), 905, 603);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("Sekolah Baitul Muttaqin");
            Image applicationIcon = new Image(getClass().getResourceAsStream(
                    "/com/mycompany/loginprokon/img/icon.png"));
            stage.getIcons().add(applicationIcon);

            stage.show();

            stage.setOnCloseRequest(event -> {
                Scene currentScene = stage.getScene();
                if (!currentScene.getRoot().getId().equals("loginPane")) {
                    event.consume();
                    try {
                        handleLogoutButtonAction(stage);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void handleLogoutButtonAction(Stage stage) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Logout");
        alert.setContentText("Apakah anda ingin Logout ?");

        if (alert.showAndWait().get() == ButtonType.OK) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Primary.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        }
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
        testConnection();

    }

    public static void testConnection() {
        Connection connection = DBConnection.getDBStatus();
        if (connection != null) {
            System.out.println("Status koneksi database: Connected");
        } else {
            System.out.println("Status koneksi database: Not connected");
        }
    }
}
